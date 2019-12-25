package com.lianjie.andorid.mvp.constract;

import com.lianjie.andorid.bean.DelatileBean;
import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 15:59
 */
public class IConstract_WorkDelatile {
    public interface IView{
        void showData(DelatileBean delatileBean);
        void showError(Throwable error);
    }
    public interface IModule{
        <T>void getData(NetWorkCallBack<T> callBack, Map<String, Object> map);
    }
    public interface IPerenter{
        <T>void setData(Map<String, Object> map);
    }

}
