import React, { Component } from 'react';
import {
    View,
    Text,
    StyleSheet,
    Image,
    TouchableOpacity,

} from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from './../utils/screenPhone';
import { showSha1 } from './../utils/Sha1';
import { onLoading, onToast, onMsg, onImgEnlarge, onCemare, onloadout, onFinish } from './../utils/MoudleUtils';
import { createMaterialTopTabNavigator, createAppContainer, HeaderStyleInterpolator } from "react-navigation";;
import ScrollableTabView, { DefaultTabBar, ScrollableTabBar } from 'react-native-scrollable-tab-view';
import InAppeal from './shensuTab/InAppeal'
import { DeviceEventEmitter } from 'react-native';

export default class MyAppeal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            responseData: "",
            URL_Constract: "https://app.lianjieshenghuo.com/",
            Token: this.props.navigation.getParam('token', ""),
            Userid: this.props.navigation.getParam("userid", ""),
            resource: 1 + "",
            bean: "0",
            Tab_state: 0//初始的时候的下标
        };
    }


    componentDidMount() {
        //收到监听
        this.requestgetHistoryComment()
        DeviceEventEmitter.addListener('通知名称', (param) => {
            //收到通知后处理逻辑
            //eg 刷新数据    
            this.setState({
                bean: 2012,
                responseData: "",
                URL_Constract: "https://app.lianjieshenghuo.com/",
                Token: this.props.navigation.getParam('token', ""),
                Userid: this.props.navigation.getParam("userid", ""),
                Tab_state: param,
            }, () => {
                this.requestgetHistoryComment()
            })
        })


    }

    componentWillUnmount() {
        //移除监听
        DeviceEventEmitter.removeListener();
    }



    requestgetHistoryComment() {
        onLoading(0)//网络请求的转圈圈东西
        var url = "getAppealReasonLists"
        var timestamp = Date.parse(new Date()) / 1000;
        var sign = showSha1('resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        var formData = new FormData();
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
                break;



        }

    }
    //fragment 
    renderFragment = (item, i) => {
        return (
            <InAppeal      /**这里是要复用的页面 我的名字是PopularTab*/
                tabLabel={item.name}  /** Tab对应的标题的名字*/
                tabdata={item}
                Token={this.state.Token}
                Userid={this.state.Userid}
                onClick={this}
                position={i}
            />


        )
    };

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


                        <Text style={Styles.textstyle}>我的申诉</Text>
                    </View>
                </View>


                <Text style={{ height: 1, backgroundColor: "#dddddd" }}></Text>
                <ScrollableTabView
                    style={styles.scrollTab}
                    initialPage={this.state.Tab_state}
                    locked={false}  //不可滑动

                    renderTabBar={() =>
                        <ScrollableTabBar
                            style={{ height: scaleSizeH(90), backgroundColor: '#fff', alignItems: 'center', justifyContent: "center", width: "100%" }}
                            tabStyle={{ width: "25%", alignSelf: "center" }}
                            backgroundColor={'#FFFDD5'}
                            underlineStyle={styles.underlineStyle}
                            textStyle={{ fontSize: 11 }}
                            activeTextColor={'#4C90F3'}
                            inactiveTextColor={'#333333'}

                        />
                    }
                >

                    {this.state.responseData == "" ? (<Text />) : this.state.responseData.code == 1000 ? this.state.responseData.data.map(this.renderFragment) : (<Text />)}
                    {/* {a.map(this.renderFragment)} */}
                </ScrollableTabView>




            </View>
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
    }




}

)




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


    }
});
