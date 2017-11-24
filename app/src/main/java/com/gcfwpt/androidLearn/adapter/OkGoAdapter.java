package com.gcfwpt.androidLearn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.bean.JokeBean;
import com.gcfwpt.androidLearn.callback.OnRecycleClick;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

/**
 * Created by LH on 2017/11/22.
 */

public class OkGoAdapter extends RecyclerView.Adapter<OkGoAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<JokeBean> mListJokeBean;
    private OnRecycleClick onRecycleClick;

    public OkGoAdapter(Context context,ArrayList<JokeBean> mListJokeBean){
        this.context=context;
        this.mListJokeBean=mListJokeBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OkGoAdapter.MyViewHolder holder = new OkGoAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_okgo, parent,
                false));
        AutoUtils.autoSize(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvContent.setText(mListJokeBean.get(position).getContent());
        holder.tvTime.setText(mListJokeBean.get(position).getUpdatetime());
        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecycleClick!=null){
                    onRecycleClick.onItemClick(holder.linItem,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListJokeBean.size();
    }

    public void setOnRecycleClick(OnRecycleClick onRecycleClick) {
        this.onRecycleClick = onRecycleClick;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linItem;
        TextView tvContent;
        TextView tvTime;

        public MyViewHolder(View view) {
            super(view);
            linItem = view.findViewById(R.id.lin_item);
            tvContent = (TextView) view.findViewById(R.id.tv_content);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
        }
    }
}
