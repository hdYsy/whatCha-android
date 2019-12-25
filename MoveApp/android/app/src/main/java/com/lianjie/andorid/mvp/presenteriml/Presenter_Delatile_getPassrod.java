package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.DelatileMsg_bean;
import com.lianjie.andorid.bean.Delatile_bt_LoadBean;
import com.lianjie.andorid.bean.EvaluateBean;
import com.lianjie.andorid.bean.GetPasswordBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Delatile_StartServer;
import com.lianjie.andorid.mvp.moudleiml.Moudle_EvaluateTitle;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Presenter_Delatile_getPassrod implements IConstract_Stract.IPerenter {
    private IConstract_Stract.IModule Imoudle;
    private IConstract_Stract.IView Iview;

    public Presenter_Delatile_getPassrod(IConstract_Stract.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_Delatile_StartServer();
        this.Iview = iview;
    }

    @Override
    public void setData(Map<String, Object> map) {
        Imoudle.getData(new NetWorkCallBack<GetPasswordBean>() {
            @Override
            public void onSucess(GetPasswordBean evaluateBean) {
                LogUtils.e(evaluateBean.toString());
                Iview.showData(evaluateBean);
            }

            @Override
            public void onError(Throwable e) {
                Iview.showError(e);
            }
        }, map);
    }
}
