package com.gcfwpt.androidLearn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.bean.KnowledgeBean;
import com.gcfwpt.androidLearn.callback.OnRecycleClick;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by LH on 2017/11/6.
 */

public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.MyViewHolder>{
    private Context context;
    private List<KnowledgeBean> mListKnowledgeBean;
    private OnRecycleClick onRecycleClick;

    public KnowledgeAdapter(Context context, List<KnowledgeBean> mListKnowledgeBean){
        this.context=context;
        this.mListKnowledgeBean=mListKnowledgeBean;
    }

    public void setOnRecycleClick(OnRecycleClick onRecycleClick) {
        this.onRecycleClick = onRecycleClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder= new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_knowledge,parent,false));
        AutoUtils.autoSize(parent);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvName.setText(mListKnowledgeBean.get(position).getmStrData());
        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecycleClick.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListKnowledgeBean.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linItem;
        TextView tvName;

        public MyViewHolder(View view) {
            super(view);
            linItem = view.findViewById(R.id.lin_item);
            tvName = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
