package com.lianjie.andorid.rnui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.ImageBean;
import com.lianjie.andorid.bean.Photos_ID_bean;
import com.lianjie.andorid.ui.MainActivity;
import com.lianjie.andorid.ui.SubmitauditActivity;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.Gps_Utils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.PhotoUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class RNActivity extends ReactActivity {


    private static com.facebook.react.bridge.Callback result;
    private Context context;
    private static String mId;
    private static int  type;
    List urlString = new ArrayList<>();
    private ArrayList<String> pathList;
    String url;
    private String msg_string;
    private String filePah_s;
    private String string;

    public static void setID(String ID, com.facebook.react.bridge.Callback result) {
        RNActivity.mId = ID;
        RNActivity.result = result;
    }

    public static void setType(int  type, com.facebook.react.bridge.Callback result) {
        RNActivity.type = type;
        RNActivity.result = result;
    }
    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */

    @Override
    protected String getMainComponentName() {
        return GlobalInfoUtils.Page_RN;
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected ReactRootView createRootView() {

                return new RNGestureHandlerEnabledRootView(RNActivity.this);
//                return RNActivity.class;
            }
        };
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 1 && type == 999) {
                DialogUtils.showWaiting(this, context.getResources().getString(R.string.com_login_tips));
                onLoadImage(data);
            } else if (requestCode == 2) {//上传普通图片
                DialogUtils.showWaiting(this, context.getResources().getString(R.string.com_login_tips));
                onRequestIMG(data);
            }else if(requestCode == 3){//上传证件照片
                DialogUtils.showWaiting(this, context.getResources().getString(R.string.com_login_tips));
                onRequestPhotoID(data);
            }
        }

    }

    /**
     * 上传证件照片
     * @param data
     */
    private void onRequestPhotoID(final Intent data) {
        Runnable requestTask = new Runnable() {
            private ArrayList urlString;
            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                // 图片、视频、音频选择结果回调
                final List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                LocalMedia localMedia = selectList.get(0);
                String path_file = localMedia.getPath();
                filePah_s = BitmapUtil.compressImage(path_file);
                Map<String, Object> map = ComsentUtils.onSendNoScrosessLoad_Type(type);
                File file = new File(filePah_s);
                try {
                    OkHttpClient client = new OkHttpClient();
                    // form 表单形式上传
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (file != null) {
                        // MediaType.parse() 里面是上传的文件类型。
                        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                        // 参数分别为， 请求key ，文件名称 ， RequestBody
                        requestBody.addFormDataPart("file", file.getName(), body);
                    }
                    if (map != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : map.entrySet()) {
                            requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }
                    Request request = new Request.Builder().url(URLConstant.LoadPhotoID).post(requestBody.build()).tag(context).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    string = execute.body().string();
                    msg.what = 111;
                } catch (IOException ex) {
                    msg.what = 112;
                } finally {
                    msg.sendToTarget();
                }
            }
        };

        Thread requestThread = new Thread(requestTask);
        requestThread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

    }

    /**
     * 上传头像
     */
    private void onLoadImage(Intent data) {
        // 图片、视频、音频选择结果回调
        final List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
        LocalMedia localMedia = selectList.get(0);
        final String path = localMedia.getPath();
        Map<String, Object> map = ComsentUtils.onSendUserImage();
        File file = new File(path);
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", file.getName(), body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }

        Request request = new Request.Builder().url(URLConstant.URL_UserLoad).post(requestBody.build()).tag(getApplicationContext()).build();
        Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                DialogUtils.hideWaiting(getApplicationContext());
                Looper.prepare();
                if (Gps_Utils.getInstance().isNetworkAvailable(context)) {
                    DialogUtils.showToast(context, "网络错误");
                } else {
                    DialogUtils.showToast(context, context.getResources().getString(R.string.com_login_tips));
                }
                result.invoke(999);

                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                DialogUtils.hideWaiting(getApplicationContext());
                Gson gson = new Gson();
                ImageBean imageBean = gson.fromJson(response.body().string(), ImageBean.class);
                Looper.prepare();
                DialogUtils.showToast(context, imageBean.getMsg());
                result.invoke(1000);
                Looper.loop();
            }
        });
    }


    /**
     * 上传图片得方法
     *
     * @param data
     */
    private void onRequestIMG(final Intent data) {
        Runnable requestTask = new Runnable() {
            private ArrayList urlString;
            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                // 图片、视频、音频选择结果回调
                final List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                LocalMedia localMedia = selectList.get(0);
                String path_file = localMedia.getPath();
                filePah_s = BitmapUtil.compressImage(path_file);
                Intent intent = getIntent();
                //做出了  如果 是""  那么就从intent 中去取
                mId = mId.equals("") ? intent.getStringExtra("id") : mId;
                Map<String, Object> map = ComsentUtils.onSendNoScrosessLoad(mId);
                File file = new File(filePah_s);
                try {
                    OkHttpClient client = new OkHttpClient();
                    // form 表单形式上传
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (file != null) {
                        // MediaType.parse() 里面是上传的文件类型。
                        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                        // 参数分别为， 请求key ，文件名称 ， RequestBody
                        requestBody.addFormDataPart("file", file.getName(), body);
                    }
                    if (map != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : map.entrySet()) {
                            requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }
                    Request request = new Request.Builder().url(URLConstant.Base_LOADIMG).post(requestBody.build()).tag(context).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    Gson gson = new Gson();
                    ImageBean imageBean = gson.fromJson(execute.body().string(), ImageBean.class);

                    if (imageBean != null) {
                        if (imageBean.getCode() == 1000) {
                            url = imageBean.getData().getUrl();
                            msg_string = imageBean.getMsg();
                            msg.what = GlobalInfoUtils.REQUEST_SUCCESS;

                        } else {
                            msg.what = GlobalInfoUtils.REQUEST_FAIL;
                            url = imageBean.getCode() + "";
                            msg_string = imageBean.getMsg() + "";
                            return;
                        }
                    } else {
                        url = "";
                        msg_string = "";
                    }


                } catch (IOException ex) {
                    url = "" + ex.getMessage();
                    msg.what = GlobalInfoUtils.REQUEST_FAIL;
                } finally {
                    msg.sendToTarget();
                }
            }
        };

        Thread requestThread = new Thread(requestTask);
        requestThread.start();
    }


    /**
     * 上传图片
     */
    @SuppressLint("HandlerLeak")
    final Handler requestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GlobalInfoUtils.REQUEST_SUCCESS:
                    result.invoke(url);
                    DialogUtils.hideWaiting(context);

                    break;
                case GlobalInfoUtils.REQUEST_FAIL:
                    DialogUtils.hideWaiting(context);
                    DialogUtils.showToast(context, "上传失败请重试...");

                    break;
                case 111:
                    DialogUtils.hideWaiting(context);
                    result.invoke(string);
                    break;
                case 112:
                    DialogUtils.hideWaiting(context);
                    DialogUtils.showToast(context, "上传失败请重试...");
                    result.invoke(-1);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };
}







