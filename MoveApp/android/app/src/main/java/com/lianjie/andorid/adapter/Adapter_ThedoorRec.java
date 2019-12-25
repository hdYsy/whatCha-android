package com.lianjie.andorid.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.WorkListBean;
import com.lianjie.andorid.utils.GlobalInfoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/17
 * Time: 10:26
 */
public class Adapter_ThedoorRec extends RecyclerView.Adapter<Adapter_ThedoorRec.MyViewHolder> {
    Context context;
    WorkListBean workListBean;

    Onclick onclick;
    private List<View> lbsList;

    public WorkListBean getDate() {
        return workListBean;
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public Adapter_ThedoorRec(WorkListBean workListBean, Context context) {
        this.workListBean = workListBean;
        this.context = context;
    }

    public Adapter_ThedoorRec(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_thedoor_item, null, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        WorkListBean.DataBean.ListBean listBean = workListBean.getData().getList().get(i);
        //保洁类型及各类标签
        holder.item_btypename.setText(listBean.getBtypename());

        String villageaddress = listBean.getVillageaddress();
        String rentTag = listBean.getRentTag();
        String statTag = listBean.getStatTag();
        String areaTag = listBean.getAreaTag();
        String timeTag = listBean.getTimeTag();
        String hoursTag = listBean.getHoursTag();
        String doingTag = listBean.getDoingTag();
        String lockTag = listBean.getLockTag();
        String contactTag = listBean.getContactTag();
        String orderTag = listBean.getOrderTag();
        String delayTag = listBean.getDelayTag();
        String onlineTag = listBean.getOnlineTag();
        String batteryStatus = listBean.getBatteryStatus();
        String bedTag = listBean.getBedTag();//床品信息

        //是否是整租
        onIfCase(holder.item_linear1, rentTag);
        onIfCase(holder.item_battary, batteryStatus); //电池
        onIfCase(holder.item_villageaddress, villageaddress);
        onIfCase(holder.item_statTag, statTag);
        onIfCase(holder.item_areaTag, areaTag);
        onIfCase(holder.item_linear2, timeTag);
        onIfCase(holder.item_hoursTag, hoursTag);
        onIfCase(holder.item_doingTag, doingTag);

        onIfCase(holder.item_lockTag, lockTag);
        onIfCase(holder.item_contactTag, contactTag);
        onIfCase(holder.item_orderTag, orderTag);
        onIfCase(holder.item_linear11, delayTag);
        onIfCase(holder.item_linear12, onlineTag);
        onIfCase(holder.item_bedTag, bedTag);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onItemClick(v, i);
            }
        });



    }

    /**
     * 有值就显示 无值就不显示
     *
     * @param view
     * @param rentTag
     */
    private void onIfCase(View view, String rentTag) {
        if (view instanceof LinearLayout) {
            if (!rentTag.equals("")) {
                view.setVisibility(View.VISIBLE);
                LinearLayout linearLayout = (LinearLayout) view;
                TextView childAt = (TextView) linearLayout.getChildAt(1);
                childAt.setText(rentTag);
            } else {
                view.setVisibility(View.GONE);
            }
        } else if (view instanceof TextView) {
            TextView tx = (TextView) view;
            if (!rentTag.equals("")) {
                tx.setVisibility(View.VISIBLE);
                tx.setText(rentTag);
            } else {
                tx.setVisibility(View.GONE);
            }
        }


    }

    @Override
    public int getItemCount() {
        if (workListBean != null)
            return workListBean.getData().getList().size();
        return 0;
    }

    public void setData(WorkListBean workListBean) {
        this.workListBean = workListBean;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_btypename;
        private final TextView item_villageaddress;
        private final LinearLayout item_linear1;
        private final TextView item_statTag;
        private final TextView item_areaTag;
        private final LinearLayout item_linear2;
        private final TextView item_hoursTag;
        private final TextView item_doingTag;
        private final TextView item_lockTag;
        private final TextView item_contactTag;
        private final TextView item_orderTag;
        private final LinearLayout item_linear11;
        private final LinearLayout item_linear12;
        private final TextView item_battary;
        private final TextView item_bedTag;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //类型
            item_btypename = itemView.findViewById(R.id.item_btypename);

            item_villageaddress = itemView.findViewById(R.id.item_villageaddress);
            //linear1
            item_linear1 = itemView.findViewById(R.id.item_linear1);

            item_statTag = itemView.findViewById(R.id.item_statTag);

            item_areaTag = itemView.findViewById(R.id.item_areaTag);
            //时间
            item_linear2 = itemView.findViewById(R.id.item_linear2);

            //
            item_hoursTag = itemView.findViewById(R.id.item_hoursTag);

            item_doingTag = itemView.findViewById(R.id.item_doingTag);

            //第二行
            item_lockTag = itemView.findViewById(R.id.item_lockTag);

            item_contactTag = itemView.findViewById(R.id.item_contactTag);

            item_orderTag = itemView.findViewById(R.id.item_orderTag);
            //警告
            item_linear11 = itemView.findViewById(R.id.item_linear11);


            item_linear12 = itemView.findViewById(R.id.item_linear12);
            //电池标签
            item_battary = itemView.findViewById(R.id.item_battary);
            item_bedTag = itemView.findViewById(R.id.item_bedTag);



        }
    }

    public interface Onclick {
        void onItemClick(View v, int position);

    }


}
