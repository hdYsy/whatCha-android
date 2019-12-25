package com.lianjie.andorid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.utils.LogUtils;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/19
 * Time: 19:30
 */
public class AdapterDelatile_data extends RecyclerView.Adapter<AdapterDelatile_data.MyViewholder> {
    List<String> list;
    Context context;

    public AdapterDelatile_data(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_delatile_data, null, false);
        return new MyViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int i) {
        if(null!=list.get(i)){


        if (list.get(i).equals("") || list.get(i).equals(":")) {
            holder.item_tx.setText("暂无");
        } else {
            holder.item_tx.setText(list.get(i));
        }
        }
        }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        LogUtils.e(list.size()+"getItemCount");
        return 0;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private final TextView item_tx;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            item_tx = itemView.findViewById(R.id.item_tx);
        }
    }
}
