package com.lianjie.andorid.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/8/7
 * Time: 9:41
 */
public class Adapter_Submitaud extends RecyclerView.Adapter<Adapter_Submitaud.MyViewhoder> {
    Context mContext;
    List<String> list;
    private LayoutInflater inflater;
    private int size;
    private List<List<String>> data;
    private int pagepage;

    public Adapter_Submitaud(Context context, List<String> list,int size) {
        this.mContext = context;
        this.list = list;
        this.size = size;
    }

    @NonNull
    @Override
    public MyViewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflater = LayoutInflater.from(mContext);
        View inflate = inflater.inflate(R.layout.activity_requestimg_yes, null, false);
        return new MyViewhoder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewhoder holder, final int position) {
        int i1 = PhoneUtils.dip2px(mContext, 80); //缩略图
        int i2 = PhoneUtils.dip2px(mContext, 20); //缩略图


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onImageClickListener(v, position);
            }
        };
        View.OnClickListener onNoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onNoImageClickListener(v, position);
            }
        };
        View.OnClickListener onClickListener_xx = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onXImageClickListener(v, pagepage,position);
            }
        };
        if (position%2==0) {
            GlideUtils.getInstance().setImages(holder.img1,list.get(position),i1,i1,true);
            holder.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noIntentImageD(list.get(position));
                }
            });
            holder.img1_x.setVisibility(View.GONE);
        } else {
            if(list.get(position).isEmpty()){
                GlideUtils.getInstance().setImageWrap(holder.img1, R.mipmap.add_pic);
                holder.img1_x.setVisibility(View.GONE);
                holder.img1.setOnClickListener(onNoClickListener);
            }else{
                GlideUtils.getInstance().setImages(holder.img1,list.get(position),i1,i1,true);
                holder.img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noIntentImageD(list.get(position));
                    }
                });
                holder.img1_x.setVisibility(View.VISIBLE);
                holder.img1_x.setOnClickListener(onClickListener_xx);
            }
        }
    }

    @Override
    public int getItemCount() {

        return size;
    }

    public void setSize(int size) {
        this.size = size;
        notifyDataSetChanged();
    }

    public void setData(List<List<String>> data, int pagepage) {
        this.pagepage=pagepage;
        this.data = data;
        notifyDataSetChanged();
    }

    public class MyViewhoder extends RecyclerView.ViewHolder {

        private final ImageView img1;
        private final ImageView img1_x;

        public MyViewhoder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img1_x = itemView.findViewById(R.id.img1_x);

        }
    }

    Onclick onclick;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public interface  Onclick{
        void onImageClickListener(View v, int position); // 大图
        void onNoImageClickListener(View v, int position);  //无图
        void onXImageClickListener(View v, int i, int position);//X号
    }

    /**
     * 点击 照片
     *
     * @param s
     */
    private void noIntentImageD(String s) {
        List<String> list = new ArrayList<>();
        list.add(s);
        GlobalInfoUtils.onImageEnlarge(list, 0, (Activity) mContext, GlobalInfoUtils.ImageDa);

    }
}
