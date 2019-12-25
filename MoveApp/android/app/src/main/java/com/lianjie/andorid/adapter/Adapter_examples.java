package com.lianjie.andorid.adapter;

import android.media.Image;
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

public class Adapter_examples extends RecyclerView.Adapter<Adapter_examples.MyViewholder> {
    private final SubmitauditActivityAuditforward mContext;
    private final List<String> examples;


    public Adapter_examples(SubmitauditActivityAuditforward mContext, List<String> examples) {
        this.mContext = mContext;
        this.examples = examples;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_noscrosses_addimg, null, false);
        return new MyViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, final int position) {
        holder.img_x.setVisibility(View.GONE);
        GlideUtils.getInstance().setImageWrap(holder.img, examples.get(position));
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalInfoUtils.onImageEnlarge(examples,position+1,mContext,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (examples != null) {
            return examples.size();
        }
        return 0;
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
}
