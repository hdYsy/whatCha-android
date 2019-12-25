package com.lianjie.andorid.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.AdapterJinji_Recycle;
import com.lianjie.andorid.adapter.Adapter_Submitaud;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.ImageBean;
import com.lianjie.andorid.bean.LoadScrosses;
import com.lianjie.andorid.bean.LodSizeImageBean;
import com.lianjie.andorid.bean.ReminBean;
import com.lianjie.andorid.bean.SubTwoPhotoBean;
import com.lianjie.andorid.bean.SubaudBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_StractSubmitaIml;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Stract_SubAudiIml;
import com.lianjie.andorid.newsView.AutoLinefeedLayout;
import com.lianjie.andorid.rnui.RNActivity;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideCacheUtil;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.PhoneUtils;
import com.lianjie.andorid.utils.PhotoUtils;
import com.lianjie.andorid.utils.SPUtil;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/5
 * Time: 15:01
 * 上传图片页
 * 小杨
 */
public class SubmitauditActivity extends BaseActivity {

    private static int TAPE = 0; // 等于1   为 photoList  等于 2  为 order/photo

    private static final int REQUEST_SUCCESS = 0;
    private static final int REQUEST_FAIL = 1;
    private View broad_title;
    private SubmitauditActivity mContext;
    private static String id;
    private LinearLayout linearGrop;
    private String channel;
    private int List_id = 3;  //当 这个  请求回来的参数是  3：开荒  8深度返还   4返工  的时候 是无示例图的
    private int List_id2 = 8;
    private int List_channel = 4;
    public String url = "";
    private List<String> list = new ArrayList<>();
    private int finalI;  //老二
    private int pageda;   // 最大的那个list
    private List<SubaudBean.DataBean.ListBean> dataList;

    private int page;
    private String btype;
    private ImageView addImage;
    List<String> listImageDa = new ArrayList<>();  //第二种的 传图片、
    private SubTwoPhotoBean subTwoPhotoBean = null;//无示例图的bean
    private SubaudBean subaudBean = null;//有示例的bean
    private LayoutInflater mInflater;
    private ImageBean imageBean;

    List<List<String>> pageList ;//有示例图ImageList
    List<Adapter_Submitaud> adapterlist = new ArrayList<>();


    List<SubaudBean.DataBean.ListBean> listBean_list = new ArrayList<>();
    private int pagepage;
    private int jinjiPage = 0;  //紧急保洁等逻辑
    private List<String> img_list;
    private TextView tx2_server;
    private TextView work_tx;
    private Dialog dialog;
    private AdapterJinji_Recycle adapterJinji_recycle;

    private List<View> list_img = new ArrayList<>();
    private int allow;
    private TextView lod_size;
    private static final int PL_PHOTOCODE = 1123; //批量上传的CODE
    private static final  int PL_SUCCESS= 113; //成功
    private static final  int PL_DATAFULL = 115; //上传失败
    private static int PL_CLOSE= 0; //上传完毕
    private LodSizeImageBean lod; //批量上传 的 bean类
//    private  Map<Integer,String> PL_succesPage = new HashMap<>();//批量成功
    private int  PL_succesPage = 0;//批量成功
//    private  Map<Integer,String> PL_errorPage = new HashMap<>();//批量失败
    private  int PL_errorPage = 0;//批量失败

    private int PL_PAGE = 0;
   private Map<String,Integer> PL_map  = new HashMap<>();


    @Override
    protected int onLayout() {
        mContext = this;
        mInflater = LayoutInflater.from(mContext);
        return R.layout.activity_delatiles_submit_audit;
    }

    @Override
    protected void onDrishData() {
//      subTwoPhotoBean;//无示例图的bean
//       subaudBean;//有示例的bean
//       objectFromShare = SPUtil.getObjectFromShare(mContext, id);
        if (subTwoPhotoBean!=null) {
            subTwoPhotoBean.getData().getList().get(0).setList(listImageDa);//20号添加存储list图片逻辑
            SPUtil.addSP(id,subTwoPhotoBean);
        }else if(subaudBean!= null){
//            dataList.set()     dataList
            SPUtil.addSP(id,pageList);
        }




    }

    @Override
    protected void initData() {
    }

    /**
     * 无示例图
     *
     * @param button
     */
    private void onNoSubList(Button button) {


        //  第一个协议
        Presenter_StractSubmitaIml presenter_stractIml = new Presenter_StractSubmitaIml(new IConstract_Stract.IView() {
            @Override
            public <T> void showData(T t) {
                subTwoPhotoBean = (SubTwoPhotoBean) t;
                Object objectFromShare = SPUtil.readSP(id,subTwoPhotoBean );
                if(objectFromShare!=null){

                    subTwoPhotoBean = (SubTwoPhotoBean) objectFromShare;
                }else{
                }
                int code = subTwoPhotoBean.getCode();
                if (code == 1000) {
                    DialogUtils.hideWaiting(mContext);
                    SubTwoPhotoBean.DataBean data = subTwoPhotoBean.getData();
                    onNoSubListNewView(data);
                    /**
                     * 上传图片 第三种 工单提醒
                     */
                    if (data.getAboutReminderList().get_$3() == 1) {
                        GlobalInfoUtils.List_Jobreminder(mContext, id, "3");
                    }


                } else if (code == 10011) {
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp((Activity) mContext);
                    DialogUtils.showToast(mContext, subTwoPhotoBean.getMsg());
                } else {
                    DialogUtils.hideWaiting(mContext);
                    DialogUtils.showToast(mContext, subTwoPhotoBean.getMsg());
                }


            }


            private void onNoSubListNewView(SubTwoPhotoBean.DataBean data) {
                List<SubTwoPhotoBean.DataBean.ListBean> list = data.getList();
                AutoLinefeedLayout parent;

                for (int i = 0; i < list.size(); i++) {
                    final View inflate = mInflater.inflate(R.layout.layout_new_inclode, null, false);
                    TextView tx_biaoti = inflate.findViewById(R.id.tx_biaoti);  //标题
                    TextView rework_imglinear_gengduo = inflate.findViewById(R.id.rework_imglinear_gengduo);  //展开更多
                    TextView tx_biaotipage = inflate.findViewById(R.id.tx_biaotipage);//img的数量
                    final AutoLinefeedLayout linearNo = inflate.findViewById(R.id.linearNo);
                    addImage = inflate.findViewById(R.id.addImage);
                     parent  = (AutoLinefeedLayout) addImage.getParent();

                    tx_biaoti.setText(list.get(i).getCatname());
                    //8-28修改无图不显示的问题
                    if (!(list.get(i).getNum()+"").equals("0")) {
                        tx_biaotipage.setText("(" + list.get(i).getNum() + "张)");
                    }
                    for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                        View inflate1 = mInflater.inflate(R.layout.item_noscrosses_addimg, null, false);
                        ImageView img = inflate1.findViewById(R.id.img);
                        View imgx = inflate1.findViewById(R.id.img_x);
                        GlideUtils.getInstance().setImages(img, list.get(i).getList().get(i1), 0, 0, false);
                        parent.addView(inflate1,parent.getChildCount() - 1);
                        img.setTag(i1);
                        imgx.setTag(i1);
                        listImageDa.add(list.get(i).getList().get(i1));
                        list_img.add(inflate1);
                        //tag+1无示例图  暂时+1  配合详情页 完成图片的左右滑动
                    }
                    for (int i1 = 0; i1 < list_img.size(); i1++) {
                        final View view = list_img.get(i1);
                        View viewById = view.findViewById(R.id.img);
                        View viewById1 = view.findViewById(R.id.img_x);
                        viewById.setTag(i1);
                        viewById1.setTag(i1);
                        //tag+1无示例图  暂时+1  配合详情页 完成图片的左右滑动
                        viewById.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!Check.isFastClick())
                                    return;
                                int tag = (int) v.getTag();
                                GlobalInfoUtils.onImageEnlarge(listImageDa, tag+1, mContext, GlobalInfoUtils.ImageDa);
                            }
                        });
                        final AutoLinefeedLayout finalParent = parent;
                        viewById1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!Check.isFastClick())
                                    return;
                                for (int i1 = 0; i1 < list_img.size(); i1++) {
                                    if (list_img.get(i1).equals(view)) {
                                        listImageDa.remove(i1);
                                        finalParent.removeView(view);
                                    }
                                }
                                list_img.remove(view);
                            }
                        });
                    }
                    rework_imglinear_gengduo.setVisibility(View.GONE);
                    linearNo.setVisibility(View.VISIBLE);
                    addImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!Check.isFastClick())
                                return;
                            onAddImage();
                        }
                    });
                    linearGrop.addView(inflate);
                }




            }

            /**
             * 点击  图片调起相册
             */
            private void onAddImage() {
                List<LocalMedia> localMedia = new ArrayList<>();
                PhotoUtils.openPhotos((Activity) mContext, 5, GlobalInfoUtils.REQUEST_CODE, localMedia);
            }

            @Override
            public void showError(Throwable error) {
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext,"");
                finish();
            }
        });

        Map<String, Object> stringObjectMap = ComsentUtils.onSendLoadImageBt(id, 0);
        presenter_stractIml.setData(stringObjectMap);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                if (subTwoPhotoBean != null) {
                    List<SubTwoPhotoBean.DataBean.ListBean> list = subTwoPhotoBean.getData().getList();
                    String catname, num;
//                点击 上传整页面图片
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getList().size() >= Integer.parseInt(list.get(i).getNum())) {
                            /**
                             * 上传图片 第4种 工单提醒
                             */
                            if (subTwoPhotoBean.getData().getAboutReminderList().get_$4() == 1) {
                                List_Jobreminder(mContext, id, "4");
                            } else {
                                showSubMit_Dialog(mContext); //提交
                            }


                        } else {
                            catname = list.get(i).getCatname();
                            num = list.get(i).getNum();
                            DialogUtils.showToast(mContext, catname + "需要上传" + num + "张图片");
                        }
                    }

                }


            }
        });

    }

    /**
     * 工单提醒   提交按钮逻辑
     */
    public void List_Jobreminder(final Activity mContext, final String orderId, String eventId) {
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
                                        showSubMit_Dialog(mContext); //提交
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
                                            showSubMit_Dialog(mContext); //提交
                                        }
                                        handler.postDelayed(this, 1000);

                                    }
                                };
                                handler.postDelayed(runnable, 1000);
                            }
                        } else if (data.getType().equals("img")) {
                            GlobalInfoUtils.showQiang_storgeImage(mContext, url, second);
                            //9901 的时候 图片结束
                        }
                    }
                }if(imageBean.getCode() == 10011){
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp((Activity) mContext);
                    DialogUtils.showToast(mContext, imageBean.getMsg());
                }
                Looper.loop();

            }


        });

    }


    //Dialog  弹出 是否 确认提交
    public void showSubMit_Dialog(final Context context) {
        View inflate = mInflater.inflate(R.layout.dialogsucess_item, null, false);
        dialog = new Dialog(context);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.y = 0;//设置Dialog距离底部的距离————bottom 必须要设置这个background为null 底部才能不铺满
        dialog.getWindow().setBackgroundDrawable(null);
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
        TextView no = inflate.findViewById(R.id.no);
        TextView yes = inflate.findViewById(R.id.yes);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                dialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                dialog.dismiss();
                onSubmitAudit(id);
            }
        });
    }


    /**
     * 有示例图
     *
     * @param button
     */
    private void onYesSubList(Button button) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;
                List<String> list = new ArrayList<>();
                for (int i1 = 0; i1 < pageList.size(); i1++) {
                    List<String> strings = pageList.get(i1);
                    for (int i2 = 0; i2 < strings.size(); i2++) {
                        if (strings.size() == 1 && strings.size() != 0) {

                        } else {
                            if (i2 == 0) {
                            } else {
                                if (i2 % 2 == 0) {

                                } else {
                                    String s = strings.get(i2);
                                    list.add(s);
                                }

                            }
                        }
                    }
                }
                int page = 0;
                for (int i = 0; i < dataList.size(); i++) {
                    List<List<String>> exlist = dataList.get(i).getExlist();

                    for (int i1 = 0; i1 < exlist.size(); i1++) {

                        List<String> strings = exlist.get(i1);
                        if (strings.size() == 1 && strings.size() != 0) {

                        } else {
                            strings.set(1, list.get(page));
                        }
                        page++;
                    }
                }


                String catname, num;
                //对紧急工单  其他图片的处理
                if (!listBean_list.isEmpty()) {
                    List<List<String>> lists = new ArrayList<>();
                    for (int i = 0; i < img_list.size(); i++) {
                        List<String> lll = new ArrayList<>();
                        lll.add(img_list.get(i));
                        lists.add(lll);
                        lll = null;
                    }
                    listBean_list.get(0).setExlist(lists);
                    if (dataList.size() == 1) {
                        dataList.add(listBean_list.get(0));
                    } else {
                        dataList.set(1, listBean_list.get(0));
                    }
                }


                //                点击 上传整页面图片
                boolean contains = false;
                for (int i = 0; i < dataList.size(); i++) {
                    SubaudBean.DataBean.ListBean listBean = dataList.get(i);
                    List<List<String>> exlist = listBean.getExlist();

                    for (int i1 = 0; i1 < exlist.size(); i1++) {
                        List<String> strings = exlist.get(i1);
                        contains = strings.contains("");
                        if (contains) {
                            catname = listBean.getCatname();
                            num = listBean.getNum();
                            DialogUtils.showToast(mContext, catname + "需要上传" + num + "张图片");
                            return;
                        }
                    }
                }
                /**
                 * 第四种工单提醒
                 */
                if (!contains && subaudBean != null) {
                    if (subaudBean.getData().getAboutReminderList().get_$4() == 1) {
                        List_Jobreminder(mContext, id, "4");
                    } else {
                        showSubMit_Dialog(mContext); //提交

                    }
                }


            }

        });


        /**
         * 有示例图
         */
        Presenter_Stract_SubAudiIml presenter_stract_subAudiIml = new Presenter_Stract_SubAudiIml(new IConstract_Stract.IView() {
            @Override
            public <T> void showData(T t) {
                subaudBean = (SubaudBean) t;

                if (subaudBean.getCode() == 1000) {
                    pageList = new ArrayList<>();

                    //取出list
                    List<List<String>> objectFromShare = (List<List<String>>) SPUtil.readSP( id,pageList);
                    if(objectFromShare !=null){
                        pageList =objectFromShare;
                    }
                    List<SubaudBean.DataBean.ListBean> list = subaudBean.getData().getList();
                    for (int i1 = 0; i1 < list.size(); i1++) {
                        SubaudBean.DataBean.ListBean listBean1 = list.get(i1);
                        List<List<String>> exlist = listBean1.getExlist();
                        for (int i = 0; i < exlist.size(); i++) {
                            List<String> strings = exlist.get(i);
                            if (strings.size() == 1) {
                                listBean_list.add(listBean1);
                                list.remove(listBean1);
                                break;
                            }
                        }
                    }
                    allow = subaudBean.getData().getAllow(); //是否是a级保洁员  a 级 可以用相册上传多张图片
                    onListData(subaudBean);
                    /**
                     * 上传图片 第三种
                     */
                    if (subaudBean.getData().getAboutReminderList().get_$3() == 1) {
                        GlobalInfoUtils.List_Jobreminder(mContext, id, "3");
                    }
                } else if (subaudBean.getCode() == 10011) {
                    GlobalInfoUtils.clearApp((Activity) mContext);
                    DialogUtils.showToast(mContext, subTwoPhotoBean.getMsg());
                } else {
                    DialogUtils.showToast(mContext, subaudBean.getMsg());
                }
                DialogUtils.hideWaiting(mContext);
            }

            @Override
            public void showError(Throwable error) {

            }
        });
        Map<String, Object> stringObjectMap = ComsentUtils.onSendNoScrosess(id);
        presenter_stract_subAudiIml.setData(stringObjectMap);
    }


    @Override
    protected void initView() {
        broad_title = findViewById(R.id.broad_title);
        ImageView callbaclk = broad_title.findViewById(R.id.callbaclk);
        TextView title = broad_title.findViewById(R.id.title);
        work_tx = findViewById(R.id.work_tx);        //顶部文案 的文字
        lod_size = findViewById(R.id.lod_size);        //批量传图
        /**
         * 批量传图之后的逻辑
         */
        lod_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<LocalMedia> localMedia = new ArrayList<>();
                PhotoUtils.openPhotos((Activity) mContext, 9,PL_PHOTOCODE , localMedia);
            }
        });


        callbaclk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!Check.isFastClick())
                            return;
                        finish();
                    }
                });
        title.setText(getResources().getString(R.string.com_work_detile_submitaudit));
        linearGrop = findViewById(R.id.linearGrop);            //最直接的父布局

//        URL_photoList
        LinearLayout group_grop = findViewById(R.id.group_grop);//顶级 linear
        Button button = new Button(mContext);
        button.setText(getResources().getString(R.string.com_work_detile_submitaudit));
        button.setTextColor(getResources().getColor(R.color.color_move_FFFFFF));
        button.setBackground(getResources().getDrawable(R.drawable.shape_gradient3));
        group_grop.addView(button);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) button.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = PhoneUtils.dip2px(mContext, 40);
        /**
         * 上个页面过来的  几个必须的值
         */
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        channel = intent.getStringExtra("channel");
        btype = intent.getStringExtra("btype");// btype ： 3：开荒  8深度返还   4返工  的时候 是无示例图的 //101 C端工单-擦玻璃
        DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_login_tips));
        if ((btype.equals("3") || btype.equals("8") || channel.equals("4")) && !channel.equals("3")) { //这些没有提醒
            RelativeLayout parent = (RelativeLayout) work_tx.getParent();
            parent.setVisibility(View.GONE);
        }

        if (channel.equals("3")) {//C端工单
            work_tx.setText("请您务必上传用户签字的验收单!");
        } else { //非C端工单
            work_tx.setText("请您务必参照示例及要求进行上传!");
        }

        if ((btype.equals(List_id + "")&&channel.equals(List_channel + "") ) || btype.equals(List_id2 + "") || channel.equals(List_channel + "") || channel.equals("3")) {
            lod_size.setVisibility(View.GONE);
            TAPE = 2;
            //无示例图的
            onNoSubList(button);
        } else {
            lod_size.setVisibility(View.VISIBLE);
            TAPE = 1;
            //有示例图
            onYesSubList(button);
        }


    }


    /**
     * 列表数据成功之后的list 逻辑   有示例图
     *
     * @param subaudBean
     */
    private void onListData(SubaudBean subaudBean) {
//        Object objectFromShare = SPUtil.getObjectFromShare(mContext, id);
//        if(objectFromShare!=null){
//            dataList = (List<SubaudBean.DataBean.ListBean>) objectFromShare;
//        }else {
            dataList = subaudBean.getData().getList();
//        }
        LinearLayout linearLayout = onThere_are_illustrations(dataList);
        SubaudBean.DataBean.MultiServiceBean multiService = subaudBean.getData().getMultiService();//判断是否有多服务
        if (multiService.getFlag() == 1) {  //flag 是1 就显示 多服务  2 不显示
            View inflate = mInflater.inflate(R.layout.com_wthk_submitaud_multiservice, null, false);
            linearLayout.addView(inflate);
            tx2_server = inflate.findViewById(R.id.tx2);
            RelativeLayout relative = inflate.findViewById(R.id.relative);
            tx2_server.setText(multiService.getStr() + " >>");
            relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    Intent intent = new Intent(mContext, RNActivity.class);
                    GlobalInfoUtils.Page_RN = "multServer";
                    GlobalInfoUtils.multServer_id = id;
                    startActivityForResult(intent, 333);
                }
            });
        }

    }

    /**
     * 有实例图的方法
     *
     * @param list
     */
    private LinearLayout onThere_are_illustrations(List<SubaudBean.DataBean.ListBean> list) {

        LinearLayout linearLayout = findViewById(R.id.linearGrop);
        //有示例图的
        for (int i = 0; i < list.size(); i++) {
            List<String> listdata = null;
            final SubaudBean.DataBean.ListBean listBean = list.get(i);
            //名字
            String  catname = listBean.getCatname();
            PL_map.put(catname,i);

            String num = listBean.getNum();         //图片的数量
            final View inflate = mInflater.inflate(R.layout.layout_new_inclode, null, false);
            TextView tx_biaoti = inflate.findViewById(R.id.tx_biaoti);  //标题
            final TextView rework_imglinear_gengduo = inflate.findViewById(R.id.rework_imglinear_gengduo);  //展开更多
            TextView tx_biaotipage = inflate.findViewById(R.id.tx_biaotipage);//img的数量
            final RecyclerView recycle = inflate.findViewById(R.id.recycle);
            recycle.setLayoutManager(new GridLayoutManager(mContext, 4){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
                List<List<String>> exlist = listBean.getExlist();

                //初始化 recycleview
                listdata = new ArrayList<>();
                for (int i1 = 0; i1 < exlist.size(); i1++) {
                    String img = exlist.get(i1).get(0);
                    String kong = exlist.get(i1).get(1);
                    listdata.add(img);
                    listdata.add(kong);
                }
                //todo 存下总的
                pageList.add(listdata);
            final Adapter_Submitaud adapter_submitaud = new Adapter_Submitaud(mContext, pageList.get(i), 4);
            recycle.setAdapter(adapter_submitaud);
            tx_biaoti.setText(catname);
            tx_biaotipage.setText("(" + num + "张)");
            linearLayout.addView(inflate);
            adapter_submitaud.setSize(4);
            final List<String> finalListdata = listdata;

            //点击更多的点击事件
            rework_imglinear_gengduo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    if (adapter_submitaud.getItemCount() == 4) {
                        adapter_submitaud.setSize(finalListdata.size());
                        rework_imglinear_gengduo.setText(getResources().getString(R.string.com_work_detile_dow));
                    } else {
                        adapter_submitaud.setSize(4);
                        rework_imglinear_gengduo.setText(getResources().getString(R.string.com_work_detile_opens));

                    }
                }
            });
            //点击adapter里面的操作
            final int finalI2 = i;
            adapter_submitaud.setOnclick(new Adapter_Submitaud.Onclick() {


                @Override
                public void onImageClickListener(View v, int position) {

                }

                @SuppressLint("CheckResult")
                @Override // 无照片
                public void onNoImageClickListener(final View v, final int position) {
                    page = position;
                    pagepage = finalI2;
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //提示用户开户权限
                        String[] perms = {"android.permission.CAMERA"};
                        RxPermissions rxPermissions = new RxPermissions(mContext);
                        //动态申请权限
                        rxPermissions.requestEach(perms).subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {
                                    onPhotograph((ImageView) v);
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
                        onPhotograph((ImageView) v);
                    }

                }

                @Override   //X掉的照片
                public void onXImageClickListener(View v, int i, int position) {
                    pageList.get(i).set(position, "");
                    adapter_submitaud.setData(pageList, i);

                }
            });
            adapterlist.add(adapter_submitaud);


        }
        //判断 是否 是一张  紧急 /推转换
        for (int i = 0; i < listBean_list.size(); i++) {
            final SubaudBean.DataBean.ListBean listBean = listBean_list.get(i);
            String catname = listBean.getCatname(); //名字
            String num = listBean.getNum();         //图片的数量
            final View inflate = mInflater.inflate(R.layout.layout_new_inclode, null, false);
            TextView tx_biaoti = inflate.findViewById(R.id.tx_biaoti);  //标题
            final TextView rework_imglinear_gengduo = inflate.findViewById(R.id.rework_imglinear_gengduo);  //展开更多
            TextView tx_biaotipage = inflate.findViewById(R.id.tx_biaotipage);//img的数量
            final RecyclerView recycle = inflate.findViewById(R.id.recycle);
            recycle.setLayoutManager(new GridLayoutManager(mContext, 4){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            List<List<String>> exlist = listBean.getExlist();
            img_list = new ArrayList<>();
            //初始化 recycleview
            for (int i1 = 0; i1 < exlist.size(); i1++) {
                String img = exlist.get(i1).get(0);
                img_list.add(img);
            }
            recycle.setLayoutManager(new GridLayoutManager(mContext, 4){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            adapterJinji_recycle = new AdapterJinji_Recycle(mContext, img_list, 4);

            recycle.setAdapter(adapterJinji_recycle);

            //存下总的
            tx_biaoti.setText(catname);
            tx_biaotipage.setText("(" + num + "张)");
            linearLayout.addView(inflate);

            //点击更多的点击事件
            rework_imglinear_gengduo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    if (adapterJinji_recycle.getItemCount() == 4) {
                        adapterJinji_recycle.setSize(img_list.size());
                        rework_imglinear_gengduo.setText(getResources().getString(R.string.com_work_detile_dow));
                    } else {
                        adapterJinji_recycle.setSize(4);
                        rework_imglinear_gengduo.setText(getResources().getString(R.string.com_work_detile_opens));
                    }
                    adapterJinji_recycle.notifyDataSetChanged();
                }
            });
            //点击adapter里面的操作
            final int finalI2 = i;
            adapterJinji_recycle.setOnclick(new AdapterJinji_Recycle.Onclick() {


                @Override
                public void onImageClickListener(View v, int position) {

                }

                @Override
                public void onNoImageClickListener(final View v, final int position) {

                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //提示用户开户权限
                        String[] perms = {"android.permission.CAMERA"};
                        RxPermissions rxPermissions = new RxPermissions(mContext);
                        //动态申请权限
                        rxPermissions.requestEach(perms).subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {
                                    jinjiPage = position;
//                                    img_list.set(jinjiPage, "http://pic39.nipic.com/20140321/18063302_210604412116_2.jpg");
//                                    adapterJinji_recycle.setData(img_list);
                                    onRequestImageJINji();


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
                        jinjiPage = position;
//                        img_list.set(jinjiPage, "http://pic39.nipic.com/20140321/18063302_210604412116_2.jpg");
//                        adapterJinji_recycle.setData(img_list);

                        onRequestImageJINji();
                    }

                }

                @Override
                public void onXImageClickListener(View v, int position) {
                    img_list.set(position, "");
                    adapterJinji_recycle.notifyDataSetChanged();
                }
            });
        }
        return linearLayout;
    }

    /**
     * 紧急保洁
     */
    private void onRequestImageJINji(){
    boolean  br = false;
    for (int i1 = 0; i1 < pageList.size(); i1++) {
        List<String> strings = pageList.get(i1);
        for (int i2 = 0; i2 < strings.size(); i2++) {
            if (i2 % 2 != 0) {
                if (!strings.get(i2).isEmpty()) {
                    br = true;
                    break;
                }
            }

        }
        if(br){
            break;
        }
    }
    if (allow == 2  && br) {
        PhotoUtils.openPhotos(mContext, 1,2,null);
    }else{
        PhotoUtils.openCamera(mContext, 1, 2);
    }
}


    /**
     * 有示例图
     *
     * @param img
     */
    int i = 1;

    public void onPhotograph(ImageView img) {
       boolean  br = false;
        for (int i1 = 0; i1 < pageList.size(); i1++) {
            List<String> strings = pageList.get(i1);
            for (int i2 = 0; i2 < strings.size(); i2++) {
                if (i2 % 2 != 0) {
                    if (!strings.get(i2).isEmpty()) {
                        br = true;
                        break;
                    }
                }

            }
            if(br){
                break;
            }
        }
        if (allow == 2  && br) {
            PhotoUtils.openPhotos(mContext, 1,1,null);
        }else{
            PhotoUtils.openCamera(mContext, 1, 1);
        }
    }


    @SuppressLint("HandlerLeak")
    Handler requestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (TAPE == 1) {//有示例图
                switch (msg.what) {
                    case REQUEST_SUCCESS:
                        DialogUtils.hideWaiting(SubmitauditActivity.this);
                        DialogUtils.showToast(SubmitauditActivity.this, getResources().getString(R.string.com_work_detile_noscrosess_responseyes));
                        Adapter_Submitaud adapter_submitaud = adapterlist.get(pagepage);
                        List<String> strings = pageList.get(pagepage);
                        strings.set(page, url);
                        adapter_submitaud.setData(pageList,pagepage);
                        break;
                    case REQUEST_FAIL:
                        DialogUtils.hideWaiting(SubmitauditActivity.this);
                        DialogUtils.showToast(SubmitauditActivity.this, getResources().getString(R.string.com_work_detile_noscrosess_responseno));
                        break;
                    case 111: //紧急保洁  的全是 default图片的
                        DialogUtils.hideWaiting(mContext);
                        if (adapterJinji_recycle != null) {
                            adapterJinji_recycle.notifyDataSetChanged();
                        }
                        break;
                    case PL_SUCCESS: //批量
                        if (lod!=null) {
                        if(lod.getData()!=null){
                            LodSizeImageBean.DataBean data = lod.getData();
                            int isDiscernSuccess =data.getIsDiscernSuccess();
                            if (isDiscernSuccess == 1) {//识别成功
                                List<LodSizeImageBean.DataBean.Bean_Success> identificationData = lod.getData().getIdentificationData();
                                LodSizeImageBean.DataBean.Bean_Success bean_success = identificationData.get(0);

                                if (dataList!=null) {
                                    for (int i1 = 0; i1 < dataList.size(); i1++) {
                                        SubaudBean.DataBean.ListBean listBean = dataList.get(i1);
                                        if (!listBean.getCatname().equals(bean_success.getClassName())) {
                                            //不做任何操作
                                        }else{
                                            String type = listBean.getType();
                                            if (type.equals( bean_success.getClassid()+"")) {
                                                Adapter_Submitaud adapter_submitaud1 = adapterlist.get( i1);//得到当前的adapter
                                                List<String> strings1 = pageList.get( i1);//得到当前的list
                                                int i = (bean_success.getImgindex() + 1) * 2 - 1;
                                                if( strings1.get(i).isEmpty()){
                                                    strings1.set(i,data.getUrl());
                                                    if (PL_map.containsKey(listBean.getCatname())) {
                                                        Integer integer = PL_map.get(listBean.getCatname());
                                                        adapter_submitaud1.setData(pageList, integer);
                                                    }

                                                }else{//如果已经有图片了 则不往上搞
                                                }
                                            }


                                        }

                                    }
                                }


                            }
                        }
                        }

                        if (PL_CLOSE == 1) {
                            DialogUtils.hideWaiting(mContext);
                            DialogUtils.showToast(SubmitauditActivity.this,
                                    "本次识别成功:"+PL_succesPage+";"+"其他:"+PL_errorPage+"。(注意：识别成功的位置若已有图片不会被替换)");
                            PL_CLOSE = 0;
                            PL_succesPage = 0;
                            PL_errorPage = 0 ;
                        }

                        break;

                    case PL_DATAFULL:
                        if (PL_CLOSE == 1) {
                            DialogUtils.hideWaiting(mContext);
                            DialogUtils.showToast(SubmitauditActivity.this,
                                    "本次识别成功:"+PL_succesPage+";"+"其他:"+PL_errorPage+"。(注意：识别成功的位置若已有图片不会被替换)");
                            PL_CLOSE = 0;
                            PL_succesPage = 0;
                            PL_errorPage = 0 ;
                        }
                        break;



                    default:
                        super.handleMessage(msg);
                }
            } else if (TAPE == 2) {
                switch (msg.what) {
                    case GlobalInfoUtils.REQUEST_SUCCESS:
                        DialogUtils.hideWaiting(mContext);
                        DialogUtils.showToast(SubmitauditActivity.this, getResources().getString(R.string.com_work_detile_noscrosess_responseyes));
                        listImageDa.addAll(list);
                        subTwoPhotoBean.getData().getList().get(finalI).setList(listImageDa);
                        onNewImage(list, addImage);
                        list.clear();
                        break;
                    case 111: //紧急保洁  的全是 default图片的上传图片
                        DialogUtils.hideWaiting(mContext);
                        if (adapterJinji_recycle != null) {
                            adapterJinji_recycle.notifyDataSetChanged();
                        }
                        break;
                    case GlobalInfoUtils.REQUEST_FAIL:
                        DialogUtils.hideWaiting(mContext);
                        DialogUtils.showToast(SubmitauditActivity.this, getResources().getString(R.string.com_work_detile_noscrosess_responseno));
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }

        }
    };

    /**
     * TYPE:2
     * 创建新的View
     *
     * @param list
     * @param imgview
     */
    private void onNewImage(final List<String> list, final ImageView imgview) {

        final AutoLinefeedLayout parent = (AutoLinefeedLayout) imgview.getParent();
        for (int i = 0; i < list.size(); i++) {
             View inflate = mInflater.inflate(R.layout.item_noscrosses_addimg, null, false);
            ImageView img = inflate.findViewById(R.id.img);
            GlideUtils.getInstance().setImages(img, list.get(i), 0, 0, false);
            parent.addView(inflate, parent.getChildCount() - 1);
            list_img.add(inflate);
        }
        for (int i = 0; i < list_img.size(); i++) {
            final View view = list_img.get(i);
            View viewById = view.findViewById(R.id.img);
            View viewById1 = view.findViewById(R.id.img_x);
            viewById.setTag(i);
            viewById1.setTag(i);
            //tag+1无示例图  暂时+1  配合详情页 完成图片的左右滑动
            viewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    int tag = (int) v.getTag();
                    GlobalInfoUtils.onImageEnlarge(listImageDa, tag+1, mContext, GlobalInfoUtils.ImageDa);
                }
            });
            viewById1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    for (int i1 = 0; i1 < list_img.size(); i1++) {
                        if (list_img.get(i1).equals(view)) {
                            listImageDa.remove(i1);
                            parent.removeView(view);
                        }
                    }
                    list_img.remove(view);


                }
            });
        }


    }

    /**
     * 点击上传的时候 上传整张图的 逻辑
     */
    private void onSubmitAudit(final String id) {
        String json = null;
        String url = "";
        if (TAPE == 1) {
            for (int i = 0; i < dataList.size(); i++) {
                List<List<String>> exlist = dataList.get(i).getExlist();

                for (int i1 = 0; i1 < exlist.size(); i1++) {
                    List<String> strings = exlist.get(i1);
                    if (strings.size() == 1) {
                        strings.add(0, "");
                        strings.add("");
                    }
                }
            }
            json = GlobalInfoUtils.toJson(dataList, 1);

            url = URLConstant.URL_LOADSHENHE;
        } else if (TAPE == 2) {
            //接口不同  所以 出现问题
            Map<String, List<String>> map = new HashMap<>();
            if (!listImageDa.isEmpty()) {
                map.put(subTwoPhotoBean.getData().getList().get(finalI).getId(), listImageDa);
            }

            json = GlobalInfoUtils.toJson(map, 1);
            url = URLConstant.URL_LOADSHENHENON;
        }
        //submit
        Map<String, Object> stringObjectMap = ComsentUtils.onSendSubmitAudit(id, json);
        OkHttpClient okHttpClient = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);

        if (stringObjectMap != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : stringObjectMap.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }

        Request request = new Request.Builder().url(url).post(requestBody.build()).tag(mContext).build();
        Call call = okHttpClient.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
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
                Looper.prepare();
                Gson gson = new Gson();
                LoadScrosses loadScrosses = gson.fromJson(response.body().string(), LoadScrosses.class);
                switch (loadScrosses.getCode()) {
                    case 1000:
                        GlideCacheUtil.getInstance().clearImageAllCache(mContext);//清除图片所有缓存
                        Intent intent = new Intent(mContext, WorkOrderActvity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("channel", channel);
                        startActivity(intent);
                        break;
                    case 1001:
                        if (TAPE == 1) {
                            for (int i = 0; i < dataList.size(); i++) {
                                List<List<String>> exlist = dataList.get(i).getExlist();

                                for (int i1 = 0; i1 < exlist.size(); i1++) {
                                    List<String> strings = exlist.get(i1);
                                    strings.remove("");
                                }
                            }
                        }
                        DialogUtils.showToast(mContext, loadScrosses.getMsg());
                        break;
                    case 10011:
                        DialogUtils.hideWaiting(mContext);
                        GlobalInfoUtils.clearApp((Activity) mContext);
                        DialogUtils.showToast(mContext, imageBean.getMsg());
                        break;

                    default:
                        DialogUtils.showToast(mContext, loadScrosses.getMsg());
                        break;
                }
                Looper.loop();
            }
        });

    }

    /**
     * 第二个 上传图片的
     *
     * @param pathList
     * @param i
     */
    String  filePah_s;
    private void onRequestIMG(final List<LocalMedia> pathList, final int i) {
        filePah_s= BitmapUtil.compressImage(pathList.get(i).getPath());

        Runnable requestTask = new Runnable() {
            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();

                Map<String, Object> map = ComsentUtils.onSendNoScrosessLoad(id);
                File file = new File(filePah_s);
                try {

                    OkHttpClient client = new OkHttpClient();
                    // form 表单形式上传
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (file != null) {
                        // MediaType.parse() 里面是上传的文件类型。
                        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                        // 参数分别为， 请求key ，文件名称 ， RequestBody
                        requestBody.addFormDataPart("file", file.getName(), body);
                    }
                    if (map != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : map.entrySet()) {
                            requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }
                    Request request = new Request.Builder().url(URLConstant.Base_LOADIMG).post(requestBody.build()).tag(mContext).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    Gson gson = new Gson();
                    imageBean = gson.fromJson(execute.body().string(), ImageBean.class);
                    String url;
                    if (imageBean != null) {
                        if (imageBean.getCode() == 1000) {
                            url = imageBean.getData().getUrl();
                            list.add(url);
                            msg.what = GlobalInfoUtils.REQUEST_SUCCESS;
                        } else if (imageBean.getCode() == 1001) {
                            msg.what = GlobalInfoUtils.REQUEST_FAIL;
                        }
                        if (imageBean.getCode() == 10011) {
                            Looper.prepare();
                            DialogUtils.hideWaiting(mContext);
                            GlobalInfoUtils.clearApp((Activity) mContext);
                            DialogUtils.showToast(mContext, imageBean.getMsg());
                            Looper.loop();
                        }
                    } else{
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
     * 无示例图的 相机返回
     *
     * @param pathList
     */
    private void onLoadImg(final List<LocalMedia> pathList) {
        for (int i = 0; i < pathList.size(); i++) {
            DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_work_detile_noscrosess_response));
            onRequestIMG(pathList, i);
        }
    }

    /**
     * 添加照片
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {  //有 /无示例图 的逻辑
            if (data != null) {
                if (TAPE == 1) {
                    DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_work_detile_noscrosess_response));
                    switch (requestCode) {
                        case 1:

                                onLoadImage(data);


                            break;
                    }
                } else if (TAPE == 2) {
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
//                    DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_work_detile_noscrosess_response));
                    onLoadImg(selectList);

                }


            }
        } else if (requestCode == 2 && data != null) { //紧急保洁  等  个例工单 又有示例图 又 无示例图的
            DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_work_detile_noscrosess_response));
            onJINjiImageLoad(data);
        } else if (requestCode == 333) {//多服务页回来
            if (TAPE == 1) {
                /**
                 * 初始化  提交审核页
                 */
                Presenter_Stract_SubAudiIml presenter_stract_subAudiIml = new Presenter_Stract_SubAudiIml(new IConstract_Stract.IView() {
                    @Override
                    public <T> void showData(T t) {

                        SubaudBean subaudBean = (SubaudBean) t;
                        if (subaudBean.getCode() == 1000) {
                            SubaudBean.DataBean.MultiServiceBean multiService = subaudBean.getData().getMultiService();//判断是否有多服务
                            if (multiService.getFlag() == 1) {  //flag 是1 就显示 多服务  2 不显示
                                tx2_server.setText(multiService.getStr() + " >>");
                            }
                        } else if (subaudBean.getCode() == 10011) {
                            DialogUtils.hideWaiting(mContext);
                            GlobalInfoUtils.clearApp((Activity) mContext);
                            DialogUtils.showToast(mContext, subTwoPhotoBean.getMsg());
                        }

                    }

                    @Override
                    public void showError(Throwable error) {
                        String message = error.getMessage();
                        finish();
                    }
                });
                Map<String, Object> stringObjectMap = ComsentUtils.onSendNoScrosess(id);
                presenter_stract_subAudiIml.setData(stringObjectMap);

            }

        } else if (resultCode == 9901 && requestCode != 903) { //图片倒计时回来
            showSubMit_Dialog(mContext);
        }else if(requestCode == PL_PHOTOCODE && data != null){//批量上传的图片数据
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_work_detile_noscrosess_response));
            onLodSizeImage(selectList);
        }


    }

    /**
     * 紧急保洁 上传图片的小逻辑
     *
     * @param data
     */
    private void onJINjiImageLoad(Intent data) {
        // 图片、视频、音频选择结果回调
        final List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);

        Runnable requestTask = new Runnable() {

            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                try {
                    LocalMedia localMedia = selectList.get(0);

                     String path = localMedia.getPath();
                    filePah_s= BitmapUtil.compressImage(path);

                    Map<String, Object> map = ComsentUtils.onSendNoScrosessLoad(id);
                    File file = new File(filePah_s);
                    OkHttpClient client = new OkHttpClient();
                    // form 表单形式上传
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (file != null) {
                        // MediaType.parse() 里面是上传的文件类型。
                        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                        // 参数分别为， 请求key ，文件名称 ， RequestBody
                        requestBody.addFormDataPart("file", file.getName(), body);
                    }
                    if (map != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : map.entrySet()) {
                            requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }

                    Request request = new Request.Builder().url(URLConstant.Base_LOADIMG).post(requestBody.build()).tag(mContext).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    Gson gson = new Gson();
                    imageBean = gson.fromJson(execute.body().string(), ImageBean.class);
                    if (imageBean != null) {
                        Looper.prepare();
                        if (imageBean.getCode() == 1000) {
                            img_list.set(jinjiPage, imageBean.getData().getUrl());

                        } else if (imageBean.getCode() == 1001) {
                            msg.what = REQUEST_FAIL;
                        }if(imageBean.getCode() == 10011){
                            DialogUtils.hideWaiting(mContext);
                            GlobalInfoUtils.clearApp((Activity) mContext);
                            DialogUtils.showToast(mContext, imageBean.getMsg());
                        }
                        Looper.loop();
                    } else {
                        url = "";
                        msg.what = REQUEST_FAIL;
                    }


                    if (execute.isSuccessful()) {
                        msg.what = 111;
                    } else {
                        msg.what = REQUEST_FAIL;
                    }

                } catch (IOException ex) {
                    msg.what = REQUEST_FAIL;
                } finally {
                    msg.sendToTarget();
                }
            }
        };
        Thread requestThread = new Thread(requestTask);
        requestThread.start();


    }

    private void onLoadImage(Intent data) {
        // 图片、视频、音频选择结果回调
        final List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
        onLoadImage_child(selectList,0);
    }

    private void onLoadImage_child(final List<LocalMedia> selectList, int i ) {
        Runnable requestTask = new Runnable() {

            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                try {
                    LocalMedia localMedia = selectList.get(0);
                    String path = localMedia.getPath();
                    filePah_s= BitmapUtil.compressImage(path);
                    Map<String, Object> map;
                    map = ComsentUtils.onSendRequesID(id,dataList.get(pagepage).getType(),(page-1)/2+"");
                    File file = new File(filePah_s);
                    OkHttpClient client = new OkHttpClient();
                    // form 表单形式上传
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (file != null) {
                        // MediaType.parse() 里面是上传的文件类型。
                        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                        // 参数分别为， 请求key ，文件名称 ， RequestBody
                        requestBody.addFormDataPart("file", file.getName(), body);
                    }
                    if (map != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : map.entrySet()) {
                            requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }

                    Request request = new Request.Builder().url(URLConstant.Base_LOADIMG).post(requestBody.build()).tag(mContext).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    Gson gson = new Gson();
                    imageBean = gson.fromJson(execute.body().string(), ImageBean.class);
                    if (imageBean != null) {
                        if (imageBean.getCode() == 1000) {
                            url = imageBean.getData().getUrl();
                            list.add(url);
                            msg.what = REQUEST_SUCCESS;
                        } else if (imageBean.getCode() == 1001) {
                            msg.what = REQUEST_FAIL;
                            return;
                        }if(imageBean.getCode() == 10012){
                            Looper.prepare();
                            DialogUtils.hideWaiting(mContext);
                            GlobalInfoUtils.clearApp((Activity) mContext);
                            DialogUtils.showToast(mContext, imageBean.getMsg());
                            Looper.loop();
                        }
                    } else {
                        url = "";
                        msg.what = REQUEST_FAIL;
                    }

                } catch (IOException ex) {
                    msg.what = REQUEST_FAIL;
                } finally {
                    msg.sendToTarget();
                }
            }
        };
        Thread requestThread = new Thread(requestTask);
        requestThread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //            //就是 有 未读消息
        ComsentUtils.onMsgPush2(mContext);

    }


    /**
     * 上传图片-批量上传的逻辑
     *
     * @param selectList
     */
    private void onLodSizeImage(List<LocalMedia> selectList) {
        for (int i1 = 0; i1 < selectList.size(); i1++) {
            onLodSizeImage(selectList,i1);
        }

    }

    /**
     * 上传图片-批量上传的逻辑
     *
     * @param selectList
     */
    private void onLodSizeImage(final List<LocalMedia> selectList, final int i ) {
        Runnable requestTask = new Runnable() {

            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                try {
                    LocalMedia localMedia = selectList.get(i);

                    String path = localMedia.getPath();
//                    filePah_s= BitmapUtil.compressImage(path);

                    Map<String, Object> map = ComsentUtils.onSendLodSizeImage(id,selectList.size());
                    File file = new File(path);
                    OkHttpClient client = new OkHttpClient();
                    // form 表单形式上传
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (file != null) {
                        // MediaType.parse() 里面是上传的文件类型。
                        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                        // 参数分别为， 请求key ，文件名称 ， RequestBody
                        requestBody.addFormDataPart("file", file.getName(), body);
                    }
                    if (map != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : map.entrySet()) {
                            requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }

                    Request request = new Request.Builder().url(URLConstant.Base_LOADIMG).post(requestBody.build()).tag(mContext).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                        Gson gson = new Gson();
                        lod = gson.fromJson(execute.body().string(), LodSizeImageBean.class);
                        if (lod !=null) {
                            if (lod.getCode() == 1000) {
                                int isDiscernSuccess = lod.getData().getIsDiscernSuccess();
                                if (isDiscernSuccess == 1) {
                                    if (PL_map.containsKey(lod.getData().getIdentificationData().get(0).getClassName())) {
                                        PL_succesPage++;
                                    }else{
                                        PL_errorPage ++;
                                    }
                                }else{
                                    PL_errorPage ++;
                                }
                                msg.what =  PL_SUCCESS;

                            } else if(lod.getCode() == 1001){
                                lod=null;
                                PL_errorPage ++;
                                msg.what =  PL_DATAFULL;

                            }else if(lod.getCode() == 10011){
                                Looper.prepare();
                                DialogUtils.hideWaiting(mContext);
                                GlobalInfoUtils.clearApp((Activity) mContext);
                                DialogUtils.showToast(mContext, imageBean.getMsg());
                                Looper.loop();
                            }
                        }


                } catch (IOException ex) {
                    msg.what =  PL_DATAFULL;
                    lod = null;
                    PL_errorPage ++;
                } finally {
                    PL_PAGE++;

                    if (selectList.size() == PL_PAGE) {
                        DialogUtils.hideWaiting(mContext);
                        PL_CLOSE = 1;
                        PL_PAGE = 0;
                    }

                    msg.sendToTarget();
                }
            }
        };
        Thread requestThread = new Thread(requestTask);
        requestThread.start();


    }


}
