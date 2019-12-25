package com.lianjie.andorid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.PhoneUtils;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/22
 * Time: 16:50
 */
public class Adapter_DelatileRework extends RecyclerView.Adapter<Adapter_DelatileRework.MyViewHolder> {
    private static boolean PAGE = true;
    List<String> list;
    private Context context;

    public Adapter_DelatileRework(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_delatile_work_item, viewGroup, false);
        return new MyViewHolder(inflate);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        int i1 = PhoneUtils.dip2px(context, 80); //缩略图
        GlideUtils.getInstance().setImages(myViewHolder.item_ic_img, list.get(i),i1,i1,true );
        myViewHolder.item_ic_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.OnclickListener(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            if (PAGE) {
                return list.size() > 3 ? 4 : list.size();
            } else {
                return list.size();
            }

        }
        return 0;

    }

    public void setPAGE(boolean b) {
        this.PAGE = b;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView item_ic_img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_ic_img = itemView.findViewById(R.id.item_ic_img);
        }
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public Onclick onclick;
    public interface Onclick{
        void OnclickListener(View v,int position);
    }
}
