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
    Alert
} from 'react-native';
import { ScrollView } from 'react-native-gesture-handler';
import { showSha1 } from './../../utils/Sha1';
import { onLoading, onToast, onMsg, onImgEnlarge, onloadout } from './../../utils/MoudleUtils';
import TitleBar from '../TitleBar';
import { scaleSizeH, scaleSizeW } from './../../utils/screenPhone';

export default class MyInformation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            Userid: "",
            Token: "",
            username: "",
            URL_Constract:"https://app.lianjieshenghuo.com/",//主URL
            lat:"",
            lng:"",
            userData:"",
            username_name:"",
            photoiddata:"",
            arr:[]
                };
    }
    // //oncreate
    componentDidMount() {
        this.getData()
    }


    //接受Android 的数据    
    getData (){
        NativeModules.IntentModule.toRnLat((user, token, username,lat,lng) => {
            this.setState({
                Userid: user,
                Token: token,
                username: username,
                lat:lat,
                lng:lng,
                username_name: this.props.navigation.getParam('data', ""),
                

            },()=>{
                let url = "getCleanCardInfo"
                this.InforMationData(url)
            })
        })
    }


    //userData
    InforMationData(url) {
        var timestamp = Date.parse(new Date()) / 1000;
        let sign = showSha1('lat='+this.state.lat+'&lng='+this.state.lng+'&resource=' + this.state.resource + '&timestamp=' + timestamp + '&token=' + this.state.Token + '&userid=' + this.state.Userid + '&secret=pR(k10qlx$T*V)@u')
        let formData = new FormData();
        formData.append('timestamp', timestamp);
        formData.append('token', this.state.Token);
        formData.append('userid', this.state.Userid);
        formData.append('sign', sign);
        formData.append('resource', this.state.resource);
        formData.append('lat', this.state.lat);
        formData.append('lng', this.state.lng);


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
                        userData:responseJson.data,
                            arr:responseJson.data.cardData
                    })
                } else if (responseJson.code == 10011) {
                    onloadout()
                    onToast(responseJson.msg)
                } else {
                  
                    onToast(responseJson.msg)
                }


            }).catch((error) => {
                onToast("系统错误"+JSON.stringify(error))
                onloadout()
            }).finally(
                onLoading(1)
            )
    }
    // 点击事件
    activeEvent(msg) {
        switch (msg) {
                case "photoID_no"://弹出Toast
                break;
               case "address"://住址
                Alert.alert(
                    '我的住址',
                    this.state.username_name.addre,
                    [
                        {text: '好的', onPress: () => console.log('Ask me later pressed')},
                    ],
                    { cancelable: false }
                );
                break;  
        }
    }



    render() {
        //请求回来的数据
       var user =  this.state.userData==""?"":this.state.userData.imgData

        return (
            <View style={{ flex: 1 }}>
                <TitleBar
                    onClick={this}
                    name={'我的资料'}
                    flexstate={0.6}
                />
                <ScrollView>
                {/* 上面的蓝色背景 */}
                <View style={{ padding: 15 }}>
                    <View style={Styles.sytle_background}>
                        <View style={{
                            flexDirection: "row"
                        }}>
                            {/* 头像上传 */}

                            <TouchableOpacity
                                activeOpacity={1.5}
                                onPress={()=>{//头像上传需要一个返回值刷新当前页面
                                    NativeModules.IntentModule.AdbCamera((999), (dat)=>{
                                        dat ==1000?
                                        this.InforMationData("getCleanCardInfo"):""
                                    })
                                }}
                            >
                                <View>
                                    {
                                        user!="" ?user.face!=""? <Image style={Styles.style_userimage} source={{ uri: user.face }} /> :  <Image style={Styles.style_userimage} source={require("./../../img/defaultimg.png")} />: <Image style={Styles.style_userimage} source={require("./../../img/defaultimg.png")} />
                                    }


                                    <Image style={{ width: scaleSizeW(30), height: scaleSizeW(30), position: "absolute", bottom: scaleSizeW(10), right: 5 }}
                                        resizeMethod={"auto"} source={require('./../../img/camera.png')} />

                                </View>
                            </TouchableOpacity>
                            <View style={{ marginLeft: scaleSizeW(30) ,marginTop:scaleSizeH(20)}}>
                                <Text style={{ color: "#ffffff" }}>{this.state.username_name.username}</Text>
                                <Text style={{ marginTop: scaleSizeH(15), fontSize: 10, color: "#ffffff", }}>{user!=""?user.statusValue:""}</Text>

                            </View>


                        </View>
                        <View style={{
                        flexDirection:"row",alignItems:"center"
                        ,marginTop: scaleSizeH(25),}}>
                            <TouchableOpacity
                                     activeOpacity={1.5}
                                    onPress={()=>{this.activeEvent("address")}}                      >
                    <Image style={{ width: 12, height: 12, }} resizeMethod={"auto"} source={require('./../../img/jinggao.png')} />
                    </TouchableOpacity>
                            {/* <Text style={{ marginLeft: 10,}}>我的住址：</Text>              
                        <Text style={{ color: "#1f92fa" ,}}>{this.state.username_name.lefttx}</Text> */}
    <Text style={{ marginLeft: scaleSizeH(5), fontSize: 12, color: "#ffffff" }}>
    我的住址: {this.state.username_name.lefttx}
        { this.state.username_name!=""?( this.state.username_name.addre.length > 9 ? this.state.username_name.addre.substr(0, 9) + "..." : this.state.username_name.addre):""}

    </Text>

    

                        </View> 
                        <View style={{ flexDirection: "row", marginTop: scaleSizeH(10), alignItems: "center" }}>
                            <Image style={{ width: 12, height: 12, }} resizeMethod={"auto"} source={require('./../../img/jinggao.png')} />
                            <Text style={{ marginLeft: scaleSizeH(5), fontSize: 12, color: "#ffffff" }}>请通过上传您的相关证件照片完成身份验证</Text>
                        </View>

                    </View>
                    <Text style={{ fontSize: 12, marginTop: scaleSizeH(15), paddingLeft: scaleSizeW(35), }}>证件置于拍摄框内·四角完整，无遮挡·照片清晰，避免反光</Text>

                    {
                        this.state.arr.map((item, index) =>
                            (
                                <View style={Styles.style_item}>
                                    <View style={{}}>
                                        <View style={{ flexDirection: "row" }}>
                                            <Text style={{ color: "#000000" }}>{item.cardName}</Text>
                                          
                                                {item.cardStatus == 2 && item.allowUpload == 1?
                                                     <Text style={{
                                                        borderRadius: 10, borderWidth: 1, fontSize: 12, paddingLeft: 5, paddingRight: 5, marginLeft: 5,
                                                        borderColor: "#4C90F3", color: "#4C90F3"
                                                    }}
                                                    onPress={()=>{
                                                        NativeModules.IntentModule.onPhotoID((index+1), (dat) => { 
                                                            let   data =  JSON.parse(dat) 
                                                           
                                                           
                                                            if( dat == -1){
                                                                onToast("上传失败请重试...")
                                                            }else{
                                                                if(data.code == 1000){
                                                                    let   arr =   this.state.arr
                                                                    arr[index]  = data.data
                                                                      this.setState({
                                                                        arr:arr,
                                                                      })
                                                                      onToast(data.msg)
              
                                                                  }else if(data.code ==10011){
                                                                      onloadout()
                                                                      onToast(data.msg)
                                                                  }else{
                                                                      onToast(data.msg)
                                                                  }
                                                            }
                                                           
                                                        })
                                                    
                                                    
                                                    }}
                                                    > 点此更换 </Text> 
                                                    :
                                                    item.cardStatus == -1 ?
                                                    <Text style={{
                                                        borderRadius: 10, borderWidth: 1, fontSize: 12, paddingLeft: 5, paddingRight: 5, marginLeft: 5,
                                                        borderColor: "#4C90F3", color: "#4C90F3"
                                                    }}
                                                    onPress={()=>{
                                                        NativeModules.IntentModule.onPhotoID((index+1), (dat) => { 
                                                            let   data =  JSON.parse(dat) 
                                                           
                                                           
                                                            if( dat == -1){
                                                                onToast("上传失败请重试...")
                                                            }else{
                                                                if(data.code == 1000){
                                                                    let   arr =   this.state.arr
                                                                    arr[index]  = data.data
                                                                      this.setState({
                                                                          arr:arr,
                                                                          URL_Constract:"https://app.lianjieshenghuo.com/",//主URL
                                                                          photoiddata:"",
                                                                      })
                                                                      onToast(data.msg)
              
                                                                  }else if(data.code ==10011){
                                                                      onloadout()
                                                                      onToast(data.msg)
                                                                  }else{
                                                                      onToast(data.msg)
                                                                  }
                                                            }
                                                           
                                                        })
                                                    
                                                    
                                                    }}
                                                    >
                                                    点此上传
                                                    </Text>
                                                    :
                                                    item.cardStatus == 1 || item.cardStatus == 3?
                                                    <Text style={{
                                                        borderRadius: 10, borderWidth: 1, fontSize: 12, paddingLeft: 5, paddingRight: 5, marginLeft: 5,
                                                        borderColor: "#4C90F3", color: "#4C90F3"
                                                    }}
                                                    onPress={()=>{
                                                        NativeModules.IntentModule.onPhotoID((index+1), (dat) => { 
                                                            let   data =  JSON.parse(dat) 
                                                           
                                                           
                                                            if( dat == -1){
                                                                onToast("上传失败请重试...")
                                                            }else{
                                                                if(data.code == 1000){
                                                                    let   arr =   this.state.arr
                                                                    arr[index]  = data.data
                                                                      this.setState({
                                                                        arr:arr,
                                                                        URL_Constract:"https://app.lianjieshenghuo.com/",//主URL
                                                                        photoiddata:"",
                                                                      })
                                                                      onToast(data.msg)
              
                                                                  }else if(data.code ==10011){
                                                                      onloadout()
                                                                      onToast(data.msg)
                                                                  }else{
                                                                      onToast(data.msg)
                                                                  }
                                                            }
                                                           
                                                        })
                                                    
                                                    
                                                    }}
                                                    >
                                                    重新上传
                                                    </Text>
                                                    :<Text/>  
                                            }
                                        </View>
                                        {/* {item.cardStatusValue} */}
                                        <Text style={{ marginTop: scaleSizeH(10), fontSize: 13 ,color:item.colorValue,width:scaleSizeW(350)}}numberOfLines={10}>{item.cardStatusValue}</Text>
                                    </View>
                                    {/* 后面的类型图差异 */}
                         { item.cardStatus == -1 ?
                  <TouchableOpacity
                                     activeOpacity={1.5}
                                    onPress={() =>{onToast(item.cardStatusValue)}}                      >
                                <Image style={{ width: scaleSizeW(250), height: scaleSizeH(150), marginRight: scaleSizeW(30) }}
                                    resizeMethod={"auto"} source={require('./../../img/shenfenzheng.png')} />
                </TouchableOpacity>:

                <TouchableOpacity
                              activeOpacity={1.5}
                              onPress={() =>{onImgEnlarge(item.cardUrl,0)}}
                          >
                          <Image style={{ width: scaleSizeW(250), height: scaleSizeH(150), marginRight: scaleSizeW(30) }}
                           resizeMethod={"auto"} source={require('./../../img/photo_id.png')} />
             </TouchableOpacity> 
                            }
                                </View>
                            )
                        )
                    }

                </View>
                </ScrollView>

            </View >
        );
    }
}
var Styles = StyleSheet.create({

    //头像
    style_userimage: {
        width: scaleSizeH(130),
        height: scaleSizeH(130),
        borderRadius: 50,


    }
    , //item的样式
    style_item: {
        flexDirection: "row", borderWidth: 1, borderRadius: 10, borderColor: "#dddddd",
        paddingLeft: scaleSizeW(30), paddingTop: scaleSizeW(20), paddingBottom: scaleSizeW(20), marginTop: scaleSizeW(10),
        justifyContent: "space-between", borderBottomWidth: 3,
    },
    //顶部的View背景
    sytle_background: {
        width: "100%", backgroundColor: "#1f92fa", paddingLeft: scaleSizeW(30), paddingBottom: scaleSizeW(20),
        paddingTop: scaleSizeW(20),
        borderRadius: 10
    }
})