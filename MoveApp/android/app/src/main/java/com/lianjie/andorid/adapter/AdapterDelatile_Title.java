package com.lianjie.andorid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lianjie.andorid.R;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/19
 * Time: 19:30
 */
public class AdapterDelatile_Title extends RecyclerView.Adapter<AdapterDelatile_Title.MyViewholder> {
    List<String> list;
    Context context;

    public AdapterDelatile_Title(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_delatile_title, null, false);
        return new MyViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int i) {
            holder.item_tx.setText(list.get(i));


    }

    @Override
    public int getItemCount() {
        if(list!=null)return list.size();
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
