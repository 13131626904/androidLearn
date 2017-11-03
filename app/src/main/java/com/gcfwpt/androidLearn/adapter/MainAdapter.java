package com.gcfwpt.androidLearn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.bean.MainBean;
import com.gcfwpt.androidLearn.callback.OnRecycleClick;
import com.orhanobut.logger.Logger;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by LH on 2017/11/3.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context context;
    private List<MainBean> mListMainBean;
    private OnRecycleClick onRecycleClick;

    public MainAdapter(Context context, List<MainBean> mListMainBean) {
        this.context = context;
        this.mListMainBean = mListMainBean;
        Logger.e(mListMainBean.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main, parent,
                false));
        AutoUtils.autoSize(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvName.setText(mListMainBean.get(position).getmStrData());
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
        return mListMainBean.size();
    }

    public void setOnRecycleClick(OnRecycleClick onRecycleClick) {
        this.onRecycleClick = onRecycleClick;
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
