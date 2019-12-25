package com.lianjie.andorid.utils;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/23
 * Time: 17:52
 */

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<ImageView> images;
    private ViewPager viewPager;

    /**
     * 构造方法，传入图片列表和ViewPager实例
     *
     * @param images
     * @param viewPager
     */

    public ViewPagerAdapter(List<ImageView> images, ViewPager viewPager) {


           this.images = images;



        this.viewPager = viewPager;
    }

    @Override
    public int getCount() {
//        return Integer.MAX_VALUE;//返回一个无限大的值，可以 无限循环
        return images.size() * 100;//返回一个无限大的值，可以 无限循环
    }

    /**
     * 判断是否使用缓存, 如果返回的是true, 使用缓存. 不去调用instantiateItem方法创建一个新的对象
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化一个条目
     *
     * @param container
     * @param position  当前需要加载条目的索引
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        // 把position对应位置的ImageView添加到ViewPager中
//        final ImageView iv = images.get(position % images.size());
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onclik.Onclick(v,position);
//            }
//        });
//        viewPager.addView(iv);
//        // 把当前添加ImageView返回回去.
//        return iv;
        //对ViewPager页号求模取出View列表中要显示的项
        position %= images.size();
        if (position < 0) {
            position = images.size() + position;
        }
        ImageView view = images.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        //add listeners here if necessary
        final int finalPosition = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclik.Onclick(v, finalPosition);
            }
        });
        return view;
    }

    /**
     * 销毁一个条目
     * position 就是当前需要被销毁的条目的索引
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 把ImageView从ViewPager中移除掉
        viewPager.removeView(images.get(position % images.size()));

    }

    Onclik onclik;

    public void setOnclik(Onclik onclik) {
        this.onclik = onclik;
    }

    public interface Onclik {
        void Onclick(View v, int position);
    }
}
