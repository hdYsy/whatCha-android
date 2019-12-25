package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.mvp.constract.IConstract_WorkList;
import com.lianjie.andorid.mvp.moudleiml.Moudle_WorkListIml;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/17
 * Time: 14:06
 */
public class Presenter_WorkListIml implements IConstract_WorkList.IPerenter{
    IConstract_WorkList.IModule moudle;
    IConstract_WorkList.IView mainview;

    public Presenter_WorkListIml(IConstract_WorkList.IView mainview) {
        this.mainview = mainview;
        this.moudle = new Moudle_WorkListIml();
    }

    @Override
    public void setData(Map<String, Object> map) {
        moudle.getData(new NetWorkCallBack<WorkListBean>() {
            @Override
            public void onSucess(WorkListBean workListBean) {
                mainview.showData(workListBean);
            }

            @Override
            public void onError(Throwable e) {
                mainview.showError(e);
            }
        },map);
    }
}
