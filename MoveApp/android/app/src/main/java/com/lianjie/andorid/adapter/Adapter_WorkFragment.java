package com.lianjie.andorid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.lianjie.andorid.fragment.Fragment_success;
import com.lianjie.andorid.fragment.Fragment_thedoor;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/14
 * Time: 10:33
 */
public class Adapter_WorkFragment extends FragmentStatePagerAdapter {
    List<Fragment> fragments;
    private Fragment fragment_;

    public Adapter_WorkFragment(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 获取 当前显示的是哪个fragment
     * @param container
     * @param position
     * @param object
     */
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        //第二个
        if(object instanceof Fragment_thedoor ){
            fragment_ = (Fragment_thedoor) object;  //第一个
        }  if(object instanceof Fragment_success ){
            fragment_ = (Fragment_success)object;  //第er个
        }

        super.setPrimaryItem(container, position, object);

    }
    public Fragment getCurrentFragment() {
        return fragment_;
    }
}
