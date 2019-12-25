package com.lianjie.andorid.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.adapter.time.util.WheelUtils;
import com.lianjie.andorid.bean.WorkListBean;

import java.util.List;

/**
 * Created by My on 2019/12/16.
 */

public class AdapterRecycle_Tools extends RecyclerView.Adapter<AdapterRecycle_Tools.MyViewholder> {


    private final Activity mContext;
    private final List<WorkListBean.DataBean.ToolDataBean.DataTotalBean> data;
    private final LinearLayout.LayoutParams vlp_img_min;

    public AdapterRecycle_Tools(Activity context, WorkListBean.DataBean.ToolDataBean data) {
        this.mContext = context;
            this.data = data.getDataTotal();
        vlp_img_min = new LinearLayout.LayoutParams(   ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,1);
        vlp_img_min.setMargins(2,2,2,2);
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_tools_item, viewGroup, false);
        return new MyViewholder(inflate);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        WorkListBean.DataBean.ToolDataBean.DataTotalBean dataTotalBean = data.get(position);
        holder.tx_title.setText(dataTotalBean.getBtypeName()+"/"+dataTotalBean.getNum());
        if (dataTotalBean.getDelayNum() <= 0) {
            holder.yuqi_dan.setVisibility(View.GONE);
        }else{
            holder.yuqi_dan.setVisibility(View.VISIBLE);
            holder.yuqi_dan.setText("逾期"+dataTotalBean.getDelayNum()+"单");
        }
        List<String> visitTime = dataTotalBean.getVisitTime();
        int blackcolor = mContext.getResources().getColor(R.color.color_move_000000);
        for (int i = 0; i < visitTime.size(); i++) {
            TextView textView = new TextView(mContext);
            textView.setText(visitTime.get(i));
            textView.setTextColor(blackcolor);
            textView.setTextSize(11);
            textView.setLayoutParams(vlp_img_min);
            textView.setTextAlignment(Gravity.RIGHT);
            holder.linear_subtitle.addView(textView);
        }

    }

    @Override
    public int getItemCount() {
        if (data!=null)
        return data.size();
        return 0;
    }

    class MyViewholder extends RecyclerView.ViewHolder{

        private final LinearLayout linear_subtitle;
        private final TextView yuqi_dan;
        private final TextView tx_title;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tx_title = itemView.findViewById(R.id.tx_title);//左侧title
            yuqi_dan = itemView.findViewById(R.id.yuqi_dan);//逾期量
            linear_subtitle = itemView.findViewById(R.id.linear_subtitle);//右侧数据列表
        }
    }
}
