package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.SubaudBean;
import com.lianjie.andorid.bean.VideoListBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Stract_SubAudi;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Stract_getTrainList;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Presenter_Stract_getTrainList implements IConstract_Stract.IPerenter {
    private IConstract_Stract.IModule Imoudle;
    private IConstract_Stract.IView Iview;

    public Presenter_Stract_getTrainList(IConstract_Stract.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_Stract_getTrainList();
        this.Iview = iview;
    }

    @Override
    public void setData(Map<String, Object> map) {
        Imoudle.getData(new NetWorkCallBack<VideoListBean>() {
            @Override
            public void onSucess(VideoListBean videoListBean) {
                Iview.showData(videoListBean);
            }

            @Override
            public void onError(Throwable e) {
                Iview.showError(e);
            }
        }, map);
    }
}
