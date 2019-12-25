package com.lianjie.andorid.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Base64;

import com.lianjie.andorid.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Message:  选择图片工具类
 compile 'com.github.LuckSiege.PictureSelector:picture_library:v2.1.7'
 //查看图片
 PictureSelector.create(activity).externalPicturePreview(position, "保存的文件路径",imgList);

 //选择图片回调
 if (resultCode == RESULT_OK) {
 if (requestCode == ContantParmer.PHOTO_CODE) {
 List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
 if (ToolUtils.isList(list)) {
 String path = list.get(0).getPath();
 }
 }
 }

 * Created by  ChenLong.
 * Created by Time on 2017/11/28.
 */

public class PhotoUtils {
    public static void openPhoto(Activity activity, int code, List<LocalMedia> selectList){
        // 进入相册
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE )// 多选 or 单选
                .previewImage(true)// 是否可预览图片
//                .previewVideo(true)// 是否可预览视频
//                .enablePreviewAudio(true) // 是否可播放音频
//                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
//                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
//                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(true)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
//                .compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(code);//结果回调onActivityResult code
    }
  public static void openPhotos(Activity activity, int num, int code, List<LocalMedia> selectList){
    // 进入相册
    PictureSelector.create(activity)
      .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
      .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
      .maxSelectNum(num)// 最大图片选择数量
      .minSelectNum(1)// 最小选择数量
      .imageSpanCount(4)// 每行显示个数
      .selectionMode(PictureConfig.MULTIPLE )// 多选 or 单选
      .previewImage(true)// 是否可预览图片
//                .previewVideo(true)// 是否可预览视频
//                .enablePreviewAudio(true) // 是否可播放音频
//                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
      .isCamera(true)// 是否显示拍照按钮
      .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
      //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
//                .enableCrop(true)// 是否裁剪
      .compress(true)// 是否压缩
//                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
      //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
      .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
      .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
      .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
      .isGif(true)// 是否显示gif图片
      .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
      .circleDimmedLayer(false)// 是否圆形裁剪
      .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
      .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
      .openClickSound(false)// 是否开启点击声音
//      .selectionMedia(selectList)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
      //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
      //.cropCompressQuality(90)// 裁剪压缩质量 默认100
      //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
      //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
      //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
      //.rotateEnabled() // 裁剪是否可旋转图片
      //.scaleEnabled()// 裁剪是否可放大缩小图片
      //.videoQuality()// 视频录制质量 0 or 1
      //.videoSecond()//显示多少秒以内的视频or音频也可适用
      //.recordVideoSecond()//录制视频秒数 默认60s
      .forResult(code);//结果回调onActivityResult code
  }
    public static void openCamera(Activity activity, int num,int code){
      List<LocalMedia> selectList = new ArrayList<>();
        // 单独拍照
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .maxSelectNum(num)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode( PictureConfig.SINGLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .enablePreviewAudio(true) // 是否可播放音频
                .isCamera(false)// 是否显示拍照按钮
//                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                .isGif(true)// 是否显示gif图片
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
//                .selectionMedia(selectList)// 是否传入已选图片
                .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .cropCompressQuality(90)// 裁剪压缩质量 默认为100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
//                .videoQuality(1)// 视频录制质量 0 or 1
//                .videoSecond(60)////显示多少秒以内的视频or音频也可适用
//                .recordVideoSecond(60)//录制视频秒数 默认60s
                .forResult(code);//结果回调onActivityResult code

//      // 进入相册
//      PictureSelector.create(activity)
//              .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//              .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
//              .maxSelectNum(num)// 最大图片选择数量
//              .minSelectNum(1)// 最小选择数量
//              .imageSpanCount(4)// 每行显示个数
//              .selectionMode(PictureConfig.MULTIPLE )// 多选 or 单选
//              .previewImage(true)// 是否可预览图片
////                .previewVideo(true)// 是否可预览视频
////                .enablePreviewAudio(true) // 是否可播放音频
////                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
//              .isCamera(false)// 是否显示拍照按钮
//              .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//              //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
////                .enableCrop(true)// 是否裁剪
//              .compress(true)// 是否压缩
////                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
//              //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//              .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//              .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//              .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
//              .isGif(true)// 是否显示gif图片
//              .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
//              .circleDimmedLayer(false)// 是否圆形裁剪
//              .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//              .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//              .openClickSound(false)// 是否开启点击声音
//              .selectionMedia(selectList)// 是否传入已选图片
////                        .videoMaxSecond(15)
////                        .videoMinSecond(10)
//              //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
//              //.cropCompressQuality(90)// 裁剪压缩质量 默认100
//              //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
//              //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
//              //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
//              //.rotateEnabled() // 裁剪是否可旋转图片
//              //.scaleEnabled()// 裁剪是否可放大缩小图片
//              //.videoQuality()// 视频录制质量 0 or 1
//              //.videoSecond()//显示多少秒以内的视频or音频也可适用
//              //.recordVideoSecond()//录制视频秒数 默认60s
//              .forResult(code);//结果回调onActivityResult code
    }

  /*
   * bitmap转base64
   * */
  public static String bitmapToBase64(Bitmap bitmap) {
    String result = null;
    ByteArrayOutputStream baos = null;
    try {
      if (bitmap != null) {
        baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        baos.flush();
        baos.close();

        byte[] bitmapBytes = baos.toByteArray();
        result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (baos != null) {
          baos.flush();
          baos.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
