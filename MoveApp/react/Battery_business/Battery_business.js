import React, { Component } from 'react';
import { View, Text, FlatList, Image, StyleSheet, TouchableOpacity, NativeModules, Button,BackHandler } from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from './../utils/screenPhone';
import { onFinish, onLoading, onToast, onImgEnlarge, onloadout } from './../utils/MoudleUtils';
import { showSha1 } from './../utils/Sha1';
import { ScrollView } from 'react-native-gesture-handler';
import { RadioGroup, RadioButton } from 'react-native-flexi-radio-button'
import Error from './../utils/Error';


export default class Battery_business extends Component {
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
            rdID: [], //已处理 或者未处理
            imgList: [{ "name": "", "imgs": [], "status": "", }],
            arr: [],//提交进行逻辑运算的下标
            battary_state: [],
            battary_desStr: []
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
                error: "",

            }, () => {
                let openBatteryView = "battery/openBatteryView"
                this.requestData(openBatteryView)//数据源
            })
        })
    }
    // id:1063264
    // timestamp:1568019133
    // token:S17idn4qNPAxzjrGbsVITDyFcBLRXHl2
    // userid:99999
    // sign:5d106c0a9aa37326f87c7e5ea4723cbbab584ddb
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
                    var arr = new Array()
                    var batt_statearr = []; //记录 流式布局标签的下标
                    let arr1 = this.state.rdID
                    responseJson.data.example.map((item, index) => {
                        var aa = { "name": item.door, "imgs": ["", ""], "status": "0" }
                        arr.push(aa)
                        arr1.push(0)
                    })
                    //电池标签
                    responseJson.data.batteryDesLists.map((item, index) => {
                        batt_statearr.push("")
                    }
                    )



                    this.setState({
                        data: responseJson,
                        error: true,
                        imgList: arr,
                        rdID: arr1,
                        battary_state: batt_statearr

                    })
                } else if (responseJson.code == 10011) {
                    onloadout()
                    onToast(responseJson.msg)
                } else {
                    this.setState({
                        error: false
                    })
                    onToast(responseJson.msg + "")
                }
            }).catch((error) => {
                this.setState({
                    error: false
                })

            }).finally(
                onLoading(1)
            )
    }

    /**
     * 提交电池   页面的业务
     */
    submit() {


        // updateBatteryLocksAfterChangeInfo
        var timestamp = Date.parse(new Date()) / 1000;
        let sign = showSha1('desStr=' + this.state.battary_desStr.join(',') + '&id=' +
            this.state.id + '&imagelist=' + JSON.stringify(this.state.imgList) +
            '&resource=' + this.state.resource + '&timestamp=' + timestamp + '&token='
            + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')

        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
        formData.append('id', this.state.id);
        formData.append('imagelist', JSON.stringify(this.state.imgList));
        formData.append('desStr', this.state.battary_desStr.join(','));

        fetch(this.state.URL_Constract + 'updateBatteryLocksAfterChangeInfo', {
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
    const nav = this.props; 
    //     const routers = nav.getCurrentRoutes();
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
    onFinish()
        return true;
  };



    render() {




        var title =
            (<View><View style={{
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
                        }} source={require("./../img/icon-navBack.png")} />


                    </TouchableOpacity>
                    <Text style={Styles.textstyle}>更换电池</Text>
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
                        source={require('./../img/icon_tips.png')}
                    />
                    <Text
                        style={{
                            color: "#EC7866"
                        }}
                    >换电池费用会与工单一起结算,此单无法完成请勿更换!</Text>

                </View >

            </View>
            )




        if (this.state.error) {
            if (this.state.data != "") {

                var arr = this.state.data.data.example.map((item, indexitem) =>

                    (

                        <View style={{ marginLeft: 15 }}>
                            <View style={{ flexDirection: "row", paddingTop: 10, paddingBottom: 10, borderBottomColor: "#f4f4f4", borderBottomWidth: 1 }}>
                                <Image style={{ width: 11, height: 16 }} source={require('./../img/battery.png')} />
                                <Text style={{ color: "#000000", marginLeft: 5 }}>{item.door}</Text>
                            </View>
                            <RadioGroup
                                selectedIndex={0}

                                onSelect={(index, value) => {
                                    let arr = this.state.rdID
                                    let arr2 = this.state.imgList
                                    arr.splice(indexitem, 1, index)
                                    arr2[indexitem].status = index + ""
                                    this.setState({
                                        rdID: arr,
                                        imgList: arr2
                                    })
                                }}
                            >

                                <RadioButton value={'item1'} >

                                    <View style={{ flexDirection: "row" }}>
                                        <Text style={{ color: "#000000" }} >未处理电池   :</Text>
                                        <Text style={{ alignSelf: "flex-end" }}>   没带电池或没时间处理选此项</Text>
                                    </View>
                                </RadioButton>

                                <RadioButton value={'item2'} >
                                    <View style={{ flexDirection: "row" }}>
                                        <Text style={{ color: "#000000" }}>已安装电池   :</Text>
                                        <Text style={{}}>   已将新电池放入门锁选择此项</Text>
                                    </View>
                                </RadioButton>

                            </RadioGroup>
                            {/* 是否显示相机相册 */}
                            {
                                this.state.rdID[indexitem] != 1 ? <Text></Text> :
                                    <View style={{ flexDirection: "row", alignContent: "space-around" }}>
                                        <View style={{ padding: 5, height: 80 }}>
                                            <TouchableOpacity
                                                style={{}}
                                                activeOpacity={1.5}
                                                onPress={() => { onImgEnlarge(item.install, 0) }}>
                                                <Image style={{ width: 70, height: 70 }} source={{ uri: item.install }} />
                                            </TouchableOpacity>
                                        </View>
                                        {
                                            <View style={{ padding: 5, height: 80 }}>
                                                <TouchableOpacity
                                                    style={{}}
                                                    activeOpacity={1.5}
                                                    onPress={() => {
                                                        this.state.imgList[indexitem].imgs[0] == "" ?
                                                            //調用系统相机进行拍照，并且存到数组当中赋值
                                                            NativeModules.IntentModule.AdbImageCamere_PhotoImage(this.state.id, (Type) => {
                                                                // let arr = new Array()
                                                                // let arr = new State()
                                                                //把这个数组赋值
                                                                let arr = this.state.imgList
                                                                arr[indexitem].imgs.splice(0, 1, Type)
                                                                this.setState({
                                                                    imgList: arr
                                                                })

                                                            }) : onImgEnlarge(this.state.imgList[indexitem].imgs[0], 0)
                                                    }}>
                                                    <Image style={{ width: 70, height: 70 }}
                                                        source={this.state.imgList != "" ?
                                                            this.state.imgList[indexitem].imgs[0] == ""
                                                                ? require('./../img/addPic.png') : { uri: this.state.imgList[indexitem].imgs[0] } :
                                                            require('./../img/addPic.png')} />
                                                </TouchableOpacity>
                                                {/* 删除按钮 */}
                                                {
                                                    this.state.imgList[indexitem].imgs[0] == "" ? <TouchableOpacity></TouchableOpacity>
                                                        :
                                                        <TouchableOpacity
                                                            style={{ position: "absolute", right: 5, top: 5 }}
                                                            activeOpacity={1.5}
                                                            onPress={() => {
                                                                let arr = this.state.imgList
                                                                arr[indexitem].imgs.splice(0, 1, "")
                                                                this.setState({
                                                                    imgList: arr
                                                                })

                                                            }}>
                                                            <Image style={{ width: 20, height: 20, }} source={require('./../img/close.png')} />

                                                        </TouchableOpacity>
                                                }


                                            </View>


                                        }



                                        <View style={{ padding: 5, height: 80 }}>
                                            <TouchableOpacity
                                                style={{}}
                                                activeOpacity={1.5}
                                                onPress={() => { onImgEnlarge(item.place, 0) }}>
                                                <Image style={{ width: 70, height: 70 }} source={{ uri: item.place }} />
                                            </TouchableOpacity>
                                        </View>
                                        {
                                            <View style={{ padding: 5, height: 80 }}>
                                                <TouchableOpacity
                                                    style={{}}
                                                    activeOpacity={1.5}
                                                    onPress={() => {
                                                        this.state.imgList[indexitem].imgs[1] == "" ?
                                                            //調用系统相机进行拍照，并且存到数组当中赋值
                                                            NativeModules.IntentModule.AdbImageCamere_PhotoImage(this.state.id, (Type) => {
                                                                // let arr = new Array()
                                                                // let arr = new State()
                                                                //把这个数组赋值
                                                                let arr = this.state.imgList
                                                                arr[indexitem].imgs.splice(1, 1, Type)
                                                                this.setState({
                                                                    imgList: arr
                                                                })

                                                            }) : onImgEnlarge(this.state.imgList[indexitem].imgs[1], 0)
                                                    }}>
                                                    <Image style={{ width: 70, height: 70 }}
                                                        source={this.state.imgList != "" ?
                                                            this.state.imgList[indexitem].imgs[1] == ""
                                                                ? require('./../img/addPic.png') : { uri: this.state.imgList[indexitem].imgs[1] } :
                                                            require('./../img/addPic.png')} />
                                                </TouchableOpacity>
                                                {
                                                    this.state.imgList[indexitem].imgs[1] == "" ? <TouchableOpacity></TouchableOpacity>
                                                        :
                                                        <TouchableOpacity
                                                            style={{ position: "absolute", right: 5, top: 5 }}
                                                            activeOpacity={1.5}
                                                            onPress={() => {
                                                                let arr = this.state.imgList
                                                                arr[indexitem].imgs.splice(1, 1, "")
                                                                this.setState({
                                                                    imgList: arr
                                                                })

                                                            }}>
                                                            <Image style={{ width: 20, height: 20, }} source={require('./../img/close.png')} />

                                                        </TouchableOpacity>
                                                }


                                            </View>


                                        }

                                    </View>


                            }





                        </View >

                    )

                )

            } else {
                arr = (<Text></Text>)
            }
            {/* 2.0标签 */ }
            var battary_2_0 = (<View >

                <View style={{ flexDirection: "row", flexWrap: "wrap" }}>
                    {this.state.data != "" ? this.state.data.data.batteryDesLists.map((item, index) =>
                        <Text style={
                            {
                                marginTop: scaleSizeH(20),
                                borderWidth: 1,
                                borderRadius: 10,
                                width: '23%',
                                textAlign: "center",
                                marginRight: 3,
                                marginLeft: 3,

                                backgroundColor: this.state.battary_state[index] == "" ? '#ffffff' : "#1f92fa",
                                color: this.state.battary_state[index] == "" ? '#848485' : "#ffffff",
                                borderColor: "#dddddd"
                            }


                        } onPress={() => {
                            let batt_statearr = this.state.battary_state
                            if (batt_statearr[index] == "") {
                                batt_statearr.splice(index, 1, 'true')
                            } else {
                                batt_statearr.splice(index, 1, "")
                            }
                            this.setState({
                                battary_state: batt_statearr
                            })


                        }}>{item.desValue}</Text>
                    ) : <View />}
                </View></View>)
            return (
                <View style={{ backgroundColor: "#ffffff", flex: 1 }} >
                    <ScrollView style={{ flex: 1 }}>
                        {title}
                        <View style={{ flex: 1 }}>
                            {/* <View style={{ marginLeft: 15, flex: 1 }}> */}
                            {arr}
                            <View style={{ paddingLeft: 15, paddingRight: 15, borderTopWidth: 10, borderTopColor: "#f4f4f4", paddingBottom: 50 }}>
                                <Text style={{ color: "#59595B", marginTop: scaleSizeH(20) }}>电池处理备注（非必选）</Text>
                                {battary_2_0}
                            </View>

                        </View>



                    </ScrollView>

                    <Button style={{ position: "absolute", position: "absolute", bottom: 0 }} title={'处理完成'} onPress={() => {
                        var aa = []
                        this.state.battary_state.forEach((element, index) => {
                            if (element) {
                                aa.push(index + 1)
                            }
                        })

                        this.setState({
                            battary_desStr: aa
                        }, () => {
                            var dialog_text = "【";
                            this.state.data.data.example.forEach((element, index) => {
                                dialog_text += element.door + ":" + (this.state.rdID[index] == 1 ? "已安装, " : "未处理, ")
                            })
                            dialog_text += '】'

                            NativeModules.IntentModule.AdbCenterDialog(`您选择的电池处理结果为${dialog_text}是否确认提交? 点击确认后暂不支持再次修改！`, (Type) => {
                                if (Type == 1) {
                                    this.submit()
                                }
                            })
                        })

                    }} />
                </View>
            );
        } else {
            return (
                <View style={{ backgroundColor: "#f4f4f4", flex: 1 }} >
                    {title}
                    <Error></Error>

                </View>
            )
        }


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
    },





}

)