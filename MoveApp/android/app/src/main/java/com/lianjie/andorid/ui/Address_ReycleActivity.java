package com.lianjie.andorid.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.IndoorData;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.SubPoiItem;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.LocationRecycleAdapter;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.LatLngBean;
import com.lianjie.andorid.utils.GlobalInfoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/9/25
 * Time: 15:36
 */
public class Address_ReycleActivity extends BaseActivity {
    /**
     * 请在此输入详细地址并点击匹配项
     */
    private EditText mEdAddress;
    private RecyclerView mRecycle;
    private Intent intent;
    private double longitude;
    private double latitude;
    private Context mContext;
    private String city;
    private String adCode;
    private List<PoiItem> pois;

    private LocationRecycleAdapter listAdapter;
    private ArrayList<LatLngBean> data;
    private LinearLayout lineqr_error;

    @Override
    protected int onLayout() {
        this.mContext = this;
        return R.layout.activity_recycle_poi;
    }

    @Override
    protected void onDrishData() {
    }


    @Override
    protected void initData() {

        intent = getIntent();
        longitude = intent.getDoubleExtra("longitude", 0);
        latitude = intent.getDoubleExtra("latitude", 0);
        city = intent.getStringExtra("city");
        adCode = intent.getStringExtra("adCode");
        pois = GlobalInfoUtils.pois;
        mRecycle.setLayoutManager(new LinearLayoutManager(this));


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onPoi(s.toString());
            }

        };
        mEdAddress.addTextChangedListener(textWatcher);

        onPoi("1");  //第一次

    }

    @Override
    protected void initView() {
        mEdAddress = (EditText) findViewById(R.id.ed_address);
        mRecycle = (RecyclerView) findViewById(R.id.recycle);
        lineqr_error = (LinearLayout) findViewById(R.id.lineqr_error);
        ImageView mCallbaclk = findViewById(R.id.callbaclk);
        TextView mTitle = findViewById(R.id.title);
        mTitle.setText("设置现居地址");
        mCallbaclk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onPoi(CharSequence s) {
         if (s.toString().equals("1")) {
             if (pois.size() == 0) {
                 mRecycle.setVisibility(View.VISIBLE);
                 lineqr_error.setVisibility(View.GONE);
             }else{
                 mRecycle.setVisibility(View.VISIBLE);
                 lineqr_error.setVisibility(View.GONE);
             }

            data = new ArrayList<>();
            if (pois != null) {

                for (int i = 0; i < pois.size(); i++) {
                    PoiItem poiItem = pois.get(i);
                    double latitude = poiItem.getLatLonPoint().getLatitude();
                    double longitude = poiItem.getLatLonPoint().getLongitude();
                  String   adName = poiItem.getSnippet();
                    data.add(new LatLngBean(poiItem.toString(),latitude,longitude,adName));

                }
            }else{
                mRecycle.setVisibility(View.VISIBLE);
                lineqr_error.setVisibility(View.GONE);
            }
            listAdapter = new LocationRecycleAdapter(mContext, data);
             mRecycle.setAdapter(listAdapter);

            listAdapter.setOnclickListener(new LocationRecycleAdapter.OnclickListener() {
                @Override
                public void onClickListener(View v, int position) {
                    intent.putExtra("lat", data.get(position).getLat());
                    intent.putExtra("lng", data.get(position).getLon());
                    setResult(999, intent);
                    finish();
                }
            });
        } else {
//            PoiSearch.Query  query=null;
//          query = new PoiSearch.Query(s.toString(), "", adCode);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
//            query.setCityLimit(true);
//            query.setPageSize(10);// 设置每页最多返回多少条poiitem
//            query.setPageNum(1);// 设置查第一页
//            LatLonPoint latLonPoint = new LatLonPoint(longitude, latitude);
//            query.setLocation(latLonPoint);
//            PoiSearch poiSearch; //POI搜索
//            poiSearch = new PoiSearch(Address_ReycleActivity.this, query);
            //输入框框
            InputtipsQuery inputquery = new InputtipsQuery(s.toString(), city);
            inputquery.setCityLimit(true);//限制在当前城市
            Inputtips inputTips = new Inputtips(this, inputquery);
            //输入 进行 搜索 辉县poi数据
            inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                @Override
                public void onGetInputtips(List<Tip> list, int i) {
                    //rCode 为1000 时成功,其他为失败
                    if (i == AMapException.CODE_AMAP_SUCCESS) {
                        if (list.size() == 0) {
                            data = new ArrayList<>();
                            listAdapter = new LocationRecycleAdapter(mContext, data);
                            listAdapter.setData();
                            mRecycle.setVisibility(View.GONE);
                            lineqr_error.setVisibility(View.VISIBLE);
                        }else{


                        mRecycle.setVisibility(View.VISIBLE);
                        lineqr_error.setVisibility(View.GONE);
                                data = new ArrayList<>();

                        // 解析result   获取搜索poi的结果
                        for (int i1 = 0; i1 < list.size(); i1++) {
                            Tip tip = list.get(i1);
                            String district = tip.getDistrict();
                            data.add(new LatLngBean(tip.getName(),tip.getPoint().getLatitude(), tip.getPoint().getLongitude(),district));
                        }
                        listAdapter = new LocationRecycleAdapter(mContext, data);
                        mRecycle.setAdapter(listAdapter);

                        listAdapter.setOnclickListener(new LocationRecycleAdapter.OnclickListener() {
                            @Override
                            public void onClickListener(View v, int position) {
                                intent.putExtra("lat", data.get(position).getLat());
                                intent.putExtra("lng", data.get(position).getLon());
                                setResult(999, intent);
                                finish();
                            }
                        });
                        }
                    }else if(i == 1901){
                        mRecycle.setVisibility(View.VISIBLE);
                        lineqr_error.setVisibility(View.GONE);
                        data = new ArrayList<>();
                        if (pois != null) {

                            for (int i1 = 0; i1 < pois.size(); i1++) {
                                PoiItem poiItem = pois.get(i1);
                                double latitude = poiItem.getLatLonPoint().getLatitude();
                                double longitude = poiItem.getLatLonPoint().getLongitude();
                                String   adName = poiItem.getSnippet();
                                data.add(new LatLngBean(poiItem.toString(),latitude,longitude,adName));

                            }
                        }else{
                            mRecycle.setVisibility(View.VISIBLE);
                            lineqr_error.setVisibility(View.GONE);
                        }
                        listAdapter = new LocationRecycleAdapter(mContext, data);
                        mRecycle.setAdapter(listAdapter);

                        listAdapter.setOnclickListener(new LocationRecycleAdapter.OnclickListener() {
                            @Override
                            public void onClickListener(View v, int position) {
                                intent.putExtra("lat", data.get(position).getLat());
                                intent.putExtra("lng", data.get(position).getLon());
                                setResult(999, intent);
                                finish();
                            }
                        });
                    }else{
                        data = new ArrayList<>();
                        listAdapter = new LocationRecycleAdapter(mContext, data);
                        listAdapter.setData();
                        mRecycle.setVisibility(View.GONE);
                        lineqr_error.setVisibility(View.VISIBLE);
                    }
                }
            });
            inputTips.requestInputtipsAsyn();
        }

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
        return super.onKeyDown(keyCode, event);
    }

}
