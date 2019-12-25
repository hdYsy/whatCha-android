package com.lianjie.andorid.rnui;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.request.target.ViewTarget;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.lianjie.andorid.BuildConfig;
import com.lianjie.andorid.R;
import com.lianjie.andorid.rnui.packger.MyReactPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class RNApplication extends Application implements ReactApplication {
    private static List<Activity> lists = new ArrayList<>();

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new MyReactPackage(),
                    new RNGestureHandlerPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }

        @Nullable
        @Override
        protected String getBundleAssetName() {
            return super.getBundleAssetName();
        }
    };
    public static RNApplication myApp;

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.myApp = this;
        ViewTarget.setTagId(R.id.glide_tag); //解决 上传图片返回闪退的bug 10/12 glide会在第一次获取这个tag
//    SoLoader.init(this, /* native exopackage */ false);
        Fresco.initialize(getApplicationContext());
    }


//    public static void addActivity(Activity activity) {
//        if (lists != null) {
//            lists.add(activity);
//        }
//    }

//    public static void clearList() {
//        if (lists != null) {
//            for (int i = 0; i < lists.size(); i++) {
//                for (int i1 = 0; i1 < lists.size(); i1++) {
//                    Activity activity = lists.get(i1);
//                    boolean finishing = activity.isFinishing();
//                    if (!finishing) {
//                        activity.finish();
//                    }
//                }
//            }
//        }
//    }
}
