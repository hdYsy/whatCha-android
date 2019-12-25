package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.DelatileMsg_bean;
import com.lianjie.andorid.bean.SubTwoPhotoBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Stract;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Presenter_StractSubmitaIml implements IConstract_Stract.IPerenter {
    private IConstract_Stract.IModule Imoudle;
    private IConstract_Stract.IView Iview;

    public Presenter_StractSubmitaIml(IConstract_Stract.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_Stract();
        this.Iview = iview;
    }

    @Override
    public void setData(Map<String, Object> map) {
        Imoudle.getData(new NetWorkCallBack<SubTwoPhotoBean>() {
            @Override
            public void onSucess(SubTwoPhotoBean delatileMsgBean) {
                LogUtils.e(delatileMsgBean.toString());
                Iview.showData(delatileMsgBean);
            }

            @Override
            public void onError(Throwable e) {
                Iview.showError(e);
            }
        }, map);
    }
}
