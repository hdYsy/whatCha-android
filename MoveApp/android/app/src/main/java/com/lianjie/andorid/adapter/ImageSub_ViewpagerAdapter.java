package com.lianjie.andorid.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.lianjie.andorid.fragment.Fragment_DaImage;

import java.util.List;

import indi.liyi.viewer.ImageViewer;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/9/5
 * Time: 15:42
 */
public class ImageSub_ViewpagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> imgList;

    public ImageSub_ViewpagerAdapter(FragmentManager fm, List<Fragment> imgList) {
        super(fm);
        this.imgList = imgList;
    }

    @Override
    public Fragment getItem(int i) {
        return imgList.get(i);
    }

    @Override
    public int getCount() {
        if(imgList!=null)
        return imgList.size();
        return 0;
    }

}