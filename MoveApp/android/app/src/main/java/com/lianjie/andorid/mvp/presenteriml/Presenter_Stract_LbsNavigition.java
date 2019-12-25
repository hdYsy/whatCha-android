package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.EvaluateBean;
import com.lianjie.andorid.bean.MapOrderBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.moudleiml.Moudle_EvaluateTitle;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Stract_LBSNavigation;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Presenter_Stract_LbsNavigition implements IConstract_Stract.IPerenter {
    private IConstract_Stract.IModule Imoudle;
    private IConstract_Stract.IView Iview;

    public Presenter_Stract_LbsNavigition(IConstract_Stract.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_Stract_LBSNavigation();
        this.Iview = iview;
    }

    @Override
    public void setData(Map<String, Object> map) {
        Imoudle.getData(new NetWorkCallBack<MapOrderBean>() {
            @Override
            public void onSucess(MapOrderBean evaluateBean) {
                Iview.showData(evaluateBean);
            }

            @Override
            public void onError(Throwable e) {
                Iview.showError(e);
            }
        }, map);
    }
}
