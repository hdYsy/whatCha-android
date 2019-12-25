package com.lianjie.andorid.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.Adapter_WorkFragment;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.MsgPushBean;
import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.fragment.Fragment_success;
import com.lianjie.andorid.fragment.Fragment_thedoor;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_MSGpush;
import com.lianjie.andorid.rnui.RNActivity;
import com.lianjie.andorid.utils.ActionUtils;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.LocationUtils;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.Gps_Utils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.PhoneUtils;
import com.lianjie.andorid.utils.SPUtil;
import com.lianjie.andorid.utils.SystemDateUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.shizhefei.view.coolrefreshview.SimpleOnPullListener;
import com.shizhefei.view.coolrefreshview.header.MaterialHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/12
 * Time: 14:59
 */
public class WorkListActivity extends BaseActivity implements View.OnClickListener, Fragment_thedoor.CallBackValue {
    private long mExitTime;
    private TextView mWorkTx;
    private ViewPager mWorkVp;
    private ImageView mWordMy;
    private ImageView mWordBell;
    private TextView mTab1;
    //    private ImageView mTab1Bot;
    private TextView mTab2;
    private TextView jintian__;
    private TextView yiwancheng__;
    private WorkListActivity mContext;
    private View msgitem;
    private List<MsgPushBean.DataBeanX.DataBean> data;
    private CoolRefreshView pullto;
    private Adapter_WorkFragment adapter_workFragment;
    private Fragment_success fragment_success;
    private Fragment_thedoor fragment_thedoor;
    private List<WorkListBean.DataBean.ListBean> list;


    @Override
    protected int onLayout() {
        mContext = this;
        return R.layout.activity_ac_worklist_layout;
    }

    @Override
    protected void onDrishData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        onGps();
        Intent intent = getIntent();
        if (intent.getStringExtra("msg") != null) {
            String msg = intent.getStringExtra("msg");
            DialogUtils.showToast(mContext, msg);
        }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        //显示 已完成 就让他  重新加载
        if (GlobalInfoUtils.resumed) {
            onTab2();
            GlobalInfoUtils.resumed = false;
        } else {

        }

    }

    @Override
    protected void initData() {

        pullto.setPullHeader(new MaterialHeader(mContext));
        /**
         * 无责任刷新当前activity
         */
        pullto.addOnPullListener(new SimpleOnPullListener() {
            @Override
            public void onRefreshing(CoolRefreshView refreshView) {
                GlobalInfoUtils.mfirstVisibleItemPosition = 0;
                GlobalInfoUtils.LBS_page = 0;//lbs标签
                refreshActivity();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WorkListBean.DataBean.ListBean listBean = list.get(i);
                        String id = listBean.getId();
                        if (SPUtil.contain(id)) {//如果存了这个id
                            if (listBean.getDoingTag().equals("进行中")) {
                            } else { //判断存进去的sp是否是进行中的单子，如果是被后台上传的则把此ID的sp删掉
                                SPUtil.removeSP(id);
                            }
                        }
                    }
                }
            }


        });

    }

    /**
     * 无责任刷新当前Activity
     */
    private void refreshActivity() {
        Fragment currentFragment = adapter_workFragment.getCurrentFragment();
        if (currentFragment instanceof Fragment_success) {
            GlobalInfoUtils.resumed = currentFragment.isVisible();
        }

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        //登陆过来后 给GlobalInfoUtils 给值
        GlobalInfoUtils.Userid = (String) SPUtil.readSP("Userid", "");
        GlobalInfoUtils.Phone = (String) SPUtil.readSP("Phone", "");
        GlobalInfoUtils.Realname = (String) SPUtil.readSP("Realname", "");
        GlobalInfoUtils.Status = (String) SPUtil.readSP("Status", "");
        GlobalInfoUtils.Thumb = (String) SPUtil.readSP("Thumb", "");
        GlobalInfoUtils.Token = (String) SPUtil.readSP("Token", "");
        GlobalInfoUtils.Username = (String) SPUtil.readSP("Username", "");
        GlobalInfoUtils.FACE = (String) SPUtil.readSP("face", "");
        GlobalInfoUtils.tools_date = (String) SPUtil.readSP("tools_date", "");//存年月日


        mWorkTx = (TextView) findViewById(R.id.work_tx); //消息提醒
        jintian__ = (TextView) findViewById(R.id.jintian__);
        yiwancheng__ = (TextView) findViewById(R.id.yiwancheng__);
        mWorkVp = (ViewPager) findViewById(R.id.work_vp);
        mWordMy = (ImageView) findViewById(R.id.word_my);
        mWordBell = (ImageView) findViewById(R.id.word_bell);
        mTab1 = (TextView) findViewById(R.id.tab1);
//        mTab1Bot = (ImageView) findViewById(R.id.tab1_bot);
        mTab2 = (TextView) findViewById(R.id.tab2);
        pullto = (CoolRefreshView) findViewById(R.id.pullto);
        mTab2.setOnClickListener(this);
        mWorkVp.setOnClickListener(this);
        mWordMy.setOnClickListener(this);
        mWordBell.setOnClickListener(this);
        mWorkTx.setOnClickListener(this);
        mTab1.setOnClickListener(this);
//        mTab1Bot.setOnClickListener(this);
        List<Fragment> fragments = new ArrayList<>();
        fragment_success = new Fragment_success();
        fragment_thedoor = new Fragment_thedoor();
        fragments.add(fragment_thedoor);
        fragments.add(fragment_success);
        adapter_workFragment = new Adapter_WorkFragment(getSupportFragmentManager(), fragments);
        mWorkVp.setAdapter(adapter_workFragment);


    }


    /**
     * GPS  动态权限申请
     */
    private void onGps() {

//
        boolean oPen = Gps_Utils.getInstance().isOPen(this);
        if (!oPen) {
            DialogUtils.showDialog(this, getResources().getString(R.string.com_login_gps_title)
                    , getResources().getString(R.string.com_login_gps_msg));
        } else {
            Location location = LocationUtils.getLocation(this);
            if (location != null) {
                GlobalInfoUtils.latitude = location.getLatitude();
                GlobalInfoUtils.longitude = location.getLongitude();
                LocationUtils la = new LocationUtils();
                la.getLatLng(mContext);
            } else {
                LocationUtils la = new LocationUtils();
                la.getLatLng(mContext);
            }

        }
    }


    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            DialogUtils.showToast(WorkListActivity.this, getResources().getString(R.string.com_lowork_mouths));
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
            case R.id.word_my: //My页面
                if (!Check.isFastClick())
                    return;
                Intent intent = new Intent(mContext, RNActivity.class);
                GlobalInfoUtils.Page_RN = "Move_App_My";
                startActivityForResult(intent, 1);

//                SPUtil.clearSP();
//                Intent intent = new Intent(mContext,LoginActivity.class);
//                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
//                mContext.startActivity(intent);
                break;
            case R.id.word_bell:
                if (!Check.isFastClick())
                    return;
                onIntentDialog();
                break;
            case R.id.tab1:
                if (!Check.isFastClick())
                    return;
                onTab1();
                break;
            case R.id.tab2:
                if (!Check.isFastClick())
                    return;
                onTab2();
                break;
            case R.id.work_vp:
                break;
        }
    }


    private void onIntentDialog() {
        Presenter_MSGpush presenterMsGpush = new Presenter_MSGpush(new IConstract_Stract.IView() {
            @Override
            public <T> void showData(T t) {
                MsgPushBean msgPushBean = (MsgPushBean) t;
                if (msgPushBean.getCode() == 1000) {
                    //消息
                    Intent intent = new Intent(mContext, DialogActivity.class);
                    intent.putExtra("TYPE", "1");
                    startActivity(intent);
                } else if (msgPushBean.getCode() == 10011) {
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp((Activity) mContext);
                    DialogUtils.showToast(mContext, msgPushBean.getMsg());
                } else {
                    DialogUtils.showToast(mContext, "暂无消息通知");
                }

            }

            //
            @Override
            public void showError(Throwable error) {

            }
        });
        Map<String, Object> stringObjectMap = ComsentUtils.onsendMsg_PushList(1 + "");
        presenterMsGpush.setData(stringObjectMap);


    }


    /**
     * Tab1
     */
    private void onTab1() {
        int i1 = PhoneUtils.dip2px(mContext, 80); //缩略图
        mWorkVp.setCurrentItem(0);
        mTab1.setTextColor(getResources().getColor(R.color.color_move_4C90F3));
        mTab2.setTextColor(getResources().getColor(R.color.color_move_000000));
        jintian__.setVisibility(View.VISIBLE);
        yiwancheng__.setVisibility(View.GONE);
//        GlideUtils.getInstance().setImageWrap(mTab1Bot, this.getResources().getDrawable(R.mipmap.icon_arrow_sel_bot));

    }

    /**
     * Tab2
     */
    private void onTab2() {
        mWorkVp.setCurrentItem(1);
//        GlideUtils.getInstance().setImageWrap(mTab1Bot, this.getResources().getDrawable(R.mipmap.icon_arrowmormal));
        mTab2.setTextColor(getResources().getColor(R.color.color_move_4C90F3));
        mTab1.setTextColor(getResources().getColor(R.color.color_move_000000));
        yiwancheng__.setVisibility(View.VISIBLE);
        jintian__.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }
    }

    /**
     * @param strValue 总共 多少单
     * @param page     返工工单有多少
     * @param toolData
     * @param list
     */
    @Override
    public void SendMessageValue(int strValue, int page, WorkListBean.DataBean.ToolDataBean toolData, List<WorkListBean.DataBean.ListBean> list) {
        if (page == 0 && strValue > 0) {
            mWorkTx.setText("今天有" + strValue + "单，记得带工具哦～");
        } else if (page > 0 && strValue > 0) {
            mWorkTx.setText("今天还有" + strValue + "单，有" + page + "单需要返工 ~");
        } else if (strValue == 0) {
            mWorkTx.setText("暂无工单");
        }
        this.list = list;//数据源
        //工具弹窗
        if (!GlobalInfoUtils.tools_date.equals(SystemDateUtils.getNowDate() + "")) {
            DialogUtils.showToolsDialog(mContext, toolData);
        }

    }
}
