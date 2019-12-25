package com.lianjie.andorid.mvp.presenteriml;

import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.bean.Work_SuccesBean;
import com.lianjie.andorid.mvp.constract.IConstract_WorkComment;
import com.lianjie.andorid.mvp.constract.IConstract_WorkList;
import com.lianjie.andorid.mvp.moudleiml.Moudle_WorkCommentiml;
import com.lianjie.andorid.mvp.moudleiml.Moudle_WorkListIml;
import com.lianjie.andorid.utils.internetutils.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/17
 * Time: 14:06
 */
public class Presenter_WorkComentlml implements IConstract_WorkComment.IPerenter{
    IConstract_WorkComment.IModule moudle;
    IConstract_WorkComment.IView mainview;

    public Presenter_WorkComentlml(IConstract_WorkComment.IView mainview) {
        this.mainview = mainview;
        this.moudle = new Moudle_WorkCommentiml();
    }

    @Override
    public void setData(Map<String, Object> map) {
        moudle.getData(new NetWorkCallBack<Work_SuccesBean>() {
            @Override
            public void onSucess(Work_SuccesBean workListBean) {
                mainview.showData(workListBean);
            }

            @Override
            public void onError(Throwable e) {
                mainview.showError(e);
            }
        },map);
    }
}
