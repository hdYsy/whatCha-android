package com.lianjie.andorid.utils.internetutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lianjie.andorid.bean.MsgPushBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_MSGpush;
import com.lianjie.andorid.ui.DialogActivity;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.Sha1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/13
 * Time: 14:46
 */
public class ComsentUtils {
    /**
     * 必传参数 经纬度
     */
//    private static String latitude = "116.38";  //维度
//    private static String longitude = "39.9 ";//经度

    private static String resource = "1";  //后台识别 App 接口
    private static String latitude = GlobalInfoUtils.latitude + "";  //维度
    private static String longitude = GlobalInfoUtils.longitude + "";//经度


    /**
     * Login登陆
     *
     * @param map
     * @return
     */
    public static Map<String, Object> onSendLogin(Map<String, Object> map) {
        String s = "" + GlobalInfoUtils.getTime();
        map.put("timestamp", s);
        map.put("resource", resource);
        String s1 = "mobile=" + map.get("mobile") + "&password=" + map.get("password") + "&resource=" + resource + "&timestamp=" + s + URLConstant.Key;
        map.put("sign", Sha1.encode(s1));
        return map;
    }

    /**
     * Worker—News 消息列表
     *
     * @param map
     * @return
     */
    public static Map<String, Object> onSendNews(Map<String, Object> map) {
        String s = GlobalInfoUtils.getTime() + "";
        map.put("timestamp", s);
//        String s1 = "mobile=" + map.get("mobile") + "&password=" + map.get("password") + "&timestamp=" + s +"&secret="+URLConstant.Key;
//        map.put( "sign",Sha1.encode(s1));
        return map;
    }

    /**
     * Worker____ list   列表   (待上门、今明后) 1  2 3
     *
     * @return
     */
    public static Map<String, Object> onSendList(int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("lat", latitude);
        map.put("lng", longitude);
        map.put("optiontype", page + "");
        map.put("timestamp", "" + GlobalInfoUtils.getTime());
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("resource", resource);
        String sign = Sha1.encode(
                "lat=" + latitude +
                        "&lng=" + longitude + "&optiontype="
                        + page + "&resource=" + resource + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        String s = GlobalInfoUtils.getTime() + "";
        map.put("timestamp", s);
        return map;
    }

    /**
     * Worker____ comment 详情页面
     *
     * @return
     */
    public static Map<String, Object> onSendComment(int page) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("date", page + "");
        String sign = Sha1.encode(
                "date=" + page +
                        "&lat=" + latitude +
                        "&lng=" + longitude
                        + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * Worker____ list   列表   (待上门、今明后) 1  2 3
     *
     * @return
     */
    public static Map<String, Object> onSendDelatile(String id) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("id", id);
        String sign = Sha1.encode(
                "id=" + id +
                        "&lat=" + latitude +
                        "&lng=" + longitude
                        + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * No scrosusses
     *
     * @return上传 图片
     */
    public static Map<String, Object> onSendNoScrosessLoad(String id) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);

        map.put("oid", id);
        String sign = Sha1.encode(
                "lat=" + latitude +
                        "&lng=" + longitude + "&oid=" + id
                        + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * No scrosusses
     * <p>
     * 上传图片页 有示例图单张上传图片参数
     */
    public static Map<String, Object> onSendRequesID(String id, String classId, String imgIndex) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);

        map.put("oid", id);
        map.put("imgIndex", imgIndex);
        map.put("classId", classId);
        String sign = Sha1.encode(
                "classId=" + classId + "&imgIndex=" + imgIndex + "&lat=" + latitude +
                        "&lng=" + longitude + "&oid=" + id
                        + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * No scrosusses
     *
     * @return上传 图片
     */
    public static Map<String, Object> onSendNoScrosessLoad_Type(int Type) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);

        String sign = Sha1.encode(
                "lat=" + latitude +
                        "&lng=" + longitude +
                        "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&type=" + Type + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("type", Type);
        map.put("sign", sign);
        return map;
    }


    /**
     * No scrosusses
     *
     * @return 提交整个页面
     */
    public static Map<String, Object> onSendNoScrosessLoadPage(String... id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id[0]);
        map.put("images", id[1]);
        map.put("reason", id[2]);
        map.put("reasonid", id[3]);
        map.put("relaydes", id[4]);
        map.put("resource", resource);
        map.put("timestamp", GlobalInfoUtils.getTime());
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        String sign = Sha1.encode(
                "id=" + id[0] + "&images=" + id[1] + "&reason=" + id[2] + "&reasonid=" + id[3] + "&relaydes=" + id[4] + "&resource=" + resource + "&timestamp=" + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }


    /**
     * No scrosusses
     *
     * @return
     */
    public static Map<String, Object> onSendNoScrosess(String id) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("id", id);
        String sign = Sha1.encode(
                "id=" + id +
                        "&lat=" + latitude +
                        "&lng=" + longitude
                        + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }


    /**
     * pholderList   // 上传图片得第一个协议
     *
     * @return
     */
    public static Map<String, Object> onSendLoadImageBt(String id, int page) {

        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", GlobalInfoUtils.getTime());
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("resource", resource);
        map.put("id", id);
        String sign;
        if (page == 1) {
            map.put("stat", "1");
            sign = Sha1.encode(
                    "id=" + id
                            + "&resource=" + resource + "&stat=1"
                            + "&timestamp="
                            + GlobalInfoUtils.getTime() +
                            "&token=" + GlobalInfoUtils.Token + "&userid="
                            + GlobalInfoUtils.Userid + URLConstant.Key);
        } else {
            sign = Sha1.encode(
                    "id=" + id
                            + "&resource=" + resource
                            + "&timestamp="
                            + GlobalInfoUtils.getTime() +
                            "&token=" + GlobalInfoUtils.Token + "&userid="
                            + GlobalInfoUtils.Userid + URLConstant.Key);
        }
        map.put("sign", sign);


        return map;
    }


    /**
     * 评价此单 页面的list请求
     */
    public static Map<String, Object> onSendEvalusList() {
        Map<String, Object> map = new HashMap<>();
        String sign = Sha1.encode(
                "resource=" + resource +
                        "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("timestamp", GlobalInfoUtils.getTime());
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("resource", resource);
        map.put("sign", sign);

        return map;
    }


    /**
     * 提交审核页 提交页面  的所有数据
     */
    public static Map<String, Object> onSendSubmitAudit(String... params) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("id", params[0]);
        map.put("imagelist", params[1]);
        String sign = Sha1.encode(
                "id=" + params[0] + "&imagelist=" + params[1] + "&lat=" + latitude + "&lng=" + longitude + "&resource=" + resource +
                        "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);

        return map;
    }

    /**
     * 完成工单的提交按钮协议
     * text:safasfdasfd
     * address:sdfsadfasdfasf
     * images:["http://oss.lianjieshenghuo.com/public/uploads/2019/07/26/20190726184604_5d3ad9ecd8471.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNy0yNiAxODo0NjowNA==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100","http://oss.lianjieshenghuo.com/public/uploads/2019/07/26/20190726184606_5d3ad9ee83fe2.png?x-oss-process=image/watermark,g_se,y_50,size_20,text_MjAxOS0wNy0yNiAxODo0NjowNg==,color_ffffff,shadow_100/watermark,text_5pyd6Ziz5Yy65pS_5bqc5YaFLOWMl-S6rOW4guacnemYs-WMuue7n-iuoeWxgOmZhOi_kTDnsbM=,color_ffffff,size_20,shadow_100"]
     * lease:1
     * compound:2
     * id:1063398
     * tags:{"person":[2,5,8,3],"room":[11,14,17]}
     * orderRemark
     *
     * @param params
     */
    public static Map<String, Object> onSendLoadWorkOrder(List<String> params) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("address", params.get(0));
        map.put("id", params.get(1));
        map.put("images", params.get(2));
        map.put("tags", params.get(4));
        map.put("orderRemark", params.get(3));

        String sign = Sha1.encode(
                "address=" + params.get(0) + "&id=" + params.get(1) + "&images=" + params.get(2) + "&lat="
                        + latitude + "&lng=" + longitude + "&orderRemark=" + params.get(3) + "&resource=" + resource + "&tags=" + params.get(4) + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);

        return map;
    }

    /**
     * 多服务页的数据  UIR
     */
    public static Map<String, Object> onSendMulti_serviceList(String id) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("id", id);
        String sign = Sha1.encode(
                "id=" + id + "&lat=" + latitude + "&lng=" + longitude + "&resource=" + resource + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);

        return map;
    }

    /**
     * 消息推送
     */
    public static Map<String, Object> onsendMsg_PushList(String type) {
        Map<String, Object> map = new HashMap<>();


        map.put("type", type);
        map.put("resource", resource);
        map.put("timestamp", GlobalInfoUtils.getTime());
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);

        String sign = Sha1.encode(
                "resource=" + resource + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&type=" + type + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);

        return map;
    }

    public static void onMsgPush2(final Activity mContext) {
        Map<String, Object> stringObjectMap = ComsentUtils.onsendMsg_PushList(GlobalInfoUtils.TYPE + "");
        Presenter_MSGpush presenterMsGpush = new Presenter_MSGpush(new IConstract_Stract.IView() {
            @Override
            public <T> void showData(T t) {
                MsgPushBean msgPushBean = (MsgPushBean) t;
                int code = msgPushBean.getCode();
                if (code == 1000) {
                    LogUtils.e(t.toString());
                    ComsentUtils.onIntentDialog(mContext, 2);
                } else if (code == 1001) {
                    //请求失败
                    LogUtils.e(msgPushBean.getMsg());
                } else if (code == 9901) {

                    //没有推送消息
//                    DialogUtils.showToast(mContext, "123324234");
                } else if (code == 10011) {
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp((Activity) mContext);
                    DialogUtils.showToast(mContext, msgPushBean.getMsg());
                }

            }

            @Override
            public void showError(Throwable error) {

            }
        });
        presenterMsGpush.setData(stringObjectMap);

    }


    /**
     * 消息 推送 tyPE 为2 的方法
     */


    public static void onIntentDialog(Context context, int i) {
        //消息
        Intent intent = new Intent(context, DialogActivity.class);
        intent.putExtra("TYPE", i + "");
        context.startActivity(intent);
    }


    /**
     * 详情页 ： 获取密码接口
     * <p>
     * id:62onSendLoadImageBt
     * timestamp:1563765136
     * token:j3FLEueMWwiGOHcmyoZkBtz1Jfvd6C0h
     * userid:99999
     * sign:0fc481cbdae0752c3dcd192d00eafd273977ea58
     * /order/getpass
     */
    public static Map<String, Object> onSendDelatilePassword(String id) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("id", id);
        String sign = Sha1.encode(
                "id=" + id + "&lat=" + latitude + "&lng=" + longitude + "&resource=" + resource + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);

        return map;
    }

    /**
     * 详情页面：人脸识别
     */
    public static Map<String, Object> onSendDelatileFace(String id, String faceid) {

        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("id", id);
        map.put("faceid", faceid);
        String sign = Sha1.encode(
                "faceid=" + faceid + "&id=" + id + "&lat=" + latitude + "&lng=" + longitude + "&resource=" + resource + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * 获取密码
     * getpass
     * id:40
     * timestamp:1564394660
     * token:9JZtH5XALVqYDTakBWnNyCoejSRibPGF
     * userid:99999
     * sign:f22aa602e03cf1b411b4ce78a7172a9fed3aac2b
     */
    public static <T> Map<String, Object> onSendgetPasssword(String id) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("timestamp", GlobalInfoUtils.getTime());
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("resource", resource);
        String sign = Sha1.encode(
                "id=" + id + "&resource=" + resource + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);

        return map;
    }


    /**
     * id:43    工单ID
     * phone:18201342202   当前手机号
     * timestamp:1564553397
     * token:gB9IPNo5dElpjt4h3rzWTMwbn2VF1xya
     * userid:100397
     * sign:9f780927c9b6e912a8e435fc790eb64282603fec
     * 获取隐私号
     */
    public static Map<String, Object> onsendDelatileGetPhone(String... id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id[0]);
        map.put("phone", id[1]);
        map.put("timestamp", GlobalInfoUtils.getTime() + "");
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("resource", resource);

        String sign = Sha1.encode(
                "id=" + id[0] + "&phone=" + id[1] + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }


    /**
     * No scrosusses
     *
     * @return上传 图片
     */
    public static <T> Map<String, Object> onSendMultserLoadImage(String id, String t) {
        Map<String, Object> map = new HashMap<>();
        LogUtils.e(t);
        map.put("resource", resource);
        map.put("timestamp", GlobalInfoUtils.getTime());
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("id", id);
        map.put("imagelist", t);
        LogUtils.e(t);
        String sign = Sha1.encode(
                "id=" + id + "&imagelist=" + t
                        + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);

        return map;
    }


    /**
     * No scrosusses
     *
     * @return上传头像
     */
    public static Map<String, Object> onSendUserImage() {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", GlobalInfoUtils.getTime() + "");
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("resource", resource);
        String sign = Sha1.encode(
                "resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }


    /**
     * 基础方法
     *
     * @param map
     */
    private static void onSend(Map<String, Object> map) {
        map.put("resource", resource);
        map.put("lat", latitude);
        map.put("lng", longitude);
        map.put("timestamp", GlobalInfoUtils.getTime());
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
    }

    /**
     * 保洁员地址的提交接口
     *
     * @param
     */
    public static Map<String, Object> onSendUpdataAddress(String address) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("address", address);
        String sign = Sha1.encode(
                "address=" + address + "&lat=" + latitude + "&lng=" + longitude + "&resource=" + resource + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }


    /**
     * 列表强提醒
     *
     * @param id
     * @return
     */
    public static Map<String, Object> onSend_List_strongreminder(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("resource", resource);
        String sign = Sha1.encode(
                "id=" + id + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);


        map.put("timestamp", GlobalInfoUtils.getTime() + "");
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("resource", resource);
        map.put("sign", sign);
        map.put("id", id);


        return map;
    }

    /**
     * 工单提醒
     *
     * @param
     * @return
     */
    public static Map<String, Object> onSendJob_reminder(String orderId, String eventId) {
        Map<String, Object> map = new HashMap<>();


        String sign = Sha1.encode(
                "eventId=" + eventId + "&orderId=" + orderId + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);

        map.put("resource", resource);
        map.put("orderId", orderId);

        map.put("eventId", eventId);
        map.put("timestamp", GlobalInfoUtils.getTime() + "");
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);
        map.put("resource", resource);
        map.put("sign", sign);


        return map;
    }


    /**
     * 批量上chuan 的 参数
     */
    public static Map<String, Object> onSendLodSizeImage(String id, int imgNum) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);

        map.put("oid", id);
        map.put("imgNum", imgNum);//图片总数

        String sign = Sha1.encode(
                "imgNum=" + imgNum + "&lat=" + latitude +
                        "&lng=" + longitude + "&oid=" + id
                        + "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * 培训资料 列表
     *
     * @return
     */
    public static Map<String, Object> onGetTrainList() {
        Map<String, Object> map = new HashMap<>();
        onSend(map);


        String sign = Sha1.encode(
                "lat=" + latitude +
                        "&lng=" + longitude +
                        "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * 培训资料 详情
     *
     * @return
     */
    public static Map<String, Object> onGetTrainDelatils(String courseId) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("courseId", courseId);

        String sign = Sha1.encode(
                "courseId=" + courseId +
                        "&lat=" + latitude +
                        "&lng=" + longitude +
                        "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * 培训资料 详情返回
     *
     * @param lon
     * @param readStatus userid	String
     * @return
     */
    public static Map<String, Object> onGetTrainDelatilsType(int lon, int readStatus, String courseId) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        map.put("courseId", courseId);
        map.put("fileType", (GlobalInfoUtils.page + 1));
        map.put("long", lon);
        map.put("readStatus", readStatus);

        String sign = Sha1.encode(
                "courseId=" + courseId +
                        "&fileType=" + (GlobalInfoUtils.page + 1) +
                        "&lat=" + latitude +
                        "&lng=" + longitude +
                        "&long=" + lon +
                        "&readStatus=" + readStatus +
                        "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        return map;
    }

    /**
     * LBS 的list 列表数据
     *
     * @return
     */
    public static Map<String, Object> onSend_LBS_list() {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        String sign = Sha1.encode(
                "lat=" + latitude +
                        "&lng=" + longitude +
                        "&mode=" + GlobalInfoUtils.LBS_page +
                        "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        map.put("mode", GlobalInfoUtils.LBS_page);
        return map;
    }
    /**
     * LBS 的列表详情数据
     *
     * @return
     */
    public static Map<String, Object> onSend_LBS_list(String id) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        String sign = Sha1.encode(
                "id="+id+"&lat=" + latitude +
                        "&lng=" + longitude +
                        "&mode=" + GlobalInfoUtils.LBS_page +
                        "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        map.put("id", id);
        map.put("mode", GlobalInfoUtils.LBS_page);
        return map;
    }
    /**
     * LBS 的列表详情数据
     *
     * @return
     */
    public static Map<String, Object> onSendSavePhoto(String id,String imagelist) {
        Map<String, Object> map = new HashMap<>();
        onSend(map);
        String sign = Sha1.encode(
                "id="+id+"&imagelist="+imagelist+"&lat=" + latitude +
                        "&lng=" + longitude +
                        "&resource=" + resource
                        + "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);
        map.put("id", id);
        map.put("imagelist", imagelist);
        return map;
    }

}
