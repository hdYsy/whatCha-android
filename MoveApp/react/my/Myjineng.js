import React, { Component } from 'react';
import {
    View,
    Text,
    StyleSheet,
    Image, NativeModules,
    ImageBackground,
    TouchableOpacity,
    FlatList
} from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from '../utils/screenPhone';
import { showSha1 } from '../utils/Sha1';
import { onLoading, onToast, onMsg, onImgEnlarge, onCemare, onloadout, onFinish } from '../utils/MoudleUtils';

export default class Myjineng extends Component {
    constructor(props) {
        super(props);
        this.state = {
            AllSkillLabel: "",
            URL_Constract: "https://app.lianjieshenghuo.com/",
            Userid: "",
            Token: "",


        };
    }



    //oncreate
    componentDidMount() {
        this.getData()
    }
    //接受Android 的数据    
    getData = () => {
        NativeModules.IntentModule.toRNdata((user, token, username,str) => {
            this.setState({
                Userid: user,
                Token: token,
            }, () => {
                let getAllSkillLabel = "getAllSkillLabel"
                this.request_getAllSkillLabel(getAllSkillLabel)
                onMsg()
            })
        })
    }
    //error 事件
    onerror = () => {
        if (this.state.error === "") {
            this.setState({
                error: true
            })
        } else {
            if (this.state.error) {
                this.setState({
                    error: true
                })
            } else {
                this.setState({
                    error: false
                })
            }
        }
    }


    //技能标签

    //技能标签
    request_getAllSkillLabel(url) {
        var timestamp = Date.parse(new Date()) / 1000;
        let sign = showSha1('timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);

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
                    this.setState({
                        AllSkillLabel: responseJson,
                    })
                    this.onerror()  //判断接口是否通顺

                } else if (responseJson.code == 10011) {
                    onloadout()
                    onToast(responseJson.msg)
                } else {
                    onToast(responseJson.msg)
                }


            }).catch((error) => {
                onToast("系统错误")
                onloadout()
                this.setState({
                    error: false
                })
            }).finally(
                onLoading(1)
            )
    }












    //点击事件
    activeEvent(msg) {
        let userdate = this.state.userdata
        switch (msg) {
            case "id_back"://退出
                this.props.navigation.goBack()
                break;
            case "id_username": //点击头像
                if (userdate !== "") {
                    onImgEnlarge(userdate.data.thumb, 0)
                }
                break;

            case "id_loadout"://退出登陆
                onloadout()
                break;
            case "id_myuserimg"://上传头像
                onCemare()
                break;
            case "id_myjineng"://我的技能
                // () => this.props.navigation.navigate('Myjineng')
                this.props.navigation.navigate('Myjineng')
                break
            case "id_appeal"://我的
                break;
            case "id_materials"://申请物料
                break;
            case "id_bed"://申请床品
                break;
            case "id_pwd"://修改密码
                break;
            case "id_manual"://操作手册
                break;

        }

        //评级 是否 是0/1

    }










    render() {
        return (
            <View style={{ flex: 1 }}>
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


                        <Text style={Styles.textstyle}>我的技能</Text>
                    </View>
                </View>




                <View style={{ backgroundColor: "#f4f4f4", flex: 1 }}>

                    {this.state.AllSkillLabel === "" ? <Text></Text> :

                        <FlatList
                            style={{ flex: 1, backgroundColor: "#f4f4f4" }}
                            data={this.state.AllSkillLabel.data.data}
                            renderItem={({ item }) => (

                                <View >
                                    <View style={Styles.style_loadimg}>
                                        <View style={{ flexDirection: "row" }}>
                                            <Image style={Styles.item_icon} source={{ uri: item.isHave == 1 ? item.normalImage : item.grayImage }} />
                                            <View style={{ marginLeft: scaleSizeW(15), justifyContent: "center" }}>
                                                <Text style={{ fontSize: setSpText(34), color: "#000000" }}>{item.title}</Text>
                                                <Text style={{ fontSize: setSpText(27), marginTop: scaleSizeH(100) }} > {item.haveDate}</Text>

                                            </View>
                                        </View>

                                    </View>

                                </View>
                            )
                            }
                            key={index => index}
                        />}

                </View>
            </View >











        );
    }
}

var Styles = StyleSheet.create({
    style_Homeview: {
        justifyContent: "space-between",
        alignItems: "center"
        , flex: 1

    },


    //Item 属性
    style_loadimg: {
        flexDirection: "row",
        justifyContent: "space-between",
        marginLeft: scaleSizeW(20),
        marginRight: scaleSizeW(20),
        marginTop: scaleSizeH(20),
        paddingBottom: 10,
        paddingTop: 10,
        backgroundColor: "#ffffff",
    },

    //图片
    item_icon: {
        width: 80,
        marginLeft: 15,
        marginRight: 15
    },
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
    }



}

)



