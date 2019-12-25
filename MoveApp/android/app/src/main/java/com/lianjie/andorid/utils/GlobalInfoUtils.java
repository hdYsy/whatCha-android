package com.lianjie.andorid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.ReminBean;
import com.lianjie.andorid.bean.SderBean;
import com.lianjie.andorid.ui.DelatileActivity;
import com.lianjie.andorid.ui.Dialog_Img_activity;
import com.lianjie.andorid.ui.ImageDaActivity;
import com.lianjie.andorid.ui.LoginActivity;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static java.lang.String.valueOf;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/10
 * Time: 18:00
 */
public class GlobalInfoUtils {

    public static  long SECONDS = 6;
    public static int LBS_page = 0;//LBS的出行方式  0步行、1骑行、2驾车
    public static GlobalInfoUtils shalUtils;

    public static int PAGE_OPTION = 1;//1：今天待上门  2.明天待上门  3. 后天待上门
    public static int TYPE = 2;//消息推送  进来首先 默认推送   默认 推送是2
    public static int TYPE11 = 1;//消息推送   点击 是请求1

    public static String GaoDe = "http://daohang.amap.com/index.php?id=201&CustomID=C021100017443";//  进入默认浏览器  下载高de浏览器
    public static String Tencent = "https://acj4.pc6.com/pc6_soure/2019-12/com.tencent.map_662.apk";//  进入默认浏览器  下载腾讯
    public static int DankeChannal = 1;//
    public static int qudaoChannal = 2;//
    public static int CduanChannal = 3;//
    //蛋壳
    public static int work_sz = 1;
    public static int work_tzh = 2;
    public static int work_kh = 3;
    public static int work_fhsd = 8;
    public static int work_ydyx = 13;
    public static int work_rc = 4;
    public static int work_jj = 5;
    public static int work_wxh = 7;

    //C端
    public static int c_work_rc = 4;
    public static int c_work_cbl = 101;


    //登陆得face
    public static String FACE;
    //此ID
    public static String Userid;
    //手机号
    public static String Phone;
    //用户实名
    public static String Realname;
    //此账号的状态   0  1  暂时没用
    public static String Status;
    //用户头像
    public static String Thumb;
    //Token
    public static String Token;
    //用户昵称
    public static String Username;
    //    用户密码
    public static String Password;
    //   手机宽度
    public static int screenWidth;
    //   手机高度
    public static int screenHeight;
    public static String tools_date;

    public static double latitude = 0;
    public static double longitude = 0;
    public static String multServer_id;//上传图片页的工单id 。用于 多服务页传值使用
    public static String apkUrl = null;
    public static String apkName = "lianjie.apk";
    public static int mfirstVisibleItemPosition = 0;
    public static int br_contactInfosize = 0;
    private static String filename;
    private static Bitmap bitmap;
    public static List<PoiItem> pois;
    public static int page;
    public static String error_lbs  = "系统错误，请重试！";


    public static int get80dp(Context context) {
        return PhoneUtils.dip2px(context, 80); //缩略图
    }

    //设置相机的requestcode
    public static final int REQUEST_CODE = 1;


    //异步handler 请求成功以及失败
    public static final int REQUEST_SUCCESS = 0;
    public static final int REQUEST_FAIL = 1;


    public static final int Camera_ReSultCode = 5;//人脸识别的resultcode
    public static final int Camera_ReQesttCode = 6;//人脸识别的resultcode
    public static int day = 0;
    public static int ServerState = 0;
    public static boolean resumed = false;


    public static String Page_RN = "My"; //我的 ——RN

    public static int RNCode = 1123;

    public static int ImageDa_Sub = 0;
    public static int ImageDa = 1;

    private GlobalInfoUtils() {
    }

    /**
     * 当前时间
     *
     * @return
     */
    public static long getTime() {
        return (Date.parse(String.valueOf(new Date()))) / 1000;
    }

    /**
     * 将实体类转换成json字符串对象            注意此方法需要第三方gson  jar包
     *
     * @param obj 对象
     * @return map
     */
    public static String toJson(Object obj, int method) {
        // TODO Auto-generated method stub
        if (method == 1) {

//字段是首字母小写，其余单词首字母大写
            Gson gson = new Gson();
            String obj2 = gson.toJson(obj);
            return obj2;
        } else if (method == 2) {

// FieldNamingPolicy.LOWER_CASE_WITH_DASHES    全部转换为小写，并用空格或者下划线分隔

            //FieldNamingPolicy.UPPER_CAMEL_CASE    所以单词首字母大写
            Gson gson2 = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            String obj2 = gson2.toJson(obj);
            return obj2;
        }
        return "";
    }

    /**
     * 所有的都跳转到 大图activity
     *
     * @param listImageDa shuju
     * @param position    xiabiao
     * @param activity    跳转的
     *                    type    0 是大图
     */

    public static void onImageEnlarge(List<String> listImageDa, int position, Activity activity, int type) {

        Intent intent = new Intent(activity, ImageDaActivity.class);
        intent.putStringArrayListExtra("list", (ArrayList<String>) listImageDa);
        intent.putExtra("position", position);
        intent.putExtra("Type", type + "");

        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);


    }

    /**
     * 跳转至   设置页面
     *
     * @param mActivity
     */
    public static void toJurisdiction(Activity mActivity) {
        Uri packageURI = Uri.parse("package:" + "com.lianjie.andorid");
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        mActivity.startActivity(intent);
    }

    public static Calendar dataToCalendar(String str) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = null;
        try {
            Date parse = df.parse(str);
            calendar = Calendar.getInstance();
            calendar.setTime(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        {
            if (calendar == null) {
                return Calendar.getInstance();
            }
        }

        return calendar;
    }

    /**
     * 10011 退出登录
     *
     * @param activity
     */
    public static void clearApp(Activity activity) {
        SPUtil.clearSP();
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 列表强提醒
     */
    public static void List_strongreminder(final Activity mContext, final String id, final String doing, int firstVisibleItemPosition) {
        mfirstVisibleItemPosition = firstVisibleItemPosition;
        Map<String, Object> map = ComsentUtils.onSend_List_strongreminder(id);
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(URLConstant.URL_chek).post(requestBody.build()).tag(mContext).build();
        Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //todo  错误 应该做什么  什么都不做 等着
                Looper.prepare();
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext, e.getMessage());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                SderBean sderBean = gson.fromJson(response.body().string(), SderBean.class);
                int code = sderBean.getCode();
                Looper.prepare();
                DialogUtils.hideWaiting(mContext);

                if (code == 1000) {

                    Intent intent = new Intent(mContext, DelatileActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("doing", doing);
                    mContext.startActivityForResult(intent, 1);
                } else if (code == 9902) {
                    if (sderBean.getData().getFlag() == 1) {
                        View view = DialogUtils.showQiang_storge(mContext, "提示", sderBean.getMsg(), "好的");
                        TextView tvNameview = view.findViewById(R.id.it_tx);      //提示文字
                        tvNameview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DialogUtils.closeLJdialog();
                            }
                        });
                    } else if (sderBean.getData().getFlag() == 2) {
                        View view = DialogUtils.showQiang_storge(mContext, "温馨提示", sderBean.getMsg(), "知道了");

                        TextView tvNameview = view.findViewById(R.id.it_tx);      //提示文字
                        tvNameview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DialogUtils.closeLJdialog();
                                Intent intent = new Intent(mContext, DelatileActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("doing", doing);
                                mContext.startActivityForResult(intent, 1);
                            }
                        });

                    }
                } else if (code == 1001) {
                    DialogUtils.showToast(mContext, sderBean.getMsg());
                }
                Looper.loop();

            }
        });


    }

    /**
     * 工单提醒
     */
    public static void List_Jobreminder(final Activity mContext, final String orderId, final String eventId) {
        Map<String, Object> map = ComsentUtils.onSendJob_reminder(orderId, eventId);
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(URLConstant.URL_GetRemiuderInfo).post(requestBody.build()).tag(mContext).build();
        Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {


            private int parseInt;

            @Override
            public void onFailure(Call call, IOException e) {
                //todo  错误 应该做什么  什么都不做 等着
                Looper.prepare();
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext, e.getMessage());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();
                ReminBean reminBean = gson.fromJson(response.body().string(), ReminBean.class);
                int code = reminBean.getCode();
                Looper.prepare();
                if (code == 1000) {
                    ReminBean.DataBean data = reminBean.getData();
                    String titleType = data.getTitleType(); //title
                    String content = data.getContent();     //描述文字
                    final String second = data.getSecond();     //时间秒钟
                    String url = data.getUrl();     //时间秒钟
                    final Handler handler;
//                        isstatus  == 1  就开始
                    if (data.getIsStatus().equals("1")) {
                        if (data.getType().equals("text")) {
                            if (data.getTipsType() == 1) {//手动关闭 dialog
                                //手dong开关
                                View view = DialogUtils.showQiang_storge(mContext, titleType, content, "知道了");
                                View viewById = view.findViewById(R.id.it_tx);
                                viewById.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DialogUtils.closeLJdialog();
                                    }
                                });
                            } else if (data.getTipsType() == 2) {  //自动按照秒数 关闭
                                View view = DialogUtils.showQiang_storge(mContext, titleType, content, second + "s后自动关闭");
                                final TextView tx_second = view.findViewById(R.id.it_tx);
                                tx_second.setTextColor(mContext.getResources().getColor(R.color.color_move_4C90F3));
                                tx_second.setOnClickListener(null);
                                parseInt = Integer.parseInt(second);
                                handler = new Handler();
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        parseInt--;
                                        if (parseInt > 0) {
                                            tx_second.setText(parseInt + "s后自动关闭");
                                        } else if (parseInt == 0) {
                                            DialogUtils.closeLJdialog();
                                        }
                                        handler.postDelayed(this, 1000);

                                    }
                                };
                                handler.postDelayed(runnable, 1000);
                            }
                        } else if (data.getType().equals("img")) {
                            if (eventId.equals("3")) { //上传图片页 刚进来第一步
                                Intent intent = new Intent(mContext, Dialog_Img_activity.class);
                                intent.putExtra("url", url);
                                intent.putExtra("second", second);
                                mContext.startActivityForResult(intent, 903);
                            } else {
                                showQiang_storgeImage(mContext, url, second);
                            }
                        }
                    }
                }
                Looper.loop();

            }


        });

    }

    /**
     * 跳转到 大图
     *
     * @param mContext
     * @param url
     * @param second
     */
    public static void showQiang_storgeImage(Activity mContext, String url, String second) {
        Intent intent = new Intent(mContext, Dialog_Img_activity.class);
        intent.putExtra("url", url);
        intent.putExtra("second", second);
        mContext.startActivityForResult(intent, 9901);
    }

    /**
     * View转为Btmap方法
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        return bitmap;

    }

}
