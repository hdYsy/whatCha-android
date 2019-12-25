package com.lianjie.andorid.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.AdapterRecycle_Tools;
import com.lianjie.andorid.adapter.time.adapter.ArrayWheelAdapter;
import com.lianjie.andorid.adapter.time.util.WheelUtils;
import com.lianjie.andorid.adapter.time.widget.WheelView;
import com.lianjie.andorid.bean.WorkListBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by markliu on 2019/2/25.
 */

public class DialogUtils {


    private static AlertDialog showdialog;
    private static ProgressDialog waitingDialog = null;
    private static Dialog dialog_main;
    private static List<Integer> list;
    private static Dialog dialog;
    private static WheelView mainWheelView;
    private static Toast toast;
    private static View toastRoot;


    public static AlertDialog createDialog(Context pContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(pContext, R.style.com_wthk_dialog_MyDialogStyle);
        final AlertDialog progressDialog = builder.create();
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    /**
     * 关闭弹出框
     */
    public static void closeProDialog() {
        try {
            if (showdialog != null) {
                showdialog.dismiss();
            }
        } catch (Exception e) {
            LogUtils.d(e + "");
        }
    }

    /**
     * 显示toast提示
     *
     * @param context 上下文
     * @param tips    弹出提示
     */
    public static void showToast(Context context, String tips) {
        if (toast == null) {
            toast = new Toast(context);
            toastRoot = LayoutInflater.from(context).inflate(R.layout.com_wthk_toast, null);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(toastRoot);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        TextView tv = (TextView) toastRoot.findViewById(R.id.toast_notice);
        tv.setText(tips);
        toast.show();
    }

    /**
     * 显示等待提示
     *
     * @param context 上下文
     * @param tips    弹出提示
     */
    @SuppressLint("RestrictedApi")
    public static void showWaiting(Context context, String tips) {
        if (context instanceof Activity) {
            if(((Activity) context).isDestroyed())
                return;
            if (waitingDialog == null) {
                waitingDialog = new ProgressDialog(context);
                waitingDialog.setTitle("");
                waitingDialog.setMessage(tips);
                waitingDialog.setIndeterminate(true);
                waitingDialog.setCancelable(false);
            }
            waitingDialog.show();

        } else{
            if (waitingDialog == null) {
                waitingDialog = new ProgressDialog(context);
                waitingDialog.setTitle("");
                waitingDialog.setMessage(tips);
                waitingDialog.setIndeterminate(true);
                waitingDialog.setCancelable(false);
            }
            waitingDialog.show();

        }



    }


    public static void showDialog(final Context context, String title, String msg) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(msg)
                //  取消选项
                .setNegativeButton(context.getResources().getString(R.string.com_login_gps_clo), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 关闭dialog
                        dialogInterface.dismiss();
                        //并且需要 退出程序
                        ActionUtils.onStartHome(context);

                    }
                })
                //  确认选项
                .setPositiveButton(context.getResources().getString(R.string.com_login_gps_sta), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //跳转到手机原生设置页面   ACTION_LOCATION_SOURCE_SETTINGS  设置GPS
                        ActionUtils.onStartStrting(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    }
                })
                .setCancelable(false)
                .show();
    }


    /**
     * 隐藏等待提示
     *
     * @param context 上下文
     */
    public static void hideWaiting(Context context) {
        if (context instanceof Activity) {
            Activity activity= (Activity) context;
            if(!activity.isDestroyed()){
                if (waitingDialog != null) {
                    waitingDialog.dismiss();
                    waitingDialog = null;
                }else{
                    return;
                }
            }
        }else{
                if (waitingDialog != null) {
                    waitingDialog.dismiss();
                    waitingDialog = null;
            }
        }

    }


    public static void closedialog() {
        if (dialog_main != null)
            dialog_main.dismiss();
    }

    /**
     * Word_Data列表的弹窗
     *
     * @param context
     * @return
     */
    public static View onBottomDialog(Context context, List<String> data, int page) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.com_wthk_bottom, null);
        mainWheelView = inflate.findViewById(R.id.main_wheelview);
        mainWheelView.setWheelAdapter(new ArrayWheelAdapter(context));
        mainWheelView.setSkin(WheelView.Skin.Holo);
        mainWheelView.setWheelData(data);
        mainWheelView.setWheelSize(page);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 18;
        style.textSize = 16;
        style.holoBorderColor = context.getResources().getColor(R.color.color_move_CACACA);
        mainWheelView.setStyle(style);
        list = new ArrayList<>();
        list.add(Gravity.BOTTOM);
        list.add(LinearLayout.LayoutParams.MATCH_PARENT);
        list.add(300);
        //回调
        LJSHdialog(context, inflate, list);
        return inflate;
    }


    /**
     * NoScrosess 的 两个底部导航栏
     *
     * @param context
     * @return
     */
    public static View onNoScrosessBottomDialog(Context context, List<String> data, int page) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.com_wthk_bottom_nosucess, null);
        mainWheelView = inflate.findViewById(R.id.main_wheelview);
        mainWheelView.setWheelAdapter(new ArrayWheelAdapter(context));
        mainWheelView.setSkin(WheelView.Skin.Holo);
        mainWheelView.setWheelData(data);
        mainWheelView.setWheelSize(page);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 18;
        style.textSize = 16;
        style.holoBorderColor = context.getResources().getColor(R.color.color_move_CACACA);
        mainWheelView.setStyle(style);
        list = new ArrayList<>();
        list.add(Gravity.BOTTOM);
        list.add(LinearLayout.LayoutParams.MATCH_PARENT);
        list.add(300);
        //回调
        LJSHdialog(context, inflate, list);
        return inflate;
    }


    /**
     * 弹框的基础回调
     *
     * @param context
     * @param inflate
     * @param list
     */
    private static void LJSHdialog(Context context, View inflate, List<Integer> list) {
        dialog = new Dialog(context);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(list.get(0));
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离————bottom 必须要设置这个background为null 底部才能不铺满
        dialog.getWindow().setBackgroundDrawable(null);
        if (list.get(2) == LinearLayout.LayoutParams.WRAP_CONTENT) {
            dialog.getWindow().setLayout(list.get(1), LinearLayout.LayoutParams.WRAP_CONTENT);
        } else {
            dialog.getWindow().setLayout(list.get(1), PhoneUtils.dip2px(context, list.get(2)));
        }
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }


    //中间显示的dialog
    public static View showCentreDialog(Context context, String... string) {
        //自定义dialog显示布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.com_work_dialog, null);
        TextView viewById = inflate.findViewById(R.id.tvUID);
        TextView tvName = inflate.findViewById(R.id.tvName);
        LinearLayout ll_bottom = inflate.findViewById(R.id.ll_bottom);//第一个
        LinearLayout linear_bottom = inflate.findViewById(R.id.linear_bottom);//第二个
        viewById.setText(string[0]);
        tvName.setText("提示");
        //自定义dialog显示风格
        dialog = new Dialog(context, R.style.DialogCentre);
        dialog.setContentView(inflate);
        if (string.length == 2) {
            linear_bottom.setVisibility(View.VISIBLE);
            ll_bottom.setVisibility(View.GONE);
            //点击其他区域消失
            dialog.setCanceledOnTouchOutside(false);

        } else {
            linear_bottom.setVisibility(View.GONE);
            ll_bottom.setVisibility(View.VISIBLE);
            //点击其他区域消失
            dialog.setCanceledOnTouchOutside(true);
        }

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
        return inflate;
    }


    //中间显示的dialog
    public static View showCenterTishi(Context context) {
        //自定义dialog显示布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.center_item, null);
        //自定义dialog显示风格
        dialog = new Dialog(context, R.style.DialogCentre);
        //点击其他区域消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
        return inflate;
    }

    //中间显示的dialog  地图 温馨提示
    public static View showCenterMap(Context context, String str) {
        //自定义dialog显示布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.center_item_map, null);
        TextView viewById = inflate.findViewById(R.id.tvUID);
        viewById.setText(str);
        //自定义dialog显示风格
        dialog = new Dialog(context, R.style.DialogCentre);
        //点击其他区域消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
        return inflate;
    }


    //中间显示的dialog  —— 强提醒 /列表提醒
    public static View showQiang_storge(Activity context, String tvName, String tvUID, String it_tx) {
        //自定义dialog显示布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.center_qiang_storge, null);
        TextView viewById = inflate.findViewById(R.id.it_tx);  //按钮文字
        TextView tvUID_ = inflate.findViewById(R.id.tvUID);      //msg
        TextView tvNameview = inflate.findViewById(R.id.tvName);      //提示文字
        tvNameview.setText(tvName);
        tvUID_.setText(tvUID);
        viewById.setText(it_tx);

        //自定义dialog显示风格
        dialog = new Dialog(context, R.style.DialogCentre);
        //点击其他区域消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
        return inflate;
    }


    private static Activity getActivity(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        } else
            return null;
    }

    /**
     * 关闭 dialog
     */
    public static void closeLJdialog() {
        try {
            if (dialog != null) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            LogUtils.d(e + "");
        }
    }

    /**
     * Word_Data列表的弹窗
     *
     * @param context
     * @param villageaddress
     * @param latitude
     * @param longitude      @return
     */
    public static View onLBSBottomDialog(final Context context, final String villageaddress, final double latitude, final double longitude) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_lbs_mapbottom, null);
        list = new ArrayList<>();
        list.add(Gravity.BOTTOM);
        list.add(LinearLayout.LayoutParams.MATCH_PARENT);
        list.add(LinearLayout.LayoutParams.WRAP_CONTENT);
        //回调
        LJSHdialog(context, inflate, list);
        //取消dialog
        inflate.findViewById(R.id.cannel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLJdialog();
            }
        });
        //腾讯地图
        inflate.findViewById(R.id.txmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MapUtil.isTencentMapInstalled()) {
                    MapUtil.openTencentMap(context, 0, 0, null, latitude, longitude, villageaddress);
                } else {
                    //这里必须要写逻辑，不然如果手机没安装该应用，程序会闪退，这里可以实现下载安装该地图应用
                    showToast(context, "尚未安装腾讯地图");
                    ActionUtils.onStartTencent((Activity) context);
                }
            }
        });
        //高德地图
        inflate.findViewById(R.id.gdmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MapUtil.isGdMapInstalled()) {
                    MapUtil.openGaoDeNavi(context, 0, 0, null, latitude, longitude, villageaddress);
                } else {
                    //这里必须要写逻辑，不然如果手机没安装该应用，程序会闪退，这里可以实现下载安装该地图应用
                    showToast(context, "尚未安装高德地图");
                    ActionUtils.onStartGaode((Activity) context);
                }
            }
        });
        return inflate;
    }


    //中间显示的dialog  —— 强提醒 /列表提醒
    public static View showToolsDialog(final Activity context, WorkListBean.DataBean.ToolDataBean data) {
        //自定义dialog显示布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_center_tools, null);
        final CheckBox check = inflate.findViewById(R.id.check);//多选框
        TextView my_bt = inflate.findViewById(R.id.my_bt);//我知道了
        final RecyclerView recycle_tools = inflate.findViewById(R.id.recycle_tools);//RecycleView
        final ImageView alpha_img = inflate.findViewById(R.id.alpha_img);//RecycleView
         LinearLayout work_jinri = inflate.findViewById(R.id.work_jinri);//RecycleView
        ViewTreeObserver vto = recycle_tools.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {//监听获取视图树观察者在全局布局侦听器上
            @Override
            public void onGlobalLayout() {
                recycle_tools.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (recycle_tools.getHeight()> WheelUtils.dip2px(context, 180)) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,WheelUtils.dip2px(context, 180));
                    recycle_tools.setLayoutParams(layoutParams);
                }
            }
        });

        if (data==null) {
            work_jinri.setVisibility(View.GONE);
        }else{
            work_jinri.setVisibility(View.VISIBLE);
            recycle_tools.setLayoutManager(new LinearLayoutManager(context));
            AdapterRecycle_Tools adapterRecycle_tools = new AdapterRecycle_Tools(context, data);
            recycle_tools.setAdapter(adapterRecycle_tools);
        }
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(R.drawable.clickme).apply(options).into(alpha_img);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {
                    alpha_img.setVisibility(View.INVISIBLE);
                } else {
                    alpha_img.setVisibility(View.VISIBLE);
                }
            }
        });
        my_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {
                    DialogUtils.closeLJdialog();
                   SPUtil.addSP("tools_date",SystemDateUtils.getNowDate());//存年月日
                } else {
                    showToast(context, context.getResources().getString(R.string.tools_bt));
                }

            }
        });

        //自定义dialog显示风格
        dialog = new Dialog(context, R.style.DialogCentre);
        //点击其他区域消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(inflate);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
        return inflate;
    }


}
