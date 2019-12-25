package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.LoginBean;
import com.lianjie.andorid.bean.NoScrosessBean;
import com.lianjie.andorid.mvp.constract.IConstract_Login;
import com.lianjie.andorid.mvp.constract.IConstract_Reason;
import com.lianjie.andorid.mvp.moudleiml.Moudle_LoginIml;
import com.lianjie.andorid.mvp.moudleiml.Moudle_ReasonIml;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Presenter_ReasonIml implements IConstract_Reason.IPerenter{
   private IConstract_Reason.IModule Imoudle;
  private  IConstract_Reason.IView Iview;

    public Presenter_ReasonIml(IConstract_Reason.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_ReasonIml();
        this.Iview = iview;
    }
    @Override
    public void setData(Map<String,Object>map) {
        Imoudle.getData(new NetWorkCallBack<NoScrosessBean>() {
            @Override
            public void onSucess(NoScrosessBean noScrosessBean) {
                LogUtils.e(noScrosessBean.toString());
                Iview.showData(noScrosessBean);
            }

            @Override
            public void onError(Throwable e) {
                Iview.showError(e);
            }
        },map);
    }
}
