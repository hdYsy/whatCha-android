package com.lianjie.andorid.fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lianjie.andorid.R;
import com.lianjie.andorid.base.BaseFragment;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.Check;
import com.luck.picture.lib.widget.longimage.ImageSource;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;

import java.io.File;
import java.util.ArrayList;

import indi.liyi.viewer.ImageTransfer;
import indi.liyi.viewer.dragger.DragStatus;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/9/5
 * Time: 16:11
 */
public class Fragment_DaImage extends BaseFragment {
    private FragmentActivity mContext;
    private View mInflate;
    private ArrayList<String> list;
    private int position;


    @Override
    protected void onDrishData() {

    }

    @Override
    protected View getLayout() {
        mContext = getActivity();
        mInflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_img_sub, null, false);
        initView();
        return mInflate;
    }

    @SuppressLint("CheckResult")
    private void initView() {

         final SubsamplingScaleImageView mImage_sub = mInflate.findViewById(R.id.image_sub);
          TextView tx_page = mInflate.findViewById(R.id.tx_page);
        tx_page.setText(position+1+"/"+list.size());
        if(list.size() == 1){
            tx_page.setVisibility(View.GONE);
        }else{
            tx_page.setVisibility(View.VISIBLE);
        }


        //支持图片缩放   涉及到有些手机 内存太低，所以 不使用缓存，等待图 整给
        mImage_sub.setZoomEnabled(true);
        final RequestOptions requestOptions = new RequestOptions()
                .dontAnimate().format(DecodeFormat.PREFER_RGB_565).skipMemoryCache(true) // 使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 使用磁盘缓存 ;
        Glide.with(mContext).load(list.get(position)).
                apply(requestOptions).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                String s = BitmapUtil.compressImage(resource.getPath());//压缩后

                mImage_sub.setImage(ImageSource.uri(s));
            }
        });
        mImage_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                mContext.finish();
            }
        });

    }



    public void setImageURL(ArrayList<String> list, int position) {
        this.list =list;
        this.position =position;
    }
}
