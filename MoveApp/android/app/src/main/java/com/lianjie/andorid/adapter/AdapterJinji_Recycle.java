package com.lianjie.andorid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lianjie.andorid.R;
import com.lianjie.andorid.ui.SubmitauditActivity;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.PhoneUtils;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/9/12
 * Time: 10:34
 */
public class AdapterJinji_Recycle extends RecyclerView.Adapter<AdapterJinji_Recycle.MyViewholder_> {
    private final SubmitauditActivity mContext;
    private List<String> img_list;
    private int size;
    private List<List<String>> data;

    public AdapterJinji_Recycle(SubmitauditActivity mContext, List<String> img_list, int size) {
        this.mContext = mContext;
        this.img_list = img_list;
        this.size = size;
    }


    @NonNull
    @Override
    public MyViewholder_ onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View inflate = inflater.inflate(R.layout.activity_requestimg_yes, null, false);
        return new MyViewholder_(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder_ holder, final int position) {
        int i1 = PhoneUtils.dip2px(mContext, 80); //缩略图
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
                onclick.onXImageClickListener(v, position);
            }
        };


        if(img_list.get(position).isEmpty()){

            GlideUtils.getInstance().setImageWrap(holder.img1, R.mipmap.add_pic);
            holder.img1_x.setVisibility(View.GONE);
            holder.img1.setOnClickListener(onNoClickListener);
        }else{
            GlideUtils.getInstance().setImages(holder.img1,img_list.get(position),i1,i1,true);
            holder.img1.setOnClickListener(onClickListener);
            holder.img1_x.setVisibility(View.VISIBLE);
            holder.img1_x.setOnClickListener(onClickListener_xx);
        }
    }


    @Override
    public int getItemCount() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public void setData(List<String> data) {
        this.img_list = data;
        notifyDataSetChanged();
    }

    public  class MyViewholder_ extends RecyclerView.ViewHolder {

        private final ImageView img1;
        private final ImageView img1_x;

        public MyViewholder_(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img1_x = itemView.findViewById(R.id.img1_x);


        }
    }


    public interface  Onclick{
        void onImageClickListener(View v, int position); // 大图
        void onNoImageClickListener(View v, int position);  //无图
        void onXImageClickListener(View v, int position);//X号
    }

    Onclick onclick;


}
