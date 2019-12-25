package com.lianjie.andorid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.ErrorList;
import com.lianjie.andorid.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/1
 * Time: 17:57
 */
public class Adapter_NoScrRecycle extends RecyclerView.Adapter<Adapter_NoScrRecycle.MyViewHolder> {
    List<ErrorList> list;
    Context context;
    OnClik onClik;
    List<View> views= new ArrayList<>();

    public Adapter_NoScrRecycle(List<ErrorList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_noscrosess_error, viewGroup, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.item_noscroses.setText(list.get(position).getData());
        boolean br = list.get(position).isBr();
        if (br) {
            GlideUtils.getInstance().setImageWrap(holder.rdbt,R.mipmap.icon_sel);
        }else{
            GlideUtils.getInstance().setImageWrap(holder.rdbt,R.mipmap.icon_del);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClik.onClickListener(view, position);
            }
        });


    }


    @Override
    public int getItemCount() {
        if (list!=null)return list.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_noscroses;
        private final ImageView rdbt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_noscroses = itemView.findViewById(R.id.item_noscroses);
            rdbt = itemView.findViewById(R.id.rdbt);
        }
    }

    public void setOnClik(OnClik onClik) {
        this.onClik = onClik;
    }

    public  interface  OnClik{
        void onClickListener(View v,int position);
   }
}
