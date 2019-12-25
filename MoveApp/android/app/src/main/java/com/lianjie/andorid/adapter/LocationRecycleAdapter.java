package com.lianjie.andorid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.LatLngBean;

import java.util.ArrayList;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/9/24
 * Time: 15:26
 */
public class LocationRecycleAdapter extends RecyclerView.Adapter<LocationRecycleAdapter.ViewHolder> {

    private final Context mContext;
    private ArrayList<LatLngBean> data;



    public LocationRecycleAdapter(Context mContext, ArrayList<LatLngBean> data) {
        this.mContext = mContext;
        this.data = data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_poi_list, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.tx.setText(data.get(i).getAddress());
        holder.tx_title.setText(data.get(i).getDistrict());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickListener.onClickListener(v, i);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (data != null){
            if(data.size()>9){
                return 9;
            }
            return data.size();
        }else{
            return 0;
        }



    }

    public void setData() {
        data.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tx;
        private final TextView tx_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tx = itemView.findViewById(R.id.tx);
            tx_title = itemView.findViewById(R.id.tx_title);


        }
    }

    public OnclickListener onclickListener;

    public void setOnclickListener(OnclickListener onclickListener) {
        this.onclickListener = onclickListener;
    }

    public interface OnclickListener {
        void onClickListener(View v, int position);
    }
}
