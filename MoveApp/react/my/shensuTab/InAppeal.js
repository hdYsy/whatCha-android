import React, { Component } from 'react';
import { View, Text, FlatList, Image, StyleSheet, TouchableOpacity } from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from './../../utils/screenPhone';
import { onLoading, onToast, onMsg, onImgEnlarge, onCemare, onloadout, onFinish, onStartDelatiles } from '../../utils/MoudleUtils';
import { showSha1 } from '../../utils/Sha1';
import MyAppeal from '../MyAppeal';

// (toDelatils)
export default class InAppeal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            responseData: "",
            URL_Constract: "https://app.lianjieshenghuo.com/",
            resource: 1 + "",
            Token: this.props.Token,
            Userid: this.props.Userid
            , item: this.props.tabdata,
            position: this.props.position
        };
    }

    // 点击事件
    activeEvent(msg) {
        switch (msg) {
            case "item_delatile"://退chu

                break;


        }
    }



    render() {
        var itemdata;
        var item = this.state.item
        if (item.status == 2) {
            itemdata = (<View style={{ flex: 1, backgroundColor: "#f4f4f4" }}>
                <Image source={require('./../../img/listNone.png')} style={{ height: scaleSizeH(200), width: '100%', marginTop: scaleSizeH(150) }}></Image>
                <Text style={{ alignSelf: "center", marginTop: scaleSizeH(20) }}>暂无相关数据</Text>
            </View>)

        } else {
            itemdata = (
                <View
                    style={{ flex: 1 }}>
                    <FlatList
                        style={{ flex: 1, backgroundColor: "#f4f4f4" }}
                        data={item.lists}
                        renderItem={({ item }) =>
                            <TouchableOpacity
                                activeOpacity={1.5}
                                onPress={() => this.props.onClick.props.navigation.navigate('Delatiles',
                                    {
                                        'id': item.id,
                                        'Token': this.state.Token,
                                        'Userid': this.state.Userid,
                                        "position": this.state.position,

                                    })}
                            >

                                <View style={{ backgroundColor: "#ffffff" }}>
                                    <View style={Styles.style_item}>
                                        <View style={{ flex: 1 }}>
                                            <View style={{
                                                flexDirection: "row", flex: 1, justifyContent: "space-between",
                                            }}>
                                                <View style={{ marginLeft: scaleSizeW(10) }}>
                                                    <Text style={{ color: "#4C90F3" }} onPress={() => onStartDelatiles(null, item.oid + "")}  >工单号:   {item.gid}</Text>
                                                    <Text style={{ marginTop: scaleSizeH(20) }}>申诉类型:   {item.appeal_type}</Text>
                                                    <Text style={{ marginTop: scaleSizeH(20) }}>申诉时间:   {item.createtime}</Text>
                                                </View>
                                                <Text style={{ marginTop: scaleSizeH(30), color: "#4C90F3" }} >
                                                </Text>
                                            </View>


                                        </View>
                                    </View>
                                    <Text style={{ backgroundColor: "#dddddd", height: scaleSizeH(1), marginTop: scaleSizeH(30) }} />

                                </View>
                            </TouchableOpacity>
                        }

                    />
                    <Text>{item.status}</Text>
                </View>)
        }
        return (
            < View style={{ flex: 1 }}>
                {itemdata}
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
        backgroundColor: "#ffffff"
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
