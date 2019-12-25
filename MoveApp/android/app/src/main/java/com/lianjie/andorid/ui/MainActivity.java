package com.lianjie.andorid.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.lianjie.andorid.R;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.UpdataBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Stract_updatalml;
import com.lianjie.andorid.utils.ActionUtils;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LocationUtils;
import com.lianjie.andorid.utils.PhoneUtils;
import com.lianjie.andorid.utils.SPUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zenglb.downloadinstaller.DownloadInstaller;
import com.zenglb.downloadinstaller.DownloadProgressCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.reactivex.functions.Consumer;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {


    private TextView mMain_tx;
    int page = 4;
    private Handler handler;
    private MainActivity mContext;
    private NumberProgressBar numpRrogress;
    private Button btn_ok;
    private long mDownloadId;
    private DownloadManager downloadManager;
    private Uri localFilePath;
    private DownloadManager.Request request;
//    private DownloadUtils downloadUtils; //下载工具类

    @Override
    protected int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDrishData() {
    }


    @Override
    protected void initData() {
        Presenter_Stract_updatalml presenter = new Presenter_Stract_updatalml(new IConstract_Stract.IView() {
            @Override
            public <T> void showData(T t) {
                UpdataBean updataBean = (UpdataBean) t;
                if (updataBean.getCode() == 1000) {
                    String versionCode = updataBean.getData().getVersionCode();
                    int currVersionCode = LocationUtils.getPackageVersionCode(MainActivity.this);
                    if (currVersionCode < Integer.parseInt(versionCode)) {
                        showHintDialog(updataBean); //弹出对话框，提示用户更新APP
                    } else {
                        sendDate();//跳转
                    }
                } else {
                    sendDate();//跳转
                }
            }

            @Override
            public void showError(Throwable error) {
                sendDate();//跳转
            }
        });
        Map<String, Object> map = new HashMap<>();
        presenter.setData(map);
    }

    @Override
    protected void initView() {
        mContext = this;
        mMain_tx = findViewById(R.id.main_tx);
        PhoneUtils.getWindowManager(this);
//        initReceiver();
    }


    //显示询问用户是否更新APP的dialog
    private void showHintDialog(final UpdataBean updataBean) {
//        DialogUtils.showCenterLoadAPK(this,updataBean.getData());
        final UpdataBean.DataBean data = updataBean.getData();
        //自定义dialog显示布局
        String isMandatory = data.getIsMandatory();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dilog_newapk, null);
        TextView tv_title = inflate.findViewById(R.id.tv_title);
        btn_ok = inflate.findViewById(R.id.btn_ok);//升级
         numpRrogress = inflate.findViewById(R.id.numpRrogress);
      LinearLayout linear = inflate.findViewById(R.id.linear);

        GlobalInfoUtils.apkUrl = data.getApkUrl();
        LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        vlp.setMargins(0,5,0,0);

            //强制更新
            tv_title.setText(data.getUpdateTitle());
            List<String> changeLog = data.getChangeLog();
            for (int i = 0; i < changeLog.size(); i++) {
                TextView textView = new TextView(mContext);
                textView.setText(changeLog.get(i));
                textView.setLayoutParams(vlp);
                linear.addView(textView);
            }

            btn_ok.setOnClickListener(new View.OnClickListener() {


                @SuppressLint("CheckResult")
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    numpRrogress.setVisibility(View.VISIBLE);
                    btn_ok.setVisibility(View.GONE);
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//                        downloadUtils =  new DownloadUtils(mContext, data.getApkUrl(), GlobalInfoUtils.apkName,numpRrogress);
//                        一般的弹出对话框提示升级，需要强制升级的大家一起提issues 来完善啊
                        new DownloadInstaller(mContext, data.getApkUrl(), new DownloadProgressCallBack() {
                            @Override
                            public void downloadProgress(int progress) {
                                numpRrogress.setProgress(progress);
                                Log.e("PROGRESS","Progress"+progress);
                            }

                            @Override
                            public void downloadException(Exception e) {
                                e.printStackTrace();
                            }


                            @Override
                            public void onInstallStart() {

                            }
                        }).start();
                    } else {
                        //6.0以上,先检查，申请权限，再下载
                        checkPermission();
                    }
                }
                /**
                 * 权限
                 */
                private void checkPermission() {
                    //是否有内外存储、联网权限
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
                    if (ContextCompat.checkSelfPermission(mContext, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                        RxPermissions rxPermissions = new RxPermissions(mContext);
                        //动态申请权限
                        rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {//成功
//                                    downloadUtils = new DownloadUtils(mContext, data.getApkUrl(), "lianjie.apk", numpRrogress);
                                 onLoadApk(data);
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // 用户拒绝了该权限，没选中不再询问,再次启动时，还会提示请求权限的对话框
                                    Log.d("TAG", permission.name + " is denied. More info should be provided.");
                                } else {
                                    // 用户拒绝了该权限，并且选中不再询问
                                    View view = DialogUtils.showCentreDialog(mContext, "是否跳转至设置页打开权限(无权限或将无法打开app");
                                    final View tvSendMSG = view.findViewById(R.id.tvSendMSG);
                                    final View tvAdd = view.findViewById(R.id.tvAdd);
                                    //取消 将关闭弹窗 并且finish
                                    View.OnClickListener onClickListener = new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            if (!Check.isFastClick())
                                                return;
                                            if (v.equals(tvSendMSG)) {
                                                DialogUtils.closeLJdialog();
                                            } else if (v.equals(tvAdd)) {
                                                DialogUtils.closeLJdialog();
                                                GlobalInfoUtils.toJurisdiction(mContext);
                                            }
                                        }
                                    };
                                    tvSendMSG.setOnClickListener(onClickListener);
                                    tvAdd.setOnClickListener(onClickListener);
                                }
                            }

                        });
                    } else {
                        onLoadApk(data);
                        //旧版本的安装  小米 note2 中会出现问题
//                        downloadUtils = new DownloadUtils(mContext, data.getApkUrl(), "lianjie.apk", numpRrogress);
                    }

                }

            });



        //自定义dialog显示风格
        Dialog dialog = new Dialog(mContext, R.style.DialogCentre);


        //点击其他区域消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
        //设置返回键不可用
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {

                    return true; // 默认返回 false

            }

        });

    }

    /**
     * 下载新版本
     * @param data
     */
    private void onLoadApk(UpdataBean.DataBean data) {
        new DownloadInstaller(mContext, data.getApkUrl(), new DownloadProgressCallBack() {
            @Override
            public void downloadProgress(int progress) {
                numpRrogress.setProgress(progress);
                Log.e("PROGRESS","Progress"+progress);
            }

            @Override
            public void downloadException(Exception e) {
                e.printStackTrace();

            }


            @Override
            public void onInstallStart() {

            }
        }).start();
    }





    /**
     * 定时器
     */
    public void sendDate() {
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                page--;
                if (page >= 0) {
                    mMain_tx.setText(getResources().getString(R.string.com_main_mouths) + " " + page + " " + "秒");
                    if (handler != null) {
                        handler.postDelayed(this, 1000);
                    }
                } else {
                    if (SPUtil.contain("Token")) {
                        ActionUtils.onStart(MainActivity.this, WorkListActivity.class);
                    } else {
                        ActionUtils.onStart(MainActivity.this, LoginActivity.class);
                    }
                }
            }
        };
        if (handler != null) {
            handler.postDelayed(runnable, 1000);
        }
        mMain_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                if (SPUtil.contain("Token")) {
                    ActionUtils.onStart(MainActivity.this, WorkListActivity.class);
                } else {
                    ActionUtils.onStart(MainActivity.this, LoginActivity.class);
                }
                handler = null;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (downloadUtils != null) {
//            downloadUtils.onDestroyThread();
//        }

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            //do something.
            return true;
        }else {
            return super.dispatchKeyEvent(event);
        }
    }

}
