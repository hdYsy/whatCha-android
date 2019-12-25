package com.lianjie.andorid.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lianjie.andorid.R;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.MsgPushBean;
import com.luck.picture.lib.widget.longimage.ImageSource;

import java.io.File;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/19
 * Time: 10:49
 */
public class Dialog_Img_activity extends BaseActivity {


    private MsgPushBean.DataBeanX data;
    private LinearLayout linear_dingjigrop;
    private Dialog_Img_activity mContext;
    private ImageView mImg;
    /**
     * adsfasdfasdfasdf
     */
    private TextView mSecond;
    private String url;
    private String second;
    private int parseInt;
    private Handler handler;

    @Override
    protected int onLayout() {
        return R.layout.center_item_img;
    }

    @Override
    protected void onDrishData() {
    }



    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        final RequestOptions requestOptions = new RequestOptions()
                .dontAnimate().format(DecodeFormat.PREFER_RGB_565).skipMemoryCache(true) // 使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 使用磁盘缓存
         Glide.with(mContext).load(url).
                apply(requestOptions).into(mImg);


        mSecond.setText(second + "s后自动关闭");
        parseInt = Integer.parseInt(second);
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                parseInt--;
                if (parseInt > 0) {
                    mSecond.setText(parseInt + "s后自动关闭");
                } else if (parseInt == 0) {
                    setResult(9901);
                    finish();
                }
                handler.postDelayed(this, 1000);

            }
        };
        handler.postDelayed(runnable, 1000);
    }


    @Override
    protected void initView() {
        mContext = this;
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        second = intent.getStringExtra("second");
        mImg = (ImageView) findViewById(R.id.img);
        mSecond = (TextView) findViewById(R.id.second);
    }

    @Override
    public void onBackPressed() {
        //返回键不控制
    }
}
