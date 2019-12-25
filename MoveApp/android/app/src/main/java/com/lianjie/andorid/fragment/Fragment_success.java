package com.lianjie.andorid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.Adapter_BottomRecycleView;
import com.lianjie.andorid.adapter.Adapter_SuccessRec;
import com.lianjie.andorid.adapter.Adapter_ThedoorRec;
import com.lianjie.andorid.adapter.time.widget.WheelView;
import com.lianjie.andorid.base.BaseFragment;
import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.bean.Work_SuccesBean;
import com.lianjie.andorid.mvp.constract.IConstract_WorkComment;
import com.lianjie.andorid.mvp.presenteriml.Presenter_WorkComentlml;
import com.lianjie.andorid.ui.DelatileActivity;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.SPUtil;
import com.lianjie.andorid.utils.SystemDateUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/13
 * Time: 18:53
 */
public class Fragment_success extends BaseFragment implements View.OnClickListener, IConstract_WorkComment.IView {
    private View inflate;
    private FragmentActivity activity;
    private TextView mSuccessDatatx;
    private LinearLayout mPupdata;
    private RecyclerView mRecycle;
    private Adapter_BottomRecycleView adapter;
    private Presenter_WorkComentlml presenter;
    private Adapter_SuccessRec success_adapter;
    private int day;
    private ImageView boxPagestate;
    private TextView success_datatx2;
    private int PageDay = 0;
    private WheelView mainWheelView;

    @Override
    protected void onDrishData() {

    }

    @Override
    protected View getLayout() {
        activity = getActivity();
        inflate = LayoutInflater.from(activity).inflate(R.layout.fragment_success, null, false);
        initView();
        initData();
        return inflate;
    }

    private void initPresenter(int page) {
        DialogUtils.showWaiting(activity, getResources().getString(R.string.com_login_tips));
        presenter = new Presenter_WorkComentlml(this);
        Map<String, Object> stringObjectMap = ComsentUtils.onSendComment(page);
        presenter.setData(stringObjectMap);
        ComsentUtils.onMsgPush2(getActivity());

    }

    private void initData() {
        success_adapter.setOncLick(new Adapter_SuccessRec.OncLick() {
            @Override
            public void OnclickListener(View v, int position) {
                if (!Check.isFastClick())
                    return;
                Work_SuccesBean date = success_adapter.getDate();
                Work_SuccesBean.DataBean.ListBean listBean = date.getData().getList().get(position);
                String id = listBean.getId();
                String doing = listBean.getDoing() + "";
                Intent intent = new Intent(activity, DelatileActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("doing", doing);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void initView() {
        mSuccessDatatx = (TextView) inflate.findViewById(R.id.success_datatx);
        success_datatx2 = (TextView) inflate.findViewById(R.id.success_datatx2);
        mPupdata = (LinearLayout) inflate.findViewById(R.id.pupdata);
        mRecycle = (RecyclerView) inflate.findViewById(R.id.recycle);
        boxPagestate = (ImageView) inflate.findViewById(R.id.boxPageState);
        mPupdata.setOnClickListener(this);
        String nowDate = SystemDateUtils.getNowDate();//当前时间
        mSuccessDatatx.setText(nowDate);
        mRecycle.setLayoutManager(new LinearLayoutManager(activity));
        success_adapter = new Adapter_SuccessRec(activity);
        mRecycle.setAdapter(success_adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pupdata:      //日期
                if (!Check.isFastClick())
                    return;
                onDialog();
                break;


        }
    }

    /**
     * Dialog 弹出
     */
    private void onDialog() {


//        SPUtil.readSP()
        int days = SystemDateUtils.getDays();
        final List<String> list = new ArrayList<>();
        for (int i = days; i >= 1; i--) {
            if(i <= SystemDateUtils.getDay()){
                list.add(i + "");
            }
        }
        View view = DialogUtils.onBottomDialog(activity, list, 3);
        final TextView bottom_confirm = view.findViewById(R.id.bottom_confirm);  //确定
        final TextView bottom_cancel = view.findViewById(R.id.bottom_cancel);    //取消
        mainWheelView = view.findViewById(R.id.main_wheelview);        //列表View
        bottom_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                int currentPosition = mainWheelView.getCurrentPosition();
                initPresenter(Integer.parseInt(list.get(currentPosition))); //-1 就是 中间的 数据
                    mSuccessDatatx.setText(SystemDateUtils.getMonth() + "月" + (Integer.parseInt(list.get(currentPosition)) + "日"));
                GlobalInfoUtils.day = Integer.parseInt(list.get(currentPosition));//得到日期
                DialogUtils.closeLJdialog();
            }
        });
        bottom_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                DialogUtils.closeLJdialog();
            }
        });
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (GlobalInfoUtils.day == 0) {
                if (s.equals("" + SystemDateUtils.getDay())) {
                    mainWheelView.setSelection(i);//跳转至当天
                }
            } else {
                if (s.equals("" + GlobalInfoUtils.day)) {
                    mainWheelView.setSelection(i);//缓存的 位置
                }
            }
        }
    }

    @Override
    public void showData(Work_SuccesBean workListBean) {
        DialogUtils.hideWaiting(activity);
        int code = workListBean.getCode();
        switch (code) {
            case 1000:  //成功
                success_datatx2.setText("(" + workListBean.getData().getCount() + "单)");
                boxPagestate.setVisibility(View.GONE);
                mRecycle.setVisibility(View.VISIBLE);
                DialogUtils.showToast(activity, workListBean.getMsg());
                success_adapter.setData(workListBean);
                break;
            case 1001: //没有数据
                success_datatx2.setText("(" + PageDay + "单)");
                boxPagestate.setVisibility(View.VISIBLE);
                mRecycle.setVisibility(View.GONE);
                DialogUtils.showToast(activity, workListBean.getMsg());
                success_adapter.setData(workListBean);
                break;
            case 10011://注销登陆
//                success_datatx2.setText("(" + PageDay + "单)");
                //            账户权限验证失败
                DialogUtils.showToast(activity, workListBean.getMsg());
                GlobalInfoUtils.clearApp(getActivity());

                break;
        }

    }

    @Override
    public void showError(Throwable error) {
        success_datatx2.setText("(" + PageDay + ")");
        DialogUtils.hideWaiting(activity);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (GlobalInfoUtils.day == 0) {
                day = SystemDateUtils.getDay();
            } else {
                day = GlobalInfoUtils.day;
                mSuccessDatatx.setText(SystemDateUtils.getMonth() + "月" + day+ "日");
            }

            initPresenter(day);

        }

    }



}
