import {
    NativeModules
} from 'react-native';
//Dialog
export function onLoading(Tab) {
    NativeModules.IntentModule.AdbLoading(Tab)
}
//Toast
export function onToast(msg) {
    NativeModules.IntentModule.AdbToast(msg)
}

//推送消息
export function onMsg() {
    NativeModules.IntentModule.AdbMsg()
}
//放大图片
export function onImgEnlarge(url, position) {
    NativeModules.IntentModule.AdbImage(url, position)
}

//退出登录
export function onloadout() {
    NativeModules.IntentModule.AdbLoadout()
}
//关闭
export function onFinish() {
    NativeModules.IntentModule.AdbFinish()
}
//时间选择器三级联动
export function onData(datestr) {
    NativeModules.IntentModule.AdbDate(datestr)
}
//工单跳转到详情页
export function onStartDelatiles(id, oid) {
    NativeModules.IntentModule.AdbStartDelatiles(id, oid)
}
//工单跳转到详情页
export function onsStartAppeal() {
    NativeModules.IntentModule.onsStartAppeal()
}
//关闭
export function adbClose() {
    NativeModules.IntentModule.adbClose()
}
//保洁员住址
export function AdbMap(str,str1) {
    NativeModules.IntentModule.AdbMap(str,str1)
}

//培训资料
export function TrainingVideo() {
    NativeModules.IntentModule.TrainingVideo()
}
//back到main
export function AdbMain() {
    NativeModules.IntentModule.AdbMain()
}
