import React, { Component } from 'react';
import {
    View,
    Text,
    StyleSheet,
    Image, NativeModules,
    ImageBackground,
    TouchableOpacity,
    FlatList, TextInput, Button
} from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from '../utils/screenPhone';
import { showSha1 } from '../utils/Sha1';
import { onLoading, onToast, onMsg, onloadout } from '../utils/MoudleUtils';

export default class uploadPwd extends Component {
    constructor(props) {
        super(props);
        this.state = {
            URL_Constract: "https://app.lianjieshenghuo.com/",
            Token: this.props.navigation.getParam('token', ""),
            Userid: this.props.navigation.getParam('userid', ""),
            Commentdata: "",
            pass: "",
            repass: "",
            resource: 1
        };
    }
    // pass: pwd,
    // repass: repwd,
    // timestamp: timestamp,
    // token: token,
    // userid: userid,
    // sign: sign

    requestgetHistoryComment() {
        onLoading(0)//网络请求的转圈圈东西
        var url = "resset"
        var timestamp = Date.parse(new Date()) / 1000;
        // var sign = showSha1('pass=' + this.state.pass + '&repass=' + this.state.repass + '&resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token +
        //     + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        var sign = showSha1('pass=' + this.state.pass + '&repass=' + this.state.repass + '&resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        var formData = new FormData();
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
        formData.append('pass', this.state.pass);
        formData.append('repass', this.state.repass);
        formData.append('timestamp', timestamp);




        fetch(this.state.URL_Constract + url, {
            method: 'POST',
            header: {
                'Accept': 'application/json',
                'content-type': 'application/x-www-form-urlencoded'
            },
            body: formData
        })
            .then((response) => response.json()).
            then((responseJson) => {
                if (responseJson.code == 1000) {

                    //成功 就 跳转到 登陆页
                    onloadout()
                } else if (responseJson.code == 10011) {
                    onloadout()
                    onToast(responseJson.msg)
                } else {
                    onToast(responseJson.msg)
                }


            }).catch((error) => {
                onToast("系统错误")
                onloadout()
            }).finally(
                onLoading(1)
            )
    }
    //点击事件
    activeEvent(msg) {
        switch (msg) {
            case "id_back"://左上角首页退出
                this.props.navigation.goBack()
                break;
            case "id_submit"://修改
                this.requestgetHistoryComment()
                break;

        }

    }

    render() {

        //标题
        var title = (
            <View style={{
                flexDirection: 'row',
                alignItems: 'center',
                height: scaleSizeH(80),
                alignSelf: 'stretch',
                backgroundColor: "#ffffff"
            }}>
                <View style={{ flexDirection: "row", justifyContent: "space-between", flex: 0.6, alignItems: "center" }}>
                    <TouchableOpacity
                        activeOpacity={1.5}
                        onPress={() => this.activeEvent("id_back")}
                    >
                        <Image style={{
                            width: scaleSizeW(35),
                            height: scaleSizeH(35),
                            marginLeft: scaleSizeW(15),
                        }} source={require("./../img/icon-navBack.png")} />

                    </TouchableOpacity>
                    <Text style={Styles.textstyle}>个人中心</Text>
                </View>
            </View>
        )
        return (
            <View style={{ flex: 1 }}>
                {title}
                <TextInput placeholder="请输入新密码"
                    secureTextEntry={true}
                    onChangeText={(pass) => this.setState({ pass: pass })}
                    maxLength={15}
                    style={{ borderRadius: 50, marginLeft: scaleSizeW(30), marginRight: scaleSizeW(30), backgroundColor: "#f4f4f4", marginTop: scaleSizeH(35), padding: scaleSizeW(10) }}
                />
                <TextInput placeholder="请再次输入新密码"
                    secureTextEntry={true}
                    onChangeText={(repass) => this.setState({ repass: repass })}

                    maxLength={15}
                    style={{ borderRadius: 50, marginLeft: scaleSizeW(30), marginRight: scaleSizeW(30), backgroundColor: "#f4f4f4", marginTop: scaleSizeH(25), padding: scaleSizeW(10) }}
                />

                <TouchableOpacity
                    activeOpacity={0.5}
                    onPress={() => this.activeEvent("id_submit")}
                    style={{ position: "absolute", bottom: scaleSizeH(40), width: "100%", }}
                >

                    <View style={{ borderRadius: 30, marginLeft: scaleSizeW(30), marginRight: scaleSizeW(30), backgroundColor: "#4C90F3", alignItems: "center" }}>
                        <Text style={{
                            paddingTop: scaleSizeH(20), paddingBottom: scaleSizeH(20)
                        }} >确认修改</Text>

                    </View>
                </TouchableOpacity>

            </View>
        );
    }
}
var Styles = StyleSheet.create({

    //标题栏
    container: {
        flexDirection: 'row',
        alignItems: 'center',
        height: scaleSizeH(80),
        alignSelf: 'stretch',
    },
    //标题栏
    textview: {
        flex: 1,
    },
    //标题栏
    textstyle: {
        fontSize: 17,
        color: "#000000",
        alignSelf: "center",
    },
    //评级 的 tx  style
    pingjitxstyle: {
        fontSize: setSpText(22),
        color: "#000000",
        marginTop: scaleSizeH(15),
        marginBottom: scaleSizeH(15),
    },


}



)

