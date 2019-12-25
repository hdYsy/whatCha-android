package com.lianjie.andorid.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.AdapterDelatile_Title;
import com.lianjie.andorid.adapter.AdapterDelatile_data;
import com.lianjie.andorid.adapter.Adapter_DelatileRework;
import com.lianjie.andorid.adapter.Adapter_getPhone;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.DelatileBean;
import com.lianjie.andorid.bean.DelatileMsg_bean;
import com.lianjie.andorid.bean.GetPasswordBean;
import com.lianjie.andorid.bean.GetPhoneBean;
import com.lianjie.andorid.bean.LoadScrosses;
import com.lianjie.andorid.bean.ReminBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.constract.IConstract_WorkDelatile;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Delatile_getPassrod;
import com.lianjie.andorid.mvp.presenteriml.Presenter_StractIml;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Stract_getPhone;
import com.lianjie.andorid.mvp.presenteriml.Presenter_WorkDelatilelml;
import com.lianjie.andorid.newsView.NoScrollViewPager_Delatile;
import com.lianjie.andorid.rnui.RNActivity;
import com.lianjie.andorid.utils.ActionUtils;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.MapUtil;
import com.lianjie.andorid.utils.PhoneUtils;
import com.lianjie.andorid.utils.SPUtil;
import com.lianjie.andorid.utils.SystemDateUtils;
import com.lianjie.andorid.utils.ViewPagerAdapter;

import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/19
 * Time: 14:11
 */
public class DelatileActivity extends BaseActivity implements IConstract_WorkDelatile.IView, View.OnClickListener {

    private Presenter_WorkDelatilelml presenter;
    private ImageView mCallbaclk;
    //    private TextView mWorkTx;
    private TextView mAddress;
    private ImageView mAddress_img;
    private TextView mWorknews1;
    private LinearLayout mWorknews2;
    private TextView mWorknews3;
    private DelatileBean.DataBean data;
    private RecyclerView rec_title;
    private RecyclerView rec_title2;
    private LinearLayout work_linear;
    private TextView mNews2tx;
    private LinearLayout apartment_linear;
    //    private Banner banner;
    private RelativeLayout bannerRelative;
    private TextView page_zong;
    private TextView page_fanye;
    private LinearLayout linear_rework;
    private LinearLayout rework_imglinear;
    private TextView rework_imglinear_gengduo;
    private RecyclerView wenti_recycle;
    private Adapter_DelatileRework adapter_delatileRework;
    private TextView delatile_noScusses;
    private String doing;  //传过来的 doing
    public static String id;      //传过来的id值
    private LinearLayout bottom_navigation;
    private LinearLayout resCremare;
    private ImageView img;
    private ScrollView scroll;
    public  DelatileActivity mContext;
    private Button startserver;
    private String channel;
    private String btype;
    private String status;
    private LinearLayout linear_grop;
    private NoScrollViewPager_Delatile viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LoadScrosses imageBean;
    private LinearLayout linear_password;
    private TextView getpassword;
    private LinearLayout chilrdlinear_password;
    private TextView txpassword;
    private LinearLayout linear_tophone;
    private RecyclerView recyclepassword;
    private LinearLayout linear_xiala;
    private String oid;

    private LinearLayout dianchi_linear;
    private LinearLayout linear_subtitle;
    private int TYPE_Tixing = 0;
    private LinearLayout battartry_tx;
    private String eventId_ = "";
    private LinearLayout impression_linear;

    @Override
    protected int onLayout() {
        mContext = this;
        return R.layout.activity_delatiles_layout;
    }

    @Override
    protected void onDrishData() {
        GlobalInfoUtils.br_contactInfosize = 0;//租户联系方式的回显
    }


    @Override
    protected void initData() {
        /**
         * id
         * login
         * 请求详情数据的=接口
         */
        Intent intent = getIntent();
//        oid = intent.getStringExtra("oid") ==null?"":intent.getStringExtra("oid");
        id = !intent.getStringExtra("id").isEmpty() ? intent.getStringExtra("id") : intent.getStringExtra("oid");
        presenter = new Presenter_WorkDelatilelml(this);
        Map<String, Object> stringObjectMap = ComsentUtils.onSendDelatile(id);
        presenter.setData(stringObjectMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (doing != null) {
            if (!doing.equals("") && !doing.equals("1")) {  //todo  一会需要改
                if (null == status) {
                    //异步请求  status 还没有回来得时候  不做任何表示
                } else if ("3".equals(status)) {
                    //回来了  是 详情  不做任何表示
                } else {
                    //是进行中  那么  就从新运行一下
                    Map<String, Object> stringObjectMap = ComsentUtils.onSendDelatile(id);
                    presenter.setData(stringObjectMap);
                }

            } else {
                if (null != status) {
                    //status  不是 null   并且 不是3  那么 就肯定不是  已完成
                    if (!status.equals("3")) {
                        //要实时的渲染
                        Map<String, Object> stringObjectMap = ComsentUtils.onSendDelatile(id);
                        presenter.setData(stringObjectMap);
                    }

                }

            }
            // 未读消息的推送
            ComsentUtils.onMsgPush2(this);
        }
    }

    @Override
    protected void initView() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置了北京透明
        mCallbaclk = (ImageView) findViewById(R.id.callbaclk);//返回 <
//        mWorkTx = (TextView) findViewById(R.id.work_tx);//喇叭消息
        mAddress = (TextView) findViewById(R.id.address);        //地址tx
        mAddress_img = (ImageView) findViewById(R.id.address_img);        //切换地址的图片
        mWorknews1 = (TextView) findViewById(R.id.worknews1);        //第一个工单信息的标签
        mWorknews2 = (LinearLayout) findViewById(R.id.worknews2);        //第二个工单信息的标签
        mNews2tx = (TextView) findViewById(R.id.news2tx);   // 第二个工单信息的标签  里面的tx
        mWorknews3 = (TextView) findViewById(R.id.worknews3);        // 第san个工单信息的标签  里面的tx
        rec_title = (RecyclerView) findViewById(R.id.rec_title);        // 第san个工单信息的标签  里面的tx
        rec_title2 = (RecyclerView) findViewById(R.id.rec_title2);        // 第san个工单信息的标签  里面的tx
        work_linear = (LinearLayout) findViewById(R.id.work_linear);        //gongdan 模板
        apartment_linear = (LinearLayout) findViewById(R.id.apartment_linear);        //公寓 模板
        impression_linear = (LinearLayout) findViewById(R.id.impression_linear);        //印象备注
//        banner = (Banner) findViewById(R.id.banner);        //banner轮播
        bannerRelative = (RelativeLayout) findViewById(R.id.bannerRelative);   //banner轮播 的父布局
        linear_rework = (LinearLayout) findViewById(R.id.Linear_Rework);        //返工工单 title
        rework_imglinear = (LinearLayout) findViewById(R.id.Rework_imglinear);        //返工图片的Linear
        rework_imglinear_gengduo = (TextView) findViewById(R.id.rework_imglinear_gengduo);        //返工图片的Linear
        wenti_recycle = (RecyclerView) findViewById(R.id.wenti_recycle);        //返工图片的recycleView
        bottom_navigation = (LinearLayout) findViewById(R.id.bottom_navigation);        //底部路由的Grop
        startserver = (Button) findViewById(R.id.startserver);        //底部路由的 开始服务
        img = (ImageView) findViewById(R.id.img);        //请求失败的
        scroll = (ScrollView) findViewById(R.id.scroll);        //数据
        linear_grop = (LinearLayout) findViewById(R.id.linear_grop);              //装着  t轮播图 左下角的title
        viewPager = (NoScrollViewPager_Delatile) findViewById(R.id.viewPager);   //Viewpager

        linear_password = (LinearLayout) findViewById(R.id.linear_password);     //获取密码模块
        getpassword = (TextView) findViewById(R.id.getpassword);                //获取密码 按钮
        chilrdlinear_password = (LinearLayout) findViewById(R.id.chilrdlinear_password);        //获取密码的 chilrdlinearn
        txpassword = (TextView) findViewById(R.id.txpassword);                   //装密码的 tx
        linear_tophone = (LinearLayout) findViewById(R.id.linear_tophone);        //隐私号的模块
        recyclepassword = (RecyclerView) findViewById(R.id.recyclepassword);    //显示 拨打电话的列表
        linear_xiala = (LinearLayout) findViewById(R.id.linear_xiala);         //下拉菜单的linear
        dianchi_linear = (LinearLayout) findViewById(R.id.dianchi_linear);    //电池的  父布局
        linear_subtitle = (LinearLayout) findViewById(R.id.linear_subtitle);  //顶部喇叭逻辑
        battartry_tx = (LinearLayout) findViewById(R.id.battartry_tx);             //请先更新电池
        recyclepassword.setLayoutManager(new LinearLayoutManager(mContext));
        mCallbaclk.setOnClickListener(this);

        /**
         * RecycleView的  滑动监听
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        /**
         * 两个RecycleView 的东西
         */
        rec_title.setLayoutManager(layoutManager);
        rec_title2.setLayoutManager(layoutManager1);
        //等待
        DialogUtils.showWaiting(this, getResources().getString(R.string.com_login_tips));

    }

    @Override
    public void showData(DelatileBean delatileBean) {
        if (delatileBean.getCode() == 1000) {
            img.setVisibility(View.GONE);
            scroll.setVisibility(View.VISIBLE);
            data = delatileBean.getData();
            doing = data.getDoing() + "";
            status = data.getStatus() + "";
            /**
             * 这是  关于主View的逻辑判断
             *  喇叭
             */
            DialogUtils.hideWaiting(this);

            onSuccess(data);
            //界面太大  scrollview 会滚动到顶部
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    scroll.fullScroll(ScrollView.FOCUS_UP);
                }
            };
            handler.post(runnable);
            handler = null;
        } else {
            img.setVisibility(View.VISIBLE);
            scroll.setVisibility(View.GONE);
            DialogUtils.showToast(DelatileActivity.this, delatileBean.getMsg() + " " + delatileBean.getCode() + "");
            LogUtils.e(delatileBean.getMsg());
            DialogUtils.hideWaiting(this);
        }
        LogUtils.e(delatileBean.getCode() + "");
    }


    @Override
    public void showError(Throwable error) {
        DialogUtils.hideWaiting(this);
        img.setVisibility(View.VISIBLE);
        scroll.setVisibility(View.GONE);
        bottom_navigation.setVisibility(View.GONE);

    }

    /**
     * 这是  关于default 页的判断
     */
    private void onSuccess(final DelatileBean.DataBean data) {
        String contactTag = data.getContactTag();
        if (contactTag.isEmpty()) {                 //顶部喇叭逻辑
            linear_subtitle.setVisibility(View.GONE);
        } else {
            linear_subtitle.setVisibility(View.VISIBLE);
            TextView childAt = (TextView) linear_subtitle.getChildAt(1);
            childAt.setText(contactTag);
        }
        //虚拟号的功能
        final List<DelatileBean.Bean> contactInfo = data.getContactInfo();
//        if (!doing.equals("1") ) {//&& status.equals("3")
//            contactInfo.clear();
//        }
        if (contactInfo.size() != 0) {
            linear_tophone.setVisibility(View.VISIBLE);
            final Adapter_getPhone adapter_getPhone = new Adapter_getPhone(mContext, contactInfo);
            recyclepassword.setAdapter(adapter_getPhone);
            //初始化数据，点击下拉菜单切换
            adapter_getPhone.setMsgNo(GlobalInfoUtils.br_contactInfosize);
            final TextView childAt_tx = (TextView) linear_xiala.getChildAt(0);
            final ImageView childAt_img = (ImageView) linear_xiala.getChildAt(1);
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    if (childAt_tx.getText().toString().equals(getResources().getString(R.string.com_work_bottom))) {
                        childAt_tx.setText(getResources().getString(R.string.com_work_top));
                        GlideUtils.getInstance().setImageWrap(childAt_img, R.mipmap.more_top);
                        adapter_getPhone.setMsgNo(contactInfo);
                        GlobalInfoUtils.br_contactInfosize = contactInfo.size();
                    } else {
                        childAt_tx.setText(getResources().getString(R.string.com_work_bottom));
                        GlideUtils.getInstance().setImageWrap(childAt_img, R.mipmap.xiala);
                        adapter_getPhone.setMsgNo(0);
                        GlobalInfoUtils.br_contactInfosize = 0;

                    }
                }
            };
            linear_xiala.setOnClickListener(onClickListener);
            //  点击 获取  虚拟号
            adapter_getPhone.setOnclick(new Adapter_getPhone.Onclick() {
                @Override
                public void onClickListener(View view, int position) {
                    if (!Check.isFastClick())
                        return;
                    DialogUtils.showWaiting(mContext, "获取中...");
                    String phone = contactInfo.get(position).getPhone();//手机号
                    Presenter_Stract_getPhone presenter_stract_getPhone = new Presenter_Stract_getPhone(new IConstract_Stract.IView() {
                        @Override
                        public <T> void showData(T t) {
                            final GetPhoneBean getPhoneBean = (GetPhoneBean) t;
                            if (getPhoneBean.getCode() == 1000) {
                                DialogUtils.hideWaiting(mContext);
                                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getPhoneBean.getData().getYsh()));//跳转到拨号界面，同时传递电话号码

                                try {
                                    startActivity(dialIntent);
                                } catch (IllegalStateException e) {
                                    DialogUtils.showToast(mContext, "系统找不到您的手机拨号，请联系客服处理");
                                }


                            } else {
                                DialogUtils.hideWaiting(mContext);
                            }

                        }

                        @Override
                        public void showError(Throwable error) {
                            DialogUtils.showToast(mContext, error.getMessage());
                            DialogUtils.hideWaiting(mContext);
                        }
                    });
                    Map<String, Object> stringStringMap = ComsentUtils.onsendDelatileGetPhone(id, phone);
                    presenter_stract_getPhone.setData(stringStringMap);
                }
            });

        } else {
            //这个数组为空 那么就 不显示出租户联系方式的东西
            linear_tophone.setVisibility(View.GONE);

        }
        //返回 关闭Activity
//        mWorkTx.setText(data.getContactTag());//喇叭
        String s;


        channel = data.getChannel();        //channel

        if (channel.equals("3")) {
            s = "#" + "" + data.getVillageaddress();

        } else {
            s = "#" + data.getYid() + " " + data.getVillageaddress();
        }
        btype = data.getBtype();
        mAddress.setText(s);//地址 1(默认)
        status = data.getStatus(); //status
        final String s1 = "#" + data.getYid() + " " + data.getVillageaddress();
        String s2 = "";
        if (!data.getOfficialAddress().isEmpty()) {
            s2 = "#" + data.getYid() + " " + data.getOfficialAddress();
        }
        if (s2.isEmpty()) {
            mAddress_img.setVisibility(View.GONE);
        } else {
            mAddress_img.setVisibility(View.VISIBLE);
        }


        //第一个 tx  里面得 经纬度
        final String officialAddressLat = data.getOfficialAddressLat();
        final String officialAddressLng = data.getOfficialAddressLng();
        //第二个 tx 里面 经纬度
        final String lat = data.getLat();
        final String lng = data.getLng();

        //判断 一下 是否有经纬度
        final String finalS = s2;
        mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                TextView tx = (TextView) v;
                //看点击 了那个  地址
                if (finalS.equals(tx.getText().toString())) {
                    if (officialAddressLat != null) {
                        if (MapUtil.isGdMapInstalled()) {
                            double latx = Double.parseDouble(officialAddressLat);
                            double laty = Double.parseDouble(officialAddressLng);
                            MapUtil.openGaoDeNavi(mContext, 0, 0, null, latx, laty, data.getOfficialAddress());
                        } else {
                            //这里必须要写逻辑，不然如果手机没安装该应用，程序会闪退，这里可以实现下载安装该地图应用
                            DialogUtils.showToast(mContext, "尚未安装高德地图");
                            ActionUtils.onStartGaode(mContext);
                        }
                    }
                } else if (s1.equals(tx.getText().toString())) {
                    if (lat != null) {
                        if (MapUtil.isGdMapInstalled()) {
                            double latx = Double.parseDouble(lat);
                            double laty = Double.parseDouble(lng);
                            MapUtil.openGaoDeNavi(mContext, 0, 0, null, latx, laty, data.getVillageaddress());
                        } else {
                            //这里必须要写逻辑，不然如果手机没安装该应用，程序会闪退，这里可以实现下载安装该地图应用
                            DialogUtils.showToast(mContext, "尚未安装高德地图");
                            ActionUtils.onStartGaode(mContext);
                        }
                    }
                }

            }
        });


        mAddress_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.getOfficialAddress().isEmpty()) {
                    //有房产证得时候 就可以
                    if (mAddress.getText().toString().equals(finalS)) {
                        mAddress.setText(s1);//地址 1(默认)
                    } else {
                        mAddress.setText(finalS);//地址 1(默认)
                    }
                }

            }
        });
//        //长按TextView 弹出复制按钮  复制 得到tx值
        mAddress.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                initPopuptWindow();
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(mAddress.getText());
                DialogUtils.showToast(mContext, "复制成功√");
                return true;
            }
        });


        String statTag = data.getStatTag();//1
        String delayTag = data.getDelayTag();//2
        String orderTag = data.getOrderTag();//3
        String des = data.getDes();//工单备注
        String suitDes = data.getSuitDes();//公寓备注
        String impressionDes = data.getImpressionDes();//印象备注
        onVisinble(mWorknews1, statTag);
        onVisinble(mWorknews2, delayTag);
        onVisinble(mWorknews3, orderTag);
        TextView childAt = (TextView) work_linear.getChildAt(1);
        if (des.equals("")) {
            childAt.setText(getResources().getString(R.string.com_work_detile_gondantx));
        } else {
            childAt.setText(des);
        }

        onVisinble(apartment_linear, suitDes);//公寓备注
        onVisinble(impression_linear, impressionDes);//印象备注

        /**
         * 详情左侧 title
         */
        onTitle();
        /**
         * 已完成 才会有的顶部bannder
         */
        onBanner();
        /**
         * 返工的返回值
         */
        onRework();
        /**
         * 底部导航栏的逻辑  为 doing1  是进行中
         */
        onNavigetion(data);


    }

    private void onNavigetion(final DelatileBean.DataBean data) {
        TextView childAt;
        if (doing.equals("1") && (status.equals("2") || status.equals("6") || status.equals("5"))) {  //进行中的单子/非月度优选保洁
            mCallbaclk.setVisibility(View.GONE);
            startserver.setVisibility(View.GONE);
            bottom_navigation.setVisibility(View.VISIBLE);
             if (btype.equals("13")) {//月度优选保洁
                linear_password.setVisibility(View.GONE);//无获取密码
            }else{
                 linear_password.setVisibility(View.VISIBLE);
             }
            //点击 密码
            getpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    chilrdlinear_password.setVisibility(View.VISIBLE);
                    if (txpassword.getText().toString().equals("")) {
                        getPassword();
                    }
                }
            });

            //此单无法完成的按钮
            childAt = (TextView) bottom_navigation.getChildAt(0);

            //上传图片页面
            LinearLayout childAt1 = (LinearLayout) bottom_navigation.getChildAt(1);

            /**
             * 电池业务
             * status  == 1 就是 有，否则就没有
             */
            DelatileBean.DataBean.Battery battery = this.data.getBattery();
            if (battery.getStatus() == 1) {
                dianchi_linear.setVisibility(View.VISIBLE);
                TextView childAt22 = (TextView) dianchi_linear.getChildAt(0);
                TextView childAt2 = (TextView) dianchi_linear.getChildAt(1);
                childAt22.setText(battery.getTitle() + "：");
                childAt2.setText(battery.getContent());
                bottom_navigation.setVisibility(View.GONE); //隐藏原先布局
                battartry_tx.setVisibility(View.VISIBLE);  //显示更换电池
                TextView childAt3 = (TextView) battartry_tx.getChildAt(0);
                childAt3.setText("请先更换电池");
                battartry_tx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!Check.isFastClick())
                            return;
                        Intent intent = new Intent(mContext, RNActivity.class);
                        GlobalInfoUtils.Page_RN = "Battery_business"; //电池业务
                        GlobalInfoUtils.multServer_id = DelatileActivity.this.data.getId();
                        startActivityForResult(intent, 1);
                    }
                });
            } else if (battery.getStatus() == 3) { //已完成 电池页面(更换/未更换)
                dianchi_linear.setVisibility(View.VISIBLE);
                TextView childAt22 = (TextView) dianchi_linear.getChildAt(0);
                TextView childAt2 = (TextView) dianchi_linear.getChildAt(1);
                childAt22.setText(battery.getTitle() + ":");
                childAt2.setText(battery.getContent());
                bottom_navigation.setVisibility(View.VISIBLE); //原先布局
                battartry_tx.setVisibility(View.GONE);  //显示更换电池
                childAt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击上传的时候 请求一下数据 如果给数据  就让上~
                        if (!Check.isFastClick())
                            return;
                        onLoad(data.getPhotoBeforeWork());
                    }
                });
            } else  {
                dianchi_linear.setVisibility(View.GONE);
                battartry_tx.setVisibility(View.GONE);
                if (data.getPhotoBeforeWork()!=null) {
                    if(data.getPhotoBeforeWork().equals("1")){//显示开荒工单的服务前~
                        TextView childAt2 = (TextView) childAt1.getChildAt(1);//右边的按钮文字
                        childAt2.setText("上传做单前照片");
                    }
                    childAt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //点击上传的时候 请求一下数据 如果给数据  就让上~
                            if (!Check.isFastClick())
                                return;
                            onLoad(data.getPhotoBeforeWork());
                        }
                    });
                }else{
                    childAt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //点击上传的时候 请求一下数据 如果给数据  就让上~
                            if (!Check.isFastClick())
                                return;
                            onLoad(data.getPhotoBeforeWork());
                        }
                    });
                }


            }

            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    //  9/19 添加返工工单无法再次返工的逻辑
                    if (channel.equals("4")) {
                        View view = DialogUtils.showCenterTishi(mContext);
                        View viewById = view.findViewById(R.id.tvAdd);
                        viewById.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!Check.isFastClick())
                                    return;
                                DialogUtils.closeLJdialog();
                            }
                        });
                    } else {
                        /**
                         * 第五种 工单提醒
                         */
                        if (data.getAboutReminderList().get_$5() == 1) {

                            List_Jobreminder(mContext, id, "5");


                        } else {
                            Intent intent = new Intent(DelatileActivity.this, NoScrosess_Activity.class);
                            intent.putExtra("id", id);
                            startActivityForResult(intent, 1);
                        }

                    }

                }
            });


        } else if (!doing.equals("1") && (status.equals("2") || status.equals("6") || status.equals("5"))) {
            /**
             * 工单提醒
             */
            if (TYPE_Tixing == 0) {
                if (doing.equals("0") && channel.equals("4")) {  //返工工单的工单提醒是这个
                    View view = DialogUtils.showQiang_storge(mContext, "返工须知", "由于" + data.getGid() + "工单存在质量问题特建立此返工工单，请务必保证卫生质量。", "知道了");
                    View viewById = view.findViewById(R.id.it_tx);
                    viewById.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!Check.isFastClick())
                                return;
                            DialogUtils.closeLJdialog();
                        }
                    });
                } else {
                    DelatileBean.DataBean.AboutReminderList aboutReminderList = data.getAboutReminderList();
                    //工单提醒 等于1  才做出操作
                    if (aboutReminderList.get_$1() == 1) {
                        String id = data.getId();
                        GlobalInfoUtils.List_Jobreminder(mContext, id, "1");
                    }
                }


                TYPE_Tixing++;


            }
            linear_password.setVisibility(View.GONE);
            mCallbaclk.setVisibility(View.VISIBLE);
            bottom_navigation.setVisibility(View.GONE);
            startserver.setVisibility(View.VISIBLE);
            //点击开始服务
            startserver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    onStartServer();
                }
            });
        } else if (!doing.equals("1") && status.equals("3")) {  //已完成按钮
            mCallbaclk.setVisibility(View.VISIBLE);
            linear_password.setVisibility(View.GONE);

        }


        /**
         * 电池业务
         * status  == 1 就是 有，否则就没有
         */
        DelatileBean.DataBean.Battery battery = this.data.getBattery();
        if (battery.getStatus() == 1) {
            dianchi_linear.setVisibility(View.VISIBLE);
            TextView childAt22 = (TextView) dianchi_linear.getChildAt(0);
            TextView childAt2 = (TextView) dianchi_linear.getChildAt(1);
            childAt22.setText(battery.getTitle() + ":");
            childAt2.setText(battery.getContent());


        } else if (battery.getStatus() == 3) { //已完成 电池页面(更换/未更换)
            dianchi_linear.setVisibility(View.VISIBLE);
            TextView childAt22 = (TextView) dianchi_linear.getChildAt(0);
            TextView childAt2 = (TextView) dianchi_linear.getChildAt(1);
            childAt22.setText(battery.getTitle() + ":");
            childAt2.setText(battery.getContent());
        }


    }


    /**
     * 工单提醒   单独的 跳转到  无法完成页
     */
    public void List_Jobreminder(final Activity mContext, final String orderId, final String eventId) {
        eventId_ = eventId; //result方法返回後 用到這個值
        Map<String, Object> map = ComsentUtils.onSendJob_reminder(orderId, eventId);
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(URLConstant.URL_GetRemiuderInfo).post(requestBody.build()).tag(mContext).build();
        Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {


            private int parseInt;

            @Override
            public void onFailure(Call call, IOException e) {
                //todo  错误 应该做什么  什么都不做 等着
                Looper.prepare();
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext,e.getMessage());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();
                String string = response.body().string();
                ReminBean reminBean = gson.fromJson(string, ReminBean.class);
                int code = reminBean.getCode();
                Looper.prepare();
                if (code == 1000) {
                    ReminBean.DataBean data = reminBean.getData();
                    String titleType = data.getTitleType(); //title
                    String content = data.getContent();     //描述文字
                    final String second = data.getSecond();     //时间秒钟
                    String url = data.getUrl();     //时间秒钟
                    final Handler handler;
//                        isstatus  == 1  就开始
                    if (data.getIsStatus().equals("1")) {
                        if (data.getType().equals("text")) {
                            if (data.getTipsType() == 1) {//手动关闭 dialog
                                //手dong开关
                                View view = DialogUtils.showQiang_storge(mContext, titleType, content, "知道了");
                                View viewById = view.findViewById(R.id.it_tx);
                                viewById.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (!Check.isFastClick())
                                            return;
                                        DialogUtils.closeLJdialog();
                                        onEventSuccess(eventId);
                                    }


                                });
                            } else if (data.getTipsType() == 2) {  //自动按照秒数 关闭
                                View view = DialogUtils.showQiang_storge(mContext, titleType, content, second + "s后自动关闭");
                                final TextView tx_second = view.findViewById(R.id.it_tx);
                                tx_second.setTextColor(mContext.getResources().getColor(R.color.color_move_4C90F3));
                                tx_second.setOnClickListener(null);
                                parseInt = Integer.parseInt(second);
                                handler = new Handler();
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        parseInt--;
                                        if (parseInt > 0) {
                                            tx_second.setText(parseInt + "s后自动关闭");
                                        } else if (parseInt == 0) {
                                            DialogUtils.closeLJdialog();
                                            onEventSuccess(eventId);
                                        }
                                        handler.postDelayed(this, 1000);

                                    }

                                };
                                handler.postDelayed(runnable, 1000);
                            }
                        } else if (data.getType().equals("img")) {
                            GlobalInfoUtils.showQiang_storgeImage(mContext, url, second);
                        }
                    }
                }
                Looper.loop();

            }


        });

    }


    /**
     * start server  详情 开启服务
     */
    private void onStartServer() {


        String date = (String) SPUtil.readSP("date", "");
        String days = SystemDateUtils.getNowDate();
        if (date.equals(days)) {
            DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_login_tips));
            //2.开工单时进行的提醒
            if (data.getAboutReminderList().get_$2() == 1) {
                List_Jobreminder(mContext, id, "2");
            } else {
                onRequestIMG();
            }
        } else {
            //2.开工单时进行的提醒
            if (data.getAboutReminderList().get_$2() == 1) {
                List_Jobreminder(mContext, id, "2");
            } else {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //提示用户开户权限
                    openWord();
                } else {
                    // 用户已经同意该权限
                    Intent intent = new Intent(mContext, CameraActivity.class);
                    intent.putExtra("id", id);
                    startActivityForResult(intent, GlobalInfoUtils.Camera_ReQesttCode);
                    DialogUtils.closedialog();

                }
            }
        }

    }

    /**
     * 工单提醒   第二种 关于无法完成和开启服务的逻辑
     *
     * @param eventId
     */
    private void onEventSuccess(String eventId) {
        if (eventId.equals("2")) {
            String date = (String) SPUtil.readSP("date", "");
            String days = SystemDateUtils.getNowDate();
            if (date.equals(days + "")) {//开启工单(有缓存的时候)
                onRequestIMG();
            } else {
                openWord();
            }
        } else { //无法完成
            Intent intent = new Intent(mContext, NoScrosess_Activity.class);
            intent.putExtra("id", id);
            mContext.startActivityForResult(intent, 1);
        }
    }


    /**
     * 上传图片提交的时候 工单提醒后 进行权限确认(无缓存开单的情况下)
     */
    public void openWord() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            String[] perms = {"android.permission.CAMERA"};
            RxPermissions rxPermissions = new RxPermissions(this);
            //动态申请权限
            rxPermissions.requestEach(perms).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        Intent intent = new Intent(mContext, CameraActivity.class);
                        intent.putExtra("id", id);
                        startActivityForResult(intent, GlobalInfoUtils.Camera_ReQesttCode);
                        DialogUtils.closeLJdialog();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没选中不再询问,再次启动时，还会提示请求权限的对话框
                        Log.d("TAG", permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，并且选中不再询问
                        View view = DialogUtils.showCentreDialog(mContext, getResources().getString(R.string.com_work_carema));
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
            // 用户已经同意该权限
            Intent intent = new Intent(mContext, CameraActivity.class);
            intent.putExtra("id", id);
            startActivityForResult(intent, GlobalInfoUtils.Camera_ReQesttCode);
            DialogUtils.closedialog();

        }


    }


    /**
     * 点击上串图片的第一个协议
     */
    private void onLoad(final String aotuwork) {
        //  第一个协议
        Presenter_StractIml presenter_stractIml = new Presenter_StractIml(new IConstract_Stract.IView() {
            @Override
            public <T> void showData(T t) {
                DelatileMsg_bean delatileMsgBean = (DelatileMsg_bean) t;
                int code = delatileMsgBean.getCode();
                if (code == 1000) {
                    //跳转到
                    if (aotuwork!=null) {
                        if(aotuwork.equals("1")){
                            Intent intent = new Intent(mContext, SubmitauditActivityAuditforward.class);
                            intent.putExtra("id", id);
                            intent.putExtra("channel", channel);
                            intent.putExtra("btype", btype);
                            intent.putExtra("btype", btype);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(mContext, SubmitauditActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("channel", channel);
                            intent.putExtra("btype", btype);
                            startActivity(intent);
                        }


                    }else{
                        Intent intent = new Intent(mContext, SubmitauditActivityAuditforward.class);
                        intent.putExtra("id", id);
                        intent.putExtra("channel", channel);
                        intent.putExtra("btype", btype);
                        startActivity(intent);

                    }

                } else if (code == 10011) {
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp(mContext);
                    DialogUtils.showToast(mContext, delatileMsgBean.getMsg());
                } else if (code == 9901) {
                    View view = DialogUtils.showQiang_storge(mContext, "温馨提示", delatileMsgBean.getMsg(), "好的");
                    view.findViewById(R.id.it_tx).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!Check.isFastClick())
                                return;
                            DialogUtils.closeLJdialog();
                        }
                    });
                } else {
                    DialogUtils.showToast(mContext, delatileMsgBean.getMsg());
                }


            }

            @Override
            public void showError(Throwable error) {
            }
        });
        Map<String, Object> stringObjectMap = ComsentUtils.onSendLoadImageBt(id, 1);
        presenter_stractIml.setData(stringObjectMap);
    }


    /**
     * 返工工单的逻辑
     */
    private void onRework() {
        String orderRevokeReason = data.getOrderRevokeReason();
        if (!orderRevokeReason.equals("")) {
            linear_rework.setVisibility(View.VISIBLE);
            TextView linear_reworktx_tx = (TextView) linear_rework.getChildAt(1);
            linear_reworktx_tx.setText(orderRevokeReason);
            final List<String> orderRevokeImage = data.getOrderRevokeImage();
            if (orderRevokeImage != null && orderRevokeImage.size() != 0) {
                rework_imglinear.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 4);

                wenti_recycle.setLayoutManager(linearLayoutManager);
                /**
                 * 展示  返工图片
                 */
                adapter_delatileRework = new Adapter_DelatileRework(orderRevokeImage);
                wenti_recycle.setAdapter(adapter_delatileRework);
                adapter_delatileRework.setOnclick(new Adapter_DelatileRework.Onclick() {
                    @Override
                    public void OnclickListener(View v, int position) {
                        if (!Check.isFastClick())
                            return;
                        GlobalInfoUtils.onImageEnlarge(orderRevokeImage, position + 1, mContext, GlobalInfoUtils.ImageDa);
                    }
                });
                /**
                 * 点击  切换
                 */
                if (data.getOrderRevokeImage().size() > 4) {
                    rework_imglinear_gengduo.setVisibility(View.VISIBLE);
                    rework_imglinear_gengduo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!Check.isFastClick())
                                return;
                            if (getResources().getString(R.string.com_work_detile_dow).equals(rework_imglinear_gengduo.getText().toString())) {
                                rework_imglinear_gengduo.setText(getResources().getString(R.string.com_work_detile_opens));
                                adapter_delatileRework.setPAGE(true);
                            } else {
                                rework_imglinear_gengduo.setText(getResources().getString(R.string.com_work_detile_dow));
                                adapter_delatileRework.setPAGE(false);
                            }

                        }
                    });
                } else {
                    rework_imglinear_gengduo.setVisibility(View.GONE);
                }

            } else {
                rework_imglinear.setVisibility(View.GONE);
            }


        } else {
            linear_rework.setVisibility(View.GONE);
            rework_imglinear.setVisibility(View.GONE);
        }
    }

    /**
     * banner 图片  是3 的时候 就显示  不是 就不显示
     * 差一个 通用的按钮 这些逻辑
     */
    @SuppressLint("WrongConstant")
    private void onBanner() {

        if (status.equals("3")) {
            bannerRelative.setVisibility(View.VISIBLE);
            final List<String> imgList = new ArrayList<>();//img 装的list
            final List<String> imgbtList = new ArrayList<>();//装bt的list
            final List<Integer> sizeLIst = new ArrayList<>();//装img下标的list
            final List<ImageView> mImageList = new ArrayList<>(); //装imageview的 viewlist  用于搞viewpager

            final List<DelatileBean.DataBean.ImagelistBean.ListBean> list = data.getImagelist().getList(); //请求回来的数据
            for (int i = 0; i < list.size(); i++) {
                List<String> list1 = list.get(i).getList();
                String name = list.get(i).getName();
                imgbtList.add(name);
                sizeLIst.add(list1.size());
                imgList.addAll(list1);
            }
            int i2 = PhoneUtils.dip2px(mContext, 80);
            for (int i = 0; i < imgList.size(); i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideUtils.getInstance().setImages(imageView, imgList.get(i), i2, i2, true);
                mImageList.add(imageView);
            }
            final TextView txpage = findViewById(R.id.txpage);
            final TextView txpagenum = findViewById(R.id.txpagenum);

            //当这个详情图片 是一张的时候 不可滑动
            if (imgList.size() == 1) {
                viewPager.setNoScroll_State(true);
            } else {
                viewPager.setNoScroll_State(false);
            }

            viewPagerAdapter = new ViewPagerAdapter(mImageList, viewPager);
            viewPager.setAdapter(viewPagerAdapter);
            final List<View> views = new ArrayList<>();
            LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            for (int i = 0; i < imgbtList.size(); i++) {
                TextView textView = new TextView(mContext);
                textView.setTextColor(getResources().getColor(R.color.color_move_f4f4f4));
                textView.setText(imgbtList.get(i));
                if (i == 0) {
                    onSeletor_Yes((TextView) textView);
                } else {
                    onSeletor_No(textView);
                }
                textView.setLayoutParams(vlp);
                int i1 = PhoneUtils.dip2px(mContext, 5);
                textView.setPadding(i1, i1, i1, i1);  //动态设置padding  注意  先设置 layoutparems 再padding
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                linear_grop.addView(textView);
                views.add(textView);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!Check.isFastClick())
                            return;
                        for (int i1 = 0; i1 < views.size(); i1++) {
                            View view = views.get(i1);
                            if (v.equals(view)) {
                                onSeletor_Yes((TextView) view);
                            } else {
                                onSeletor_No((TextView) view);
                            }
                            if (view.equals(v)) {
                                if (i1 == 0) {
                                    viewPager.setCurrentItem(0);
                                } else {
                                    int pagesize = 0;
                                    for (int i2 = 0; i2 <= i1 - 1; i2++) {
                                        pagesize += sizeLIst.get(i2);
                                        viewPager.setCurrentItem(pagesize);  //涉及到  最后 一个 我把他 % 了 到最后一个 就会是0
                                    }
                                }
                            }

                        }


                    }
                });
            }
            txpage.setText("/" + imgList.size());  //设置初始值
            txpagenum.setText(1 + "");//初始值


            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    if (imgList.size() > i) {
                        if (i == 0) {
                            txpage.setText("/" + (imgList.size()));
                            txpagenum.setText(1 + "");//初始值
                        } else {
                            txpage.setText("/" + (imgList.size()));
                            txpagenum.setText(i + 1 + "");//初始值
                        }

                    } else {
                        txpage.setText("/" + (imgList.size()));
                        if ((i % imgList.size()) == 0) {
                            txpagenum.setText("1");//初始值
                        } else {
                            txpagenum.setText((i % imgList.size()) + "");//初始值
                        }

                    }
                    //半完成   已经差不多
                    String page = txpagenum.getText().toString();

                    for (int i1 = 0; i1 < sizeLIst.size(); i1++) {
                        View view = views.get(i1);

                        if (i1 == 0) {
                            if (sizeLIst.get(0) >= Integer.parseInt(page)) {
                                for (int j = 0; j < views.size(); j++) {
                                    View view1 = views.get(j);
                                    if (view1.equals(view)) {
                                        onSeletor_Yes((TextView) view1);
                                    } else {
                                        onSeletor_No((TextView) view1);
                                    }
                                }
                            }
                        } else if (i1 == 1) {
                            if (sizeLIst.get(0) + sizeLIst.get(1) >= Integer.parseInt(page) && Integer.parseInt(page) > sizeLIst.get(0)) {
                                for (int j = 0; j < views.size(); j++) {
                                    View view1 = views.get(j);
                                    if (view1.equals(view)) {
                                        onSeletor_Yes((TextView) view);
                                    } else {
                                        onSeletor_No((TextView) view1);
                                    }
                                }
                            }


                        } else if (i1 == 2) {
                            if (sizeLIst.get(0) + sizeLIst.get(1) + sizeLIst.get(2) >= Integer.parseInt(page) && Integer.parseInt(page) > sizeLIst.get(0) + sizeLIst.get(1)) {
                                for (int j = 0; j < views.size(); j++) {
                                    View view1 = views.get(j);
                                    if (view1.equals(view)) {
                                        onSeletor_Yes((TextView) view);
                                    } else {
                                        onSeletor_No((TextView) view1);
                                    }
                                }
                            }


                        } else if (i1 == 3) {
                            if (sizeLIst.get(0) + sizeLIst.get(1) + sizeLIst.get(2) + sizeLIst.get(3)
                                    >= Integer.parseInt(page) && Integer.parseInt(page) > sizeLIst.get(0) + sizeLIst.get(1) + sizeLIst.get(2)) {
                                for (int j = 0; j < views.size(); j++) {
                                    View view1 = views.get(j);
                                    if (view1.equals(view)) {
                                        onSeletor_Yes((TextView) view);
                                    } else {
                                        onSeletor_No((TextView) view1);
                                    }
                                }
                            }


                        } else if (i1 == 4) {
                            if (sizeLIst.get(0) + sizeLIst.get(1) + sizeLIst.get(2) + sizeLIst.get(3) + sizeLIst.get(4)
                                    >= Integer.parseInt(page) && Integer.parseInt(page) > sizeLIst.get(0) + sizeLIst.get(1) + sizeLIst.get(2) + sizeLIst.get(3)) {
                                for (int j = 0; j < views.size(); j++) {
                                    View view1 = views.get(j);
                                    if (view1.equals(view)) {
                                        onSeletor_Yes((TextView) view);
                                    } else {
                                        onSeletor_No((TextView) view1);
                                    }
                                }
                            }
                        } else if (i1 == 5) {
                            if (sizeLIst.get(0) + sizeLIst.get(1) + sizeLIst.get(2) + sizeLIst.get(3) + sizeLIst.get(4) + sizeLIst.get(5)
                                    >= Integer.parseInt(page) && Integer.parseInt(page) > sizeLIst.get(0) + sizeLIst.get(1) + sizeLIst.get(2) + sizeLIst.get(3) + sizeLIst.get(4)) {
                                for (int j = 0; j < views.size(); j++) {
                                    View view1 = views.get(j);
                                    if (view1.equals(view)) {
                                        onSeletor_Yes((TextView) view);
                                    } else {
                                        onSeletor_No((TextView) view1);
                                    }
                                }
                            }
                        }


                    }

                }

                @Override
                public void onPageScrollStateChanged(int i) {


                }
            });
            //点击图片 跳转到大图
            viewPagerAdapter.setOnclik(new ViewPagerAdapter.Onclik() {
                @Override
                public void Onclick(View v, int position) {
                    if (!Check.isFastClick())
                        return;
                    int parseInt = Integer.parseInt(txpagenum.getText().toString());
                    GlobalInfoUtils.onImageEnlarge(imgList, parseInt, mContext, GlobalInfoUtils.ImageDa);

                }
            });

        }

    }


    private void onSeletor_No(TextView textView) {
        textView.setBackground(null);
    }

    private void onSeletor_Yes(TextView textView) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(1, getResources().getColor(R.color.color_move_000000));
        int i1 = PhoneUtils.dip2px(mContext, 30);
        gradientDrawable.setCornerRadius(i1);
        gradientDrawable.setColor(getResources().getColor(R.color.color_move_4C90F3));
        textView.setBackground(gradientDrawable);
    }

    /**
     * 详情里面的  最下方文字标签
     */
    private void onTitle() {
        List<String> title = new ArrayList<>(); //左侧标签title
        List<String> list = new ArrayList<>();
        title.add("工单编号");
        list.add((data.getGid()));

        title.add("工单类型");
        list.add((data.getBtypename()));
        if (!channel.equals("3")) {   // 不是C端工单 || BITMAP是 1 的时候  上门时段显示
            title.add("公寓类型");
            list.add((data.getGtype()));
            if (data.getBtype().equals("1")) {
                title.add("上门周期");//添加
                list.add(data.getStimestart() + "-" + data.getStimeend());//添加
            }
            title.add("保洁区域");
            list.add((data.getCleanarea()));
            title.add("租住类型");
            list.add((data.getRentType()));
            if (!data.getTimeTag().isEmpty()) {
                title.add("服务时长");
                list.add((data.getHoursTag()));
            }

        } else {                                                             //C端工单情况下 : 去掉公寓类型、保洁区域、租住类型、服务管家
            if (data.getBtype().equals("3") || data.getBtype().equals("101")) { //3:开荒 101:擦玻璃  需要添加服务面积
                String squareMetre = data.getSquareMetre();
                if (squareMetre != null) {
                    title.add("服务面积");
                    list.add(squareMetre);
                }
            } else {
                if (!data.getTimeTag().isEmpty()) {
                    title.add("服务时长");
                    list.add((data.getHoursTag()));
                }
            }
            //11/05 添加服务明细
            if (data.getServiceDetails()!=null) {
                if (!data.getServiceDetails().isEmpty()) {
                    title.add("服务明细");
                    list.add((data.getServiceDetails()));
                }
            }



        }
        if (btype.equals("2")) {//退转换保洁添加床品尺寸
            title.add("床品尺寸");
            list.add((data.getBedTag()));
        }

        title.add("预约上门日期");
        list.add((data.getPlanservicetime()));
        if (!data.getTimeTag().isEmpty()) {
            title.add("租户预约时段");
            list.add((data.getTimeTag()));

        }


        title.add("保洁人员");
        list.add((data.getBnames()));
        if (!channel.equals("3")) {   // 不是C端工单 才有这个东西
            title.add("服务管家");
            if (!data.getCollecthousecontact().getPhone().isEmpty()) {
                list.add(data.getCollecthousecontact().getName() + ":" + data.getCollecthousecontact().getPhone());
            } else {
                list.add(data.getCollecthousecontact().getName());
            }
        }

        if (null != data.getCreatePerson()) {
            title.add("创建人");
            if (!data.getCreatePerson().getPhone().isEmpty()) {
                list.add(data.getCreatePerson().getName() + ":" + data.getCreatePerson().getPhone());
            } else {
                list.add(data.getCreatePerson().getName());
            }
        }
        rec_title.setAdapter(new AdapterDelatile_Title(title, DelatileActivity.this));
        rec_title2.setAdapter(new AdapterDelatile_data(list, DelatileActivity.this));
    }


    /**
     * 简单的会显方法
     *
     * @param statTag
     */
    private void onVisinble(View view, String statTag) {
        if (view instanceof LinearLayout) {
            if (statTag != null) {

                if (statTag.equals("")) {
                    view.setVisibility(View.GONE);
                } else {
                    view.setVisibility(View.VISIBLE);
                    TextView childAt = (TextView) ((LinearLayout) view).getChildAt(1);
                    childAt.setText(statTag);
                }

            } else {
                view.setVisibility(View.GONE);
            }
        } else {
            if (statTag != null) {

                TextView tx = (TextView) view;
                if (statTag.equals("")) {
                    tx.setVisibility(View.GONE);
                } else {
                    tx.setVisibility(View.VISIBLE);
                    tx.setText(statTag);
                }
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回键
            case R.id.callbaclk:

                finish();
                break;

        }
    }


    /**
     * 开启服务
     */
    private void onRequestIMG() {
        Runnable requestTask = new Runnable() {
            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                Map<String, Object> map = ComsentUtils.onSendDelatileFace(id, id);
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
                    Request request = new Request.Builder().url(URLConstant.Base_startServer).post(requestBody.build()).tag(mContext).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    Gson gson = new Gson();
                    imageBean = gson.fromJson(execute.body().string(), LoadScrosses.class);
                    if (execute.isSuccessful()) {
                        msg.what = GlobalInfoUtils.REQUEST_SUCCESS;
                        DialogUtils.hideWaiting(mContext);
                    } else {
                        DialogUtils.hideWaiting(mContext);
                        msg.what = GlobalInfoUtils.REQUEST_FAIL;
                    }
                } catch (IOException ex) {
                    msg.what = GlobalInfoUtils.REQUEST_FAIL;
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
                    if (imageBean.getCode() == 1000) {
                        presenter = new Presenter_WorkDelatilelml(DelatileActivity.this);
                        Map<String, Object> stringObjectMap = ComsentUtils.onSendDelatile(id);
                        presenter.setData(stringObjectMap);
                        //08-28  修改  true 的时候 证明是有密码的 非修改则没有。
                        getPassword();

                        DialogUtils.hideWaiting(mContext);
                    } else {
                        DialogUtils.showToast(mContext, imageBean.getMsg());
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent dat1) {
        super.onActivityResult(requestCode, resultCode, dat1);
        if (requestCode == GlobalInfoUtils.Camera_ReQesttCode && resultCode == GlobalInfoUtils.Camera_ReSultCode) {

                getPassword();

        } else if (resultCode == 100) {
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        } else if (requestCode == 9901) {  //工单提醒 照片dialog 返回的 东西
            if (!eventId_.isEmpty()) {
                onEventSuccess(eventId_);
            }
        } else if (resultCode == 1) {//电池RN
            Map<String, Object> stringObjectMap = ComsentUtils.onSendDelatile(id);
            presenter.setData(stringObjectMap);
        } else if (resultCode == 111) {
            //camera 中 关闭这个页面的逻辑
            finish();
        }
    }


    /**
     * 获取密码
     */
    private void getPassword() {
        Presenter_Delatile_getPassrod presenter_delatile_startServer = new Presenter_Delatile_getPassrod(new IConstract_Stract.IView() {
            @Override
            public <T> void showData(T t) {
                GetPasswordBean delabtbean = (GetPasswordBean) t;
                if (delabtbean.getCode() == 1000) {
                    chilrdlinear_password.setVisibility(View.VISIBLE);
                    txpassword.setText(delabtbean.getData().getPass());
                } else if(delabtbean.getCode() == 10011){
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp(mContext);
                    DialogUtils.showToast(mContext, delabtbean.getMsg());
                }else{
                    chilrdlinear_password.setVisibility(View.VISIBLE);
                    txpassword.setText(delabtbean.getData().getPass());
                }
                getpassword.setText("点击此处更新密码");

            }

            @Override
            public void showError(Throwable error) {
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext, error.getMessage());
            }
        });
        Map<String, Object> stringObjectMap = ComsentUtils.onSendgetPasssword(id);
        if (data.getBtype().equals("13")) {//月度优选保洁
            linear_password.setVisibility(View.GONE);//无获取密码
        }else{
            presenter_delatile_startServer.setData(stringObjectMap);
        }
    }


//    /**
//     * 长按  弹出pupwindow 的方法   原先用于复制 现在不用了
//     */
//    private void initPopuptWindow() {
//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        View popupWindow = layoutInflater.inflate(R.layout.popup_window, null, false);
//        TextView btnCopy = (TextView) popupWindow.findViewById(R.id.btnCopy);
//        final PopupWindow mPopupWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        // ���д��� ����Ҫ
//        //点击不外部消失的两个方法
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.showAsDropDown(mAddress, PhoneUtils.dip2px(mContext, 100), 0);
////        mPopupWindow.showAtLocation(mAddress,Gravity.TOP,0,0);
//        btnCopy.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
//                clipboard.setText(mAddress.getText());
//                mPopupWindow.dismiss();
//                DialogUtils.showToast(mContext, "已复制");
//            }
//        });
//    }


}
