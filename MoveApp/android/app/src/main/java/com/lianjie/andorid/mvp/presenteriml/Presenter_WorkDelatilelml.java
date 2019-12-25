package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.DelatileBean;
import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.mvp.constract.IConstract_WorkComment;
import com.lianjie.andorid.mvp.constract.IConstract_WorkDelatile;
import com.lianjie.andorid.mvp.moudleiml.Moudle_WorkCommentiml;
import com.lianjie.andorid.mvp.moudleiml.Moudle_WorkDelatileiml;
import com.lianjie.andorid.utils.LogUtils;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/17
 * Time: 14:06
 */
public class Presenter_WorkDelatilelml implements IConstract_WorkDelatile.IPerenter{
    IConstract_WorkDelatile.IModule moudle;
    IConstract_WorkDelatile.IView mainview;

    public Presenter_WorkDelatilelml(IConstract_WorkDelatile.IView mainview) {
        this.mainview = mainview;
        this.moudle = new Moudle_WorkDelatileiml();
    }

    @Override
    public <T>void setData(Map<String, Object> map) {
        moudle.getData(new NetWorkCallBack<DelatileBean>() {
            @Override
            public void onSucess(DelatileBean t) {
                mainview.showData( t);
            }

            @Override
            public void onError(Throwable e) {
                mainview.showError(e);
            }
        },map);
    }
}
