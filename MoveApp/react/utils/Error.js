import React, { Component } from 'react';
import { View, Text, Image } from 'react-native';

export default class Error extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return (
            <View style={{ flex: 1, backgroundColor: "#f4f4f4", }}>
                <View style={{ flex: 0.7, alignItems: "center", justifyContent: "center" }}>
                    <Image style={{ width: 160, height: 160, }} source={require('../img/listNone.png')}></Image>
                </View>
            </View>
        );
    }
}
