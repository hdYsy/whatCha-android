package com.lianjie.andorid.rnui.moudle;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.lianjie.andorid.R;
import com.lianjie.andorid.rnui.RNActivity;
import com.lianjie.andorid.ui.Address_ReycleActivity;
import com.lianjie.andorid.ui.Addresss_Map_Activity;
import com.lianjie.andorid.ui.DelatileActivity;
import com.lianjie.andorid.ui.LoginActivity;
import com.lianjie.andorid.ui.MainActivity;
import com.lianjie.andorid.ui.TrainingVideoActivity;
import com.lianjie.andorid.ui.WorkListActivity;
import com.lianjie.andorid.utils.DataCleanManager;
import com.lianjie.andorid.utils.DataclearManagerUtils;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideCacheUtil;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.PhotoUtils;
import com.lianjie.andorid.utils.SPUtil;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import io.reactivex.functions.Consumer;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/8/8
 * Time: 10:16
 * //添加RN注解(@ReactMethod)，否则该方法将不被添加到RN中
 */
public class MyIntentMoudle extends ReactContextBaseJavaModule {

    private Activity mContext;
    private Uri uri;

    public MyIntentMoudle(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        //React 调用 需要 这个name
        return "IntentModule";
    }


    @ReactMethod
    public void startActivityFromJS(String name, String params) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (null != currentActivity) {
                Class toActivity = Class.forName(name);
                Intent intent = new Intent(currentActivity, toActivity);
                intent.putExtra("params", params);
                currentActivity.startActivity(intent);
            }
        } catch (Exception e) {
            throw new JSApplicationIllegalArgumentException(
                    "不能打开Activity : " + e.getMessage());
        }
    }

    /**
     * ,GlobalInfoUtils.latitude,GlobalInfoUtils.longitude
     * callback 回调数据
     */
    @ReactMethod
    public void toRNdata(Callback result) {
        Activity currentActivity = getCurrentActivity();
        result.invoke(GlobalInfoUtils.Userid, GlobalInfoUtils.Token, GlobalInfoUtils.Realname, DataclearManagerUtils.getTotalCacheSize(currentActivity));
    }

    @ReactMethod
    public void toRnLat(Callback result) {
        Activity currentActivity = getCurrentActivity();
        result.invoke(GlobalInfoUtils.Userid, GlobalInfoUtils.Token, GlobalInfoUtils.Realname, GlobalInfoUtils.latitude, GlobalInfoUtils.longitude);
    }

    /**
     * 0 是开始  1 是结束 网络请求的转圈dialog
     */
    @ReactMethod
    public void AdbLoading(int TAB) {
        mContext = getCurrentActivity();

        if (TAB == 0) {
            DialogUtils.showWaiting(mContext, mContext.getResources().getString(R.string.com_login_tips));
        } else {
            DialogUtils.hideWaiting(mContext);
        }
    }

    /**
     * andoird  自定义Toast
     *
     * @param msg
     */
    @ReactMethod
    public void AdbToast(String msg) {
        mContext = getCurrentActivity();
        DialogUtils.showToast(mContext, msg);
    }

    /**
     * andoird   消息推送
     */
    @ReactMethod
    public void AdbMsg() {
        mContext = getCurrentActivity();
        ComsentUtils.onMsgPush2(mContext);
    }

    /**
     * andoird   点击大图
     */
    @ReactMethod
    public void AdbImage(String url, int position) {
        mContext = getCurrentActivity();
        List<String> list = new ArrayList<>();
        list.add(url);
        GlobalInfoUtils.onImageEnlarge(list, position, mContext, GlobalInfoUtils.ImageDa);
    }


    /**
     * andoird   调用系统相机 进行拍照上传头像
     */
    @ReactMethod
    public void AdbCamera(int Type, Callback result) {
        mContext = getCurrentActivity();
        RNActivity.setType(Type, result);

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            final String[] perms = {"android.permission.CAMERA"};

            Handler handler = new Handler(Looper.getMainLooper());
            class MyThread implements Runnable {
                public void run() {
                    onDataCamera();
                }

                private void onDataCamera() {
                    RxPermissions rxPermissions = new RxPermissions(mContext);
                    //动态申请权限
                    rxPermissions.requestEach(perms).subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) {
                            if (permission.granted) {
                                PhotoUtils.openCamera(mContext, 1, 1);

                                DialogUtils.closeLJdialog();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没选中不再询问,再次启动时，还会提示请求权限的对话框
                                Log.d("TAG", permission.name + " is denied. More info should be provided.");
                            } else {
                                // 用户拒绝了该权限，并且选中不再询问
                                View view = DialogUtils.showCentreDialog(mContext, "是否跳转至设置页打开权限(无权限将导致拍照功能无法使用！！！");
                                final View tvSendMSG = view.findViewById(R.id.tvSendMSG);
                                final View tvAdd = view.findViewById(R.id.tvAdd);
                                //取消 将关闭弹窗 并且finish
                                View.OnClickListener onClickListener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
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
                }
            }
            handler.post(new MyThread());//调用方法发送消息（原来想要执行的地方


        } else {
            PhotoUtils.openCamera(mContext, 1, 1);

        }


    }


    /**
     * andoird  调用
     */
    @ReactMethod
    public void onPhotoID(int Type, Callback result) {
        mContext = getCurrentActivity();
        RNActivity.setType(Type, result);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            final String[] perms = {"android.permission.CAMERA"};

            Handler handler = new Handler(Looper.getMainLooper());
            class MyThread implements Runnable {
                public void run() {
                    onDataCamera();
                }

                private void onDataCamera() {
                    RxPermissions rxPermissions = new RxPermissions(mContext);
                    //动态申请权限
                    rxPermissions.requestEach(perms).subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) {
                            if (permission.granted) {
                                PhotoUtils.openPhotos(mContext, 1, 3, null);

                                DialogUtils.closeLJdialog();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没选中不再询问,再次启动时，还会提示请求权限的对话框
                                Log.d("TAG", permission.name + " is denied. More info should be provided.");
                            } else {
                                // 用户拒绝了该权限，并且选中不再询问
                                View view = DialogUtils.showCentreDialog(mContext, "是否跳转至设置页打开权限(无权限将导致拍照功能无法使用！！！");
                                final View tvSendMSG = view.findViewById(R.id.tvSendMSG);
                                final View tvAdd = view.findViewById(R.id.tvAdd);
                                //取消 将关闭弹窗 并且finish
                                View.OnClickListener onClickListener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
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
                }
            }
            handler.post(new MyThread());//调用方法发送消息（原来想要执行的地方


        } else {
            PhotoUtils.openPhotos(mContext, 1, 3, null);
        }


    }


    /**
     * andoird   初始化Load页面~
     */
    @ReactMethod
    public void AdbLoadout() {
        mContext = getCurrentActivity();
        SPUtil.clearSP();
//        GlideCacheUtil.getInstance().clearImageAllCache(mContext);
        SPUtil.addSP("Phone", GlobalInfoUtils.Phone);//添加上次登录的手机号保存记录
        DataCleanManager.clearAllCache(mContext);
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        //清理所有缓存  会清除所有数据但是会关闭App
//        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
//        manager.clearApplicationUserData();
    }

    /**
     * andoird   回到首页
     */
    @ReactMethod
    public void AdbFinish() {
        mContext = getCurrentActivity();
        mContext.setResult(100);
        mContext.finish();
    }

    /**
     * andoird   日期选择模块
     */
    @ReactMethod
    public void AdbDate(final String datestr, final Callback result) {
//        dataToCalendar
        mContext = getCurrentActivity();
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
//                        Date是个时间截，无法直接提供整型形式的年、月、日，要提取的话必须创建一个Calendar对象，用Date去配置它，再从Calendar中取得相应信息:

                        StringBuffer str = new StringBuffer();
                        Calendar calendar = Calendar.getInstance();

                        calendar.setTime(date);
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        month += 1;//月份需要加1
                        str.append(year + "-");

                        if (month < 10) {
                            str.append("0" + month + "-");
                        } else {
                            str.append(month + "-");
                        }
                        if (day < 10) {
                            str.append("0" + day);
                        } else {
                            str.append(day);
                        }
                        String s = str.toString();
                        result.invoke(s);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                        .build();
                if (!datestr.isEmpty()) {
                    Calendar calendar = GlobalInfoUtils.dataToCalendar(datestr);
                    pvTime.setDate(calendar);//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                }
                pvTime.show();
            }
        });
    }

    //默认的日期传过去
    @ReactMethod
    public void AdbDate_defaultTime(final Callback result) {
        mContext = getCurrentActivity();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month += 1;
        result.invoke(year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day));
    }

    //跳转到 详情页
    @ReactMethod
    public void AdbStartDelatiles(String id, String oid) {
        mContext = getCurrentActivity();
        Intent intent = new Intent(mContext, DelatileActivity.class);
        intent.putExtra("id", "");
        intent.putExtra("oid", oid);
        mContext.startActivityForResult(intent, GlobalInfoUtils.RNCode);
    }

    /**
     * 回传 看看点击了那个键  1 是确定 0是取消  默认是0
     *
     * @param str
     * @param result
     */

    @ReactMethod
    public void AdbCenterDialog(String str, final Callback result) {
        mContext = getCurrentActivity();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.com_work_dialog, null);
        TextView viewById = inflate.findViewById(R.id.tvUID);
        TextView tvName = inflate.findViewById(R.id.tvName);
        viewById.setText(str);
        tvName.setText("提示");
        //自定义dialog显示风格
        final Dialog dialog = new Dialog(mContext, R.style.DialogCentre);
        //点击其他区域消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
        TextView tvSendMSG = inflate.findViewById(R.id.tvSendMSG);
        TextView tvAdd = inflate.findViewById(R.id.tvAdd);
        LinearLayout ll_bottom = inflate.findViewById(R.id.ll_bottom);
        ll_bottom.setVisibility(View.VISIBLE);
        tvSendMSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.invoke(0);
                dialog.dismiss();
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.invoke(1);
                dialog.dismiss();

            }
        });

    }


    /**
     * andoird   调用系统相机  拍照
     */
    @ReactMethod
    public void AdbImageCamere(String id, Callback result) {
        if (id == null) {
            DialogUtils.showToast(mContext, "ID为null");
            return;
        }
        RNActivity.setID(id, result);
//
        mContext = getCurrentActivity();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            final String[] perms = {"android.permission.CAMERA"};

            final Handler handler = new Handler(Looper.getMainLooper());
            class MyThread implements Runnable {
                public void run() {
                    onDataCamera();
                }

                private void onDataCamera() {
                    RxPermissions rxPermissions = new RxPermissions(mContext);
                    //动态申请权限
                    rxPermissions.requestEach(perms).subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) {
                            if (permission.granted) {
                                PhotoUtils.openCamera(mContext, 1, 2);

                                DialogUtils.closeLJdialog();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没选中不再询问,再次启动时，还会提示请求权限的对话框
                                Log.d("TAG", permission.name + " is denied. More info should be provided.");
                            } else {
                                // 用户拒绝了该权限，并且选中不再询问
                                View view = DialogUtils.showCentreDialog(mContext, "是否跳转至设置页打开权限(无权限将导致拍照功能无法使用！！！");
                                final View tvSendMSG = view.findViewById(R.id.tvSendMSG);
                                final View tvAdd = view.findViewById(R.id.tvAdd);
                                //取消 将关闭弹窗 并且finish
                                View.OnClickListener onClickListener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
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
                }
            }
            handler.post(new MyThread());//调用方法发送消息（原来想要执行的地方


        } else {
            PhotoUtils.openCamera(mContext, 1, 2);

        }
    }

    /**
     * andoird   调用系统相机  拍照
     */
    @ReactMethod
    public void AdbImageCamere_PhotoImage(String id, Callback result) {
        RNActivity.setID(id, result);
//
        mContext = getCurrentActivity();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            final String[] perms = {"android.permission.READ_EXTERNAL_STORAGE"};

            final Handler handler = new Handler(Looper.getMainLooper());
            class MyThread implements Runnable {
                public void run() {
                    onDataCamera();
                }

                private void onDataCamera() {
                    RxPermissions rxPermissions = new RxPermissions(mContext);
                    //动态申请权限
                    rxPermissions.requestEach(perms).subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) {
                            if (permission.granted) {
                                PhotoUtils.openPhoto(mContext, 2, new ArrayList<LocalMedia>());

                                DialogUtils.closeLJdialog();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没选中不再询问,再次启动时，还会提示请求权限的对话框
                                Log.d("TAG", permission.name + " is denied. More info should be provided.");
                            } else {
                                // 用户拒绝了该权限，并且选中不再询问
                                View view = DialogUtils.showCentreDialog(mContext, "是否跳转至设置页打开权限(无权限将导致拍照功能无法使用！！！");
                                final View tvSendMSG = view.findViewById(R.id.tvSendMSG);
                                final View tvAdd = view.findViewById(R.id.tvAdd);
                                //取消 将关闭弹窗 并且finish
                                View.OnClickListener onClickListener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
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
                }
            }
            handler.post(new MyThread());//调用方法发送消息（原来想要执行的地方


        } else {
            PhotoUtils.openPhoto(mContext, 2, new ArrayList<LocalMedia>());

        }
    }

    /**
     * 传过去的参数 ： 参数id   纬度   经度
     *
     * @param result
     */
    @ReactMethod
    public void AdbMultID(Callback result) {
        result.invoke(GlobalInfoUtils.multServer_id + "", GlobalInfoUtils.latitude + ""
                , GlobalInfoUtils.longitude + "", GlobalInfoUtils.Userid, GlobalInfoUtils.Token);
    }


    /**
     * 跳转到地图
     * /*  点击现居地址所需文案提示
     * <p>
     * 1 暂无                  >>>>>    无
     * 2 审核通过的地址         >>>>>    您好，您的当前住址为[已审核通过住址]，是否需要修改？
     * 3（审核中）暂无         >>>>>>    您好，您的新住址[新住址]正在审核中，是否需要修改？
     * 4（审核中）已审过地址    >>>>>>   您好，您的新住址[新住址]正在审核中，是否需要修改？
     * 5（审核失败）已审过地址  >>>>>>	  您好，您的新住址[新住址]审核失败，请您核实提交的住址是否准确和详细，修改后再次重新提交审核！
     * 6（审核失败）暂无       >>>>>>    您好，您的新住址[新住址]审核失败，请您核实提交的住址是否准确和详细，修改后再次重新提交审核！
     */
    @ReactMethod
    public void AdbMap(String str, String str1) {
        final Activity currentActivity = getCurrentActivity();

        if (str.isEmpty()) {
            currentActivity.startActivity(new Intent(currentActivity, Addresss_Map_Activity.class));
        } else if (str1.equals("1")) {
            str = ("您好，您的当前住址为【" + str + "】，是否需要修改？");
            View view = DialogUtils.showCenterMap(currentActivity, str);
            TextView tvadd = view.findViewById(R.id.tvAdd);
            TextView tvload = view.findViewById(R.id.tvload);
            tvadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.closeLJdialog();
                }
            });
            tvload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.closeLJdialog();
                    currentActivity.startActivity(new Intent(currentActivity, Addresss_Map_Activity.class));
                }
            });
        } else if (str1.equals("2")) {
            str = ("您的新住址【" + str + "】审核失败，请您核实提交的住址是否准确和详细，修改后再次重新提交审核！");
            View view = DialogUtils.showCenterMap(currentActivity, str);
            TextView tvadd = view.findViewById(R.id.tvAdd);
            TextView tvload = view.findViewById(R.id.tvload);
            tvadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.closeLJdialog();
                }
            });
            tvload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.closeLJdialog();
                    currentActivity.startActivity(new Intent(currentActivity, Addresss_Map_Activity.class));
                }
            });
        } else if (str1.equals("0")) {
            str = ("您好，您的新住址【" + str + "】，正在审核中，是否需要修改？");
            View view = DialogUtils.showCenterMap(currentActivity, str);
            TextView tvadd = view.findViewById(R.id.tvAdd);
            TextView tvload = view.findViewById(R.id.tvload);
            tvadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.closeLJdialog();
                }
            });
            tvload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.closeLJdialog();
                    currentActivity.startActivity(new Intent(currentActivity, Addresss_Map_Activity.class));
                }
            });
        }

    }


    /**
     * 培训视频
     */
    @ReactMethod
    public void TrainingVideo() {
        Activity currentActivity = getCurrentActivity();
        currentActivity.startActivity(new Intent(currentActivity, TrainingVideoActivity.class));
    }

    /**
     * 培训视频
     */
    @ReactMethod
    public void AdbMain() {
        Activity currentActivity = getCurrentActivity();
        currentActivity.startActivity(new Intent(currentActivity, WorkListActivity.class));
    }

    /**
     * 培训视频
     */
    @ReactMethod
    public void AdbCachClear(final Callback result) {
        mContext = getCurrentActivity();
        final String str = ("点击【确定】会将人脸验证信息和未提交的图片信息等缓存删除，望慎重");
        View view = DialogUtils.showCenterMap(mContext, str);
        TextView tvadd = view.findViewById(R.id.tvAdd);
        TextView tvload = view.findViewById(R.id.tvload);
        TextView title = view.findViewById(R.id.title);
        title.setText("确定删除所有缓存信息么？");
        tvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.closeLJdialog();
            }
        });
        tvload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.closeLJdialog();
                Map<String, ?> allSP = SPUtil.getAllSP();
                Set<String> strings = allSP.keySet();
                for (String string : strings) {
                    char c = string.charAt(0);
                    switch (c) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            SPUtil.removeSP(string);
                            if(SPUtil.contain("date"))
                            SPUtil.removeSP("date");
                            break;
                    }
                }
                boolean b = DataclearManagerUtils.clearAllCache(mContext);//清除缓存
                if (b) {
                    DialogUtils.showToast(mContext, "清除缓存成功");
                }

                result.invoke(b);

            }
        });
    }


}
