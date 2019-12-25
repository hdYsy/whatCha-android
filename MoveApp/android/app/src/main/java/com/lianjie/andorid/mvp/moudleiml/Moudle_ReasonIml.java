package com.lianjie.andorid.mvp.moudleiml;

import com.lianjie.andorid.mvp.constract.IConstract_Reason;
import com.lianjie.andorid.mvp.constract.IConstract_WorkList;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;
import com.lianjie.andorid.utils.internetutils.RetrofitUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Moudle_ReasonIml implements IConstract_Reason.IModule {
    @Override
    public <T> void getData(NetWorkCallBack<T> callBack, Map<String, Object> map) {
        LogUtils.i(map.toString());
        RetrofitUtils.getInstance().post(URLConstant.URL_REASON, map, callBack);
    }
}
