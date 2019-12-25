package com.lianjie.andorid.mvp.constract;

import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/5
 * Time: 14:33
 */
public class IConstract_Stract {
    public interface IView {
        <T> void showData(T t);
        void showError(Throwable error);
    }

    public interface IModule {
        <T> void getData(NetWorkCallBack<T> callBack, Map<String, Object> map);
    }

    public interface IPerenter {
        void setData(Map<String, Object> map);
    }

}
