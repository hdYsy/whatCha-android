package com.lianjie.andorid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.luck.picture.lib.widget.longimage.ImageSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/14
 * Time: 14:21
 */
public class GlideUtils {
    public static GlideUtils glideUtils;

    private GlideUtils() {
    }

    public static GlideUtils getInstance() {
        if (glideUtils == null) {
            synchronized (GlideUtils.class) {
                if (glideUtils == null) {
                    return glideUtils = new GlideUtils();
                }
            }
        }
        return glideUtils;
    }
    /**
     *
     * @param imageView   view
     * @param img       view 的url
     * @param Width    宽
     * @param Height   高
     * @param br        是否只显示缩略图
     */

    @SuppressLint("CheckResult")
    public void setImages(final ImageView imageView, Object img, int Width, int Height, boolean br) {
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);//ji缓存原始图片还缓存现在的图片
        requestOptions.dontAnimate();
        //        //设置缩略图支持：先加载缩略图 然后在加载全图
//        returnBitMap()

//        Glide.with(imageView.getContext()).load(img).
//                apply(requestOptions).thumbnail(0.1f)
                // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
//                String s = BitmapUtil.compressImage(resource.getPath());//压缩后
//                Bitmap bitmap = BitmapFactory.decodeFile(resource.getPath());
//                Bitmap bitmap1 = BitmapUtil.compressImage(bitmap);
//                imageView.setImageBitmap(bitmap1);
            Glide.with(imageView.getContext()).asBitmap().load(img).apply(requestOptions).thumbnail(0.1f).into(imageView);


    }


    /**
     * 加载它的随yi图片
     * @param
     * @param icon_sel
     */
    public void setImageWrap(ImageView imageView, Object icon_sel) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);//ji缓存原始图片还缓存现在的图片
        requestOptions.dontAnimate();
        Glide.with(imageView.getContext()).load(icon_sel).apply(requestOptions).into(imageView);
    }

    /**
     * 基础 的加载图片
     *
     * @param imageView
     * @param img
     */
    public void setImage60dp(ImageView imageView, Object img) {
        int i = PhoneUtils.dip2px(imageView.getContext(), 80);
        RequestOptions override = new RequestOptions().override(i, i);
        Glide.with(imageView.getContext()).load(img).apply(override).into(imageView);
    }

    /**
     * 基础 的 加载原型图片
     *
     * @param imageView
     * @param img
     */
    public void setImage_Circlp(ImageView imageView, Object img) {
        RequestOptions requestOptions = new RequestOptions().bitmapTransform(new CenterCrop());
        Glide.with(imageView.getContext()).load(img).apply(requestOptions).into(imageView);
    }


}
