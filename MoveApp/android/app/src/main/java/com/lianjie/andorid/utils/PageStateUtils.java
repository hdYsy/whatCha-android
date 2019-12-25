package com.lianjie.andorid.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lianjie.andorid.R;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/8
 * Time: 11:20
 */
public class PageStateUtils {
    private static View view, callbaclk;
    private static TextView titleView;
    private final Activity activity;

    /**
     * 不允许有私有方法~
     *
     * @param v
     */
    public PageStateUtils(View v, Activity activity) {
        this.activity = activity;
        this.view = v;
        this.titleView = view.findViewById(R.id.title);
        this.callbaclk = view.findViewById(R.id.callbaclk);
    }

    private PageStateUtils(Activity activity) {
        this.activity = activity;
    }

    /**
     * 返回键  以及  title
     *
     * @param title
     * @param b
     */
    public void setViewState(String title, boolean b) {
        titleView.setText(title);
        if (b) {
            callbaclk.setVisibility(View.VISIBLE);
            callbaclk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });

        }else{
            callbaclk.setVisibility(View.GONE);
        }
    }




}
