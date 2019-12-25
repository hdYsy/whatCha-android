package com.lianjie.andorid.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.Adapter_ThedoorRec;
import com.lianjie.andorid.base.BaseFragment;
import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.mvp.constract.IConstract_WorkList;
import com.lianjie.andorid.mvp.presenteriml.Presenter_WorkListIml;
import com.lianjie.andorid.ui.LBsNavigation;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.Gps_Utils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/13
 * Time: 18:53
 */
public class Fragment_thedoor extends BaseFragment implements IConstract_WorkList.IView {
    private View inflate;
    private FragmentActivity activity;
    private RecyclerView mMhedoor_recycle;
    private Presenter_WorkListIml presenter_workListIml;


    private Adapter_ThedoorRec adapter;

    private ImageView boxPageState;
    private CallBackValue callBackValue1;
    private LinearLayoutManager linearLayoutManager;
    private ImageView map_navigation;
    private LinearLayout linear_lbs;
    private LinearLayout person;
    private LinearLayout qixing;
    private LinearLayout jiache;
    private ArrayList<View> lbsList;

    @Override
    protected void onDrishData() {

    }

    @Override
    protected View getLayout() {
        activity = getActivity();
        inflate = LayoutInflater.from(activity).inflate(R.layout.fragment_thedoor, null, false);
        initPresenter();
        initView();
        initData();
        return inflate;
    }


    private void initPresenter() {
        DialogUtils.showWaiting(getActivity(), getResources().getString(R.string.com_login_tips));
        presenter_workListIml = new Presenter_WorkListIml(this);
        Map<String, Object> stringObjectMap = ComsentUtils.onSendList(GlobalInfoUtils.PAGE_OPTION);
        presenter_workListIml.setData(stringObjectMap);
        ComsentUtils.onMsgPush2(getActivity());
    }

    private void initData() {


    }

    private void initView() {
        mMhedoor_recycle = inflate.findViewById(R.id.thedoor_recycle);
        boxPageState = inflate.findViewById(R.id.boxPageState); //网络失败
        map_navigation = inflate.findViewById(R.id.map_navigation);//LBS地图

        linear_lbs = inflate.findViewById(R.id.linear_lbs);//LBS右边动态样式
        person = inflate.findViewById(R.id.person);
        qixing = inflate.findViewById(R.id.qixing);
        jiache = inflate.findViewById(R.id.jiache);

        map_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LBsNavigation.class));
            }
        });
        linearLayoutManager = new LinearLayoutManager(activity);

        mMhedoor_recycle.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_ThedoorRec(activity);
        mMhedoor_recycle.setAdapter(adapter);


        adapter.setOnclick(new Adapter_ThedoorRec.Onclick() {
            @Override
            public void onItemClick(View v, int position) {
                if (!Check.isFastClick())
                    return;
                WorkListBean date = adapter.getDate();
                WorkListBean.DataBean.ListBean listBean = date.getData().getList().get(position);
                String id = listBean.getId();
                String doing = listBean.getDoing() + "";
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                DialogUtils.showWaiting(activity, getResources().getString(R.string.com_login_tips));
                GlobalInfoUtils.List_strongreminder(getActivity(), id, doing, firstVisibleItemPosition);

            }


        });

    }

    /**
     * 列表数据 成功
     *
     * @param workListBean
     */
    @Override
    public void showData(WorkListBean workListBean) {
        switch (workListBean.getCode()) {
            case 1000:
                linear_lbs.setVisibility(View.VISIBLE);
                onLbsData(workListBean);
                mMhedoor_recycle.setVisibility(View.VISIBLE);
                map_navigation.setVisibility(View.VISIBLE);
                boxPageState.setVisibility(View.GONE);
                adapter.setData(workListBean);
                WorkListBean.DataBean data = workListBean.getData();
                DialogUtils.hideWaiting(activity);
                int total = data.getTotal(); //工单总数
                int backOrderNum =data.getBackOrderNum(); //返工数量
                callBackValue1.SendMessageValue(total, backOrderNum,data.getToolData(),data.getList());  //接口回调
                mMhedoor_recycle.scrollToPosition(GlobalInfoUtils.mfirstVisibleItemPosition);
                break;
            case 10011:
                DialogUtils.hideWaiting(activity);
                GlobalInfoUtils.clearApp(getActivity());
                DialogUtils.showToast(activity, workListBean.getMsg());

                break;
            case 1001:
                DialogUtils.hideWaiting(activity);
                DialogUtils.showToast(activity, workListBean.getMsg());
                callBackValue1.SendMessageValue(0, 0, null,null);  //接口回调
                mMhedoor_recycle.setVisibility(View.GONE);
                map_navigation.setVisibility(View.GONE);
                boxPageState.setVisibility(View.VISIBLE);
                break;
            default:
                DialogUtils.hideWaiting(activity);
                boxPageState.setVisibility(View.VISIBLE);
                mMhedoor_recycle.setVisibility(View.GONE);
                map_navigation.setVisibility(View.GONE);
                DialogUtils.showToast(activity, workListBean.getMsg());
        }

    }

    private void onLbsData(WorkListBean workListBean) {
        /**
         * Lbs 出行方式
         *    //lbs骑行路线规划

         */
        if (workListBean.getData().getList().size() > 0) {
            lbsList = new ArrayList<>();
            linear_lbs.setVisibility(View.VISIBLE);
            lbsList.add(person);
            lbsList.add(qixing);
            lbsList.add(jiache);


            person.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLbsTab(v, lbsList);
                    GlobalInfoUtils.LBS_page =0;
                    int visibility = qixing.getVisibility();
                    if (visibility == View.GONE) {
                        qixing.setVisibility(View.VISIBLE);
                        jiache.setVisibility(View.VISIBLE);
                    }else{
                        qixing.setVisibility(View.GONE);
                        jiache.setVisibility(View.GONE);
                    }

                }
            });
            qixing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLbsTab(v, lbsList);
                    GlobalInfoUtils.LBS_page =1;
                    int visibility = person.getVisibility();
                    if (visibility == View.GONE) {
                        person.setVisibility(View.VISIBLE);
                        jiache.setVisibility(View.VISIBLE);
                    }else{
                        person.setVisibility(View.GONE);
                        jiache.setVisibility(View.GONE);
                    }


                }
            });
            jiache.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLbsTab(v, lbsList);
                    GlobalInfoUtils.LBS_page =2;
                    int visibility = qixing.getVisibility();
                    if (visibility == View.GONE) {
                        qixing.setVisibility(View.VISIBLE);
                        person.setVisibility(View.VISIBLE);
                    }else{
                        qixing.setVisibility(View.GONE);
                        person.setVisibility(View.GONE);
                    }
                }
            });

        } else {
            linear_lbs.setVisibility(View.GONE);
        }


    }

    /**
     * * 列表数据 Error
     *
     * @param error
     */
    @Override
    public void showError(Throwable error) {
        DialogUtils.hideWaiting(activity);
        if (!Gps_Utils.getInstance().isNetworkAvailable(activity)) {
            mMhedoor_recycle.setVisibility(View.GONE);
            boxPageState.setVisibility(View.GONE);
            mMhedoor_recycle.setVisibility(View.GONE);
        } else {
            mMhedoor_recycle.setVisibility(View.GONE);
            boxPageState.setVisibility(View.VISIBLE);
            mMhedoor_recycle.setVisibility(View.GONE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (null != getActivity()) {
            if (isVisibleToUser) {
                initPresenter();
                adapter.notifyDataSetChanged();//08-28 添加刷新适配器
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            initPresenter();
            adapter.notifyDataSetChanged();//08-28 添加刷新适配器
        }
    }

    //定义一个回调接口
    public interface CallBackValue {
        void SendMessageValue(int strValue, int page, WorkListBean.DataBean.ToolDataBean toolData, List<WorkListBean.DataBean.ListBean> list);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue1 = (CallBackValue) getActivity();

    }

    /*8
    LBS样式
     */
    private void onLbsTab(View v, List<View> lbsList) {

        Drawable drawable = getResources().getDrawable(R.drawable.com_login_lbs_itemitem2);
        int tx_write = getResources().getColor(R.color.color_move_FFFFFF);

        Drawable drawable_write = getResources().getDrawable(R.drawable.com_login_lbs_itemitem);
        int color_move_CACACA = getResources().getColor(R.color.color_move_CACACA);
        for (int position = 0; position < lbsList.size(); position++) {
            LinearLayout linearLayout;
            TextView childAt;
            ImageView img;
            if (v.equals(lbsList.get(position))) {
                linearLayout = (LinearLayout) v;
                childAt = (TextView) linearLayout.getChildAt(1);
                img = (ImageView) linearLayout.getChildAt(0);
                linearLayout.setBackground(drawable);
                childAt.setTextColor(tx_write);
                RequestManager with = Glide.with(this);
                if(position == 0){
                    with.load(R.drawable.person_write).into(img);
                }else if(position == 1){
                    with.load(R.drawable.bicycle_write).into(img);
                }else if(position == 2 ){
                    with.load(R.drawable.car_write).into(img);
                }
            } else {
                linearLayout = (LinearLayout) lbsList.get(position);
                linearLayout.setBackground(drawable_write);
                childAt = (TextView) linearLayout.getChildAt(1);
                childAt.setTextColor(color_move_CACACA);
                img = (ImageView) linearLayout.getChildAt(0);
                if(position == 0){
                    Glide.with(getActivity()). load(R.drawable.person_black).into(img);
                }else if(position == 1){
                    Glide.with(getActivity()). load(R.drawable.bicycle_black).into(img);
                }else if(position == 2 ){
                    Glide.with(getActivity()).load(R.drawable.car_black).into(img);
                }

            }
        }

    }
}
