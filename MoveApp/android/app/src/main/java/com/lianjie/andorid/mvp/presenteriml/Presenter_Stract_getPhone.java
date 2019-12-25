package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.EvaluateBean;
import com.lianjie.andorid.bean.GetPhoneBean;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.mvp.moudleiml.Moudle_Delatile_gePhone;
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
public class Presenter_Stract_getPhone implements IConstract_Stract.IPerenter {
    private IConstract_Stract.IModule Imoudle;
    private IConstract_Stract.IView Iview;

    public Presenter_Stract_getPhone(IConstract_Stract.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_Delatile_gePhone();
        this.Iview = iview;
    }

    @Override
    public void setData(Map<String, Object> map) {
        Imoudle.getData(new NetWorkCallBack<GetPhoneBean>() {
            @Override
            public void onSucess(GetPhoneBean getPhoneBean) {
                Iview.showData(getPhoneBean);
            }

            @Override
            public void onError(Throwable e) {
                Iview.showError(e);
            }
        }, map);
    }
}
