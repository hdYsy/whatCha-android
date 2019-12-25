package com.lianjie.andorid.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.SystemDateUtils;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/14
 * Time: 17:00
 */
public class Adapter_BottomRecycleView extends RecyclerView.Adapter<Adapter_BottomRecycleView.MyViewholder> {

    private final FragmentActivity context;
    private final List<String> list;



    public Adapter_BottomRecycleView(FragmentActivity activity, List<String> list) {
        this.context = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.bootem_item, viewGroup, false);
        return new MyViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, final int i) {
        myViewholder.itemView.setTag(i);
        myViewholder.item_tx.setText(list.get(i));

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
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
