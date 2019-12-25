import React, { Component } from 'react';
import {
    View,
    Text,
    StyleSheet, NativeModules,
    Image,
    ImageBackground,
    TouchableOpacity,
    FlatList,
    Button,

} from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from '../utils/screenPhone';
import { showSha1 } from '../utils/Sha1';
import { onLoading, onToast, onMsg, onImgEnlarge, onCemare, onloadout, onFinish, onStartDelatiles } from '../utils/MoudleUtils';
import { ScrollView, TextInput, State } from 'react-native-gesture-handler';
import TitleBar from './TitleBar';
import { DeviceEventEmitter } from 'react-native';//监听

export default class DayAppeal extends Component {

    constructor(props) {
        super(props); this.setState({
        })
        this.state = {
            item: this.props.navigation.getParam('item', ""),
            Token: this.props.navigation.getParam('Token', ""),
            Userid: this.props.navigation.getParam('Userid'),
            appealEd: "",//申诉原因的输入框
            img: "",
            imagearr: this.props.navigation.getParam('appeal_imglist', "") == "" ? [] : this.props.navigation.getParam('appeal_imglist', ""),
            tx_value: this.props.navigation.getParam('item', "") != "" ? this.props.navigation.getParam('item', "").appeal_reason != undefined ? this.props.navigation.getParam('item', "").appeal_reason : "" : "",
            resource: 1,
            URL_Constract: "https://app.lianjieshenghuo.com/",

        };

    }


    componentWillUnmount() {
        DeviceEventEmitter.emit('通知', "");
    }


    //点击事件

    // 点击事件
    activeEvent(msg) {
        switch (msg) {

            case "id_image_pai"://拍照/相册

                NativeModules.IntentModule.AdbImageCamere(this.state.item.id, (Type) => {
                    // let arr = new Array()
                    // let arr = new State()
                    this.setState({
                        imagearr: this.state.imagearr.concat(Type)
                    })

                })
                break;

            case "id_bt_submit"://提交
                NativeModules.IntentModule.AdbCenterDialog('确定要提交申诉么?', (Type) => {
                    if (Type == 1) {
                        this.cancelAppealReason()
                    }
                })
                break;
        }


    }



    //撤销申诉
    cancelAppealReason() {
        const a = JSON.stringify(this.state.imagearr)

        onLoading(0)//网络请求的转圈圈东西
        var url = "addAppealReason"
        var timestamp = Date.parse(new Date()) / 1000;

        const sign = showSha1('appealImglist=' + a
            + '&appealReason=' + this.state.tx_value +
            '&complainId=' + this.state.item.id +
            '&resource=' + this.state.resource +
            '&timestamp=' + timestamp +
            '&token=' + this.state.Token +
            '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        var formData = new FormData();
        formData.append('appealImglist', a);
        formData.append('appealReason', this.state.tx_value);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
        formData.append('timestamp', timestamp);
        formData.append('complainId', this.state.item.id);
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
                    //退出 并且刷新
                    this.props.navigation.goBack()
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





    fotter = () => {
        return (
            <TouchableOpacity
                activeOpacity={1.5}
                onPress={() => this.activeEvent("id_image_pai")}
            >
                <Image style={{ width: scaleSizeW(120), height: scaleSizeW(120) }} source={require('../img/addPic.png')} />
            </TouchableOpacity>
        )
    };





    render() {

        var item = this.state.item != "" ? this.state.item : ""
        var arr = this.state.imagearr
        return (
            <View style={{ flex: 1, }}>
                <TitleBar
                    onClick={this}
                    name={'申诉'}
                    flexstate={0.55}
                />
                <ScrollView style={{ flex: 1, backgroundColor: "#ffffff" }}>
                    <View>

                        <View style={{ backgroundColor: "#ffffff" }}>
                            <View style={Styles.style_item}>
                                <View style={{
                                    flexDirection: "row", justifyContent: "space-between", flex: 1
                                }}>
                                    <Text style={{ color: "#4C90F3" }} onPress={() => onStartDelatiles(item.id + "", item.oid + "")}  >工单号: {item.gid}</Text>
                                    <Text style={{}}>公寓号: {item.yid}</Text>
                                    {/* <Text style={{ marginTop: scaleSizeH(30), color: "#4C90F3" }} >
                                                    {item.pingjia}
                                                </Text> */}
                                </View>


                                <Text style={{ marginTop: scaleSizeH(15) }}>保洁服务开始时间: {item.servicestarttime}</Text>
                                <Text style={{ marginTop: scaleSizeH(15) }} >投诉内容: {item.content + "\n"}</Text>


                            </View>

                            <Text style={{ backgroundColor: "#dddddd", height: scaleSizeH(1), marginTop: scaleSizeH(15) }} />

                        </View>

                        <Text style={{ backgroundColor: "#f4f4f4", width: "100%", height: scaleSizeH(15) }}></Text>
                        <View style={{
                            width: "100%",
                            backgroundColor: "#ffffff"
                        }}>
                            <Text style={{
                                color: "#000000", paddingLeft: 10,
                                paddingRight: 10,
                                paddingBottom: 5,
                                paddingTop: 5,
                            }}>
                                申诉原因
                        </Text>
                            <Text style={{ width: "100%", height: 1, backgroundColor: "#dddddd" }} />

                            <TextInput
                                multiline={true}     //多行输入 如果为true，文本框中可以输入多行文字。默认值为false。
                                defaultValue={this.state.item.appeal_reason == null ? "" : this.state.item.appeal_reason}
                                placeholder={"请在此输入申诉原因"} onChangeText={(tx) => {
                                    this.setState({
                                        tx_value: tx
                                    })
                                }}></TextInput>
                        </View>

                        <Text style={{ backgroundColor: "#f4f4f4", width: "100%", height: scaleSizeH(15) }}></Text>
                        <View style={{
                            width: "100%",
                            backgroundColor: "#ffffff"
                        }}>
                            <Text style={{
                                color: "#000000", paddingLeft: 10,
                                paddingRight: 10,
                                paddingBottom: 5,
                                paddingTop: 5,
                            }}>
                                上传图片
                        </Text>
                            <Text style={{ width: "100%", height: 1, backgroundColor: "#dddddd" }} />


                            <View style={{ color: "#ffffff" }}>

                                <View style={{ flex: 1, flexWrap: "wrap", flexDirection: "row" }}>
                                    {this.state.imagearr.map((item, index) => (
                                        <View>
                                            <View style={{ alignItems: "center" }}>
                                                <TouchableOpacity
                                                    activeOpacity={1.5}
                                                    onPress={() => {
                                                        onImgEnlarge(item, index)
                                                    }}>
                                                    <Image style={{ width: 80, height: 80, margin: 8 }} source={{ uri: item }} >
                                                    </Image>
                                                </TouchableOpacity>
                                                <TouchableOpacity
                                                    style={{ position: "absolute", right: -5, top: -5 }}
                                                    activeOpacity={1.5}
                                                    onPress={() => {
                                                        arr.splice(index, 1)
                                                        this.setState({
                                                            imagearr: arr

                                                        })

                                                    }}>
                                                    <Image style={{ width: 20, height: 20, margin: 8 }} source={require('../img/close.png')} />

                                                </TouchableOpacity>
                                            </View>

                                        </View>
                                    ))}
                                    <TouchableOpacity
                                        activeOpacity={1.5}
                                        onPress={() => this.activeEvent("id_image_pai")}

                                    >
                                        <Image style={{ width: 80, height: 80, margin: 8 }} source={require('../img/addPic.png')} />

                                    </TouchableOpacity>

                                </View>

                            </View>

                        </View>
                    </View>

                </ScrollView>

                <Button style={{}} title={'提交申诉'} onPress={() => this.activeEvent("id_bt_submit")} />

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
        backgroundColor: "#ffffff",
        height: scaleSizeH(220)
    },

    //图片
    item_icon: {
        width: scaleSizeW(160),
        margin: scaleSizeW(15)
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
    },
    //Item属性
    style_item: {
        flex: 1,
        justifyContent: "space-between",
        paddingTop: scaleSizeH(30),
        paddingLeft: scaleSizeW(20),
        paddingRight: scaleSizeW(30),
        marginRight: scaleSizeW(20),
        backgroundColor: "#ffffff",
        paddingBottom: 5
    },
    style_subTitle: {
        backgroundColor: "#ffffff",
        margin: scaleSizeW(10),
        flex: 1, textAlign: "center",
        paddingTop: scaleSizeH(5),
        paddingBottom: scaleSizeH(5),
        borderRadius: scaleSizeW(5)
    }   //查询的样式
    , style_select: {
        backgroundColor: "#ffffff",
        margin: scaleSizeW(10), backgroundColor: "#4C90F3", padding: scaleSizeW(8)
        , color: "#ffffff",
        borderRadius: scaleSizeW(8)
    }
})

