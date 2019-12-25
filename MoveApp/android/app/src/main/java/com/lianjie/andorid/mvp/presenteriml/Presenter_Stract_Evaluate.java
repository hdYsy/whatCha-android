package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.EvaluateBean;
import com.lianjie.andorid.bean.SubaudBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.moudleiml.Moudle_EvaluateTitle;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Stract_SubAudi;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Presenter_Stract_Evaluate implements IConstract_Stract.IPerenter {
    private IConstract_Stract.IModule Imoudle;
    private IConstract_Stract.IView Iview;

    public Presenter_Stract_Evaluate(IConstract_Stract.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_EvaluateTitle();
        this.Iview = iview;
    }

    @Override
    public void setData(Map<String, Object> map) {
        Imoudle.getData(new NetWorkCallBack<EvaluateBean>() {
            @Override
            public void onSucess(EvaluateBean evaluateBean) {
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
