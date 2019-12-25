package com.lianjie.andorid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.DelatileBean;

import java.util.List;

/**
 * 租户联系方式的列表
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/30
 * Time: 16:02
 */
public class Adapter_getPhone extends RecyclerView.Adapter<Adapter_getPhone.ViewHolder> {
    private Context mContext;
    private List<DelatileBean.Bean> info;

    public Adapter_getPhone(Context mContext, List<DelatileBean.Bean> info) {
        this.mContext = mContext;
        this.info = info;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_delatile_getphone, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.name.setText(info.get(i).getName());
        holder.toPhone.setText(mContext.getResources().getString(R.string.com_work_tophone));
        if (!info.get(i).getRoom().equals("")) {
            holder.room.setText( info.get(i).getRoom()+" 房间");
        }


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onClickListener(v, i);
            }
        };
        holder.tophone_img.setOnClickListener(onClickListener);
        holder.toPhone.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        if (info != null) {
            return info.size();
        }
        return 0;
    }

    public void setMsgNo(int msgNo) {
        if (msgNo == 0) {
            info = null;
        }else{
        }
        notifyDataSetChanged();

    }

    public void setMsgNo(List<DelatileBean.Bean> info) {
        this.info = info;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView person;
        private final TextView name;
        private final ImageView tophone_img;
        private final TextView toPhone;
        private final TextView room;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            person = itemView.findViewById(R.id.person);
            name = itemView.findViewById(R.id.name);
            tophone_img = itemView.findViewById(R.id.tophone_img);
            toPhone = itemView.findViewById(R.id.toPhone);
            room = itemView.findViewById(R.id.room);
        }
    }

    Onclick onclick;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }


    public interface Onclick {
        void onClickListener(View view, int position);
    }


}
