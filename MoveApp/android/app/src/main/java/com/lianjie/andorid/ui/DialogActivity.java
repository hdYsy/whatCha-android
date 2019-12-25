package com.lianjie.andorid.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.Adapter_MsgPushList;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.LoadScrosses;
import com.lianjie.andorid.bean.MsgPushBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_MSGpush;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.PhoneUtils;
import com.lianjie.andorid.utils.Sha1;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.luck.picture.lib.widget.longimage.ImageSource;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/19
 * Time: 10:49
 */
public class DialogActivity extends BaseActivity implements IConstract_Stract.IView {

    private static final int ID_USER = 0;
    private DialogActivity mContext;
    private ImageView img;
    private LinearLayout msgitem;
    private Button bt_load;
    private TextView tx;
    private ImageView centerdiolog_imgfalse;
    private RecyclerView centerdiolog_rec;
    private int Type = 1;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ID_USER:
                    LoadScrosses loadScrosses = (LoadScrosses) msg.obj;
                    onResultList(loadScrosses);//成功后 返回 然后 请求一下 这个列表里面的东西

                    break;
            }
        }
    };
    private MsgPushBean.DataBeanX data;
    private LinearLayout linear_dingjigrop;
    private boolean state_video = true;

    @Override
    protected int onLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void onDrishData() {
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mContext = this;
        //详情顶部的灯光图标
        img = findViewById(R.id.img);
        //详情ye
        msgitem = findViewById(R.id.msgitem);
        linear_dingjigrop = findViewById(R.id.linear_dingjigrop);


        // 标题
        tx = findViewById(R.id.tx);
        //列表才会有的X按钮
        centerdiolog_imgfalse = findViewById(R.id.centerdiolog_imgfalse);
        //列表Recycle
        centerdiolog_rec = findViewById(R.id.centerdiolog_rec);

        //点击 X  关闭 Activity
        centerdiolog_imgfalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                finish();
            }
        });
        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");

        //刚进来的时候 请求  消息推送的消息
        Presenter_MSGpush presenterMsGpush = new Presenter_MSGpush(this);
        Map<String, Object> stringObjectMap = ComsentUtils.onsendMsg_PushList(type + "");
        Type = Integer.parseInt(type);
        presenterMsGpush.setData(stringObjectMap);
    }

    /**
     * 成功
     *
     * @param t
     * @param <T>
     */
    @Override
    public <T> void showData(T t) {
        MsgPushBean msgPushBean = (MsgPushBean) t;
        int code = msgPushBean.getCode();
        if (code == 1000) {
            onMsgNews(msgPushBean);
        } else if (code == 1001) {
            //请求失败
            DialogUtils.showToast(mContext, msgPushBean.getMsg());
        } else if (code == 10011) {
            DialogUtils.hideWaiting(mContext);
            GlobalInfoUtils.clearApp(mContext);
            DialogUtils.showToast(mContext, msgPushBean.getMsg());
        }

    }

    @Override
    public void showError(Throwable error) {

    }

    /**
     * 2    每次都是最新
     *
     * @param msgPushBean
     */
    private void onMsgNews(MsgPushBean msgPushBean) {
        data = msgPushBean.getData();
        //当这个 数据 只有一条的时候 显示详情
        if (Type == 1) {
            onTypeVisbal(2);
            centerdiolog_rec.setLayoutManager(new LinearLayoutManager(mContext));
            Adapter_MsgPushList adapter = new Adapter_MsgPushList(data.getData(), mContext);
            centerdiolog_rec.setAdapter(adapter);
            adapter.setOnClick(new Adapter_MsgPushList.onClick() {
                @Override
                public void onClickListenrer(View v, int positioin) {
//                    onSubmitAudit(data.getData().get(positioin));
                    //进入详情
                    if (!Check.isFastClick())
                        return;
                    onOnePage(data.getData().get(positioin));
                }
            });
        } else {
            if (data.getData().size() == 1) {
                MsgPushBean.DataBeanX.DataBean dataBean = data.getData().get(0);
                onOnePage(dataBean);
//            他等于 多条数据
            } else {
                onTypeVisbal(2);
                centerdiolog_rec.setLayoutManager(new LinearLayoutManager(mContext));
                Adapter_MsgPushList adapter = new Adapter_MsgPushList(data.getData(), mContext);
                centerdiolog_rec.setAdapter(adapter);
                adapter.setOnClick(new Adapter_MsgPushList.onClick() {
                    @Override
                    public void onClickListenrer(View v, int positioin) {
//                    onSubmitAudit(data.getData().get(positioin));
                        //进入详情
                        if (!Check.isFastClick())
                            return;
                        onOnePage(data.getData().get(positioin));
                    }
                });

            }
        }

    }

    /**
     * 详情  页
     *
     * @param data
     */
    @SuppressLint("CheckResult")
    private void onOnePage(final MsgPushBean.DataBeanX.DataBean data) {
        //蓝色 按钮 和 灰色按钮的效果
        final View.OnClickListener onclickNoEnable = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                DialogUtils.showToast(mContext, getResources().getString(R.string.com_work_msg));
            }
        };

        final View.OnClickListener onClickYes = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭 此dialog  回显上一个dialog
                if (!Check.isFastClick())
                    return;
                onMsgRead();
                JZVideoPlayer.releaseAllVideos();
            }

            /**
             * 已阅读  的操作
             */
            private void onMsgRead() {
                onSubmitAudit(data.getId());
            }
        };

        onTypeVisbal(1);
        // 为ImageView设置布局参数
        final LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                , 1);
        vlp.setMargins(PhoneUtils.dip2px(mContext, 10), PhoneUtils.dip2px(mContext, 5), PhoneUtils.dip2px(mContext, 10), PhoneUtils.dip2px(mContext, 5));

        final LinearLayout.LayoutParams video_vlp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, PhoneUtils.dip2px(mContext, 220)
                , 1);
        video_vlp.setMargins(PhoneUtils.dip2px(mContext, 10), PhoneUtils.dip2px(mContext, 5), PhoneUtils.dip2px(mContext, 10), PhoneUtils.dip2px(mContext, 5));
        final LinearLayout.LayoutParams video_vlp2 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                , 1);
        video_vlp.setMargins(PhoneUtils.dip2px(mContext, 10), PhoneUtils.dip2px(mContext, 5), PhoneUtils.dip2px(mContext, 10), PhoneUtils.dip2px(mContext, 5));

        TextView tx_title = findViewById(R.id.tx_title);//title
        TextView tx_subtitle = findViewById(R.id.tx_subtitle);//subtitle
        final CheckBox check = findViewById(R.id.check);//点击的checkbox
        final LinearLayout linearGrop = findViewById(R.id.linearGrop);//装图片的列表
        bt_load = findViewById(R.id.bt_load);        //详情页的bt按钮


        //添加 单个列表中的img title 什么的
        final List<String> images = data.getImages();
        final List<String> videos = data.getVideos();
        tx_title.setText(data.getTitle());
        tx_subtitle.setText(data.getContent());
        linearGrop.removeAllViews();
        for (int i = videos.size(); i > 0; i--) {
            JZVideoPlayer.releaseAllVideos();
            final JZVideoPlayerStandard jzVideoPlayerStandard = new JZVideoPlayerStandard(mContext);
            jzVideoPlayerStandard.setLayoutParams(video_vlp);
            jzVideoPlayerStandard.setUp(videos.get(i - 1), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
            linearGrop.addView(jzVideoPlayerStandard);
            jzVideoPlayerStandard.fullscreenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (state_video) {
                        jzVideoPlayerStandard.setLayoutParams(video_vlp2);
                        state_video = false;
                    } else {
                        state_video = true;
                        jzVideoPlayerStandard.setLayoutParams(video_vlp);
                    }
                }
            });
        }
        // 循环添加 图片
        for (int i = images.size(); i > 0; i--) {
            final SubsamplingScaleImageView imageView = new SubsamplingScaleImageView(mContext);
            imageView.setLayoutParams(vlp);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    GlobalInfoUtils.onImageEnlarge(images, finalI, mContext, GlobalInfoUtils.ImageDa_Sub);
                }
            });

            //支持图片缩放   涉及到有些手机 内存太低，所以 不使用缓存，等待图 整给
            imageView.setZoomEnabled(false);
            final RequestOptions requestOptions = new RequestOptions()
                    .dontAnimate().format(DecodeFormat.PREFER_RGB_565).skipMemoryCache(false) // 使用内存缓存
                    .diskCacheStrategy(DiskCacheStrategy.NONE); // 不使用磁盘缓存 ;
            Glide.with(mContext).load(images.get(i - 1)).
                    apply(requestOptions).downloadOnly(new SimpleTarget<File>() {
                @Override
                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                    // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                    String s = BitmapUtil.compressImage(resource.getPath());//压缩后
                    imageView.setImage(ImageSource.uri(s));
                }
            });
            linearGrop.addView(imageView, 0);
        }

        //详情的点击时间
        if (data.getStatus().equals("1")) {
            check.setChecked(true);
            bt_load.setBackgroundColor(getResources().getColor(R.color.color_move_4C90F3));
            bt_load.setOnClickListener(onClickYes);

        } else {
            check.setChecked(false);
            bt_load.setBackgroundColor(getResources().getColor(R.color.color_move_dddddd));
            bt_load.setOnClickListener(onclickNoEnable);
        }


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = check.isChecked();
                if (checked) {
                    bt_load.setBackgroundColor(getResources().getColor(R.color.color_move_4C90F3));
                    //当是蓝色的时候 是可以点击的  这时候 关闭这个Dialog  回到上一个
                    bt_load.setOnClickListener(onClickYes);
                } else {
                    bt_load.setBackgroundColor(getResources().getColor(R.color.color_move_dddddd));
                    bt_load.setOnClickListener(onclickNoEnable);
                }

            }
        });
    }


    /**
     * 1 就是详情   其余 是 列表
     *
     * @param i
     */
    private void onTypeVisbal(int i) {
        if (i == GlobalInfoUtils.TYPE11) {
            if (i == 1) {
                centerdiolog_rec.setVisibility(View.GONE);
                msgitem.setVisibility(View.VISIBLE);
                centerdiolog_imgfalse.setVisibility(View.GONE);//X
                img.setVisibility(View.VISIBLE);//X
                tx.setText("消息通知");//title
            } else {
                centerdiolog_rec.setVisibility(View.VISIBLE);
                msgitem.setVisibility(View.GONE);

                img.setVisibility(View.GONE);//X
                tx.setText("消息列表");//title
                boolean b = false;   //判断 是否 有未读消息  如果 有,X img就不显示 了
                for (int i1 = 0; i1 < data.getData().size(); i1++) {
                    if (data.getData().get(i1).getStatus().equals("2")) {
                        b = true;
                    }
                }
                if (b) {
                    centerdiolog_imgfalse.setVisibility(View.GONE);//X 隐藏
                } else {
                    centerdiolog_imgfalse.setVisibility(View.VISIBLE);  // x 显示
                }

            }
        } else {

            centerdiolog_rec.setVisibility(View.VISIBLE);
            msgitem.setVisibility(View.GONE);

            img.setVisibility(View.GONE);//X
            tx.setText("消息列表");//title
            boolean b = false;   //判断 是否 有未读消息  如果 有,X img就不显示 了
            for (int i1 = 0; i1 < data.getData().size(); i1++) {
                if (data.getData().get(i1).getStatus().equals("2")) {
                    b = true;
                }
            }
            if (b) {
                centerdiolog_imgfalse.setVisibility(View.GONE);//X 隐藏
            } else {
                centerdiolog_imgfalse.setVisibility(View.VISIBLE);  // x 显示

            }

        }


    }


    /**
     * 单个条目 点及跳转到 列表页面
     */
    private void onSubmitAudit(final String id) {

        String json = null;
        String url = "https://app.lianjieshenghuo.com/readMessage/";
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("resource", 1 + "");
        map.put("timestamp", GlobalInfoUtils.getTime() + "");
        map.put("token", GlobalInfoUtils.Token);
        map.put("userid", GlobalInfoUtils.Userid);

        String sign = Sha1.encode(
                "id=" + id + "&resource=" + 1 +
                        "&timestamp="
                        + GlobalInfoUtils.getTime() +
                        "&token=" + GlobalInfoUtils.Token + "&userid="
                        + GlobalInfoUtils.Userid + URLConstant.Key);
        map.put("sign", sign);


        json = GlobalInfoUtils.toJson(map, 1);
        //submit
        Map<String, Object> stringObjectMap = ComsentUtils.onSendSubmitAudit(id, json);
        OkHttpClient okHttpClient = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);

        if (stringObjectMap != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : stringObjectMap.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }

        Request request = new Request.Builder().url(url).post(requestBody.build()).tag(mContext).build();
        Call call = okHttpClient.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext, e.getMessage());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();
                LoadScrosses loadScrosses = gson.fromJson(response.body().string(), LoadScrosses.class);
                LogUtils.e(loadScrosses.toString());
                switch (loadScrosses.getCode()) {
                    case 1000:
                        Message message = new Message();
                        message.what = ID_USER;
                        message.obj = loadScrosses;
                        handler.sendMessage(message); // 向Handler发送消息,更新UI

//                        DialogUtils.showToast(mContext, loadScrosses.getMsg());
                        break;
                    default:
//                        DialogUtils.showToast(mContext, loadScrosses.getMsg());
                        break;
                }


            }
        });

    }

    /**
     * 成功后 返回 然后 请求一下 这个列表里面的东西
     *
     * @param loadScrosses
     */
    private void onResultList(LoadScrosses loadScrosses) {
        onTypeVisbal(2);

        //点击 确定的时候请求的列表 就是 2 的列表
        Map<String, Object> stringObjectMap = ComsentUtils.onsendMsg_PushList(GlobalInfoUtils.TYPE + "");
        Type = GlobalInfoUtils.TYPE11;
        Presenter_MSGpush presenterMsGpush = new Presenter_MSGpush(this);
        presenterMsGpush.setData(stringObjectMap);
        centerdiolog_rec.setLayoutManager(new LinearLayoutManager(mContext));
        if (data.getData().size() == 1) {
            finish();
        }
        Adapter_MsgPushList adapter = new Adapter_MsgPushList(data.getData(), mContext);
        centerdiolog_rec.setAdapter(adapter);
        adapter.setOnClick(new Adapter_MsgPushList.onClick() {
            @Override
            public void onClickListenrer(View v, int positioin) {
                if (!Check.isFastClick())
                    return;
                DialogUtils.closeLJdialog();
                onOnePage(data.getData().get(positioin));

            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //do something.
            int visibility = centerdiolog_imgfalse.getVisibility();

            if (visibility == View.VISIBLE) {
                return super.dispatchKeyEvent(event);
            } else {
                return true;
            }
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
