import React, { Component } from 'react';
import {
    View,
    Text,
    StyleSheet,
    Image, NativeModules,
    ImageBackground,
    TouchableOpacity,
    FlatList,
    Button
} from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from '../utils/screenPhone';
import { showSha1 } from '../utils/Sha1';
import { onLoading, onToast, onMsg, onImgEnlarge, onCemare, onloadout, onFinish, onStartDelatiles } from '../utils/MoudleUtils';
import { ScrollView } from 'react-native-gesture-handler';



export default class Evaluation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            URL_Constract: "https://app.lianjieshenghuo.com/",
            Token: this.props.navigation.getParam('token', ""),
            Type: this.props.navigation.getParam("type", 1),
            Userid: this.props.navigation.getParam('userid', ""),
            Commentdata: "",
            error: "",
            resource: 1,
            startTime: "",
            endTime: "",
            select: true,

        };
    }


    //oncreate
    componentDidMount() {
        this.defaultShowData()
    }





    // 点击事件
    activeEvent(msg) {
        switch (msg) {
            case "id_back"://退出
                this.props.navigation.goBack()
                break;
            case "id_starttime"://开始时间
                this.showTime(1)
                this.setState({
                    Type: 3
                })
                break;
            case "id_endtime"://结束时间
                this.showTime(2)
                this.setState({
                    Type: 3
                })
                break;
            case "id_oldSelect"://查询周期 
                if (this.state.startTime == "") {
                    onToast("请选择起始时间")
                } else if (this.state.endTime == "") {
                    onToast("请选择结束时间")
                } else {
                    this.setState({
                        select: false,
                        Type: 12
                    })
                    this.requestgetHistoryComment(this.state.startTime, this.state.endTime)
                }

                break;
            case "id_cancel"://取消

                this.setState({
                    URL_Constract: "https://app.lianjieshenghuo.com/",
                    Token: this.props.navigation.getParam('token', ""),
                    Type: this.props.navigation.getParam("type", 1),
                    Userid: this.props.navigation.getParam('userid', ""),
                    Commentdata: "",
                    error: "",
                    resource: 1,
                    startTime: "",
                    endTime: "",
                    select: true,
                }, () => {
                    this.defaultShowData()
                })

                break;


        }
    }
    //接受Android 的数据    
    showTime = (tabs) => {
        if (tabs == 1) {
            NativeModules.IntentModule.AdbDate(this.state.startTime, (time) => {
                this.setState({
                    startTime: time
                })
            })
        } else {
            NativeModules.IntentModule.AdbDate(this.state.endTime, (time) => {
                this.setState({
                    endTime: time
                })
            })
        }



    }
    /**
     * 进页面的default请求当天/周期 数据
     */
    defaultShowData = () => {
        NativeModules.IntentModule.AdbDate_defaultTime((time) => {
            this.requestgetHistoryComment(time, time)
        })
    }
    //User/info 个人头像
    /**
     * type:1
     */
    requestgetHistoryComment(startTime, endTime) {
        onLoading(0)//网络请求的转圈圈东西
        var url = "pingji/getHistoryComment"
        var timestamp = Date.parse(new Date()) / 1000;

        let sign = showSha1('endTime=' + endTime + '&resource=' + this.state.resource + '&startTime=' + startTime + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&type=' + this.state.Type
            + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
        formData.append('type', this.state.Type);
        formData.append('startTime', startTime);
        formData.append('endTime', endTime);


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
                        Commentdata: responseJson,
                    })
                    this.onerror()  //判断接口是否通顺
                } else if (responseJson.code == 10011) {
                    onloadout()
                    onToast(responseJson.msg)
                } else {
                    this.setState({
                        error: false
                    })
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



    render() {
        var MajorPage, childpage, startTime, endTime
            ;
        endTime = '结束时间'
        startTime = '起始时间'
        this.state.Type == 1 ?
            childpage = (<Text style={{ height: 0, }}></Text>) :
            childpage = (<View style={{
                width: "100%",
                backgroundColor: "#f4f4f4",
                justifyContent: "space-around", alignItems: "center"
                , flexDirection: "row"
            }} >
                <Text style={Styles.style_subTitle} onPress={() => this.activeEvent("id_starttime")}>{this.state.startTime == "" ? startTime : this.state.startTime}</Text>
                <Text style={Styles.style_subTitle} onPress={() => this.activeEvent("id_endtime")}>{this.state.endTime == "" ? endTime : this.state.endTime}</Text>
                {this.state.select ?
                    (<Text style={Styles.style_select} onPress={() => this.activeEvent("id_oldSelect")}>历史查询</Text>)
                    :
                    (<Text style={Styles.style_select} onPress={() => this.activeEvent("id_cancel")}> 取消 </Text>)
                }
            </View>
            )

        this.state.error ?
            MajorPage = (

                <ScrollView style={{ flex: 1 }}>
                    <View>
                        <FlatList
                            style={{ flex: 1, backgroundColor: "#f4f4f4" }}
                            data={this.state.Commentdata == "" ? "" : this.state.Commentdata.data.list}
                            renderItem={({ item }) =>
                                <View style={{ backgroundColor: "#ffffff" }}>
                                    <View style={Styles.style_item}>
                                        <Image style={{ width: scaleSizeH(100), height: scaleSizeH(100), alignSelf: "center" }} source={require('./../img/starBlue.png')} />
                                        <View style={{ flex: 1 }}>
                                            <View style={{
                                                flexDirection: "row", flex: 1, justifyContent: "space-between",
                                            }}>
                                                <View style={{ marginLeft: scaleSizeW(10) }}>
                                                    <Text style={{ color: "#4C90F3" }} onPress={() => onStartDelatiles(item.id + "", item.oid + "")}  >工单号:{item.gid}</Text>
                                                    <Text style={{ marginTop: scaleSizeH(20) }}>公寓号:{item.yid}</Text>
                                                </View>
                                                <Text style={{ marginTop: scaleSizeH(30), color: "#4C90F3" }} >
                                                    {item.pingjia}
                                                </Text>
                                            </View>


                                        </View>
                                    </View>
                                    <Text style={{ backgroundColor: "#dddddd", height: scaleSizeH(1), marginTop: scaleSizeH(30) }} />

                                </View>
                            }

                        />
                        {this.state.Commentdata == "" ? <Text></Text> : (<Text style={{ alignSelf: "center", marginTop: scaleSizeH(20) }}>当前数据共有{this.state.Commentdata.data.total}条评价 （{this.state.Commentdata.data.goodTotal}个好评）</Text>)}
                    </View>
                </ScrollView>)
            :
            MajorPage = (<View style={{ flex: 1, backgroundColor: "#f4f4f4" }}>
                <Image source={require('./../img/listNone.png')} style={{ height: scaleSizeH(200), width: '100%', marginTop: scaleSizeH(150) }}></Image>
                <Text style={{ alignSelf: "center", marginTop: scaleSizeH(20) }}>暂无相关数据</Text>
            </View>)



        //周期  和今日的切换
        var title, flex_ind;
        if (this.state.Type == 1) {
            flex_ind = 0.65
            title = "今日评价工单"
        } else if (this.state.Type == 12) {
            title = "历史评价工单"
            flex_ind = 0.65

        } else {
            title = "当前周期评价工单"
            flex_ind = 0.69

        }


        // 评价工单主页
        return (
            <View style={{ flex: 1 }}>
                <View style={{
                    flexDirection: 'row',
                    alignItems: 'center',
                    height: scaleSizeH(80),
                    alignSelf: 'stretch',
                    backgroundColor: "#ffffff"
                }}>
                    <View style={{ flexDirection: "row", justifyContent: "space-between", flex: flex_ind, alignItems: "center" }}>
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
                        <Text style={Styles.textstyle}>{title}</Text>
                    </View>
                </View>
                {/* 有无标签 */}
                {childpage}
                {/* default页面逻辑 */}
                {MajorPage}

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
        flex: 1, flexDirection: "row",
        justifyContent: "space-between",
        paddingTop: scaleSizeH(30),
        paddingLeft: scaleSizeW(20),
        paddingRight: scaleSizeW(30),
        marginRight: scaleSizeW(20),
        backgroundColor: "#ffffff"
    },
    style_subTitle: {
        backgroundColor: "#ffffff",
        margin: scaleSizeW(10),
        flex: 1, textAlign: "center",
        paddingTop: scaleSizeH(5),
        paddingBottom: scaleSizeH(5)
    }   //查询的样式
    , style_select: {
        backgroundColor: "#ffffff",
        margin: scaleSizeW(10), backgroundColor: "#4C90F3", padding: scaleSizeW(8)
        , color: "#ffffff",
        borderRadius: scaleSizeW(8)
    }
})


