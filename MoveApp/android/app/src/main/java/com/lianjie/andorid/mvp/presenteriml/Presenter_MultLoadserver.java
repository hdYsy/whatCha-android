package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.DelatileMsg_bean;
import com.lianjie.andorid.bean.LoadScrosses;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Delatile_StartServer;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Mult_Loadserver;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Presenter_MultLoadserver implements IConstract_Stract.IPerenter {
    private IConstract_Stract.IModule Imoudle;
    private IConstract_Stract.IView Iview;

    public Presenter_MultLoadserver(IConstract_Stract.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_Mult_Loadserver();
        this.Iview = iview;
    }

    @Override
    public void setData(Map<String, Object> map) {
        Imoudle.getData(new NetWorkCallBack<LoadScrosses>() {
            @Override
            public void onSucess(LoadScrosses evaluateBean) {
                Iview.showData(evaluateBean);
            }

            @Override
            public void onError(Throwable e) {
                Iview.showError(e);
            }
        }, map);
    }
}
