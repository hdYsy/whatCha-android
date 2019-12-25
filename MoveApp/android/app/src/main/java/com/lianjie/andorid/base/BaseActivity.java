package com.lianjie.andorid.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.LocationRecycleAdapter;
import com.lianjie.andorid.rnui.RNApplication;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/10
 * Time: 17:16
 *
 */
public abstract class BaseActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(this.getResources().getColor(R.color.color_bar_color));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(onLayout());
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDrishData();//垃圾数据
    }

    protected abstract int onLayout();

    protected abstract void onDrishData();

    protected abstract void initData();

    protected abstract void initView();




}
