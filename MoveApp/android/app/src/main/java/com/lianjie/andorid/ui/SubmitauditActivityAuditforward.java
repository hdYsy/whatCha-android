package com.lianjie.andorid.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.Adapter_addImage;
import com.lianjie.andorid.adapter.Adapter_examples;
import com.lianjie.andorid.base.BaseActivity;
import com.lianjie.andorid.bean.ImageBean;
import com.lianjie.andorid.bean.LoadScrosses;
import com.lianjie.andorid.bean.SubTwoPhotoBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_StractSubmitaIml;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.Check;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideCacheUtil;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.PhotoUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.lianjie.andorid.utils.GlobalInfoUtils.REQUEST_FAIL;
import static com.lianjie.andorid.utils.GlobalInfoUtils.REQUEST_SUCCESS;
import static java.lang.String.valueOf;

/**
 * Created by My on 2019/12/23.
 */

public class SubmitauditActivityAuditforward extends BaseActivity {

    private RecyclerView recycle_imglist;
    private RecyclerView addimage_recycle;
    private SubmitauditActivityAuditforward mContext;
    private String id;
    private String channel;
    private String btype;
    private SubTwoPhotoBean subTwoPhotoBean;
    private LayoutInflater mInflater;
    private Dialog dialog;
    private Button button;
    private TextView audifttitle;
    private TextView audiftsubtitle;
    private List<String> list_addimage;//示例图的adapter
    private Adapter_addImage adapter_addImage;//上传成功的图片 的recycleViewadapter
    private List<LocalMedia> selectList;
    private int page_state = 0;
    private String image_id;

    @Override
    protected int onLayout() {

        return R.layout.activity_delatiles_submit_auditforward;
    }

    @Override
    protected void onDrishData() {

    }

    @Override
    protected void initData() {
        DialogUtils.showWaiting(mContext, getResources().getString(R.string.com_login_tips));
        onNoSubList(button);
    }

    @Override
    protected void initView() {
        this.mContext = this;
        mInflater = LayoutInflater.from(mContext);
        View broad_title = findViewById(R.id.broad_title);
        ImageView callbaclk = broad_title.findViewById(R.id.callbaclk);
        TextView title = broad_title.findViewById(R.id.title);
        audifttitle = findViewById(R.id.audifttitle);        //title
        audiftsubtitle = findViewById(R.id.audiftsubtitle);        //subtitle
        button = findViewById(R.id.button);//提交按钮
        callbaclk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("服务前照片");
        recycle_imglist = findViewById(R.id.recycle_imglist);//17张示例图
        addimage_recycle = findViewById(R.id.addimage_recycle);//上传Recycle
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycle_imglist.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,4);
        addimage_recycle.setLayoutManager(gridLayoutManager);
        /**
         * 获取intent
         */
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        channel = intent.getStringExtra("channel");
        btype = intent.getStringExtra("btype");// btype ： 3：开荒  8深度返还   4返工  的时候 是无示例图的 //101 C端工单-擦玻璃

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
                int code = subTwoPhotoBean.getCode();
                if (code == 1000) {
                    DialogUtils.hideWaiting(mContext);
                    SubTwoPhotoBean.DataBean data = subTwoPhotoBean.getData();
                    onListData(data);//数据成功操作
                } else if (code == 10011) {
                    DialogUtils.hideWaiting(mContext);
                    GlobalInfoUtils.clearApp((Activity) mContext);
                    DialogUtils.showToast(mContext, subTwoPhotoBean.getMsg());
                } else {
                    DialogUtils.hideWaiting(mContext);
                    DialogUtils.showToast(mContext, subTwoPhotoBean.getMsg());
                }
            }


            @Override
            public void showError(Throwable error) {
                DialogUtils.hideWaiting(mContext);
                DialogUtils.showToast(mContext, "系统错误!");
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

                            showSubMit_Dialog(mContext); //提交


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
     * 点击  图片调起相册
     */
    private void onAddImage() {
        List<LocalMedia> localMedia = new ArrayList<>();
        PhotoUtils.openPhotos((Activity) mContext, 9, GlobalInfoUtils.REQUEST_CODE, localMedia);
    }

    /**
     * 数据成功操作
     *
     * @param data
     */
    private void onListData(SubTwoPhotoBean.DataBean data) {
        audifttitle.setText("做单前");
        audiftsubtitle.setText("请根据示例至少上传17张做单前图片");
        final List<SubTwoPhotoBean.DataBean.ListBean> list = data.getList();
        SubTwoPhotoBean.DataBean.ListBean listBean = list.get(0);
        List<String> examples = listBean.getExamples();//示例图
        final Adapter_examples adapter_examples = new Adapter_examples(mContext, examples);
        recycle_imglist.setAdapter(adapter_examples);
        //addimage
        list_addimage = listBean.getList();        //addimage
        //addimage
        image_id = listBean.getId();
        adapter_addImage = new Adapter_addImage(mContext, list_addimage);
        addimage_recycle.setAdapter(adapter_addImage);
        adapter_addImage.setOnclick(new Adapter_addImage.Onclick() {
            @Override
            public void onImg_xClick(View view, int position) {//X是删除
                list_addimage.remove(position);
                adapter_addImage.setData(list_addimage);
            }

            @Override
            public void onAddImageClick(View view, int position) {//添加新图片
                onAddImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GlobalInfoUtils.REQUEST_CODE) {//上传做单前图片
            if (data != null) {
                selectList = PictureSelector.obtainMultipleResult(data);
                onLoadImg(selectList);
            }
        }

    }

    /**
     * 图片的上传
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
     * 上传图片
     */
    String filePah_s;

    private void onRequestIMG( List<LocalMedia> pathList,  int i) {
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
                    ImageBean imageBean = gson.fromJson(execute.body().string(), ImageBean.class);
                    if (imageBean != null) {
                        if (imageBean.getCode() == 1000) {
                            list_addimage.add(imageBean.getData().getUrl());
                            msg.what = REQUEST_SUCCESS;
                        } else if (imageBean.getCode() == 1001) {
                            msg.what = REQUEST_FAIL;
                        }
                        if (imageBean.getCode() == 10011) {
                            Looper.prepare();
                            DialogUtils.hideWaiting(mContext);
                            GlobalInfoUtils.clearApp((Activity) mContext);
                            DialogUtils.showToast(mContext, imageBean.getMsg());
                            Looper.loop();
                        }
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

    @SuppressLint("HandlerLeak")
    Handler requestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REQUEST_SUCCESS:
                    page_state++;
                    DialogUtils.hideWaiting(mContext);
                    DialogUtils.showToast(mContext, getResources().getString(R.string.com_work_detile_noscrosess_responseyes));
                    if(selectList.size() == page_state){
                        adapter_addImage.setData(list_addimage);
                        page_state=0;
                    }
                    break;
                case REQUEST_FAIL:
                    DialogUtils.hideWaiting(mContext);
                    DialogUtils.showToast(mContext, getResources().getString(R.string.com_work_detile_noscrosess_responseno));
                    page_state++;
                    if(selectList.size() == page_state){
                        adapter_addImage.setData(list_addimage);
                        page_state=0;
                    }
                    break;
            }
        }


    };

    /**
     * 点击上传的时候 上传整张图的 逻辑
     */
    private void onSubmitAudit(final String id) {
        Map<String,List<String>> hash = new HashMap<>();
        hash.put(image_id,list_addimage);
        String json = GlobalInfoUtils.toJson(hash, 1);
        //submit
        Map<String, Object> stringObjectMap = ComsentUtils.onSendSavePhoto(id, json );
        OkHttpClient okHttpClient = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);

        if (stringObjectMap != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : stringObjectMap.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }

        Request request = new Request.Builder().url(URLConstant.savePhotoBeforeWork).post(requestBody.build()).tag(mContext).build();
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
                        DialogUtils.hideWaiting(mContext);
//                        GlideCacheUtil.getInstance().clearImageAllCache(mContext);//清除图片所有缓存
//                        Intent intent = new Intent(mContext, DelatileActivity.class);
//                        intent.putExtra("id", id);
//                        startActivity(intent);
                        DialogUtils.hideWaiting(mContext);
                        DialogUtils.showToast(mContext, loadScrosses.getMsg());
                        finish();
                        break;
                    case 1001:
                        DialogUtils.hideWaiting(mContext);
                        DialogUtils.showToast(mContext, loadScrosses.getMsg());
                        break;
                    case 10011:
                        DialogUtils.hideWaiting(mContext);
                        GlobalInfoUtils.clearApp((Activity) mContext);
                        DialogUtils.showToast(mContext, loadScrosses.getMsg());
                        break;

                    default:
                        DialogUtils.showToast(mContext, loadScrosses.getMsg());
                        break;
                }
                Looper.loop();
            }
        });

    }


}
