package com.lianjie.andorid.mvp.presenteriml;

import android.util.Log;

import com.lianjie.andorid.bean.LoginBean;
import com.lianjie.andorid.mvp.constract.IConstract_Login;
import com.lianjie.andorid.mvp.moudleiml.Moudle_LoginIml;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Presenter_LoginIml implements IConstract_Login.IPerenter{
   private IConstract_Login.IModule Imoudle;
  private  IConstract_Login.IView Iview;

    public Presenter_LoginIml(IConstract_Login.IView iview) {
        Iview = iview;
        this.Imoudle = new Moudle_LoginIml();
        this.Iview = iview;
    }
    @Override
    public void setData(Map<String,Object>map) {
        Imoudle.getData(new NetWorkCallBack<LoginBean>() {
            @Override
            public void onSucess(LoginBean loginBean) {
                Iview.showData(loginBean);
            }

            @Override
            public void onError(Throwable e) {
                Iview.showError(e);
            }
        },map);
    }
}
