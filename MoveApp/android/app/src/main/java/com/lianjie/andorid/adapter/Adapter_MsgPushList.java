package com.lianjie.andorid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.MsgPushBean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/18
 * Time: 16:14
 */
public class Adapter_MsgPushList extends RecyclerView.Adapter<Adapter_MsgPushList.MyViewholder> {
    List<MsgPushBean.DataBeanX.DataBean> list;
    Context mContext;
    public Adapter_MsgPushList(List<MsgPushBean.DataBeanX.DataBean> data,Context context) {
        this.mContext =context;
        this.list = data;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_push_list, viewGroup, false);
        return new MyViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, final int i) {
        String status = list.get(i).getStatus();
        if (status.equals("1")) {
            myViewholder.tx1.setText("[已读] ");
            myViewholder.tx1.setTextColor(mContext.getResources().getColor(R.color.color_move_CACACA));
        }else  if (status.equals("2")){
            myViewholder.tx1.setText("[未读] ");
            myViewholder.tx1.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }
        myViewholder.tx2.setText(list.get(i).getTitle());
        myViewholder.tx3.setText(list.get(i).getContent());
        myViewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickListenrer(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null) {
            return list.size();

        }
        return 0;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        private final TextView tx1;
        private final TextView tx2;
        private final TextView tx3;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tx1 = itemView.findViewById(R.id.tx1);
            tx2 = itemView.findViewById(R.id.tx2);
            tx3 = itemView.findViewById(R.id.tx3);
        }
    }
    onClick onClick;

    public void setOnClick(Adapter_MsgPushList.onClick onClick) {
        this.onClick = onClick;
    }

    public interface  onClick{
        void onClickListenrer(View v, int positioin);
    }
}
