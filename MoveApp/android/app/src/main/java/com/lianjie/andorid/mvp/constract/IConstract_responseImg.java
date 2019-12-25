package com.lianjie.andorid.mvp.constract;

import com.lianjie.andorid.bean.LoginBean;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 15:59
 */
public class IConstract_responseImg {
    public interface IView{
        <T>void showData(T t);
        void showError(Throwable error);
    }
    public interface IModule{
        <T>void getData(NetWorkCallBack<T> callBack, Map<String, RequestBody> map);
    }
    public interface IPerenter{
        void setData(Map<String, RequestBody> map);
    }
}
