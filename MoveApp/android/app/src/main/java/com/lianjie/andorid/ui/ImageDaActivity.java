package com.lianjie.andorid.ui;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.ImageSub_ViewpagerAdapter;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.fragment.Fragment_DaImage;
import com.lianjie.andorid.newsView.Viewpager_scroCatch;

import java.util.ArrayList;
import java.util.List;

import indi.liyi.viewer.ImageViewer;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/8/6
 * Time: 16:04
 */
public class ImageDaActivity extends BaseActivity {

    private ImageDaActivity mContext;

    @Override
    protected int onLayout() {
        return R.layout.com_work_imageda;
    }

    @Override
    protected void onDrishData() {
    }


    @Override
    protected void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initView() {
        mContext = this;

        ImageViewer imageViewer = findViewById(R.id.imageViewer);  //普通
        Viewpager_scroCatch imagescale_vp = findViewById(R.id.imagescale_vp);//长图

        Intent intent = getIntent();
        ArrayList<String> list = intent.getStringArrayListExtra("list");
        int position = intent.getIntExtra("position", 0);
        String type = intent.getStringExtra("Type");

//        if (type.equals("0")) {  //  0  是 超级大图    否则不是
            imagescale_vp.setVisibility(View.VISIBLE);
            imageViewer.setVisibility(View.GONE);
            List<Fragment> imgList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Fragment_DaImage fragment_daImage = new Fragment_DaImage();
                fragment_daImage.setImageURL(list,i);
                imgList.add(fragment_daImage);
            }
            ImageSub_ViewpagerAdapter  viewPagerAdapter = new ImageSub_ViewpagerAdapter(getSupportFragmentManager(),imgList);
            imagescale_vp.setAdapter(viewPagerAdapter);
            imagescale_vp.setCurrentItem(position-1);



//        }else{     //=========================分割线==============================
//
//
//            imageViewer.overlayStatusBar(true);
//            imageViewer.imageData(list);
//            imageViewer.imageLoader(new ImageLoader() {
//                @Override
//                public void displayImage(Object src, final ImageView imageView, LoadCallback callback) {
//                    RequestOptions requestOptions = new RequestOptions().dontAnimate().format(DecodeFormat.PREFER_RGB_565);
//                    Glide.with(imageView.getContext()).load(src).apply(requestOptions).downloadOnly(new SimpleTarget<File>() {
//                        @Override
//                        public void onResourceReady(File resource, Transition<? super File> transition) {
//                            Uri uri = Uri.fromFile(resource);
//                            imageView.setImageURI(uri);
//                        }
//                    });
//                }
//            });
//            imageViewer.playEnterAnim(false);
//            imageViewer.playExitAnim(false);
//            imageViewer.showIndex(true);
//            imageViewer.watch(position);
//            imageViewer.setOnBrowseStatusListener(new OnBrowseStatusListener() {
//            @Override
//            public void onBrowseStatus(int status) {
//                if (STATUS_SILENCE == status) {
//                    finish();
//                }
//            }
//        });
//        }
    }

    @Override
    protected void onPause() {
        //提交禁止退场动画2017.8.5
        overridePendingTransition(0, 0);
        super.onPause();
    }


}
