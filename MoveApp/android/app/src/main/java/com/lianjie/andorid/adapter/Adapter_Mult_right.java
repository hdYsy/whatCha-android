package com.lianjie.andorid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.MultBean;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/25
 * Time: 21:20
 */
public class Adapter_Mult_right extends RecyclerView.Adapter<Adapter_Mult_right.Viewholder> {
    List<String> list;
    Context mContext;
    private List<String> data;
    private List<MultBean> bean;

    public Adapter_Mult_right(List<String> list, Context context, List<MultBean> bean) {
        this.list = list;
        this.mContext = context;
        this.bean = bean;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_left_right2_right, viewGroup, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {

//        holder.linear.post(new Runnable() {
//
//            @Override
//            public void run() {
//
//                holder.linear.getWidth(); // 获取宽度
//                int height = holder.linear.getHeight();// 获取高度
//                LinearLayout.LayoutParams vlplinear = new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT, height, 1);
//                holder.linear.setLayoutParams(vlplinear);
//
//            }
//        });


//        holder.tx.setLayoutParams(vlp);
        int i1 = PhoneUtils.dip2px(mContext, 80); //缩略图
        int i2 = PhoneUtils.dip2px(mContext, 20); //缩略图

        if (list.get(position).equals("")) {
            GlideUtils.getInstance().setImageWrap(holder.img, R.mipmap.add_pic);
            holder.img_x.setVisibility(View.GONE);

            //图片的点击回调
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.OncNOImglickListener(v, position);
                }
            });
            holder.tx.setText(bean.get(position).getCateName());
        } else {
            GlideUtils.getInstance().setImages(holder.img, list.get(position),i1,i1,true);
            holder.img_x.setVisibility(View.VISIBLE);
            GlideUtils.getInstance().setImageWrap(holder.img_x, R.mipmap.icon_close);

            //图片的点击回调
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.OnclickListener(v, position);
                }
            });

            holder.img_x.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onXclickListener(v, position);
                }
            });
            holder.tx.setText(bean.get(position).getCateName());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setBean(List<MultBean> bean) {
        this.bean = bean;
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final ImageView img_x;
        private final TextView tx;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img_x = itemView.findViewById(R.id.img_x);
            tx = itemView.findViewById(R.id.tx);
        }
    }


    Onclick onclick;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public interface Onclick {
        void OnclickListener(View v, int position);

        void OncNOImglickListener(View v, int position);

        void onXclickListener(View v, int position);
    }

}
