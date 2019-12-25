package com.lianjie.andorid.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.VideoListBean;
import com.lianjie.andorid.newsView.WarpLinearLayout;
import com.lianjie.andorid.utils.PhoneUtils;

import java.util.List;

/**
 * Created by My on 2019/11/22.
 */

public class AdapterRecycle_Video extends RecyclerView.Adapter<AdapterRecycle_Video.MyViewHolder> {


    private List<VideoListBean.DataBean.ImageTextTrainListsBean> dataImage;
    private int page = -1 ;
    private List<VideoListBean.DataBean.VideoTrainListsBean> dataVideo;
    private Context mContext;
    private OnclicK onclicK;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.training_item_, null, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        String righttitle="",title,status,releaseTime,releasePerson;
        if(page == 0){
             title = dataVideo.get(i).getTitle();
             status = dataVideo.get(i).getStatus();
             releaseTime = dataVideo.get(i).getReleaseTime();
             releasePerson = dataVideo.get(i).getReleasePerson();
            List<String> type = dataVideo.get(i).getType();
            onData(holder,title,status,releaseTime,releasePerson,type, righttitle,i);
        }else{
            title = dataImage.get(i).getTitle();
            status = dataImage.get(i).getStatus();
            releaseTime = dataImage.get(i).getReleaseTime();
            releasePerson = dataImage.get(i).getReleasePerson();
            List<String> type = dataImage.get(i).getType();
           onData(holder,title,status,releaseTime,releasePerson,type,righttitle, i);
        }

    }

    private void onData(MyViewHolder holder, String title, String status, String releaseTime, String releasePerson, List<String> type, String righttitle, final int position) {
        if (holder.linear_title.getChildCount() !=0) {
            holder.linear_title.removeAllViews();
        }
        for (int i1 = 0; i1 < type.size(); i1++) {
            String s = type.get(i1);
            TextView textView = new TextView(mContext);
            textView.setText(s);
            onCheced_TagsBg(textView);
            textView.setTextSize(12);
            textView.setPadding(10,5,10,5);
            holder.linear_title.addView(textView);
        }

        holder.title.setText(title);
        if (releasePerson.length() <=5) {
            holder.subTitle.setText(releaseTime+" 由"+releasePerson+"发布" );
        }else{
            StringBuffer s=new StringBuffer();
            for (int i1 = 0; i1 < 5; i1++) {
                s.append(releasePerson.charAt(i1));
            }
            holder.subTitle.setText(releaseTime+" 由"+s.toString()+"发布" );
        }
        if( status.equals("1")){
            righttitle="已学习";
            holder.tx_righttitle.setTextColor(mContext.getResources().getColor(R.color.color_move_CACACA));
        }else if(status.equals("2")){
            holder.tx_righttitle.setTextColor(mContext.getResources().getColor(R.color.color_move_00CD58));
            righttitle="学习中";
        }else if(status.equals("3")){
            righttitle="未学习";
            holder.tx_righttitle.setTextColor(mContext.getResources().getColor(R.color.color_bar_color));
        }
        holder.tx_righttitle.setText(righttitle);
        if(page == 0) {
            Glide.with(mContext).load(R.drawable.course_video).into(holder.img);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                onclicK.OnclickListener(v,position);
                }
            });
        }else if(page == 1){
            Glide.with(mContext).load(R.drawable.course_text).into(holder.img);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclicK.OnclickListener(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(page ==0){
            return dataVideo.size();
        }else  if(page ==1){
            return dataImage.size();
        }else{
            return 0;
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final TextView title;
        private final WarpLinearLayout linear_title;
        private final TextView subTitle;
        private final TextView tx_righttitle;
        private final ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            linear_title = itemView.findViewById(R.id.linear_title);
            subTitle = itemView.findViewById(R.id.subTitle);
            tx_righttitle = itemView.findViewById(R.id.tx_righttitle);
            img = itemView.findViewById(R.id.img);
        }
    }

    /**
     * imag
     * @param dataImage
     */
    public void setDataImage(List<VideoListBean.DataBean.ImageTextTrainListsBean> dataImage) {
        this.dataImage = dataImage;

        this.page = 1;
        notifyDataSetChanged();
    }

    /**
     * 视频
     * @param dataVideo
     */
    public void setDataVideo(List<VideoListBean.DataBean.VideoTrainListsBean> dataVideo) {
        this.dataVideo = dataVideo;
        this.page = 0;
        notifyDataSetChanged();
    }

    /**
     * 标签
     * @param
     * @param tv
     */
    private void onCheced_TagsBg(View tv) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(2, mContext.getResources().getColor(R.color.color_move_dddddd));
        int i = PhoneUtils.dip2px(mContext, 5);
        gradientDrawable.setCornerRadius(i);
        tv.setBackground(gradientDrawable);
        TextView tx = (TextView) tv;
        tx.setTextColor(mContext.getResources().getColor(R.color.color_move_4C90F3));
    }

    public void setOnclicK(OnclicK onclicK) {
        this.onclicK = onclicK;
    }

    public interface OnclicK{
        void OnclickListener(View view, int position);
    }

}
