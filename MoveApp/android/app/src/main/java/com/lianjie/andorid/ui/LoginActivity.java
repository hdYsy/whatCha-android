package com.lianjie.andorid.ui;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lianjie.andorid.R;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.LoginBean;
import com.lianjie.andorid.mvp.constract.IConstract_Login;
import com.lianjie.andorid.mvp.presenteriml.Presenter_LoginIml;
import com.lianjie.andorid.rnui.RNApplication;
import com.lianjie.andorid.utils.ActionUtils;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.KeyBarUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.SPUtil;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ADD_VOICEMAIL;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.PROCESS_OUTGOING_CALLS;
import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.USE_SIP;
import static android.Manifest.permission.WRITE_CALL_LOG;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/10
 * Time: 17:25
 * 初始页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, IConstract_Login.IView {
    private EditText mLoginEdphone;
    private EditText mLoginEdpwd;
    private Button mLoginSubmit;
    private Presenter_LoginIml presenter_loginIml;
    //退出时的时间
    private long mExitTime;

    @Override
    protected int onLayout() {
        return R.layout.activity_layout_page;
    }

    @Override
    protected void onDrishData() {
    }


    /**
     * 权限  获取权限
     */
    private void doCode() {
        //创建 rx权限实例
        RxPermissions rxPermissions = new RxPermissions(this);
        //动态申请权限
        rxPermissions.requestEach(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION,//定位
                READ_PHONE_STATE,
                CALL_PHONE,
                READ_CALL_LOG,
                WRITE_CALL_LOG,
                ADD_VOICEMAIL,
                USE_SIP,
                PROCESS_OUTGOING_CALLS,//手机
                READ_EXTERNAL_STORAGE,        //短信
                WRITE_EXTERNAL_STORAGE,
                CAMERA//相机
        ).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    // 用户已经同意该权限
                    Log.d("TAG", permission.name + " is granted.");
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // 用户拒绝了该权限，没选中不再询问,再次启动时，还会提示请求权限的对话框
                    Log.d("TAG", permission.name + " is denied. More info should be provided.");
                } else {
                    // 用户拒绝了该权限，并且选中不再询问
                    Log.d("TAG", permission.name + " is denied.");
                }
            }
        });
    }

    @Override
    protected void initData() {
        /**
         * 获取权限    6.0以上
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            doCode();
        }

        KeyBarUtils.getInstance().openKeyBoard(mLoginEdphone);
        presenter_loginIml = new Presenter_LoginIml(this);


    }

    @Override
    protected void initView() {
        mLoginEdphone = (EditText) findViewById(R.id.login_edphone);
        mLoginEdpwd = (EditText) findViewById(R.id.login_edpwd);
        mLoginSubmit = (Button) findViewById(R.id.login_submit);
        String phone = (String) SPUtil.readSP("Phone", "");
        mLoginEdphone.setText(phone);
        mLoginSubmit.setOnClickListener(this);
    }


    /**
     * 原生监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 返回键的实现方法
     */
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            DialogUtils.showToast(LoginActivity.this, getResources().getString(R.string.com_lowork_mouths));
            mExitTime = System.currentTimeMillis();
        } else {
            ActionUtils.onStartHome(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_submit:
                if (!Check.isFastClick())
                    return;
                DialogUtils.showWaiting(LoginActivity.this, getResources().getString(R.string.com_login_tips));
                onWork();
                break;
        }
    }

    /**
     * 逻辑判断  上传
     * map.get("mobile"));
     * map.put("password", map.get("password"));
     */
    private void onWork() {
        String phone = mLoginEdphone.getText().toString();
        String pwd = mLoginEdpwd.getText().toString();
        if (phone.length() == 11 && !phone.isEmpty() && !pwd.isEmpty() && pwd.length() >= 5 && pwd.length() < 20) {
            Map<String, Object> map = new HashMap<>();
            map.put("mobile", phone);
            map.put("password", pwd);
            GlobalInfoUtils.Phone = phone;
            GlobalInfoUtils.Password = phone;
            presenter_loginIml.setData(ComsentUtils.onSendLogin(map));
        } else {
            DialogUtils.hideWaiting(LoginActivity.this);
            DialogUtils.showToast(LoginActivity.this, getResources().getString(R.string.com_login_phonepwd));
        }


    }

    @Override
    public void showData(LoginBean loginBean) {
        if (("" + loginBean.getCode()).equals("1000")) {
            DialogUtils.hideWaiting(LoginActivity.this);
            SPUtil.addSP("Userid", loginBean.getData().getUserid());
            SPUtil.addSP("Phone", loginBean.getData().getPhone());
            SPUtil.addSP("Realname", loginBean.getData().getRealname());
            SPUtil.addSP("Status", loginBean.getData().getStatus());
            if (loginBean.getData().getThumb() != null) {
                SPUtil.addSP("Thumb", loginBean.getData().getThumb());
            } else {
                SPUtil.addSP("Thumb", "");
            }
            SPUtil.addSP("Token", loginBean.getData().getToken());
            SPUtil.addSP("Username", loginBean.getData().getUsername());
            SPUtil.addSP("face", loginBean.getData().getFace() + "");
            ActionUtils.onStart(LoginActivity.this, WorkListActivity.class);
        } else {
            DialogUtils.hideWaiting(LoginActivity.this);
            DialogUtils.showToast(LoginActivity.this, loginBean.getMsg());
            LogUtils.e("errorLogin" + loginBean.toString());
        }
    }

    @Override
    public void showError(Throwable error) {
        DialogUtils.hideWaiting(LoginActivity.this);
        DialogUtils.showToast(LoginActivity.this, getResources().getString(R.string.com_login_tips2));
    }


}
