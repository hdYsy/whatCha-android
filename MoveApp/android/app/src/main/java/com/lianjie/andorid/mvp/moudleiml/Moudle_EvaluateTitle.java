package com.lianjie.andorid.mvp.moudleiml;

import com.lianjie.andorid.mvp.constract.IConstract_Reason;
import com.lianjie.andorid.mvp.constract.IConstract_Stract;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;
import com.lianjie.andorid.utils.internetutils.RetrofitUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/8
 * Time: 16:52
 */
public class Moudle_EvaluateTitle implements IConstract_Stract.IModule {
    @Override
    public <T> void getData(NetWorkCallBack<T> callBack, Map<String, Object> map) {
        RetrofitUtils.getInstance().post(URLConstant.URL_IMPRESS,map,callBack);
    }
}
