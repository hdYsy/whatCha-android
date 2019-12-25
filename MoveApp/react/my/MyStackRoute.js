import React, { Component } from 'react';
import {
    Button,
    View,
    Text,
    StyleSheet,
    Image, NativeModules,
    ImageBackground,
    TouchableOpacity,
    FlatList,
    BackHandler
} from 'react-native';
import { createAppContainer, createStackNavigator, StackRouter } from 'react-navigation';

import { scaleSizeH, scaleSizeW, setSpText } from './../utils/screenPhone';
import { ScrollView } from 'react-native-gesture-handler';
import { showSha1 } from './../utils/Sha1';
import { onLoading, onToast, onMsg, onImgEnlarge, onCemare, onloadout, AdbMap,TrainingVideo,AdbMain } from './../utils/MoudleUtils';
import Myjineng from './Myjineng';
import uploadPwd from './uploadPwd';
import pastdue from './pastdue';
import Complaint from './Complaint';
import Evaluation from './Evaluation';
import MyAppeal from './MyAppeal';
import Delatiles from './shensuTab/Delatiles';
import DayAppeal from './DayAppeal';
import Error from '../utils/Error';
import MyInformation from './information/MyInformation';


//My主页面
class HomeScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            Userid: "",
            Token: "",
            URL_Constract: "https://app.lianjieshenghuo.com/",
            userdata: "",
            pingjidata: "",
            username: "",
            arr: [],
            error: "",  //错误页面 
            pingjiEnable: "",
            day: true,//评判断是否有评级数据
            AllSkillLabel: "",
            resource: '1',
            cach_bate:""


        };
       
    }

    //oncreate
    componentDidMount() {

        this.getData()
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
        if(this.props.navigation.isFocused()){//ok
              AdbMain()
              return true;
        }else{
            return false;
        }
      };



    //接受Android 的数据    
    getData = () => {
        NativeModules.IntentModule.toRNdata((user, token, username,cathstring) => {
            this.setState({
                Userid: user,
                Token: token,
                username: username,
                cach_bate:cathstring
            }, () => {
                let userinfo = "user/info"
                let pingji = "pingji"
                let getAllSkillLabel = "getAllSkillLabel"
                this.request_getAllSkillLabel(getAllSkillLabel)
                this.request_user(userinfo)
                this.request_pingji(pingji)

                onMsg()
            })
        })
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

    //User/info 个人头像
    request_user(url) {

        var timestamp = Date.parse(new Date()) / 1000;
        let sign = showSha1('resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);

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
                        userdata: responseJson,
                    })
                    this.onerror()  //判断接口是否通顺

                } else if (responseJson.code == 10011) {
                    onloadout()
                    onToast(responseJson.msg)
                } else {
                    this.setState({
                        error: false
                    })
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

    //pingji 评级
    request_pingji(url) {
        var timestamp = Date.parse(new Date()) / 1000;
        let sign = showSha1('resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);


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
                        pingjidata: responseJson,
                    }, () => {
                        this.setState({
                            pingjiEnable: responseJson.data.enabled
                        })
                    })
                    this.onerror()  //判断接口是否通顺


                } else if (responseJson.code == 10011) {
                    onloadout()
                    onToast(responseJson.msg)
                } else {
                    this.setState({
                        error: false
                    })
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

    //技能标签
    request_getAllSkillLabel(url) {
        var timestamp = Date.parse(new Date()) / 1000;
        let sign = showSha1('resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);

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
                    this.setState({
                        error: false
                    })
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
            case "id_back"://左上角首页退出
            AdbMain()
                break;
            case "id_username": //点击头像
                if (userdate !== "") {
                    onImgEnlarge(userdate.data.thumb, 0)
                }
                break;
            case "id_switchdata"://切换查看周期数据
                if (this.state.day) {
                    this.setState({
                        day: false
                    })
                } else {
                    this.setState({
                        day: true
                    })
                }

                break;

            case "id_loadout"://退出登陆
                onloadout()
                break;
            // case "id_myuserimg"://上传头像
            //     onCemare()
            //     break;
            case "id_myuserinformation"://我的资料页面
                // if (userdate !== "") {
                //   let address_lefttx =   address_lefttx == "" ? "" : address_lefttx
                //   let address =   address == "" ? "" : address

                //     this.props.navigation.navigate('MyInformation', { "username": this.state.username })
                //     // onCemare()
                 
                // }
                // onCemare()
                break;
            case "id_myjineng"://我的技能
                this.props.navigation.navigate('Myjineng')
                break
            case "id_appeal"://我的申诉
                this.props.navigation.navigate('MyAppeal', { 'token': this.state.Token, 'userid': this.state.Userid })

                break;
            case "id_materials"://申请物料
                onToast('暂无此功能')

                break;
            case "id_bed"://申请床品
                onToast('暂无此功能')
                break;
            case "id_pwd"://修改密码
                this.props.navigation.navigate('uploadPwd', { 'token': this.state.Token, 'userid': this.state.Userid, 'type': this.state.day ? 1 : 2 })
                break;
            case "id_Trainschool"://培训课程
            TrainingVideo()
            // onToast('暂无此功能')
                break;

            case "id_Evaluation"://评价量
                this.props.navigation.navigate("Evaluation", { 'token': this.state.Token, 'userid': this.state.Userid, 'type': this.state.day ? 1 : 2 })
                break;
            case "id_Complaint"://投诉；量
                this.props.navigation.navigate("Complaint", { 'token': this.state.Token, 'userid': this.state.Userid, 'type': this.state.day ? 1 : 2 })
                break;
            case "id_pastdue"://逾期量
                this.props.navigation.navigate("pastdue", { 'token': this.state.Token, 'userid': this.state.Userid, 'type': this.state.day ? 1 : 2 })
                break;
           


        }


        //评级 是否 是0/1

    }

    render() {
        // 标签
        var arr = new Array();
        arr.push({ id: "id_appeal", msg: "我的申诉", img: require("./../img/icon-appeal.png") })
        arr.push({ id: "id_Trainschool", msg: "培训课程", img: require("./../img//icon-train.png") })
        arr.push({ id: "id_pwd", msg: "修改密码", img: require("./../img/icon-modify-pwd.png") })

        //标题
        var title = (
            <View style={Styles.container}>
                <View style={{ flexDirection: "row", justifyContent: "space-between", flex: 0.6, alignItems: "center" }}>
                    <TouchableOpacity
                        activeOpacity={1.5}
                        onPress={() => this.activeEvent("id_back")}
                    >
                        <View style={{
                            flexDirection: "row",
                            alignItems: "center",
                            flex: 1
                        }}>

                            <Image style={{
                                width: scaleSizeW(35),
                                height: scaleSizeH(35),
                                marginLeft: scaleSizeW(15),
                            }} source={require("./../img/icon-index.png")} />

                            <Text style={{
                                fontSize: 15,
                                marginLeft: scaleSizeW(6),
                            }}>首页</Text>
                        </View>
                    </TouchableOpacity>
                    <Text style={Styles.textstyle}>个人中心</Text>
                </View>
            </View>
        )

        if (this.state.error) {


            //实时渲染  无评级
            var imgurl;
            if (this.state.userdata === "") {
                imgurl =
                    <TouchableOpacity
                        activeOpacity={0.5}
                        onPress={() => this.activeEvent("id_username")}
                    >
                        <Image style={Styles.style_userimage} source={require('./../img/defaultimg.png')} />
                    </TouchableOpacity>

            } else {
                let url = this.state.userdata.data.thumb
                if(!url){
                    imgurl =  <Image style={Styles.style_userimage} source={require("./../img/defaultimg.png")} />
                }else{
                imgurl =
                    <TouchableOpacity
                        activeOpacity={0.5}
                        onPress={() => this.activeEvent("id_username")}
                    >
                        <Image style={Styles.style_userimage} source={{ uri: (url) }} />
                    </TouchableOpacity>
                    }
            }
            //实时渲染 评级 的  user Image
            var imgurl_year;
            if (this.state.userdata === "") {
                imgurl_year =
                    <TouchableOpacity
                        activeOpacity={0.5}
                        onPress={() => this.activeEvent("id_username")}
                    >
                        <Image style={Styles.style_userimage_year} source={require('./../img/defaultimg.png')} />
                    </TouchableOpacity>

            } else {
                let url = this.state.userdata.data.thumb
                if(url){
                    imgurl_year =
                    <TouchableOpacity
                        activeOpacity={0.5}
                        onPress={() => this.activeEvent("id_username")}
                    >
                        <Image style={Styles.style_userimage_year} source={{ uri: (url) }}  />
                    </TouchableOpacity>
                }else{
                    imgurl_year =    <Image style={Styles.style_userimage_year} source={require("./../img/defaultimg.png")} />
 
                }

              
                   
            }



            //无评级   imageurl ->这里的头像罢了
            var user_no = (
                <ImageBackground style={{
                    width: "100%", height: scaleSizeH(400)
                }} source={require("./../img/aa.png")}>
                    <View style={{ alignItems: "center", justifyContent: "center", paddingTop: scaleSizeH(100), paddingBottom: 50 }}>
                        {imgurl}
                        <Text style={{ fontSize: 16, 
                        color: "#FFFFFF", fontStyle: "normal",
                         marginTop: scaleSizeH(15)
                         }}           onPress={() =>{
                            if ( this.state.userdata !== "") {
              
                                this.props.navigation.navigate('MyInformation', {"data": {"username": this.state.username,"lefttx": this.state.username,"lefttx": !this.state.userdata.data.review_one_status_name?"":"("+ this.state.userdata.data.review_one_status_name+") ","addre": this.state.userdata.data.address}})
                                // onCemare()
                               
                              }


                        }}>
                            {this.state.username === "" || this.state.username === undefined ? "点米科技~" : this.state.username}</Text>
                        <Text style={{
                            fontSize: 10, marginTop: scaleSizeW(15), backgroundColor: "#FFFFFF",
                            padding: scaleSizeW(5),
                            borderRadius: 25,
                            paddingLeft: scaleSizeW(15),
                            paddingRight: scaleSizeW(15),
                            color: "#4C90F3"
                        }} >所属供应商:点米网络科技股份有限公司</Text>
                    </View>
                </ImageBackground>
            )
            //评级头像
            var user_year = (
                <View
                >
                    <ImageBackground style={{
                        width: "100%", height: scaleSizeH(400)
                    }} source={require("./../img/aa.png")}>


                        <View style={{ paddingTop: scaleSizeH(20), marginLeft: scaleSizeW(20), flexDirection: "row" }}>
                            {imgurl_year}

                            <View style={{ marginLeft: scaleSizeW(20) }}>
                                <View style={{ flexDirection: "row", alignItems: "center" }}>
                                    <Text style={{ fontSize: 16, 
                                        color: "#FFFFFF", fontStyle: "normal",
                                         marginTop: scaleSizeH(15)
                                          }}
                                          onPress={() =>{
                                            if ( this.state.userdata !== "") {
                            
                                                  this.props.navigation.navigate('MyInformation', {"data": {"username": this.state.username,"lefttx": this.state.username,"lefttx": !this.state.userdata.data.review_one_status_name?"":"("+ this.state.userdata.data.review_one_status_name+") ","addre": this.state.userdata.data.address}})
                                                  // onCemare()
                                               
                                              }
        
        
                                        }}
        
                                        >
                                        {this.state.username === "" || this.state.username === undefined ? "点米科技~" : this.state.username}</Text>
                                    <Text style={{
                                        backgroundColor: "#fbf28e", fontSize: setSpText(20), borderRadius: 50,
                                        padding: scaleSizeW(4), alignSelf: "flex-end", paddingLeft: scaleSizeW(8), paddingRight: scaleSizeW(8),
                                        marginLeft: scaleSizeW(10), marginBottom: scaleSizeH(3)
                                    }}>上周期评级:{this.state.pingjidata === "" ? "" : this.state.pingjidata.data.pingji}级</Text>
                                </View>
                                <Text style={{
                                    fontSize: 10, marginTop: scaleSizeW(15),
                                    borderRadius: 25,
                                    color: "#ffffff",
                                    fontWeight: 'bold',
                                }} >所属供应商:点米网络科技股份有限公司</Text>
                            </View>
                        </View>
                        <View style={{
                            flex: 1, margin: scaleSizeH(30),
                            backgroundColor: "#ffffff", backfaceVisibility: "hidden", borderRadius: 5
                        }}>
                            <View style={{ flexDirection: "row", paddingLeft: scaleSizeW(15), }}>
                                <Text style={{ color: "#000000" }}>{this.state.day ? "今日" : "周期"}数据</Text>
                                <TouchableOpacity
                                    activeOpacity={1.0}
                                    onPress={() => this.activeEvent("id_switchdata")}
                                >
                                    <View style={{ flexDirection: "row", alignItems: "center", paddingTop: scaleSizeH(6) }}>
                                        <Image style={{ width: scaleSizeW(22), height: scaleSizeH(22), alignSelf: "center", marginLeft: scaleSizeW(10) }} source={require('../img/icon_swich.png')} />
                                        <Text style={{ fontSize: setSpText(20), alignSelf: "center", color: "#4C90F3" }}>切换查看{this.state.day ? "周期" : "今日"}数据</Text>
                                    </View>
                                </TouchableOpacity>
                            </View>
                            <Text style={{ backgroundColor: "#f4f4f4", height: scaleSizeH(1), marginTop: scaleSizeH(10) }}></Text>
                            <View style={{ flex: 1, flexDirection: "row", justifyContent: "space-around", alignItems: "center", marginTop: scaleSizeH(15), marginBottom: scaleSizeH(15) }}>
                                <TouchableOpacity
                                    activeOpacity={1.5}
                                    onPress={() => this.activeEvent("id_Evaluation")}
                                >
                                    <View >
                                        <Text style={{ alignSelf: "center" }}>{this.state.pingjidata === "" ? "" : this.state.day ? this.state.pingjidata.data.day_pingjialiang : this.state.pingjidata.data.month_pingjialiang}</Text>
                                        <Text style={Styles.pingjitxstyle} >评价量</Text>
                                    </View>
                                </TouchableOpacity>
                                <View>
                                    <Text style={{ alignSelf: "center" }}>{this.state.pingjidata === "" ? "" : this.state.day ? this.state.pingjidata.data.day_canpinglv : this.state.pingjidata.data.month_canpinglv}</Text>
                                    <Text style={Styles.pingjitxstyle}>参评率</Text>
                                </View>
                                <View >
                                    <Text style={{ alignSelf: "center" }}>{this.state.pingjidata === "" ? "" : this.state.day ? this.state.pingjidata.data.day_haopinglv : this.state.pingjidata.data.month_haopinglv}</Text>
                                    <Text style={Styles.pingjitxstyle}>好评率</Text>
                                </View>
                                <TouchableOpacity
                                    activeOpacity={1.5}
                                    onPress={() => this.activeEvent("id_Complaint")}
                                >
                                    <View >
                                        <Text style={{ alignSelf: "center" }}>{this.state.pingjidata === "" ? "" : this.state.day ? this.state.pingjidata.data.day_tousuliang : this.state.pingjidata.data.month_tousuliang}</Text>
                                        <Text style={Styles.pingjitxstyle}>投诉量</Text>
                                    </View>
                                </TouchableOpacity>
                                <TouchableOpacity
                                    activeOpacity={1.5}
                                    onPress={() => this.activeEvent("id_pastdue")}
                                >
                                    <View >
                                        <Text style={{ alignSelf: "center" }}>{this.state.pingjidata === "" ? "" : this.state.day ? this.state.pingjidata.data.day_yuqiliang : this.state.pingjidata.data.month_yuqiliang}</Text>
                                        <Text style={Styles.pingjitxstyle}>逾期量</Text>
                                    </View>
                                </TouchableOpacity>
                            </View>

                        </View>
                    </ImageBackground>
                </View>
            )

            /**
             * 评级与否  这里是最终判断
             */
            var havepingji;
            if (this.state.pingjiEnable === "") {
                havepingji = user_no   //暂时改成  user_year
            } else if (this.state.pingjidata.data.enabled == 0) {
                havepingji = user_no   //暂时改成  user_year
            } else {
                havepingji = user_year

            }


            /*  点击现居地址所需文案提示
          
                      1 暂无                  >>>>>    无
                      2 审核通过的地址         >>>>>    您好，您的当前住址为[已审核通过住址]，是否需要修改？
                      3（审核中）暂无         >>>>>>    您好，您的新住址[新住址]正在审核中，是否需要修改？
                      4（审核中）已审过地址    >>>>>>   您好，您的新住址[新住址]正在审核中，是否需要修改？
                      5（审核失败）已审过地址  >>>>>>	  您好，您的新住址[新住址]审核失败，请您核实提交的住址是否准确和详细，修改后再次重新提交审核！
                      6（审核失败）暂无       >>>>>>    您好，您的新住址[新住址]审核失败，请您核实提交的住址是否准确和详细，修改后再次重新提交审核！
                  */
            /** showData 对应状态值
             * isExamine为-1==》1【暂无】
             * isExamine为1，address不为空  ==》2【审核通过的地址】
             * isExamine为0，address为空    ==》3【（审核中）暂无  】
             * isExamine为0，address不为空  ==》4【（审核中）已审过地址 】
             * 
             * isExamine为2，address不为空  ==》5【（审核失败）已审过地址 】
             * isExamine为2，address为空    ==》6【（审核失败）暂无 】
             */
            //进行判断是否 出现问题  当出现问题直接 回滚到初始页面不可点击
            // var address = "";
            // var address_lefttx = "";
            // var da = "";
            // if (this.state.userdata != "") {
            //     let  data = this.state.userdata.data.review_one_status_name
            //     da = data;
            //     if (data.isExamine == -1) {
            //         address = "暂无"
            //     } else if (data.isExamine == 0) {
            //         address_lefttx = "(审核中)"
            //         if (data.address == "") {
            //             address = "暂无"
            //         } else {
            //             address = data.newAddress
            //         }
            //     } else if (data.isExamine == 1) {
            //         if (data.address == "") {
            //             address = ""
            //             address_lefttx = ""
            //         } else {
            //             address_lefttx = ""
            //             address = data.newAddress
            //         }

            //     } else if (data.isExamine == 2) {
            //         if (data.address == "") {
            //             address = "暂无"
            //             address_lefttx = "(审核失败)"
            //         } else {
            //             address_lefttx = "(审核失败)"
            //             address = data.newAddress
            //         }
            //     }
            // }
        var xianjudizhi =    ( <View style={{ flexDirection: "row" }}>
       { this.state.userdata?this.state.userdata.data.review_one_status_name?  <Text style={{ marginLeft: 10, color: "#1f92fa" }}>{`(${this.state.userdata.data.review_one_status_name})`}</Text>:<Text/>:<Text/>}
        <Text >
        {this.state.userdata? (this.state.userdata.data.address.length > 9 ? this.state.userdata.data.address.substr(0, 9) + "..." : this.state.userdata.data.address?this.state.userdata.data.address:"暂无") : "暂无"}
        </Text>
        <Image style={Styles.item_icon} resizeMode={"contain"} source={require("./../img/icon-more.png")} />
    </View>
            )
            return (
                <View style={{ flex: 1 }} >
                    {/* 标题 */}
                    {title}
                    <ScrollView style={{ flex: 1 }}>
                        <View style={{ flex: 1, backgroundColor: "#f4f4f4" }}>

                            {/* //评级与否 */}
                            {havepingji}

                            {/* 我的资料 点击事件 */}
                            <TouchableOpacity
                                activeOpacity={1.5}
                                onPress={() =>{
                                    if ( this.state.userdata !== "") {
                      
                                          this.props.navigation.navigate('MyInformation', {"data": {"username": this.state.username,"lefttx": this.state.userdata.data.review_one_status_name,"addre": this.state.userdata.data.address}})
                                          // onCemare()
                                       
                                      }


                                }}
                            >
                                <View style={{ backgroundColor: "#f4f4f4", }}>
                                    <View style={Styles.style_loadimg}>
                                        <View style={{ flexDirection: "row" }}>
                                            <Image style={Styles.item_icon}  resizeMode={"contain"} source={require("./../img/icon-modify.png")} />
                                            <Text style={{ marginLeft: scaleSizeW(10) }}>我的资料</Text>
                                        </View>
                                        <View style={{ flexDirection: "row" }}>
                                            <Text style={{ color: "#1f92fa" }}></Text>
                                            <Image style={Styles.item_icon}  resizeMode={"contain"} source={require("./../img/icon-more.png")} />
                                        </View>
                                    </View>

                                    <Text style={{ backgroundColor: "#f4f4f4", height: scaleSizeH(1) }} />
                                </View>


                            </TouchableOpacity>
                            {/* 保洁员住址 */}
                            <TouchableOpacity
                                activeOpacity={1.5}
                                onPress={() => {
                                   let data =  this.state.userdata
                                    data?
                                    data.data.isExamine==""?AdbMap("",""): 
                                    data.data.isExamine == 1&&data.data.address?AdbMap( data.data.address,"1"):
                                    data.data.isExamine == 0?  AdbMap(data.data.newAddress ,"0"):
                                    data.data.isExamine==2? AdbMap(data.data.newAddress,"2")
                                    :AdbMap("","")
                                    :AdbMap("","")
                                    
                                }}
                            >
                                <View style={{ backgroundColor: "#f4f4f4" }}>
                                    <View style={Styles.style_loadimg}>
                                        <View style={{ flexDirection: "row" }}>
                                            <Image style={Styles.item_icon}  resizeMode={"contain"} source={require("./../img/icon-add.png")} />
                                            <Text style={{ marginLeft: scaleSizeW(10) }}>现居地址  </Text>
                                        </View>
                                        {xianjudizhi}

                                    </View>
                                </View>
                            </TouchableOpacity>
                            <View style={{ backgroundColor: "#f4f4f4", flex: 1, height: 10 }} />

                            {/* 我的技能 */}


                            {/* arr.push({ id: "id_myjineng", msg: "我的技能", img: require("./../img/icon_skill.png") }) */}
                            <View style={{ backgroundColor: "#f4f4f4" }}>

                                <View style={Styles.style_item}>
                                    <Image style={Styles.item_icon}  resizeMode={"contain"} source={require("./../img/icon_skill.png")} />
                                    <View style={{ flex: 1 }}>
                                        <View style={{
                                            flexDirection: "row", flex: 1, justifyContent: "space-between"
                                        }}>
                                            <Text style={{ marginLeft: scaleSizeW(10), }} onPress={() => this.activeEvent("id_myjineng")}>我的技能</Text>
                                            {/* 滑动的小布局 */}
                                            <View style={{ width: "60%", height: scaleSizeW(52), backgroundColor: "#ffffff" }}>
                                                {this.state.AllSkillLabel === "" ? <Text></Text> : <FlatList
                                                    style={{ flex: 1 }}

                                                    data={this.state.AllSkillLabel.data.data}
                                                    keyExtractor={(item, index) => index}
                                                    renderItem={({ item }) => (
                                                        <TouchableOpacity
                                                            activeOpacity={1.5}
                                                            onPress={() => this.activeEvent("id_myjineng")}
                                                        >
                                                            <Image style={{ width: scaleSizeW(45), height: scaleSizeW(52), marginLeft: scaleSizeW(10) }} source={{ uri: item.isHave == 1 ? item.normalImage : item.grayImage }} />
                                                        </TouchableOpacity>
                                                    )}
                                                    horizontal={true}
                                                    showsHorizontalScrollIndicator={false}

                                                />}
                                            </View>
                                            <TouchableOpacity
                                                activeOpacity={1.5}
                                                onPress={() => this.activeEvent("id_myjineng")}

                                            >
                                                <Image style={Styles.item_icon}  resizeMode={"contain"} source={require("./../img/icon-more.png")} />
                                            </TouchableOpacity>
                                        </View>
                                        <Text style={{ backgroundColor: "#f4f4f4", height: scaleSizeH(1), marginTop: scaleSizeH(30) }} />

                                    </View>
                                </View>


                            </View>

                            <FlatList
                                style={{ backgroundColor: "#f4f4f4" }}
                                data={arr}
                                removeClippedSubviews={true}
                                renderItem={({ item }) =>
                                    <TouchableOpacity
                                        activeOpacity={1.5}

                                        onPress={() => this.activeEvent(item.id)}  >
                                        <View>
                                            <View style={Styles.style_item}>
                                                <Image style={Styles.item_icon} source={item.img}  resizeMode={"contain"} />
                                                <View style={{ flex: 1 }}>
                                                    <View style={{
                                                        flexDirection: "row", flex: 1, justifyContent: "space-between"
                                                    }}>
                                                        <Text style={{ marginLeft: scaleSizeW(10) }}>{item.msg}</Text>

                                                        <Image style={Styles.item_icon}  resizeMode={"contain"} source={require("./../img/icon-more.png")} />

                                                    </View>
                                                    <Text style={{  height: scaleSizeH(1), marginTop: scaleSizeH(30),backgroundColor:"#f4f4f4"}} />

                                                </View>
                                            </View>

                                        </View>
                                    </TouchableOpacity>
                                }

                            />
                            <View style={{ backgroundColor: "#f4f4f4", flex: 1, height: 10 }} />
                            {/* 清除缓存 */}
                            <TouchableOpacity
                                activeOpacity={1.5}
                                onPress={() => {
                                    NativeModules.IntentModule.AdbCachClear((b)=>{
                                        if(b){
                                            this.getData()
                                        }
                                    })
                                }}
                            >
                                <View style={{ backgroundColor: "#f4f4f4" }}>
                                    <View style={Styles.style_loadimg}>
                                        <View style={{ flexDirection: "row" }}>
                                            <Image style={Styles.item_icon}  resizeMode={"contain"} source={require("./../img/clear.png")} />
                                            <Text style={{ marginLeft: scaleSizeW(10) }}>清除缓存</Text>
                                        </View>
                                        <View style={{ flexDirection: "row" }}>
                <Text style={{ marginLeft: 10}}>约{this.state.cach_bate}</Text>
                            <Image style={Styles.item_icon} resizeMode={"contain"} source={require("./../img/icon-more.png")} />
    </View>
                                    </View>
                                </View>
                            </TouchableOpacity> 
                            {/* 退出登录              */}
                            <View style={{ backgroundColor: "#f4f4f4", flex: 1, height: 10 }} />
                    <View style={{ width: "100%", backgroundColor:"#FFFFFF"}}>
                        < Text style={{
                            textAlign: "center",
                            paddingBottom: 10,
                            color: "#4C90F3",
                            paddingTop: 10,
                            height: 40,
                            alignSelf: "center"
                        }} onPress={() => this.activeEvent("id_loadout")}>退出登录</Text >
                    </View>
                        </View>

                    </ScrollView>
                    
                </View >

            )
        }
        else {
            return (//10/11幹掉錯誤頁面
                <View style={{ flex: 1 }}>
                    {title}
                    <Error />
                    {/* {title}
                    <Error /> */}

                </View >
            )

        }

    }
}
const HomeStack = createStackNavigator({
    Home: {
        screen: HomeScreen,
        navigationOptions: {
            header: null,
        }
    }
    ,
    Myjineng: {
        screen: Myjineng,
        navigationOptions: {
            header: null,
        }
    }
    , uploadPwd: {
        screen: uploadPwd,
        navigationOptions: {
            header: null,
        }
    }
    , Complaint: {
        screen: Complaint,
        navigationOptions: {
            header: null,
        }
    }
    , Evaluation: {
        screen: Evaluation,
        navigationOptions: {
            header: null,
        }
    }
    , pastdue: {
        screen: pastdue,
        navigationOptions: {
            header: null,
        }
    }
    ,
    MyAppeal: {
        screen: MyAppeal,
        navigationOptions: {
            header: null
        }
    },
    Delatiles: {
        screen: Delatiles,
        navigationOptions: {
            header: null
        }
    },
    DayAppeal: {
        screen: DayAppeal,
        navigationOptions: {
            header: null
        }
    },
    MyInformation: {
        screen: MyInformation,
        navigationOptions: {
            header: null
        }
    },
});



const AppContainer = createAppContainer(HomeStack);

export default class App extends Component {
    render() {
        return <AppContainer />;
    }
}
var Styles = StyleSheet.create({
    style_Homeview: {
        justifyContent: "space-between",
        alignItems: "center"
        , flex: 1

    },

    style_icon: {
        width: scaleSizeH(35),
        height: scaleSizeH(35),
        marginLeft: scaleSizeW(30),
    },
    //头像
    style_userimage: {
        width: scaleSizeH(186),
        height: scaleSizeH(186),
        borderRadius: 50
    }
    ,
    //评级头像
    style_userimage_year: {
        width: scaleSizeW(140),
        height: scaleSizeW(140),
        borderRadius: 50
    }
    ,//上传图片的单独属性
    style_loadimg: {
        flex: 1, flexDirection: "row",
        justifyContent: "space-between",
        marginLeft: scaleSizeW(30),
        paddingTop: scaleSizeH(30),
        paddingLeft: scaleSizeW(20),
        paddingRight: scaleSizeW(30),
        marginRight: scaleSizeW(20),
        paddingBottom: scaleSizeH(30),
        backgroundColor: "#ffffff"
        , borderRadius: 5
    },
    //Item属性
    style_item: {
        flex: 1, flexDirection: "row",
        justifyContent: "space-between",
        marginLeft: scaleSizeW(30),
        paddingTop: scaleSizeH(30),
        paddingLeft: scaleSizeW(20),
        paddingRight: scaleSizeW(30),
        marginRight: scaleSizeW(20),
        backgroundColor: "#ffffff"
    },

    //item中的小图
    item_icon: {
        width: scaleSizeW(40),
        height: scaleSizeW(40),
        
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
    //评级 的 tx  style
    pingjitxstyle: {
        fontSize: setSpText(22),
        color: "#000000",
        marginTop: scaleSizeH(15),
        marginBottom: scaleSizeH(15),
    },


}



)



