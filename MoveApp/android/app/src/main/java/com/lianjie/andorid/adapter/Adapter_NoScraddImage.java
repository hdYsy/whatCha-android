package com.lianjie.andorid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/2
 * Time: 18:51
 */
public class Adapter_NoScraddImage extends RecyclerView.Adapter<Adapter_NoScraddImage.Viewholder> {
    List<Object> list;
    Context context;

    public Adapter_NoScraddImage(List<Object> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_noscrosses_addimg, viewGroup, false);

        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        int dp = GlobalInfoUtils.get80dp(context);
        GlideUtils.getInstance().setImages(holder.img, list.get(position),dp,dp,true);
        //点击添加相册图片 以及 点击 添加
        if (position == (list.size() - 1)) {
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onLastClick(v, position);
                }
            });
            holder.img_x.setVisibility(View.GONE);
        }else{
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onImgCLick(v, position);
                }
            });
        }

        holder.img_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onXXclick(v,position);
            }
        });



    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setData(List<String> strings) {
//        list.remove(list.size() - 1);
        for (int i = 0; i < strings.size(); i++) {
            list.add(strings.get(i));
        }
        list.add(R.mipmap.add_pic);
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final ImageView img_x;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img_x = itemView.findViewById(R.id.img_x);
        }
    }

    OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick {
        void onLastClick(View v, int position);

        void onImgCLick(View v, int position);

        void onXXclick(View v, int position);
    }

}
