package com.lianjie.andorid.mvp.moudleiml;

import android.util.Log;

import com.lianjie.andorid.mvp.constract.IConstract_Login;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.Sha1;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;
import com.lianjie.andorid.utils.internetutils.RetrofitUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/11
 * Time: 16:01
 */
public class Moudle_LoginIml implements IConstract_Login.IModule {
    @Override
    public <T> void getData(NetWorkCallBack<T> callBack, Map<String, Object> map) {
        RetrofitUtils.getInstance().post(URLConstant.URL_LOGIN, map, callBack);
    }
}
