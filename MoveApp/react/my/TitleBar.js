import React, { Component } from 'react';
import { View, Text, FlatList, Image, StyleSheet, TouchableOpacity } from 'react-native';
import { scaleSizeH, scaleSizeW, setSpText } from './../utils/screenPhone';

export default class TitleBar extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        var flexstate = this.props.flexstate;
        flexstate = flexstate == undefined ? 0.6 : flexstate
        //                        onPress={() => this.props.onClick.props.navigation.goBack()
        // 那边把指针传过来 啥都好说着类
        return (
            <View style={{
                flexDirection: 'row',
                alignItems: 'center',
                height: scaleSizeH(80),
                alignSelf: 'stretch',
                backgroundColor: "#ffffff"
            }}>
                <View style={{ flexDirection: "row", justifyContent: "space-between", flex: flexstate, alignItems: "center" }}>
                    <TouchableOpacity
                        activeOpacity={1.5}
                        onPress={() => this.props.onClick.props.navigation.goBack()
                        }
                    >
                        <Image style={{
                            width: scaleSizeW(35),
                            height: scaleSizeH(35),
                            marginLeft: scaleSizeW(15),
                        }} source={require("./../img/icon-navBack.png")} />


                    </TouchableOpacity>


                    <Text style={Styles.textstyle}>{this.props.name}</Text>
                </View>
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
