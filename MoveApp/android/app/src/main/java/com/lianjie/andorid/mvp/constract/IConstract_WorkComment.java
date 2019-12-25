package com.lianjie.andorid.mvp.constract;

import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.bean.Work_SuccesBean;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 15:59
 */
public class IConstract_WorkComment {
    public interface IView{
        void showData(Work_SuccesBean work_succesBean);
        void showError(Throwable error);
    }
    public interface IModule{
        <T>void getData(NetWorkCallBack<T> callBack, Map<String, Object> map);
    }
    public interface IPerenter{
        void setData(Map<String, Object> map);
    }

}
