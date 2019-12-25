package com.lianjie.andorid.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lianjie.andorid.R;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.EvaluateBean;
import com.lianjie.andorid.bean.ImageBean;
import com.lianjie.andorid.bean.LoadScrosses;
import com.lianjie.andorid.bean.WorkOrderBeanLoad;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Stract_Evaluate;
import com.lianjie.andorid.newsView.AutoLinefeedLayout;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.PageStateUtils;
import com.lianjie.andorid.utils.PhoneUtils;
import com.lianjie.andorid.utils.PhotoUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
 * Date: 2019/7/8
 * Time: 11:04
 */
public class WorkOrderActvity extends BaseActivity implements IConstract_Stract.IView, View.OnClickListener {
    //    impression/submits
    private WorkOrderActvity mContext;
    private ImageView img;
    private TagFlowLayout boxTags;
    private TagFlowLayout boxTags2;
    private ImageView btpictor;


    List<String> list = new ArrayList<>();
    List<String> listImageDa = new ArrayList<>();
    List<View> list_view = new ArrayList<>();

    private ArrayList<String> pathList;
    private String id;
    private LinearLayout linearGrop;
    private EditText eddata2;
    private ImageBean imageBean;
    private TagFlowLayout boxTags3;
    private EvaluateBean bean = null;
    private LinearLayout jiucuo_address;

    @Override
    protected int onLayout() {
        mContext = this;
        return R.layout.activity_orderactivity;
    }

    @Override
    protected void onDrishData() {


    }


    @Override
    protected void initData() {

        Presenter_Stract_Evaluate presenter_stract_evaluate = new Presenter_Stract_Evaluate(this);
        Map<String, Object> stringObjectMap = ComsentUtils.onSendEvalusList();
        presenter_stract_evaluate.setData(stringObjectMap);

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        final String channel = intent.getStringExtra("channel");

        img = findViewById(R.id.btpictor);//     拍照/相册 一会去写自定义View
        boxTags = findViewById(R.id.boxTags);        // 流式布局第一个
        boxTags2 = findViewById(R.id.boxTags2);        // 流式布局第er个
        btpictor = findViewById(R.id.btpictor);        // 点击图片按钮 new 一个view 摆上去
        linearGrop = findViewById(R.id.linearGrop);        // 顶级的linear
        // 第2个输入框
        eddata2 = findViewById(R.id.eddata2);

        boxTags3 = findViewById(R.id.boxTags3);//新添 工单信息纠错标签
        jiucuo_address = findViewById(R.id.jiucuo_address);        //C端 有的工单 不显示 纠错

        if (channel.equals("3")) { //C端工单
            jiucuo_address.setVisibility(View.GONE);
        } else {
            jiucuo_address.setVisibility(View.VISIBLE);
        }

        btpictor.setOnClickListener(this);
        Button button = new Button(mContext);
        button.setText("完成-返回首页");
        button.setTextColor(getResources().getColor(R.color.color_move_FFFFFF));
        button.setBackgroundColor(getResources().getColor(R.color.color_move_4C90F3));
        linearGrop.addView(button);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) button.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = PhoneUtils.dip2px(mContext, 50);

        /**
         * 点击提交的按钮
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Check.isFastClick())
                    return;

                Set<Integer> selectedList = boxTags.getSelectedList();
                Set<Integer> selectedList1 = boxTags2.getSelectedList();
                Set<Integer> selectedList2 = boxTags3.getSelectedList();
                DialogUtils.showWaiting(WorkOrderActvity.this, getResources().getString(R.string.com_login_tips));
                onWorkLoad(selectedList, selectedList1, selectedList2, id);

            }
        });

        TextView mTitle = findViewById(R.id.title);
        mTitle.setText("评价此单");
        ImageView mCallbaclk = findViewById(R.id.callbaclk);
//        mCallbaclk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(mContext,WorkListActivity.class));
//                finish();
//            }
//        });
        mCallbaclk.setVisibility(View.GONE);
    }

    /**
     * 第一个流式布局里面的操作
     **/
    private void onTagLayout(final List<EvaluateBean.DataBean.ImpressListsBean> impressListsBeans) {

        TagAdapter tagAdapter = new TagAdapter(impressListsBeans) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.com_wthk_layout_boxtags, boxTags, false);
                tv.setText(impressListsBeans.get(position).getItem());
                onNocheced_TagsBg(tv);
                return tv;
            }


            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                onCheced_TagsBg(view);
            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
                onNocheced_TagsBg(view);
            }


        };
        boxTags.setAdapter(tagAdapter);
    }

    /**
     * 第二个流式布局里面的操作
     *
     * @param list
     */
    private void onTagLayout2(final List<EvaluateBean.DataBean.ImpressListsBean> list) {

        TagAdapter tagAdapter = new TagAdapter(list) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.com_wthk_layout_boxtags, boxTags2, false);
                tv.setText(list.get(position).getItem());
                onNocheced_TagsBg(tv);
                return tv;
            }


            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                onCheced_TagsBg(view);
            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
                onNocheced_TagsBg(view);
            }


        };
        boxTags2.setAdapter(tagAdapter);
    }

    /**
     * 点击标签bg
     *
     * @param
     * @param tv
     */
    private void onCheced_TagsBg(View tv) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(1, getResources().getColor(R.color.color_move_000000));
        int i = PhoneUtils.dip2px(mContext, 30);
        gradientDrawable.setCornerRadius(i);
        gradientDrawable.setColor(getResources().getColor(R.color.color_move_4C90F3));
        tv.setBackground(gradientDrawable);
        TextView tx = (TextView) tv;
        tx.setTextColor(getResources().getColor(R.color.color_move_f4f4f4));
    }

    private void onNocheced_TagsBg(View tv) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(1, getResources().getColor(R.color.color_move_000000));
        gradientDrawable.setColor(Color.parseColor("#FFFFFF"));
        int i = PhoneUtils.dip2px(mContext, 30);
        gradientDrawable.setCornerRadius(i);
        tv.setBackground(gradientDrawable);
        gradientDrawable.setColor(Color.WHITE);
        TextView tx = (TextView) tv;
        tx.setTextColor(getResources().getColor(R.color.color_move_CACACA));
    }

    /**
     * 下标为0  是租户印象
     * 下标为1  是房间印象
     *
     * @param t
     * @param <T>
     */
    @Override
    public <T> void showData(T t) {

        bean = (EvaluateBean) t;
        int code = bean.getCode();
        if (code == 1000) {
            EvaluateBean.DataBean data = bean.getData();
            List<List<EvaluateBean.DataBean.ImpressListsBean>> impressLists = data.getImpressLists();
            List<EvaluateBean.DataBean.ImpressListsBean> impressListsBeans = impressLists.get(0);
            onTagLayout(impressListsBeans);
            //第二个
            List<EvaluateBean.DataBean.ImpressListsBean> impressListsBeans1 = impressLists.get(1);
            onTagLayout2(impressListsBeans1);
            //第三个流式布局  纠错
            List<EvaluateBean.DataBean.OrderPingJiaListsBean> orderPingJiaLists = data.getOrderPingJiaLists();
            onTagLayout3(orderPingJiaLists);


        } else if (code == 10011) {
            GlobalInfoUtils.clearApp((Activity) mContext);
            DialogUtils.showToast(mContext, bean.getMsg());
        }


    }

    //第三个 流式布局
    private void onTagLayout3(final List<EvaluateBean.DataBean.OrderPingJiaListsBean> orderPingJiaLists) {

        TagAdapter tagAdapter = new TagAdapter(orderPingJiaLists) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.com_wthk_layout_boxtags, boxTags2, false);
                tv.setText(orderPingJiaLists.get(position).getValue());
                onNocheced_TagsBg(tv);
                return tv;
            }


            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                onCheced_TagsBg(view);
            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
                onNocheced_TagsBg(view);
            }
        };
        boxTags3.setAdapter(tagAdapter);


    }

    @Override
    public void showError(Throwable error) {

    }

    /**
     * 点击  图片调起相册
     */
    private void onAddImage() {
        List<LocalMedia> localMedia = new ArrayList<>();
        PhotoUtils.openPhotos((Activity) mContext, 5, GlobalInfoUtils.REQUEST_CODE, localMedia);
    }

    /**
     * 添加照片
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_work_detile_noscrosess_response));
        if (requestCode == GlobalInfoUtils.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                List<LocalMedia> pathList = PictureSelector.obtainMultipleResult(data);
                if (pathList != null) {
                    onLoadImg(pathList);                //上传图
                } else {
                    DialogUtils.hideWaiting(mContext);

                }
            } else {
                DialogUtils.hideWaiting(mContext);
            }
        } else {
            DialogUtils.hideWaiting(mContext);
        }

    }

    /**
     * @param pathList
     */
    private void onLoadImg(List<LocalMedia> pathList) {
        for (int i = 0; i < pathList.size(); i++) {
            DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_work_detile_noscrosess_response));
            onRequestIMG(pathList, i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btpictor:
                if (!Check.isFastClick())
                    return;
                onAddImage();
                break;
        }
    }

    private void onNewImage(final List<String> list, ImageView imgview) {
        final AutoLinefeedLayout parent = (AutoLinefeedLayout) imgview.getParent();
        for (int i = 0; i < list.size(); i++) {
            final View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_noscrosses_addimg, null, false);
            ImageView img = inflate.findViewById(R.id.img);
            GlideUtils.getInstance().setImages(img, list.get(i), 0, 0, false);
            parent.addView(inflate, parent.getChildCount() - 1);
            list_view.add(inflate);
        }
        for (int i = 0; i < list_view.size(); i++) {
            final View view = list_view.get(i);
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
                    GlobalInfoUtils.onImageEnlarge(listImageDa, tag + 1, mContext, GlobalInfoUtils.ImageDa);
                }
            });
            viewById1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Check.isFastClick())
                        return;
                    for (int i1 = 0; i1 < list_view.size(); i1++) {
                        if (list_view.get(i1).equals(view)) {
                            listImageDa.remove(i1);
                            parent.removeView(view);
                        }
                    }
                    list_view.remove(view);
                }
            });
        }

    }

    private String filePah_s;

    private void onRequestIMG(final List<LocalMedia> pathList, final int i) {
        filePah_s = BitmapUtil.compressImage(pathList.get(i).getPath());

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
                    } else {
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
     * handler  worker 线程
     */
    private Handler requestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GlobalInfoUtils.REQUEST_SUCCESS:
                    DialogUtils.hideWaiting(WorkOrderActvity.this);
                    DialogUtils.showToast(WorkOrderActvity.this, getResources().getString(R.string.com_work_detile_noscrosess_responseyes));
                    listImageDa.addAll(list);
                    onNewImage(list, img);
                    list.clear();
                    break;
                case GlobalInfoUtils.REQUEST_FAIL:
                    DialogUtils.showToast(WorkOrderActvity.this, getResources().getString(R.string.com_work_detile_noscrosess_responseno));
                    DialogUtils.hideWaiting(WorkOrderActvity.this);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };


    /**
     * 点击上传的时候 上传整张图的 逻辑
     */
    private void onWorkLoad(Set<Integer> selectedList, Set<Integer> selectedList1, Set<Integer> selectedList2, final String id) {
        List<String> params = new ArrayList<>();
        WorkOrderBeanLoad workOrderBeanLoad = new WorkOrderBeanLoad();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();//orderRemark:


        for (Integer integer : selectedList) {
            list.add(integer);
        }
        workOrderBeanLoad.setPerson(list);
        for (Integer integer : selectedList1) {
            list2.add(integer);
        }
        for (int i = 0; i < selectedList2.size(); i++) {
            if (bean != null & bean.getCode() == 1000) {
                int id1 = bean.getData().getOrderPingJiaLists().get(i).getId();
                list3.add(id1 + ",");
            }
        }


        workOrderBeanLoad.setPerson(list);
        workOrderBeanLoad.setRoom(list2);
        String json = null;
        String url = "";
        //接口不同  所以 出现问题

        json = GlobalInfoUtils.toJson(workOrderBeanLoad, 1);
        String orderremark = GlobalInfoUtils.toJson(list3, 1);
        url = URLConstant.URL_impression_submits;
        String address = eddata2.getText().toString();
        String json2 = GlobalInfoUtils.toJson(listImageDa, 1);


        //选择框
        params.add(address);
        params.add(id);
        params.add(json2);
        params.add(orderremark);
        params.add(json);

//
//        params.add(jsonString);
//        map.put("images", params.get(2));  // json
//        map.put("tags", params.get(4));   //选择了那个东西
//        map.put("orderRemark", params.get(3));  // 第一个框
//        map.put("address", params.get(0));    //第二个框
//        map.put("id", params.get(1));  // id

//        submit
        Map<String, Object> stringObjectMap = ComsentUtils.onSendLoadWorkOrder(params);
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
                DialogUtils.showToast(mContext, e.getMessage());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Looper.prepare();
                DialogUtils.hideWaiting(mContext);
                Gson gson = new Gson();
                LoadScrosses loadScrosses = gson.fromJson(response.body().string(), LoadScrosses.class);
                LogUtils.e(loadScrosses.toString());
                switch (loadScrosses.getCode()) {
                    case 1000:
                        Intent intent = new Intent(mContext, WorkListActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                        startActivity(intent);  //123  返回去  然后 把所有activity出战
                        break;
                    case 1001:
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


    @Override
    protected void onResume() {
        super.onResume();
        //            //就是 有 未读消息
        ComsentUtils.onMsgPush2(mContext);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //  exitApplication();	//按下back键需要操作是事情
            Intent intent = new Intent(mContext, WorkListActivity.class);
            startActivityForResult(intent, 123);  //123  返回去  然后 把所有activity出战

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


}
