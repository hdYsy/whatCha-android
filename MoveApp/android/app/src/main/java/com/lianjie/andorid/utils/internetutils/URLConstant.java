package com.lianjie.andorid.utils.internetutils;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/10
 * Time: 18:45
 */
public interface URLConstant {
//    String Base_URL = "https://bjdxcx2018.lianjieshenghuo.com/";
    String Base_URL = "https://app.lianjieshenghuo.com/";

    //密钥
    String Key = "&secret=pR(k10qlx$T*V)@u";

    //登录页面
    String URL_LOGIN = "login/";

    //  工单列表
    String URL_WORKLIST = "/order/list/";

    // 消息推送列表
    String URL_MSGPUSH = "messagePush/";

    //已完成列表
    String URL_COMPlete= "/order/complete/";

    //详情页
    String URL_INFO= "/order/info/";

    //此单无法完成
    String URL_REASON= "/order/reason";

    //点击上传图片的判断协议
    String URL_photo= "/order/photo";

    //判断过后的请求数据
    String URL_photoList= "/photolist";

    //提交
    String URL_Load= Base_URL+"cannot/";

    //小程序/批量上传
    String Base_LOADIMG= "https://picture.lianjieshenghuo.com/upload";

    //详情页面的开始服务 按钮
    String Base_startServer=Base_URL+"user/face";

    //获取密码
    String Base_getPASS= "/order/getpass";

    //评价此单里面得list
    String URL_IMPRESS= "/impress/list";

    //提交审核页的主URL
    String URL_LOADSHENHE= Base_URL+"submitorder";

    //提交审核页的第二个URL
    String URL_LOADSHENHENON= Base_URL+"submit";

    //多服务页的接口
    String URL_MULTSERVICE= "multiServiceList";

    //评价此单的提交按钮
    String URL_impression_submits= Base_URL+"impression/submits";


    //多服务页的 提交
    String URL_multiFormSubmit= "multiFormSubmit";

    //虚拟号
    String URL_ByPhone= "getYinSiHaoByPhone";

    //上传头像
    String URL_UserLoad= "https://picture.lianjieshenghuo.com/user/upload";

    //列表强提醒
    String URL_chek= Base_URL+"/order/check";

    //工单提醒
    String URL_GetRemiuderInfo= Base_URL+"/getReminderInfo";

    //是否更新apk
    String isUpdateApp= "/isUpdateApp";

    //地图-保洁员地址-提交接口
    String UpadateCleanAddress= Base_URL+"user/updateCleanAddress";

    //上传证件的接口
    String LoadPhotoID= "https://picture.lianjieshenghuo.com/uploadCard";

    //请求培训资料列表数据
    String getTrainList= "/getTrainList";


    //请求培训资料详情
    String getTrainDetail= "/getTrainDetail";

    //Train - 写入保洁员学习培训资料记录
    String addTrainProgress= Base_URL+"/addTrainProgress";

    //LBS-工单顺序
    String  mapOrderSort =   Base_URL+"/mapOrderSort";

    //LBS-工单详情
    String  mapOrderInfo =   Base_URL+"/mapOrderInfo";

    //开荒工单(蛋壳)-做单前的提交接口.
    String  savePhotoBeforeWork =   Base_URL+"/savePhotoBeforeWork";

}
