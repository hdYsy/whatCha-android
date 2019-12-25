package com.lianjie.andorid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.ui.SubmitauditActivityAuditforward;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;

import java.util.List;

/**
 * Created by My on 2019/12/23.
 */

public class Adapter_addImage extends RecyclerView.Adapter<Adapter_addImage.MyViewholder> {
    private final SubmitauditActivityAuditforward mContext;
    private List<String> list_addimage;
    public Onclick onclick;


    public Adapter_addImage(SubmitauditActivityAuditforward mContext, List<String> list_addimage) {
        this.mContext = mContext;
        this.list_addimage = list_addimage;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_noscrosses_addimg, null, false);
        return new MyViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, final int position) {
        if (position == list_addimage.size()) {//addimage
            GlideUtils.getInstance().setImageWrap(myViewholder.img, R.mipmap.add_pic);
            myViewholder.img_x.setVisibility(View.GONE);
            myViewholder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onAddImageClick(v, position);
                }
            });
        } else {
            myViewholder.img_x.setVisibility(View.VISIBLE);
            GlideUtils.getInstance().setImageWrap(myViewholder.img, list_addimage.get(position));
            myViewholder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalInfoUtils.onImageEnlarge(list_addimage, position + 1, mContext, 0);
                }
            });
            myViewholder.img_x.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onImg_xClick(v, position);
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        if (list_addimage != null) {
            return list_addimage.size() + 1;
        }
        return 0;
    }

    public void setData(List<String> data) {
        this.list_addimage = data;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final ImageView img_x;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img_x = itemView.findViewById(R.id.img_x);
        }
    }


    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public interface Onclick {
        void onImg_xClick(View view, int position);

        void onAddImageClick(View view, int position);
    }
}
