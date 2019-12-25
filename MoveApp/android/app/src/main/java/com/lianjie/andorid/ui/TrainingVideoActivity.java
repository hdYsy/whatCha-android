package com.lianjie.andorid.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.Adapter_VideoFragment;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.VideoListBean;
import com.lianjie.andorid.fragment.Fragment_Videow;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Stract_getTrainList;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.ViewPagerAdapter;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2019/11/18.
 * work_shape_cavers
 */

public class TrainingVideoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mCallbaclk;
    private TextView mTitle;
    private ViewPager mViewpager;
    private TextView mFile_video;
    private TextView mFile_imgtext;
    private TrainingVideoActivity mContext;
    private Fragment_Videow fragment_videow;

    @Override
    protected int onLayout() {
        mContext = this;
        return R.layout.activity_trainingvideo_layout;
    }

    @Override
    protected void onDrishData() {

    }

    @Override
    protected void initData() {
        mTitle.setText("培训课程");
        mCallbaclk.setOnClickListener(this);

        fragment_videow = new Fragment_Videow();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment_videow);
        fragment_videow = new Fragment_Videow();
        fragments.add(fragment_videow);
        Adapter_VideoFragment adapter_videoFragment = new Adapter_VideoFragment(getSupportFragmentManager(), fragments);
        mViewpager.setAdapter(adapter_videoFragment);

        mFile_video.setOnClickListener(this);
        mFile_imgtext.setOnClickListener(this);

        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
               GlobalInfoUtils.page = i;
                if (i == 0) {
                    mFile_video.setTextColor(getResources().getColor(R.color.color_pl_color));
                    mFile_imgtext.setTextColor(getResources().getColor(R.color.picture_list_text_color));
                } else {
                    mFile_imgtext.setTextColor(getResources().getColor(R.color.color_pl_color));
                    mFile_video.setTextColor(getResources().getColor(R.color.picture_list_text_color));
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        GlobalInfoUtils.page = 0;
    }

    @Override
    protected void initView() {
//        findViewById(R.id.)
        mCallbaclk = findViewById(R.id.callbaclk);
        mTitle = findViewById(R.id.title);
        mFile_video = findViewById(R.id.file_video);
        mFile_imgtext = findViewById(R.id.file_imgtext);
        mViewpager = findViewById(R.id.viewpager);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.file_imgtext:
                GlobalInfoUtils.page = 1;
                mViewpager.setCurrentItem(1);
                break;
            case R.id.file_video:
                GlobalInfoUtils.page = 0;
                mViewpager.setCurrentItem(0);
                break;
            case R.id.callbaclk:
                finish();
                break;
        }
    }



}
