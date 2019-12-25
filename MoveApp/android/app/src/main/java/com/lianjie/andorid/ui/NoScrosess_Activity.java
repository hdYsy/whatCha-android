package com.lianjie.andorid.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.Adapter_NoScrRecycle;
import com.lianjie.andorid.adapter.time.widget.WheelView;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.DelatileMsg_bean;
import com.lianjie.andorid.bean.ErrorList;
import com.lianjie.andorid.bean.ImageBean;
import com.lianjie.andorid.bean.NoScrosessBean;
import com.lianjie.andorid.bean.NoscrossDateBean;
import com.lianjie.andorid.mvp.constract.IConstract_Reason;
import com.lianjie.andorid.mvp.presenteriml.Presenter_ReasonIml;
import com.lianjie.andorid.newsView.AutoLinefeedLayout;
import com.lianjie.andorid.utils.ActionUtils;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideCacheUtil;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.PhotoUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
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
/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/1
 * Time: 11:26
 */
public class NoScrosess_Activity extends BaseActivity implements View.OnClickListener, IConstract_Reason.IView {
    private static final int PAGE = 5;//列表的显示条目数量
    private ImageView callbaclk;
    private RecyclerView recycle_noscrosess;
    private RelativeLayout updatedata;
    private RelativeLayout updatetime;
    private EditText eddata;
    private Button btsubmit;
    private String id;
    private NoScrosessBean noScrosessBean;
    private WheelView main_wheelview;
    private TextView time_tx;
    private TextView data_tx;
    private List<String> date;
    private static int DatePAGE = 0; //0 为日期   1 为时间
    private List<String> time;
    private LinearLayout linear;
    private ImageView boxPageState;
    private List<String> pathList;
    private Context mContext;
    private ImageView addImage;
    List<String> list = new ArrayList<>();
    private String checkedPosition = "";
    /**
     * handler  worker 线程
     */
    Handler requestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GlobalInfoUtils.REQUEST_SUCCESS:
                    DialogUtils.hideWaiting(NoScrosess_Activity.this);
                    DialogUtils.showToast(NoScrosess_Activity.this, getResources().getString(R.string.com_work_detile_noscrosess_responseyes));
                    onNewImage(list, addImage);
                    break;
                case GlobalInfoUtils.REQUEST_FAIL:
                    DialogUtils.showToast(NoScrosess_Activity.this, getResources().getString(R.string.com_work_detile_noscrosess_responseno));
                    DialogUtils.hideWaiting(NoScrosess_Activity.this);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };
    private ImageBean imageBean;
    private LinearLayout linear_yaoqiu;


    @Override
    protected int onLayout() {
        mContext = this;
        return R.layout.activity_delatiles_noscrosess_layout;
    }

    @Override
    protected void onDrishData() {

    }


    @Override
    protected void initData() {
        Presenter_ReasonIml presenter_reasonIml = new Presenter_ReasonIml(this);
        Map<String, Object> stringObjectMap = ComsentUtils.onSendNoScrosess(id);
        presenter_reasonIml.setData(stringObjectMap);
    }

    @Override
    protected void initView() {
        callbaclk = findViewById(R.id.callbaclk);//返回键
        recycle_noscrosess = findViewById(R.id.recycle_noscrosess);        //遇到的问题RecycleView
        updatedata = findViewById(R.id.updatedata);        //要求——改日期
        updatetime = findViewById(R.id.updatetime);        //要求——改时间
        eddata = findViewById(R.id.eddata); //下方的输入框
        btsubmit = findViewById(R.id.btsubmit);        //提交
        data_tx = findViewById(R.id.date_tx);       //日期
        time_tx = findViewById(R.id.timer_tx);      //时间
        linear = findViewById(R.id.linear);            //ViewGrop
        addImage = findViewById(R.id.addImage);    //点击添加图片
        boxPageState = findViewById(R.id.boxPageState);        //ViewGrop-错误图片
        linear_yaoqiu = findViewById(R.id.linear_yaoqiu);//租户要求 是否显示
        callbaclk.setOnClickListener(this);
        updatedata.setOnClickListener(this);
        updatetime.setOnClickListener(this);
        btsubmit.setOnClickListener(this);
        addImage.setOnClickListener(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击关闭页面
            case R.id.callbaclk:
                if (!Check.isFastClick())
                    return;
                finish();
                break;
            //点击该日期
            case R.id.updatedata:
                if (!Check.isFastClick())
                    return;
                DatePAGE = 0;
                onData();
                break;
            case R.id.updatetime:
                if (!Check.isFastClick())
                    return;
                DatePAGE = 1;
                onData();
                break;
            //添加照片
            case R.id.addImage:
                if (!Check.isFastClick())
                    return;
                onAddImage();
                break;
            //提交
            case R.id.btsubmit:
                if (!Check.isFastClick())
                    return;
                //底部导航的确定按钮
                onLoadPage();
                break;
            case R.id.bottom_confirm:
                if (!Check.isFastClick())
                    return;
                onSubmit();
                break;

            //底部导航的取消按钮
            case R.id.bottom_cancel:
                if (!Check.isFastClick())
                    return;
                DialogUtils.closeLJdialog();
                break;


        }
    }

    /**
     * 点击  图片调起相册
     */
    private void onAddImage() {
        List<LocalMedia> localMedia = new ArrayList<>();
        PhotoUtils.openPhotos((Activity) mContext, 5, GlobalInfoUtils.REQUEST_CODE, localMedia);

//        Intent intent = new Intent(mContext, ImagesSelectorActivity.class);
//        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 5);
//        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
//        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
//        ArrayList<String> mResults = new ArrayList<>();
//        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
//        startActivityForResult(intent, GlobalInfoUtils.REQUEST_CODE);
    }

    /**
     * 点击提交 这个页面0
     */
    private void onLoadPage() {
//        ActionUtils.onStart(mContext, WorkOrderActvity.class);
        String reason = eddata.getText().toString();
        String date = data_tx.getText().toString();  //riqi  / 时间
        String time = time_tx.getText().toString();
        NoscrossDateBean bean = new NoscrossDateBean();

        bean.setDay(date);
        bean.setTime(time);
        String relaydes = GlobalInfoUtils.toJson(bean, 1);   //JSon 串
        List<String> lj = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("")) {
                lj.add(list.get(i));
            }
        }
        String imgs = GlobalInfoUtils.toJson(list, 1);   //JSon 串
//        if (checkedPosition.isEmpty()&& reason.isEmpty()) {
//
//        } else{
//            if(!checkedPosition.isEmpty() && !reason.isEmpty()){
//
//            }else if(!checkedPosition.isEmpty()){
//
//            }else{//reason 肯定有值
//
//            }
//        }


        Map<String, Object> map = ComsentUtils.onSendNoScrosessLoadPage(id, imgs, reason, checkedPosition, relaydes);
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        LogUtils.e(map.toString());
        Request request = new Request.Builder().url(URLConstant.URL_Load).post(requestBody.build()).tag(mContext).build();
        Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext,e.getMessage());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                DelatileMsg_bean delatileMsgBean = gson.fromJson(response.body().string(), DelatileMsg_bean.class);
                int code = delatileMsgBean.getCode();
                Looper.prepare();
                if (code == 1000) {
                    GlideCacheUtil.getInstance().clearImageAllCache(mContext);//清除图片所有缓存
                    Intent intent = new Intent(mContext, WorkListActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    intent.putExtra("msg",delatileMsgBean.getMsg());
                    startActivityForResult(intent,2003);  //2003  返回去  然后 把所有activity出战
//                        finish();
                } else if(code == 10012){
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp((Activity) mContext);
                    DialogUtils.showToast(mContext, delatileMsgBean.getMsg());
                }else{
                    DialogUtils.showToast(mContext, delatileMsgBean.getMsg());
                }
                Looper.loop();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        //            //就是 有 未读消息
        ComsentUtils.onMsgPush2(this);

    }

    /**
     * 添加照片
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == RESULT_OK) {
                List<LocalMedia> pathList = PictureSelector.obtainMultipleResult(data);
                DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_work_detile_noscrosess_response));
                onLoadImg(pathList);
            }
        }

    }

    private void onLoadImg(List<LocalMedia> pathList) {
        for (int i = 0; i < pathList.size(); i++) {
            onRequestIMG(pathList, i);
        }
    }

   private   String filePah_s;
    private void onRequestIMG(final List<LocalMedia> pathList, final int i) {
        filePah_s= BitmapUtil.compressImage(pathList.get(i).getPath());
        Runnable requestTask = new Runnable() {
            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                Map<String, Object> map = ComsentUtils.onSendNoScrosessLoad(id);
                try {

                    File file = new File(filePah_s);


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
                    Request request = new Request.Builder().url(URLConstant.Base_LOADIMG).post(requestBody.build()).tag(mContext).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    Gson gson = new Gson();
                    String string = execute.body().string();
                    if (string == null) {
                        string = "";
                        return;
                    }
                    imageBean = gson.fromJson(string, ImageBean.class);
                    String url;
                    if (imageBean != null) {
                        if (imageBean.getCode() == 1000) {
                            url = imageBean.getData().getUrl();
                            list.add(url);
                        } else if (imageBean.getCode() == 1001) {
                            DialogUtils.hideWaiting(NoScrosess_Activity.this);
                            msg.what = GlobalInfoUtils.REQUEST_FAIL;
                            return;
                        }else if(imageBean.getCode() == 10011){
                            DialogUtils.hideWaiting(mContext);
                            GlobalInfoUtils.clearApp((Activity) mContext);
                            DialogUtils.showToast(mContext, imageBean.getMsg());
                        }
                    } else {

                        url = "";
                        Looper.prepare();
                        DialogUtils.showToast(mContext, "上传错误!");
                        DialogUtils.hideWaiting(NoScrosess_Activity.this);
                        Looper.loop();
                        return;

                    }

                    if (i == pathList.size() - 1) {
                        if (execute.isSuccessful()) {
                            msg.what = GlobalInfoUtils.REQUEST_SUCCESS;
                            DialogUtils.hideWaiting(mContext);
                        } else {
                            DialogUtils.hideWaiting(mContext);
                            msg.what = GlobalInfoUtils.REQUEST_FAIL;
                        }
                    }
                } catch (IOException ex) {
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
     * 创建新View
     *
     * @param list
     * @param imgview
     */
    private void onNewImage(final List<String> list, final ImageView imgview) {
        final AutoLinefeedLayout parent = (AutoLinefeedLayout) imgview.getParent();
        int childCount = parent.getChildCount();
        View childAt = parent.getChildAt(childCount - 1);
        parent.removeAllViews();
        parent.addView(childAt);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).equals("")) {
            } else {
                final View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_noscrosses_addimg, null, false);
                ImageView img = inflate.findViewById(R.id.img);
                ImageView img_x = inflate.findViewById(R.id.img_x);

//            final int finalI = i;

//            img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    GlobalInfoUtils.onImageEnlarge(list, finalI, (Activity) mContext,GlobalInfoUtils.ImageDa);
////                    LogUtils.e("                    GlobalInfoUtils.onImageEnlarge(listImageDa, finalI, (Activity) mContext)        ;\n"+finalI);
//                }
//            });
//            img_x.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    list.remove(finalI);
//                    parent.removeView(inflate);
//                }
//            });
                GlideUtils.getInstance().setImages(img, list.get(i), 0, 0, false);
                parent.addView(inflate, 0);
            }
        }
        for (int i = 0; i < parent.getChildCount() - 1; i++) {
            final LinearLayout childAt1 = (LinearLayout) parent.getChildAt(i);
            FrameLayout childAt2 = (FrameLayout) childAt1.getChildAt(0);
            childAt1.setTag(i);
            childAt2.getChildAt(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    int tag = (int) childAt1.getTag();
                    List<String> list1 = new ArrayList<>();
                    list1.add(list.get(tag));
                    GlobalInfoUtils.onImageEnlarge(list1, 0, (Activity) mContext, 0);
                    list1 = null;
                }
            });
            childAt2.getChildAt(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    Object tag = childAt1.getTag();
                    list.set((int) tag, "");
                    parent.removeView(childAt1);
                }
            });
        }

    }

    /**
     * 日期和时间的点击赋值
     */
    private void onSubmit() {
        int currentPosition = main_wheelview.getCurrentPosition();
        if (DatePAGE == 0) {
            data_tx.setText(date.get(currentPosition));
        } else {
            time_tx.setText(time.get(currentPosition));
        }

        DialogUtils.closeLJdialog();
    }

    /**
     * 点击弹出日期选择器
     */
    private void onData() {
        if (noScrosessBean != null) {
            date = noScrosessBean.getData().getDate();
            time = noScrosessBean.getData().getTime();
            View inflate;

            if (DatePAGE == 0) {
                inflate = DialogUtils.onNoScrosessBottomDialog(this, date, PAGE);
            } else {
                inflate = DialogUtils.onNoScrosessBottomDialog(this, time, PAGE);
            }
            TextView bottom_confirm = inflate.findViewById(R.id.bottom_confirm);//确定按钮
            TextView bottom_cancel = inflate.findViewById(R.id.bottom_cancel);//取消按钮
            main_wheelview = inflate.findViewById(R.id.main_wheelview);            //列表设置
            bottom_confirm.setOnClickListener(this);
            bottom_cancel.setOnClickListener(this);

        } else {
            DialogUtils.showToast(NoScrosess_Activity.this, getResources().getString(R.string.com_error));
        }

    }


    @Override
    public <T> void showData(T t) {
        NoScrosessBean noScrosessBean = (NoScrosessBean) t;
        switch (noScrosessBean.getCode()) {
            case 1000:
                boxPageState.setVisibility(View.GONE);
                linear.setVisibility(View.VISIBLE);
                onReason(noScrosessBean);
                break;
            case 10011:
                boxPageState.setVisibility(View.VISIBLE);
                linear.setVisibility(View.GONE);
                break;
            case 10012:
                boxPageState.setVisibility(View.VISIBLE);
                linear.setVisibility(View.GONE);
                DialogUtils.hideWaiting(mContext);
                GlobalInfoUtils.clearApp((Activity) mContext);
                DialogUtils.showToast(mContext, noScrosessBean.getMsg());
                break;
        }

    }

    /**
     * 问题列表
     *
     * @param noScrosessBean
     */
    private void onReason(NoScrosessBean noScrosessBean) {
        this.noScrosessBean = noScrosessBean;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        List<String> reason = noScrosessBean.getData().getReason();
        final List<ErrorList> errorLists = new ArrayList<>();
        for (int i = 0; i < reason.size(); i++) {
            errorLists.add(new ErrorList(reason.get(i), false));
        }
        recycle_noscrosess.setLayoutManager(linearLayoutManager);
        final Adapter_NoScrRecycle adapter = new Adapter_NoScrRecycle(errorLists, this);
        recycle_noscrosess.setAdapter(adapter);

        adapter.setOnClik(new Adapter_NoScrRecycle.OnClik() {
            @Override
            public void onClickListener(View v, int position) {
                if (!Check.isFastClick())
                    return;
                for (int i = 0; i < errorLists.size(); i++) {
                    if (i == position) {
                        errorLists.get(i).setBr(true);//必须选择一个
                        errorLists.get(i).setBr(true);//必须选择一个
                    } else {
                        errorLists.get(i).setBr(false);
                    }
                }
                //resonid
                checkedPosition = (position + 1) + "";   // 涉及到小程序  需要 +1
                adapter.notifyDataSetChanged();


            }
        });


        /**
         * 租户要求 是否显示
         */
        int flag = noScrosessBean.getData().getFlag();
        if (flag == 1) { //1 都不显示
            linear_yaoqiu.setVisibility(View.GONE);
        } else if (flag == 2) {
            linear_yaoqiu.setVisibility(View.VISIBLE);
            updatedata.setVisibility(View.VISIBLE);
            updatetime.setVisibility(View.GONE);
        } else if (flag == 3) {
            linear_yaoqiu.setVisibility(View.VISIBLE);
            updatedata.setVisibility(View.GONE);
            updatetime.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(Throwable error) {
        boxPageState.setVisibility(View.VISIBLE);
        linear.setVisibility(View.GONE);
    }

    /**
     * 重写startActivity方法，禁用activity默认动画
     *
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(100, 100);
    }


}
