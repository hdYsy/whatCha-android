package com.lianjie.andorid.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RideStep;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.time.util.WheelUtils;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.Lbs_delatilesBean;
import com.lianjie.andorid.bean.LoadScrosses;
import com.lianjie.andorid.bean.MapOrderBean;
import com.lianjie.andorid.bean.ReminBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Stract_LBSNavigation;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Stract_LbsNavigition;
import com.lianjie.andorid.mvp.presenteriml.Presenter_WorkDelatilelml;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.SystemDateUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.microedition.khronos.opengles.GL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_DEFAULT;
import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_NO_HIGHWAY_SAVE_MONEY;
import static com.amap.api.services.route.RouteSearch.WALK_DEFAULT;
import static java.lang.String.valueOf;

/**
 * Created by My on 2019/12/9.
 */

public class LBsNavigation extends Activity implements IConstract_Stract.IView, RouteSearch.OnRouteSearchListener, AMap.InfoWindowAdapter {

    private LBsNavigation mContext;
    private MapView mapView;
    private AMap aMap;
    private RouteSearch routeSearch;
    private Marker mMarker;
    private List<Marker> mMarkerList;
    private Polyline polyline;//折线
    private int isLine;
    private int markersImg;
    private List<Integer> markersImgList = new ArrayList<>();
    private String dateTime;//时间
    private LayoutInflater inflater;//layoutinflater 装marker的大小图片
    private View inflate_delatile;
    private TextView word_my;
    private TextView drication;
    private TextView address;
    private TextView word_code;
    private TextView word_time;
    private LinearLayout delatiles_bt;
    private TextView map_naviga;
    private String formatmmm;
    private Lbs_delatilesBean delatilesBean;
    private LinearLayout.LayoutParams vlp_img_min;
    private LinearLayout.LayoutParams vlp_img_max;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lbslayout);
        mContext = this;
        inflater = LayoutInflater.from(this);
        vlp_img_min = new LinearLayout.LayoutParams(WheelUtils.dip2px(mContext, 60),
                WheelUtils.dip2px(mContext, 60));
        vlp_img_max = new LinearLayout.LayoutParams(WheelUtils.dip2px(mContext, 85),
                WheelUtils.dip2px(mContext, 85));
        mInflater = LayoutInflater.from(mContext);//Layout
        mMarkerList = new ArrayList<>();//装Marker的list(amap 存的 marker 只存你点击的，，，)

        mapView = findViewById(R.id.mapview);
        inflate_delatile = findViewById(R.id.delatiles);//底下弹层
        //工单名称
        word_my = inflate_delatile.findViewById(R.id.word_my);
        //距离工单大概多远
        drication = inflate_delatile.findViewById(R.id.drication);
        //工单地址
        address = inflate_delatile.findViewById(R.id.address);
        //工单编号
        word_code = inflate_delatile.findViewById(R.id.word_code);
        //预约上门日期
        word_time = inflate_delatile.findViewById(R.id.word_time);
        //查看详情
        delatiles_bt = inflate_delatile.findViewById(R.id.delatiles_bt);
        //查看导航路线
        map_naviga = inflate_delatile.findViewById(R.id.map_naviga);
        initView();
        mapView.onCreate(savedInstanceState);
        initDatat();
    }

    private void initView() {
        findViewById(R.id.callbaclk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        View viewById = findViewById(R.id.dingwei);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(
                        //15是缩放比例，0是倾斜度，30显示比例
                        new CameraPosition(new LatLng(GlobalInfoUtils.latitude,  GlobalInfoUtils.longitude ), 15, 0, 30));//这是地理位置，就是经纬度。
                aMap.moveCamera(cameraUpdate);//定位的方法
            }
        });
        title.setText("地图导航");
        /**
         * 点击事件  地址 栏的
         */
        findViewById(R.id.relative_title).setOnClickListener(new View.OnClickListener() {
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
    }

    /**
     * 数据源
     */
    private void initDatat() {
        if (aMap == null) {
            aMap = mapView.getMap();
            MyLocationStyle myLocationStyle;
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            UiSettings uiSettings = aMap.getUiSettings();
            uiSettings.setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
            uiSettings.setZoomControlsEnabled(false);//+-号去掉
            aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，
//            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
            aMap.moveCamera(CameraUpdateFactory.zoomTo(12));//放大地图到17,3-19
            aMap.setInfoWindowAdapter(this);//infowindow的监听
        }
        Map<String, Object> stringObjectMap = ComsentUtils.onSend_LBS_list();
        Presenter_Stract_LbsNavigition prisenter_list = new Presenter_Stract_LbsNavigition(this);
        prisenter_list.setData(stringObjectMap);
        DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_login_tips));

    }


    /**
     * 主数据接口
     *
     * @param t
     * @param <T>
     */
    @Override
    public <T> void showData(T t) {
        DialogUtils.hideWaiting(mContext);
        final MapOrderBean mapOrderBean = (MapOrderBean) t;
        int code = mapOrderBean.getCode();
        if (code == 1001) {
            DialogUtils.showToast(mContext, mapOrderBean.getMsg());
            finish();
        } else if (code == 10011) {
            DialogUtils.hideWaiting(mContext);
            GlobalInfoUtils.clearApp((Activity) mContext);
            DialogUtils.showToast(mContext, mapOrderBean.getMsg());
        } else if (code == 1000) {
            isLine = mapOrderBean.getData().getIsLine();
            final List<MapOrderBean.DataBean.ListBean> list = mapOrderBean.getData().getList();
            for (int i = 0; i < list.size(); i++) {
//                  String btype = list.get(i).getBtype();
                MapOrderBean.DataBean.ListBean listBean = list.get(i);
                if (listBean.getBtype().isEmpty()) {
                    listBean.setBtype("0");
                    listBean.setChannel("0");
                }
                int btype = Integer.parseInt(listBean.getBtype());
                int channel = Integer.parseInt(listBean.getChannel());
                String lat = listBean.getLat();//经纬度
                String lng = listBean.getLng();
                LatLng latLng = new LatLng(Float.parseFloat(lat), Float.parseFloat(lng));
                MarkerOptions position = new MarkerOptions().position(latLng).period(i + 1);//高德地图 默认不存0下标
                Marker marker = aMap.addMarker(position);
                markersImg = getMarkersImg(btype, channel);
                markersImgList.add(markersImg);
                View inflate = mInflater.inflate(R.layout.item_lbs_markericon, null, false);//循环 调用布局
                ImageView img = inflate.findViewById(R.id.img);
                img.setImageResource(markersImg);
                img.setLayoutParams(vlp_img_min);
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(GlobalInfoUtils.convertViewToBitmap(inflate));
                marker.setIcon(bitmapDescriptor);
                mMarkerList.add(marker);
                if (i == 0) {
                    mMarker = marker;

                    /**
                     * 经纬度
                     */
                    /**
                     * 请求所有距离
                     */
                    routeSearch = new RouteSearch(this);
                    routeSearch.setRouteSearchListener(this);
                    LatLonPoint stop = new LatLonPoint(Float.parseFloat(list.get(i).getLat()), Float.parseFloat(list.get(i).getLng()));
                    LatLonPoint start = new LatLonPoint(GlobalInfoUtils.latitude, GlobalInfoUtils.longitude);
                    RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(start, stop);//路线的起点和终点
                    onTripplanning(fromAndTo);
                }
            }
            aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {
                    /**
                     * 经纬度
                     */
//                    if (mMarker!=null) {
//                        if (mMarker.equals(marker)) {
//                        }
//                    }

                    int period = marker.getPeriod() - 1;//marker的ID
                    MapOrderBean.DataBean.ListBean listBean = list.get(period);
                    mMarker = marker;
                    LatLng position = marker.getPosition();
                    LatLonPoint start = new LatLonPoint(GlobalInfoUtils.latitude, GlobalInfoUtils.longitude);
                    LatLonPoint stop = new LatLonPoint(position.latitude, position.longitude);
                    RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(start, stop);
                    onTripplanning(fromAndTo);
                    DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_login_tips));
                    onRequestDelatilesData(listBean.getId() + "");
//                    View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_lbs_markericon, null,false);
//                    ImageView viewById = inflate.findViewById(R.id.img);


                    return false;
                }
            });


        }

    }


    /**
     * 出行方式
     *
     * @param fromAndTo
     */
    private void onTripplanning(RouteSearch.FromAndTo fromAndTo) {
        if (isLine == GlobalInfoUtils.LBS_page) {//是否跟做单方式一致
            if (GlobalInfoUtils.LBS_page == 0) {
                RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, WALK_DEFAULT);//默认
                routeSearch.calculateWalkRouteAsyn(query);//步行路线规划
            } else if (GlobalInfoUtils.LBS_page == 1) {
                RouteSearch.RideRouteQuery query = new RouteSearch.RideRouteQuery(fromAndTo);//默认是最快的方式骑行
                routeSearch.calculateRideRouteAsyn(query);//骑行路线规划
            } else if (GlobalInfoUtils.LBS_page == 2) {
// 第三个参数表示途经点（最多支持16个），  第四个参数表示避让区域（最多支持32个），第五个参数表示避让道路
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(//DRIVING_SINGLE_NO_HIGHWAY_SAVE_MONEY 不走所有收费路段
                        fromAndTo, DRIVING_SINGLE_NO_HIGHWAY_SAVE_MONEY, null, null, "");
                routeSearch.calculateDriveRouteAsyn(query);
            }
        } else {
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, WALK_DEFAULT);//默认
            routeSearch.calculateWalkRouteAsyn(query);//步行路线规划
        }
    }

    /**
     * 主数据接口
     *
     * @param error
     */
    @Override
    public void showError(Throwable error) {
        //error  不做任何操作
        DialogUtils.hideWaiting(mContext);
        DialogUtils.showToast(mContext, GlobalInfoUtils.error_lbs);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int page) {
        if (page == 1000) {
            List<DrivePath> paths = driveRouteResult.getPaths();
            List<LatLng> routepaths = new ArrayList<>();
            for (DrivePath dp : paths) {
                List<DriveStep> steps = dp.getSteps();
                float distance = dp.getDistance();//总共的距离
                long duration = dp.getDuration();//总共的距离
                formatmmm = SystemDateUtils.formatmmm(distance);//距离公里数
                dateTime = SystemDateUtils.formatDateTime(duration);
                if (mMarker != null) {
                    mMarker.setTitle("预计" + dateTime + "到达");
                    mMarker.showInfoWindow();
                }
                for (DriveStep ds : steps) {
                    List<LatLonPoint> points = ds.getPolyline();
                    for (LatLonPoint llp : points) {
                        routepaths.add(new LatLng(llp.getLatitude(), llp.getLongitude()));
                    }
                }
            }

            //是否开启纹理贴图
            //绘制成大地线
            //设置纹理样式
            //设置画线的颜色
            if (polyline != null) {
                polyline.remove();
            }
            PolylineOptions option_lines = new PolylineOptions()
                    .addAll(routepaths)
                    .width(20)
                    //是否开启纹理贴图
                    .setUseTexture(true)
                    //绘制成大地线
                    .geodesic(true)
                    //设置纹理样式
                    //设置画线的颜色
                    .color(Color.argb(233, 0, 200, 0));
            polyline = aMap.addPolyline(option_lines);
        } else {
            DialogUtils.showToast(mContext, "起点与终点距离较长~");
        }
            onMarKerMax();//marker变大变小
    }

    /**
     * marker变大变小
     */
    private void onMarKerMax() {
        if (mMarker!=null) {
            int period = mMarker.getPeriod() - 1;//marker的ID

            for (int i = 0; i < mMarkerList.size(); i++) {
                View inflate = mInflater.inflate(R.layout.item_lbs_markericon, null, false);
                ImageView img = inflate.findViewById(R.id.img);
                Marker marker_s = mMarkerList.get(i);
                if (i == period) {
                    img.setLayoutParams(vlp_img_max);
                    img.setImageResource(markersImgList.get(i));
                    Bitmap bitmap = GlobalInfoUtils.convertViewToBitmap(inflate);
                    mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                } else {
                    img.setLayoutParams(vlp_img_min);
                    img.setImageResource(markersImgList.get(i));
                    Bitmap bitmap = GlobalInfoUtils.convertViewToBitmap(inflate);
                    marker_s.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                }

            }
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
        if (i == 1000) {
            List<WalkPath> pathList = walkRouteResult.getPaths();
            List<LatLng> walkPaths = new ArrayList<>();
            String s = pathList.toString();
            for (WalkPath dp : pathList) {
                List<WalkStep> stepList = dp.getSteps();
                float distance = dp.getDistance();//总共的距离
                long duration = dp.getDuration();//总共的距离时间
                dateTime = SystemDateUtils.formatDateTime(duration);
                formatmmm = SystemDateUtils.formatmmm(distance);//距离公里数
                if (mMarker != null) {
                    mMarker.setTitle("预计" + dateTime + "到达");
                    mMarker.showInfoWindow();
                }
                for (WalkStep ds : stepList) {
                    List<LatLonPoint> points = ds.getPolyline();
                    for (LatLonPoint llp : points) {
                        walkPaths.add(new LatLng(llp.getLatitude(), llp.getLongitude()));
                    }
                }
            }

            //是否开启纹理贴图
            //绘制成大地线
            //设置纹理样式
            //设置画线的颜色
            if (polyline != null) {
                polyline.remove();
            }
            PolylineOptions option_lines = new PolylineOptions()
                    .addAll(walkPaths)
                    .width(20)
                    //是否开启纹理贴图
                    .setUseTexture(true)
                    //绘制成大地线
                    .geodesic(true)
                    //设置纹理样式
                    //设置画线的颜色
                    .color(Color.argb(233, 0, 200, 0));
            polyline = aMap.addPolyline(option_lines);
        } else {
            DialogUtils.showToast(mContext, "起点与终点距离较长~");
        }
        onMarKerMax();//marker变大变小

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
        if (i == 1000) {
            List<RidePath> paths = rideRouteResult.getPaths();
            List<LatLng> ridePaths = new ArrayList<>();
            for (RidePath dp : paths) {
                List<RideStep> steps = dp.getSteps();
                float distance = dp.getDistance();//总共的距离
                long duration = dp.getDuration();//总共的时间
                formatmmm = SystemDateUtils.formatmmm(distance);//距离公里数
                dateTime = SystemDateUtils.formatDateTime(duration);
                if (mMarker != null) {
                    mMarker.setTitle("预计" + dateTime + "到达");
                    mMarker.showInfoWindow();
                }
                for (RideStep ds : steps) {
                    List<LatLonPoint> points = ds.getPolyline();
                    for (LatLonPoint llp : points) {
                        ridePaths.add(new LatLng(llp.getLatitude(), llp.getLongitude()));
                    }
                }
            }

            //是否开启纹理贴图
            //绘制成大地线
            //设置纹理样式
            //设置画线的颜色
            if (polyline != null) {
                polyline.remove();
            }
            PolylineOptions option_lines = new PolylineOptions()
                    .addAll(ridePaths)
                    .width(20)
                    //是否开启纹理贴图
                    .setUseTexture(true)
                    //绘制成大地线
                    .geodesic(true)
                    //设置纹理样式
                    //设置画线的颜色
                    .color(Color.argb(233, 0, 200, 0));
            polyline = aMap.addPolyline(option_lines);
        } else {
            DialogUtils.showToast(mContext, "起点与终点距离较长~");
        }
        onMarKerMax();//marker变大变小

    }

    /**
     * 工单类型的image
     *
     * @param btype
     * @param channel
     * @return
     */
    public int getMarkersImg(int btype, int channel) {
        int drawable = 0;
        if (channel == GlobalInfoUtils.DankeChannal) {//蛋壳工单
            if (btype == GlobalInfoUtils.work_kh) {
                drawable = R.drawable.markers_kh;
            } else if (btype == GlobalInfoUtils.work_jj) {
                drawable = R.drawable.markers_jj;
            } else if (btype == GlobalInfoUtils.work_rc) {
                drawable = R.drawable.markers_rc;
            } else if (btype == GlobalInfoUtils.work_fhsd) {
                drawable = R.drawable.markers_fh;
            } else if (btype == GlobalInfoUtils.work_ydyx) {
                drawable = R.drawable.markers_yx;
            } else if (btype == GlobalInfoUtils.work_wxh) {
                drawable = R.drawable.markers_wxh;
            } else if (btype == GlobalInfoUtils.work_sz) {
                drawable = R.drawable.markers_sz;
            } else if (btype == GlobalInfoUtils.work_tzh) {
                drawable = R.drawable.markers_tzh;
            }
        } else if (channel == GlobalInfoUtils.qudaoChannal) {//渠道
            if (btype == GlobalInfoUtils.work_kh) {
                drawable = R.drawable.markers_kh;
            } else if (btype == GlobalInfoUtils.work_jj) {
                drawable = R.drawable.markers_jj;
            } else if (btype == GlobalInfoUtils.work_rc) {
                drawable = R.drawable.markers_rc;
            } else if (btype == GlobalInfoUtils.work_fhsd) {
                drawable = R.drawable.markers_fh;
            } else if (btype == GlobalInfoUtils.work_ydyx) {
                drawable = R.drawable.markers_yx;
            } else if (btype == GlobalInfoUtils.work_wxh) {
                drawable = R.drawable.markers_wxh;
            } else if (btype == GlobalInfoUtils.work_sz) {
                drawable = R.drawable.markers_sz;
            } else if (btype == GlobalInfoUtils.work_tzh) {
                drawable = R.drawable.markers_tzh;
            }
        } else if (channel == GlobalInfoUtils.CduanChannal) {//Cduan
            drawable = R.drawable.c_icon;

        } else {//整改工单
            drawable = R.drawable.markers_cbl;
        }
        return drawable;
    }


    /**
     * LBSmarker详情
     */
    private void onRequestDelatilesData(final String ID) {
        final Runnable requestTask = new Runnable() {
            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                Map<String, Object> map = ComsentUtils.onSend_LBS_list(ID);

                try {
                    OkHttpClient client = new OkHttpClient();
                    // form 表单形式上传
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (map != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : map.entrySet()) {
                            requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }
                    Request request = new Request.Builder().url(URLConstant.mapOrderInfo).post(requestBody.build()).tag(mContext).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    Gson gson = new Gson();
                    delatilesBean = gson.fromJson(execute.body().string(), Lbs_delatilesBean.class);
                    int code = delatilesBean.getCode();

                    if (code == 1000) {
                        msg.what = GlobalInfoUtils.REQUEST_SUCCESS;
                    } else if (code == 1001) {
                        Looper.prepare();
                        DialogUtils.hideWaiting(mContext);
                        DialogUtils.showToast(mContext, delatilesBean.getMsg());
                        Looper.loop();
                    } else if (code == 10011) {
                        Looper.prepare();
                        DialogUtils.hideWaiting(mContext);
                        GlobalInfoUtils.clearApp((Activity) mContext);
                        DialogUtils.showToast(mContext, delatilesBean.getMsg());
                        Looper.loop();
                    }

                } catch (IOException e) {
                    Looper.prepare();
                    DialogUtils.hideWaiting(mContext);
                    DialogUtils.showToast(mContext, GlobalInfoUtils.error_lbs);
                    Looper.loop();
                } finally {
                    msg.sendToTarget();
                }
            }
        };

        Thread requestThread = new Thread(requestTask);
        requestThread.start();
    }

    /**
     * 人脸识别
     */
    @SuppressLint("HandlerLeak")
    Handler requestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GlobalInfoUtils.REQUEST_SUCCESS:
                    if (delatilesBean.getCode() == 1000) {
                        DialogUtils.hideWaiting(mContext);
                        inflate_delatile.setVisibility(View.VISIBLE);
                        Lbs_delatilesBean.DataBean data = delatilesBean.getData();
                        String btypename = data.getBtypename();//工单名称
                        final String villageaddress = data.getVillageaddress();//地址
                        String planservicetime = data.getPlanservicetime();//预约上门日期
                        drication.setText(formatmmm); //距离公里数
                        String gid = data.getGid();//工单编号distance
                        String textColor = data.getTextColor();
                        if (textColor==null) {
                            textColor = "#000000";
                        }
                        word_my.setText(btypename);
                        word_my.setTextColor(Color.parseColor(textColor));
                        address.setText(villageaddress);
                        word_code.setText(gid);
                        word_time.setText(planservicetime);
                        delatiles_bt.setOnClickListener(new View.OnClickListener() {//查看详情
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, DelatileActivity.class);
                                intent.putExtra("id", delatilesBean.getData().getId());
                                startActivity(intent);
                            }
                        });
                        map_naviga.setOnClickListener(new View.OnClickListener() {//查看导航路线
                            @Override
                            public void onClick(View v) {
                                DialogUtils.onLBSBottomDialog(mContext, villageaddress, mMarker.getPosition().latitude, mMarker.getPosition().longitude);
                            }
                        });

                        DialogUtils.hideWaiting(mContext);
                    } else {
                        DialogUtils.hideWaiting(mContext);
                        DialogUtils.showToast(mContext, delatilesBean.getMsg());
                    }

                    break;
                case GlobalInfoUtils.REQUEST_FAIL:
                    DialogUtils.showToast(mContext, "开启服务失败，请检查您的网络!");
                    DialogUtils.hideWaiting(mContext);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };


    @Override
    public View getInfoWindow(Marker marker) {
        /**
         * 显示 自定义Marker
         */
        View infoWindow = null;
        if (infoWindow == null) {
            infoWindow = LayoutInflater.from(this).inflate(
                    R.layout.layout_item_lbs_infowindow, null);
            TextView textView = infoWindow.findViewById(R.id.infowindowtitle);
            String title = marker.getTitle();
            textView.setText(title);
        }
        return infoWindow;

    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (aMap!=null) {
            initDatat();
        }
    }
}
