import React, { Component } from 'react';
import { View, Text, FlatList, Image, StyleSheet, TouchableOpacity, ScrollView, ImageBackground, Button, NativeModules } from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from './../../utils/screenPhone';
import { onLoading, onToast, onMsg, onImgEnlarge, onCemare, onloadout, onFinish, onStartDelatiles, adbClose } from '../../utils/MoudleUtils';
import { showSha1 } from '../../utils/Sha1';
import TitleBar from '../TitleBar';
import { DeviceEventEmitter } from 'react-native';//监听
export default class Delatiles extends Component {
    constructor(props) {
        super(props);
        this.state = {
            responseData: "", //请求回来的
            URL_Constract: "https://app.lianjieshenghuo.com/",
            resource: 1 + "",
            id: this.props.navigation.getParam("id", ""),
            Token: this.props.navigation.getParam('Token', ""),
            Userid: this.props.navigation.getParam('Userid', ""),
            position: this.props.navigation.getParam('position', ""),
            bt_title: ""
        };

    }


    componentDidMount() {
        this.getAppealReasonInfo()
        DeviceEventEmitter.addListener('通知', (param) => {
            //收到通知后处理逻辑
            this.props.navigation.goBack()

        })

    }

    componentWillUnmount() {
        DeviceEventEmitter.emit('通知名称', this.state.position);

    }

    //请求申诉详情的接口
    getAppealReasonInfo() {
        onLoading(0)//网络请求的转圈圈东西
        var url = "getAppealReasonInfo"
        var timestamp = Date.parse(new Date()) / 1000;
        var sign = showSha1('appealReasonId=' + this.state.id + '&resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        var formData = new FormData();
        formData.append('appealReasonId', this.state.id);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
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
                    this.setState({
                        responseData: responseJson
                    })
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
    activeEvent(msg, str) {
        switch (msg) {
            case "id_bt"://修改

                if (str == '撤销申诉') {
                    NativeModules.IntentModule.AdbCenterDialog('确定要撤销申诉么?', (Type) => {
                        if (Type == 1) {
                            this.cancelAppealReason()
                        }
                    })

                } else if (str == "重新申诉") {
                    var a = this.state.responseData.data
                    this.props.navigation.navigate('DayAppeal', {
                        'item': {
                            'content': a.content,
                            'gid': a.gid,
                            'yid': a.yid,
                            'servicestarttime': a.servicestarttime,
                            'appeal_reason': a.appeal_reason,
                            'id': a.complain_id,

                        },
                        'appeal_imglist': a.appeal_imglist,
                        'Token': this.state.Token,
                        "Userid": this.state.Userid
                    })
                }
        }

    }

    // 
    // appealReasonId:21
    // complainId:2
    // timestamp:1567152188
    // token:cXnwz6mW3aljiGJNCdkqu01rDRMh2UHV
    // userid:100397
    // sign:1d1dd4dd53bf3abe73341f2a5143f7cd4b3f3ed2
    //撤销申诉
    cancelAppealReason() {
        onLoading(0)//网络请求的转圈圈东西
        var url = "cancelAppealReason"
        var timestamp = Date.parse(new Date()) / 1000;
        var sign = showSha1('appealReasonId=' + this.state.id + '&complainId=' + this.state.responseData.data.complain_id + '&resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        var formData = new FormData();
        formData.append('appealReasonId', this.state.id);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
        formData.append('timestamp', timestamp);
        formData.append('complainId', this.state.responseData.data.complain_id);


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
                    onToast(responseJson.msg)
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



    render() {
        var arr = new Array()
        // arr.push('工单编号')
        // arr.push('公寓编号')
        // arr.push('保洁服务时间')
        // arr.push('投诉内容')
        var data = this.state.responseData
        if (this.state.responseData != "") {
            arr.push({ name: '工单编号', value: data.data.gid })
            arr.push({ name: '公寓编号', value: data.data.yid })
            arr.push({ name: '保洁服务时间', value: data.data.serviceEndTime })
        }
        var bt_title = "";
        if (data != "") {
            if (data.data.status == "1") {
                bt_title = "撤销申诉";
            } else if (data.data.status == "3" && data.data.isShow == "1") {
                bt_title = "重新申诉";

            }
        }

        return (
            <View style={{ flex: 1, backgroundColor: "#f4f4f4" }}>
                <TitleBar
                    onClick={this}
                    name={'我的申诉'}
                />
                <ScrollView style={{ backgroundColor: "#ffffff" }}>
                    <View style={{
                        flex: 1

                    }}>
                        <View style={{
                            flexDirection: "row", width: "100%", justifyContent: "space-between",
                            paddingLeft: 10,
                            paddingRight: 10,
                            paddingTop: 5,
                            paddingBottom: 5

                        }}>
                            <Text style={{ color: "#000000" }}>
                                投诉信息
                        </Text>
                            <ImageBackground style={{
                                height: 20,
                                width: 80,
                                alignItems: "center"
                            }}
                                resizeMethod={"auto"}
                                source={require("./../../img/back.png")}>

                                <Text style={{ color: "#ffffff", }}>
                                    {data != "" ? data.data.statusValue : ""}
                                </Text>
                            </ImageBackground>

                        </View>

                        <Text style={{ width: "100%", height: 1, backgroundColor: "#dddddd" }} />
                        <View>
                            <FlatList
                                data={arr}
                                renderItem={({ item }) =>
                                    <View>
                                        <View style={{
                                            flexDirection: "row", justifyContent: "space-between", paddingLeft: 10,
                                            marginRight: 5,
                                            paddingTop: 5,
                                            paddingBottom: 5,

                                        }}>
                                            <Text style={{ marginRight: 10, }}>{item.name}</Text>


                                            <Text style={{ color: "#000000" }}>{item.value}</Text>

                                        </View>



                                    </View>
                                }

                            />
                            <View style={{
                                flex: 1,
                                justifyContent: "space-between",
                                paddingLeft: scaleSizeW(20),
                                paddingRight: scaleSizeW(30),
                                marginRight: scaleSizeW(20),
                                backgroundColor: "#ffffff",
                                flexDirection: "row"

                            }}>
                                <Text style={{ marginRight: 10, }}>投诉内容</Text>


                                <Text style={{ color: "#000000", flex: 1 }}>{data != "" ? data.data.content
                                    + "\n" : ""}</Text>
                            </View>

                        </View>
                        <Text style={{ backgroundColor: "#f4f4f4", width: "100%", height: scaleSizeH(15) }}></Text>
                        <View style={{
                            width: "100%",

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
                            <Text style={{
                                paddingLeft: 10,
                                paddingRight: 10,
                                paddingBottom: 5,
                                paddingTop: 5,
                            }}>{data == "" ? "" : data.data.appeal_reason + "\n"}</Text>
                            <Text style={{ backgroundColor: "#f4f4f4", width: "100%", height: scaleSizeH(15) }}></Text>

                            <Text style={{
                                color: "#000000", paddingLeft: 10,
                                paddingRight: 10,
                                paddingBottom: 5,
                                paddingTop: 5,
                            }}>
                                图片材料
                        </Text>
                            <Text style={{ width: "100%", height: 1, backgroundColor: "#dddddd" }} />
                            <FlatList
                                contentContainerStyle={styles.listViewStyle}
                                onEndReachedThreshold={1}
                                numColumns={4}
                                data={this.state.responseData == "" ? "" : this.state.responseData.data.appeal_imglist}
                                renderItem={({ item, index }) =>
                                    <TouchableOpacity
                                        activeOpacity={1.5}
                                        onPress={() => {
                                            onImgEnlarge(item, index)
                                        }}
                                    >
                                        <Image style={{ width: 70, height: 70, margin: 5 }} source={{ uri: item }} />
                                    </TouchableOpacity>
                                }
                            />
                            {this.state.position == 1 || this.state.position == 0 ? <View></View> :
                                <View>
                                    <Text style={{ backgroundColor: "#f4f4f4", width: "100%", height: scaleSizeH(15) }}></Text>
                                    <View style={{
                                        flexDirection: "row", paddingLeft: 10,
                                        paddingRight: 10,
                                        paddingBottom: 5,
                                        paddingTop: 5,
                                    }}>
                                        <Text style={{
                                            color: "#000000",
                                        }}>
                                            处理结果
                </Text>

                                        <Text style={{
                                            color: "#000000", marginLeft: 10
                                        }}>
                                            {data == "" ? "" : data.data.statusValue}
                                        </Text>
                                    </View>
                                    <Text style={{ backgroundColor: "#f4f4f4", width: "100%", height: scaleSizeH(15) }}></Text>

                                </View>
                            }



                        </View>




                        {data == "" ? <View></View> : data.data.appeal_result_images.length == 0 ? <View></View> :
                            <View>
                                <View style={{
                                    flexDirection: "row", paddingLeft: 10,
                                    paddingRight: 10,
                                    paddingBottom: 5,
                                    paddingTop: 5,
                                }}>
                                    <Text style={{
                                        color: "#000000",
                                    }}>
                                        处理图片 </Text>
                                </View>
                                <FlatList
                                    contentContainerStyle={styles.listViewStyle}
                                    onEndReachedThreshold={1}
                                    numColumns={4}
                                    data={data.data.appeal_result_images}
                                    renderItem={({ item, index }) =>
                                        <TouchableOpacity
                                            activeOpacity={1.5}
                                            onPress={() => {
                                                onImgEnlarge(item, index)
                                            }}
                                        >
                                            <Image style={{ width: 70, height: 70, margin: 5 }} source={{ uri: item }} />
                                        </TouchableOpacity>
                                    }
                                />
                                <Text style={{ backgroundColor: "#f4f4f4", width: "100%", height: scaleSizeH(15) }}></Text>

                            </View>
                        }

                        {data == "" ? <View /> : data.data.appeal_result != "" ? (
                            <View style={{
                                paddingLeft: 10,
                                paddingRight: 10,
                                paddingBottom: 5,
                                paddingTop: 5,
                            }}>

                                <Text style={{
                                    color: "#000000",

                                }}>
                                    驳回原因
                        </Text>
                                <Text style={{ width: "100%", height: 1, backgroundColor: "#dddddd", marginTop: 5 }} />
                                <Text style={{ marginTop: 5 }}>{data.data.appeal_result} </Text></View>) : <View />}


                        {this.state.position != 4 ? <View></View> :
                            <View>
                                <View style={{
                                    paddingLeft: 10,
                                    paddingRight: 10,
                                    paddingBottom: 5,
                                    paddingTop: 5,
                                    borderBottomWidth: 1,
                                    borderColor: "#f4f4f4"
                                }}>
                                    <Text style={{
                                        color: "#000000",
                                    }}>
                                        申诉已超时
                </Text>

                                </View>

                                <Text style={{
                                    marginLeft: 10,
                                    marginTop: 5
                                }}>
                                    请尽快联系督导核实情况
                                </Text>
                            </View>
                        }



                    </View>

                </ScrollView>
                {bt_title == "" ? <View /> : <Button title={bt_title} onPress={() => this.activeEvent("id_bt", bt_title)} />}





            </View >
        );
    }
}
const styles = StyleSheet.create({
    //Tab样式
    scrollTab: {
        flex: 1,
        backgroundColor: '#fff',
    },
    //Tab下划线样式
    underlineStyle: {
        height: 2,
        justifyContent: "center",
        alignItems: 'center',
        backgroundColor: '#4C90F3',


    },
    listViewStyle: {
        flex: 1,

        // 主轴方向
        flexDirection: 'row',
        // 一行显示不下,换一行
        flexWrap: 'wrap',
        // 侧轴方向
        alignItems: 'center', // 必须设置,否则换行不起作用
    },
});
