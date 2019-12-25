package com.lianjie.andorid.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.ImageBean;
import com.lianjie.andorid.bean.TrainDetailsBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Stract_getTrainDetail;
import com.lianjie.andorid.newsView.WarpLinearLayout;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.PhoneUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.luck.picture.lib.widget.longimage.ImageSource;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;
//import cn.jzvd.JZResizeTextureView;
//import cn.jzvd.JZVideoPlayer;
//import cn.jzvd.JZVideoPlayerStandard;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static cn.jzvd.JZMediaManager.getDuration;
import static cn.jzvd.JZMediaManager.start;
import static cn.jzvd.JZVideoPlayer.CURRENT_STATE_AUTO_COMPLETE;
import static cn.jzvd.JZVideoPlayer.CURRENT_STATE_PAUSE;
import static cn.jzvd.JZVideoPlayer.CURRENT_STATE_PLAYING;
import static cn.jzvd.JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN;
import static cn.jzvd.JZVideoPlayer.backPress;
import static java.lang.String.valueOf;


/**
 * Created by My on 2019/11/23.
 */
public class TrainingDeails extends BaseActivity implements IConstract_Stract.IView, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    //    private JZVideoPlayerStandard jc_videos;
    private TextView video_title;
    private WarpLinearLayout linear_title;
    private TextView subTitle;
    private LinearLayout video_linear;
    private SubsamplingScaleImageView image_sub;
    private TrainingDeails mContext;
    private TrainDetailsBean bean;
    private List<String> list;
    ;
    private ScrollView scroll;
    private String courseId;
    private TextView mTitle;
    private ImageView mCallbaclk;
    private JZVideoPlayerStandard jc_videos;
    private int position = 0;
    private int startprogressPosition = -1;
    private int position_progress = -1;
    private int start_position;
    private int progress;
    private LinearLayout linear;
    private boolean state_video = true;
    private int position_start;
    private int page_position = 0;
    boolean boor = true;

    @Override
    protected int onLayout() {
        mContext = this;
        return R.layout.activity_trainingdeails;
    }

    @Override
    protected void onDrishData() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        Presenter_Stract_getTrainDetail presenter = new Presenter_Stract_getTrainDetail(this);
        presenter.setData(ComsentUtils.onGetTrainDelatils(courseId));
        DialogUtils.showWaiting(mContext, mContext.getResources().getString(R.string.com_login_tips));

    }

    @Override
    protected void initView() {
        mCallbaclk = findViewById(R.id.callbaclk);
        mTitle = findViewById(R.id.title);
        //视频
        jc_videos = findViewById(R.id.jc_videos);
        video_title = findViewById(R.id.video_title);
        linear_title = findViewById(R.id.linear_title);
        subTitle = findViewById(R.id.subTitle);
        video_linear = findViewById(R.id.video_linear);
        linear = findViewById(R.id.linear);//shipin的viewgrop

        //图文
        image_sub = findViewById(R.id.image_sub);
        scroll = findViewById(R.id.scroll);
        mCallbaclk.setOnClickListener(this);
        mTitle.setText("培训课程");
    }


    @Override
    public <T> void showData(T t) {
        DialogUtils.hideWaiting(mContext);
        bean = (TrainDetailsBean) t;
        if (bean.getCode() == 1000) {
            onScress();
        }
    }


    @Override
    public void showError(Throwable error) {
        DialogUtils.hideWaiting(mContext);
        finish();
    }

    /**
     * 成功
     */
    @SuppressLint("ClickableViewAccessibility")
    private void onScress() {

        if (GlobalInfoUtils.page == 0) {
            scroll.setVisibility(View.GONE);//图文
            video_linear.setVisibility(View.VISIBLE);//视频
            TrainDetailsBean.DataBean.TrainBean train = bean.getData().getTrain();
            List<String> type = train.getType();
            for (int i1 = 0; i1 < type.size(); i1++) {
                String s = type.get(i1);
                TextView textView = new TextView(mContext);
                textView.setText(s);
                onCheced_TagsBg(textView);
                textView.setTextSize(12);
                textView.setPadding(10, 5, 10, 5);
                linear_title.addView(textView);
            }
            video_title.setText(train.getTitle());
            subTitle.setText(train.getCreatetime() + " 由" + train.getAuthor() + "发布");
            String content = train.getContent();

            jc_videos.setUp(content, JZVideoPlayer.SCREEN_WINDOW_NORMAL);

            //时间干过去
            final String createtime = bean.getData().getTrainRead().getLongX();
            position = Integer.parseInt(createtime);//时间
            //时间
            position_start = Integer.parseInt(createtime);
            jc_videos.progressBar.setOnSeekBarChangeListener(this);
            jc_videos.fullscreenButton.setOnClickListener(new View.OnClickListener() { //全屏.半ping
                @Override
                public void onClick(View v) {
                    if (state_video) {
                        linear.setVisibility(View.GONE);
                        state_video = false;
                    } else {
                        linear.setVisibility(View.VISIBLE);
                        state_video = true;

                    }

                }
            });


//            jc_videos.fullscreenButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (jc_videos.currentState == CURRENT_STATE_AUTO_COMPLETE) return;
//                    if (jc_videos.currentScreen == SCREEN_WINDOW_FULLSCREEN) {
//                        //quit fullscreen
//                        backPress();
//                    } else {
//                        jc_videos. onEvent(JZUserAction.ON_ENTER_FULLSCREEN);
//                        jc_videos.startWindowFullscreen();
//                   jc_videos.progressBar.setVisibility(View.GONE);
//                   jc_videos.bottomProgressBar.setVisibility(View.GONE);
//                   jc_videos.progressBar.setVisibility(View.GONE);
//                   new SeekBar.OnSeekBarChangeListener() {
//                       @Override
//                       public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//                       }
//
//                       @Override
//                       public void onStartTrackingTouch(SeekBar seekBar) {
//
//                       }
//
//                       @Override
//                       public void onStopTrackingTouch(SeekBar seekBar) {
//
//                       }
//                   });

//                    }
//                }
//            });

//                    setOnTouchListener(new View.OnTouchListener() {
//
//                private boolean keepScreenOn;
//                private int page;
//                private Runnable runnable;
//                private Handler handler;
//
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    page = 2;
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            jc_videos.progressBar.setOnSeekBarChangeListener(TrainingDeails.this);
//                        }
//                    return false;
//                }
//            });


        } else if (GlobalInfoUtils.page == 1) {
            video_linear.setVisibility(View.GONE);
            scroll.setVisibility(View.VISIBLE);

            String content = bean.getData().getTrain().getContent();
            list = new ArrayList<>();
            list.add(content);

            //支持图片缩放   涉及到有些手机 内存太低，所以 不使用缓存，等待图 整给
            image_sub.setZoomEnabled(true);
            final RequestOptions requestOptions = new RequestOptions()
                    .dontAnimate().format(DecodeFormat.PREFER_RGB_565).skipMemoryCache(true) // 使用内存缓存
                    .diskCacheStrategy(DiskCacheStrategy.ALL); // 使用磁盘缓存 ;
            Glide.with(mContext).load(list.get(0)).
                    apply(requestOptions).downloadOnly(new SimpleTarget<File>() {
                @Override
                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                    // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
//                    String s = BitmapUtil.compressImage(resource.getPath());//压缩后

                    image_sub.setImage(ImageSource.uri(resource.getPath()));
                }
            });
            image_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    GlobalInfoUtils.onImageEnlarge(list, 0, mContext, 0);
                }
            });


        }
    }

    /**
     * 标签
     *
     * @param
     * @param tv
     */
    private void onCheced_TagsBg(View tv) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(2, mContext.getResources().getColor(R.color.color_move_dddddd));
        int i = PhoneUtils.dip2px(mContext, 5);
        gradientDrawable.setCornerRadius(i);
        tv.setBackground(gradientDrawable);
        TextView tx = (TextView) tv;
        tx.setTextColor(mContext.getResources().getColor(R.color.color_move_4C90F3));
    }

    /**
     * 原生监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            onKeypress();
            /**
             * 获取 是否是最底部
             */
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 返回键要做的操作
     */
    private void onKeypress() {
        if (GlobalInfoUtils.page == 1) {
            boolean b = scroll.canScrollVertically(1);
            if (!b) {//取反 就是 滑到底部了
                onRequestIMG(0, 1);
            } else {
                onRequestIMG(0, 2);
            }
        } else if (GlobalInfoUtils.page == 0) {
            TextView currentTimeTextView = jc_videos.currentTimeTextView; //当前时间
            TextView totalTimeTextView = jc_videos.totalTimeTextView; //jieshu
            String totaltime = currentTimeTextView.getText().toString();
            StringBuffer buffer_Minutes = new StringBuffer();
            StringBuffer buffer_Seconds = new StringBuffer();
            int page = 0;//字符串的下标
            for (int i = 0; i < totaltime.length(); i++) {
                char c = totaltime.charAt(i);
                if (c == ':') {
                    page = i;
                }
            }
            for (int i = 0; i < page; i++) {
                buffer_Minutes.append(totaltime.charAt(i));
            }
            for (int i = page + 1; i < totaltime.length(); i++) {
                buffer_Seconds.append(totaltime.charAt(i));
            }

            int Minutes = Integer.parseInt(buffer_Minutes.toString());//分钟
            int seconds = Integer.parseInt(buffer_Seconds.toString());//秒
            int seconds_request = Minutes * 60 + seconds;//总走的秒数


            if (currentTimeTextView.getText().toString().equals(totalTimeTextView.getText().toString()) && !totalTimeTextView.getText().toString().isEmpty() || "1".equals(bean.getData().getTrainRead().getEnd())) {//是否相同
                onRequestIMG(seconds_request, 1); //走完 了 就过去

            } else {
                if (page_position > 0) {


                    if (seconds_request == 0) {
                        onRequestIMG(0, 3);
                    } else if (seconds_request > page_position) {
                        onRequestIMG(seconds_request, 2);
                    } else {
                        onRequestIMG(page_position, 2);
                    }
                } else {
                    if (seconds_request == 0) {
                        onRequestIMG(0, 3);
                    } else if (seconds_request > position_start) {
                        onRequestIMG(seconds_request, 2);
                    } else {
                        onRequestIMG(position_start, 2);
                    }
                }
            }

        }
    }


    private void onRequestIMG(int lon, int readStatus) {

        Map<String, Object> map = ComsentUtils.onGetTrainDelatilsType(lon, readStatus, courseId);

        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(URLConstant.addTrainProgress).post(requestBody.build()).tag(mContext).build();
        Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                finish();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ImageBean imageBean = gson.fromJson(response.body().string(), ImageBean.class);
                if (imageBean.getCode() == 1000) {
                    finish();
                } else {
                    finish();
                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callbaclk:
                onKeypress();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (backPress()) {
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //提交禁止退场动画2017.8.5
        if (GlobalInfoUtils.page == 1) {
            overridePendingTransition(0, 0);
        }
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        progress = seekBar.getProgress();

        start_position = (int) ((seekBar.getProgress() * getDuration() / 100) / 1000);
        if (position < start_position) {
            position = start_position;
        }
        jc_videos.cancelProgressTimer();
        ViewParent vpdown = (ViewParent) getParent();
        while (vpdown != null) {
            vpdown.requestDisallowInterceptTouchEvent(true);
            vpdown = vpdown.getParent();
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        jc_videos.onEvent(JZUserAction.ON_SEEK_POSITION);
        jc_videos.startProgressTimer();
        ViewParent vpup = (ViewParent) getParent();
        while (vpup != null) {
            vpup.requestDisallowInterceptTouchEvent(false);
            vpup = vpup.getParent();
        }
        if (jc_videos.currentState != CURRENT_STATE_PLAYING &&
                jc_videos.currentState != CURRENT_STATE_PAUSE) return;
        long l = (seekBar.getProgress() * getDuration() / 100) / 1000;
        if ("1".equals(bean.getData().getTrainRead().getEnd())) {//如果已经学完 就不需要做这些判断了直接刚
            long time = seekBar.getProgress() * getDuration() / 100;
            JZMediaManager.seekTo(time);
        } else {
            if (position > l) {
                page_position = position;
                long time = seekBar.getProgress() * getDuration() / 100;
                JZMediaManager.seekTo(time);
            } else {
                if (l - position >= 2) {
                    if (page_position != 0) {
                        JZMediaManager.seekTo(page_position * 1000);
                    } else {
                        JZMediaManager.seekTo(position * 1000);
                    }
                }
            }

        }


    }


}
