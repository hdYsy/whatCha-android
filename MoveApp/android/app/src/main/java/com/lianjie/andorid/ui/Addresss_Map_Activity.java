package com.lianjie.andorid.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;
import com.amap.api.services.core.PoiItem;
import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.DelatileMsg_bean;
import com.lianjie.andorid.rnui.RNActivity;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideCacheUtil;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.lianjie.andorid.ui.DelatileActivity.id;
import static java.lang.String.valueOf;

public class Addresss_Map_Activity extends Activity implements LocationSource, AMapLocationListener {
    MapView mMapView = null;
    private boolean followMove = true;
    private AMap aMap;
    private Marker screenMarker;
    private double latitude = 0;
    private double longitude = 0;
    private GeocodeSearch geocoderSearch;
    private Addresss_Map_Activity mContext;
    private String city;
    private String adCode;
    private Button bt_shezhi;
    private LinearLayout ld_linear;
    private TextView tx_err;
    private TextView tx_submit;
    private TextView tx_address;
    private EditText ed_address;
    private LinearLayout linear_address;
    private RelativeLayout relative_title;
    private ImageView dingwei;
    private ImageView dingwei2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_address);
        this.mContext = this;
      ImageView mCallbaclk = findViewById(R.id.callbaclk);
     TextView   mTitle = findViewById(R.id.title);
        mTitle.setText("设置现居地址");
        mCallbaclk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        final TextView tx = (TextView) findViewById(R.id.tx);
        bt_shezhi = (Button) findViewById(R.id.bt_shezhi);
        ld_linear = (LinearLayout) findViewById(R.id.ld_linear);
        tx_err = (TextView) findViewById(R.id.tx_err);
        tx_submit = (TextView) findViewById(R.id.tx_submit);
        tx_address = (TextView) findViewById(R.id.tx_address);
        ed_address = (EditText) findViewById(R.id.ed_address);
        linear_address = (LinearLayout) findViewById(R.id.linear_address); //address
        relative_title = (RelativeLayout) findViewById(R.id.relative_title);
        dingwei = findViewById(R.id.dingwei);
        dingwei2 = findViewById(R.id.dingwei2);
        bt_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                dingwei.setVisibility(View.GONE);
                dingwei2.setVisibility(View.VISIBLE);
                ld_linear.setVisibility(View.VISIBLE);
            }
        });
        tx_err.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ld_linear.setVisibility(View.GONE);
                dingwei2.setVisibility(View.GONE);
                dingwei.setVisibility(View.VISIBLE);
                bt_shezhi.setVisibility(View.VISIBLE);
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null && GlobalInfoUtils.longitude != 0 && GlobalInfoUtils.latitude!=0) {

                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(
                                //15是缩放比例，0是倾斜度，30显示比例
                                new CameraPosition(new LatLng(GlobalInfoUtils.latitude,  GlobalInfoUtils.longitude ), 15, 0, 30));//这是地理位置，就是经纬度。
                            aMap.moveCamera(cameraUpdate);//定位的方法
                            mlocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁

                }
            }
        };
        dingwei.setOnClickListener(onClickListener);
        dingwei2.setOnClickListener(onClickListener);

        /**
         * 点击事件  地址 栏的
         */
        relative_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = DialogUtils.showCentreDialog(mContext, getString(R.string.com_work_addresstishi), "");
                LinearLayout linear_bottom = view.findViewById(R.id.linear_bottom);//第二个
                linear_bottom.getChildAt(0).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtils.closeLJdialog();
                    }
                });
            }
        });
        linear_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (longitude != 0 && latitude != 0) {
                    String s = tx_address.getText().toString();
                    if (!s.isEmpty()) {
                        Intent intent = new Intent(mContext, Address_ReycleActivity.class);
                        intent.putExtra("longitude", longitude);
                        intent.putExtra("latitude", latitude);
                        intent.putExtra("city", city);
                        intent.putExtra("adCode", adCode);
                        startActivityForResult(intent, 999);
                    }

                }

            }
        });

        tx_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ed_address.getText().toString();
                if (!s.isEmpty()) {

                        //调用接口进行提交
                        View view = DialogUtils.showCenterMap(mContext,"如果居住地址发生变化请您务必及时修改，地址修改后需要由督导审核，请您耐心等待！");
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
                                //提交接口
                                DialogUtils.closeLJdialog();
                                String s1 = tx_address.getText().toString() + ed_address.getText().toString();
                                onUpadateAddress(s1);
                            }
                        });

                }else {
                    DialogUtils.showToast(mContext, "请填写详细地址~");

                }


            }
        });


        /**
         * 蓝点
         */
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        final MyLocationStyle myLocationStyle;

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        aMap.setLocationSource(this);//定位监听
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        aMap.moveCamera(CameraUpdateFactory.zoomTo(10));//放大地图到17,3-19
        aMap.getUiSettings().setAllGesturesEnabled(true); //支持高德地图 所有手势
        aMap.getUiSettings().setZoomControlsEnabled(false); //是否显示那个特别丑的 + -
        aMap.getUiSettings().setMyLocationButtonEnabled(false); //显示默认的定位按钮
        aMap.getUiSettings().setCompassEnabled(true);//指南针
        myLocationStyle.showMyLocation(true);//设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        aMap.showIndoorMap(false);     //true：显示室内地图；false：不显示；
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        /**
//         * 设置 一个 蓝点
//         */
//        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(GlobalInfoUtils.latitude, GlobalInfoUtils.longitude), 19));
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(new LatLng(GlobalInfoUtils.latitude, GlobalInfoUtils.longitude));
//        markerOptions.title("当前位置");
//        markerOptions.visible(true);
//        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.landian_dingwei));
//        markerOptions.icon(bitmapDescriptor);
//        aMap.addMarker(markerOptions);


        //经纬度转为字符串
        geocoderSearch = new GeocodeSearch(this);
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                latitude = cameraPosition.target.latitude;
                longitude = cameraPosition.target.longitude;
                addMarkerInScreenCenter();
                LatLonPoint lp = new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude);
                RegeocodeQuery query = new RegeocodeQuery(lp, 200, GeocodeSearch.AMAP);
                geocoderSearch.getFromLocationAsyn(query);
            }
        });
        //     经纬度转为 地址
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {


            @Override
            public void onGeocodeSearched(GeocodeResult result, int rCode) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
                //根据经纬度  转换地址
                String formatAddress = result.getRegeocodeAddress().getFormatAddress();
                RegeocodeAddress regeocodeAddress = result.getRegeocodeAddress();
                List<PoiItem> pois = regeocodeAddress.getPois();
                GlobalInfoUtils.pois = pois;  //调用地址定位  高德Api 带来的 poi周边数据
                city = result.getRegeocodeAddress().getCity();


                adCode = result.getRegeocodeAddress().getAdCode();
                tx_address.setText(formatAddress);
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
        GlobalInfoUtils.pois = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    /**
     * 永远把这个点放到中间
     */
    private void addMarkerInScreenCenter() {
        if (screenMarker == null) {
            screenMarker = aMap.addMarker(new MarkerOptions().zIndex(2));
        }
        screenMarker.setAnchor(0.5f, 1.0f);
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        screenMarker.setPositionByPixels(screenPosition.x, screenPosition.y);
        screenMarker.setClickable(false);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == 999) {
            double lat = data.getDoubleExtra("lat", 0);
            double lng = data.getDoubleExtra("lng", 0);
            UiSettings mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);
            mUiSettings.setCompassEnabled(true);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(
                    //15是缩放比例，0是倾斜度，30显示比例
                    new CameraPosition(new LatLng(lat, lng), 15, 0, 30));//这是地理位置，就是经纬度。
            aMap.moveCamera(cameraUpdate);//定位的方法

        }
    }


    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    /**
     * 激活定位
     */
    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除

            //该方法默认为false。
            mLocationOption.setOnceLocation(true);

//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
            mLocationOption.setOnceLocationLatest(true);
            mLocationOption.setInterval(2000);


            mlocationClient.startLocation();//启动定位
            //获取一次定位结果：

        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private boolean b = true;

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(
                        //15是缩放比例，0是倾斜度，30显示比例
                        new CameraPosition(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude()), 15, 0, 30));//这是地理位置，就是经纬度。
                if (b) {
                    aMap.moveCamera(cameraUpdate);//定位的方法
                    b = false;
                    mlocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                }

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                LogUtils.e("TAG" + errText);
            }
        }
    }

    /**
     * 提交
     * @param address
     */
    private  void onUpadateAddress(String address){
        Map<String, Object> map = ComsentUtils.onSendUpdataAddress(address);
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(URLConstant.UpadateCleanAddress).post(requestBody.build()).tag(mContext).build();
        Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext,e.getMessage());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                DelatileMsg_bean delatileMsgBean = gson.fromJson(response.body().string(), DelatileMsg_bean.class);
                int code = delatileMsgBean.getCode();
                Looper.prepare();
                if (code == 1000) {
                    Intent intent = new Intent(mContext, RNActivity.class);
                    GlobalInfoUtils.Page_RN = "Move_App_My";
                    startActivity(intent);
                    finish();
                } else if(code == 10012){
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp((Activity) mContext);
                    DialogUtils.showToast(mContext, delatileMsgBean.getMsg());
                }else{
                    DialogUtils.showToast(mContext, delatileMsgBean.getMsg());
                }
                Looper.loop();

            }
        });

    }


}