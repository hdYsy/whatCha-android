import React, { Component } from 'react';
import { View, Text, Image, StyleSheet, TouchableOpacity, NativeModules, Button ,BackHandler} from 'react-native';
import { scaleSizeH, scaleSizeW } from './utils/screenPhone';
import { onFinish, onLoading, onToast, onImgEnlarge, onloadout } from './utils/MoudleUtils';
import { showSha1 } from './utils/Sha1';
import { ScrollView } from 'react-native-gesture-handler';

export default class multServer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: "",
            latitude: "",
            longitude: "",
            Userid: "",
            Token: "",
            URL_Constract: "https://app.lianjieshenghuo.com/",
            data: "",
            resource: '1',


        };
        this.andoirdArgs()

    }
    // 这是 工單id经纬度
    andoirdArgs() {
        NativeModules.IntentModule.AdbMultID((id, latitude, longitude, Userid, Token) => {
            this.setState({
                id: id,
                latitude: latitude,
                longitude: longitude,
                Userid: Userid,
                Token: Token,


            }, () => {
                let multiServiceList = "multiServiceList"
                this.requestData(multiServiceList)//数据源
            })
        })
    }
    //     id:1071750
    // timestamp:1567752964
    // token:cXnwz6mW3aljiGJNCdkqu01rDRMh2UHV
    // userid:100397
    // sign:090bf5c756884b027f75cf92f04e34578cd01562

    //网络请求
    requestData(url) {
        var timestamp = Date.parse(new Date()) / 1000;
        let sign = showSha1('id=' + this.state.id + '&resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
        formData.append('id', this.state.id);


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
                        data: responseJson,
                    })
                } else if (responseJson.code == 10011) {
                    onloadout()
                    onToast(responseJson.msg)
                } else {
                    onToast(responseJson.msg)
                }
            }).catch((error) => {
                this.setState({
                    error: false
                })
                onToast("系统错误")
                onloadout()
            }).finally(
                onLoading(1)
            )
    }
    //这是 那两张图片那个
    imgX(item, index) {
        var arr = this.state.data   //初始最大数据源
        var boolean = item.imgList[0] == ""
        var boolean1 = item.imgList[1] == ""

        var imgstat = boolean ? require('./img/serviceBefore.png') : { uri: item.imgList[0] }
        var imgstat1 = boolean1 ? require('./img/serviceAfter.png') : { uri: item.imgList[1] }

        return (
            <View style={{ flexDirection: "row" }}>
                <View style={{ padding: 5, height: 85 }}>
                    <TouchableOpacity
                        activeOpacity={1.5}
                        onPress={() => {
                            boolean ?
                                //調用系统相机进行拍照，并且存到数组当中赋值
                                NativeModules.IntentModule.AdbImageCamere(this.state.id, (Type) => {
                                    // let arr = new Array()
                                    // let arr = new State()
                                    //把这个数组赋值
                                    arr.data.multiServiceImageList[index].imgList.splice(0, 1, Type)
                                    this.setState({
                                        data: arr
                                    })

                                }) : onImgEnlarge(item.imgList[0], 0)
                        }}
                    >
                        <Image style={{ width: 80, height: 80 }} source={imgstat} >
                        </Image>
                    </TouchableOpacity>
                    {boolean ? <Text></Text> :

                        <TouchableOpacity
                            style={{ position: "absolute", right: -2, top: -2 }}
                            activeOpacity={1.5}
                            onPress={() => {
                                arr.data.multiServiceImageList[index].imgList.splice(0, 1, "")
                                this.setState({
                                    data: arr
                                })

                            }}>
                            <Image style={{ width: 20, height: 20 }} source={require('./img/close.png')} />

                        </TouchableOpacity>
                    }


                </View>
                <View style={{ padding: 5, height: 90 }}>
                    <TouchableOpacity
                        activeOpacity={1.5}
                        onPress={() => {
                            boolean1 ?
                                //調用系统相机进行拍照，并且存到数组当中赋值
                                NativeModules.IntentModule.AdbImageCamere("", (Type) => {
                                    // let arr = new Array()
                                    // let arr = new State()
                                    //把这个数组赋值
                                    arr.data.multiServiceImageList[index].imgList.splice(1, 1, Type)
                                    this.setState({
                                        data: arr
                                    })

                                }) : onImgEnlarge(item.imgList[1], 0)
                        }}
                    >
                        <Image style={{ width: 80, height: 80 }} source={imgstat1} >
                        </Image>
                    </TouchableOpacity>
                    {boolean1 ? <Text></Text> :
                        <TouchableOpacity
                            style={{ position: "absolute", right: -2, top: -2 }}
                            activeOpacity={1.5}
                            onPress={() => {
                                arr.data.multiServiceImageList[index].imgList.splice(1, 1, "")
                                this.setState({
                                    data: arr

                                })

                            }}>
                            <Image style={{ width: 20, height: 20, }} source={require('./img/close.png')} />

                        </TouchableOpacity>
                    }
                </View>
            </View >
        )
    }


    //提交
    submit() {
        var timestamp = Date.parse(new Date()) / 1000;
        let imglist = JSON.stringify(this.state.data != "" ? this.state.data.data.multiServiceImageList : "")
        let sign = showSha1('id=' + this.state.id + '&imagelist='
            + imglist
            + '&resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
        formData.append('id', this.state.id);
        formData.append('imagelist', imglist);

        let url = 'multiFormSubmit'
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
                    onFinish()
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

 //oncreate
 componentDidMount() {
    BackHandler.addEventListener('hardwareBackPress', this.onBackAndroid);

}
componentWillUnmount() {
    BackHandler.removeEventListener('hardwareBackPress', this.onBackAndroid);
}
onBackAndroid = () => {
    onFinish()    //     const routers = nav.getCurrentRoutes();
    // if (routers.length > 1) {
    //   const top = routers[routers.length - 1];
    //   if (top.ignoreBack || top.component.ignoreBack){
    //     // 路由或组件上决定这个界面忽略back键
    //     return true;
    //   }
    //   const handleBack = top.handleBack || top.component.handleBack;
    //   if (handleBack) {
    //     // 路由或组件上决定这个界面自行处理back键
    //     return handleBack();
    //   }rr
    //   // 默认行为： 退出当前界面。
    //   nav.pop();
    //   return true;
    // }
    // return false;
        return true;
  };

    render() {
        var arr = this.state.data   //初始最大数据源


        return (
            <View style={{ flex: 1 }}>
                <ScrollView style={{ flex: 1 }}>
                    <View style={{ backgroundColor: "#f4f4f4", flex: 1 }} >

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
                                    onPress={() => onFinish()
                                    }
                                >
                                    <Image style={{
                                        width: scaleSizeW(35),
                                        height: scaleSizeH(35),
                                        marginLeft: scaleSizeW(15),
                                    }} source={require("./img/icon-navBack.png")} />


                                </TouchableOpacity>


                                <Text style={Styles.textstyle}>添加多服务</Text>
                            </View>
                        </View>
                        {/* 喇叭 */}
                        < View style={
                            Styles.labaStyle
                        } >
                            <Image
                                style={{
                                    width: scaleSizeW(40),
                                    height: scaleSizeW(40),
                                }}

                                source={require('./img/icon_tips.png')}
                            />
                            <Text
                                style={{
                                    color: "#EC7866"
                                }}
                            >请选择服务项目并如实上传前后对比照片!</Text>

                        </View >

                        {/* 服务项目 */}
                        < View
                            style={{
                                flexDirection: "row",
                                backgroundColor: "#ffffff",
                                borderColor: "#dddddd",
                                borderBottomWidth: 1
                            }}
                        >
                            <Text style={{
                                marginLeft: 10,
                                color: "#000000",
                                marginTop: 10,
                                marginBottom: 10

                            }}>
                                服务项目
                    </Text>
                            <Text style={{
                                color: "#999999",
                                marginTop: 10,
                                marginBottom: 10
                            }}>(再次点击取消选择)</Text>

                        </View >

                        {/* 标签列表 */}


                        <View style={{
                            alignItems: "center", flexDirection: "row", justifyContent: "space-around",
                            flexWrap: "wrap", backgroundColor: "#ffffff", paddingTop: 12
                            , borderBottomColor: "#f4f4f4", borderBottomWidth: 10, paddingBottom: 15
                        }}>
                            {this.state.data == "" ? <View /> : arr.data.multiServiceList.map((item, index) => (
                                <Text style={{
                                    width: "48%", textAlign: "center", backgroundColor: item.isSelected ? "#4C90F3" : "#ffffff", marginTop: scaleSizeH(10),
                                    borderRadius: 30, fontSize: 12, padding: 5, marginTop: 12, borderWidth: 1, borderColor: "#dddddd",
                                    color: item.isSelected ? "#ffffff" : "#999999"
                                }}
                                    onPress={
                                        () => {
                                            //这里面主要对  数据的操作  不可以 扔到仿方法中 ，会出问题
                                            if (item.isSelected) {
                                                item.isSelected = false
                                                arr.data.multiServiceList.splice(index, 1, item)
                                                arr.data.multiServiceImageList.forEach((element, index) => {
                                                    if (element.cateId == item.cateId) {
                                                        arr.data.multiServiceImageList.splice(index, 1)
                                                    }
                                                });

                                            } else {
                                                item.isSelected = true
                                                arr.data.multiServiceImageList.push({ 'cateId': item.cateId, 'cateName': item.cateName, 'imgList': ["", ""] })
                                                arr.data.multiServiceList.splice(index, 1, item)
                                            }
                                            this.setState({
                                                data: arr
                                            })
                                        }
                                    }
                                >{item.cateName}</Text>
                            ))}
                        </View>

                        {/* 点击标签显现出来的东西 */}

                        {
                            arr == "" ? <Text></Text> : arr.data.multiServiceImageList.map((item, index) => (
                                index == 0 ?
                                    <View>
                                        <View style={{ borderBottomWidth: 1, borderBottomColor: "#dddddd", backgroundColor: "#ffffff" }}>
                                            <Text style={{ color: "#000000", paddingTop: 10, paddingBottom: 10, paddingLeft: 10 }}>服务详情</Text>
                                        </View>
                                        <View style={{ paddingLeft: 10, paddingTop: 10, backgroundColor: "#ffffff", }}>
                                            <Text >{`${item.cateName}前后对比`}</Text>
                                            {this.imgX(item, index)}


                                        </View>
                                    </View>
                                    :
                                    <View style={{ paddingLeft: 10, paddingTop: 10, backgroundColor: "#ffffff" }}>
                                        <Text>{`${item.cateName}前后对比`}</Text>
                                        {this.imgX(item, index)}

                                    </View>


                            )
                            )
                        }


                    </View >
                </ScrollView>
                <Button style={{ height: 50, }} title={'确认'} onPress={() => {
                    if (this.state.data != "") {
                        this.submit()
                    }

                }} />
            </View >
        )
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
    }

    ,

    //喇叭
    labaStyle: {
        width: "100%",
        backgroundColor: "#fee2e1",
        flexDirection: "row",
        paddingTop: scaleSizeH(5),
        paddingBottom: scaleSizeH(5)
    }

}

)
