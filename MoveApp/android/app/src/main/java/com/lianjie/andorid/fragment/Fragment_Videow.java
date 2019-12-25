package com.lianjie.andorid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.AdapterRecycle_Video;
import com.lianjie.andorid.base.BaseFragment;
import com.lianjie.andorid.bean.VideoListBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.presenteriml.Presenter_Stract_getTrainList;
import com.lianjie.andorid.ui.TrainingDeails;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;

import java.util.List;

/**
 * Created by My on 2019/11/22.
 */

public class Fragment_Videow extends BaseFragment implements IConstract_Stract.IView {
    private RecyclerView recycle;
    private FragmentActivity mContext;
    private Presenter_Stract_getTrainList presenter_list;
    private AdapterRecycle_Video adapterRecycle_video;
    private int page;
    private VideoListBean videoListBean;

    @Override
    protected void onDrishData() {

    }

    @Override
    protected View getLayout() {
        mContext = getActivity();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_recycle, null, false);
        recycle = inflate.findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(mContext));
        adapterRecycle_video = new AdapterRecycle_Video();
        recycle.setAdapter(adapterRecycle_video);
        initData();
        return inflate;
    }

    private void initData() {
        //请求列表 ~1
         presenter_list = new Presenter_Stract_getTrainList(this);
        presenter_list.setData(ComsentUtils.onGetTrainList());
        /**
         * adapter点击事件
         */
        adapterRecycle_video.setOnclicK(new AdapterRecycle_Video.OnclicK() {
            @Override
            public void OnclickListener(View view, int position) {
                Intent intent = new Intent(mContext, TrainingDeails.class);
                VideoListBean.DataBean data = videoListBean.getData();
                if(GlobalInfoUtils.page == 0){
                    List<VideoListBean.DataBean.VideoTrainListsBean> videoTrainLists = data.getVideoTrainLists();
                    intent.putExtra("courseId",videoTrainLists.get(position).getCourseId());
                }else{
                    List<VideoListBean.DataBean.ImageTextTrainListsBean> imageTextTrainLists = data.getImageTextTrainLists();
                    intent.putExtra("courseId",imageTextTrainLists.get(position).getCourseId());
                }
                startActivityForResult(intent,123);
            }
        });
    }


    @Override
    public <T> void showData(T t) {
        videoListBean = (VideoListBean) t;
        if (GlobalInfoUtils.page == 0) {
            List<VideoListBean.DataBean.VideoTrainListsBean> videoTrainLists = videoListBean.getData().getVideoTrainLists();
            adapterRecycle_video.setDataVideo(videoTrainLists);
        } else {
            List<VideoListBean.DataBean.ImageTextTrainListsBean> imageTextTrainLists = videoListBean.getData().getImageTextTrainLists();
            adapterRecycle_video.setDataImage(imageTextTrainLists);
        }
    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if (videoListBean!=null) {
                if (videoListBean.getCode() == 1000) {
                    if (GlobalInfoUtils.page == 0) {
                        List<VideoListBean.DataBean.VideoTrainListsBean> videoTrainLists = videoListBean.getData().getVideoTrainLists();
                        adapterRecycle_video.setDataVideo(videoTrainLists);
                    } else {
                        List<VideoListBean.DataBean.ImageTextTrainListsBean> imageTextTrainLists = videoListBean.getData().getImageTextTrainLists();
                        adapterRecycle_video.setDataImage(imageTextTrainLists);
                    }
                }
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            //进来刷新页面
            presenter_list.setData(ComsentUtils.onGetTrainList());
        }

    }
}
