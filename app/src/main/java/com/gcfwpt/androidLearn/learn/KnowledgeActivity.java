package com.gcfwpt.androidLearn.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.adapter.KnowledgeAdapter;
import com.gcfwpt.androidLearn.base.BaseActivity;
import com.gcfwpt.androidLearn.bean.KnowledgeBean;
import com.gcfwpt.androidLearn.bean.MainBean;
import com.gcfwpt.androidLearn.callback.BaseLeftCallBack;
import com.gcfwpt.androidLearn.callback.OnRecycleClick;
import com.gcfwpt.androidLearn.learn.learn1.OkGoActivity;
import com.gcfwpt.androidLearn.learn.learn2.ImmersionSystemActivity;
import com.gcfwpt.androidLearn.utils.view.HeaderUtils;
import com.gcfwpt.androidLearn.view.recycleview.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeActivity extends BaseActivity {

    @BindView(R.id.rv_learn)
    RecyclerView rvLearn;

    private int mIntKnowledgeType;
    private List<KnowledgeBean> mListKnowledgeBean;
    private KnowledgeAdapter knowledgeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mIntKnowledgeType = getIntent().getIntExtra("knowledge_type", MainBean.Type.TYPE_OTHER);
        mListKnowledgeBean=new ArrayList<>();
        initKonwledgeData();
        
    }

    private void initKonwledgeData() {
        switch (mIntKnowledgeType){
            case MainBean.Type.TYPE_OTHER:
                // https://github.com/jeasonlzy/okhttp-OkGo
                mListKnowledgeBean.add(new KnowledgeBean("OkGo", OkGoActivity.class));
                break;
            case MainBean.Type.TYPE_VIEW:
                // http://blog.csdn.net/guolin_blog/article/details/51763825
                mListKnowledgeBean.add(new KnowledgeBean("沉浸式", ImmersionSystemActivity.class));
                break;
        }
    }

    private void initView() {
        initHeader();
        initRecycleView();
    }

    private void initHeader() {
        showHeadView();
        HeaderUtils headerUtils = getHeaderUtils();
        headerUtils.setModuleTitle("知识学习");
        headerUtils.showTopLeftButton();
        headerUtils.setLeftCallBack(new BaseLeftCallBack() {
            @Override
            public void onHeaderLeftClick(View view) {
                finish();
            }
        });
    }

    private void initRecycleView() {
        knowledgeAdapter=new KnowledgeAdapter(KnowledgeActivity.this,mListKnowledgeBean);
        rvLearn.setAdapter(knowledgeAdapter);
        rvLearn.setItemAnimator(new DefaultItemAnimator());
        rvLearn.setLayoutManager(new LinearLayoutManager(KnowledgeActivity.this));
        rvLearn.addItemDecoration(new DividerItemDecoration(KnowledgeActivity.this,DividerItemDecoration.VERTICAL_LIST));
        knowledgeAdapter.setOnRecycleClick(new OnRecycleClick() {
            @Override
            public void onItemClick(View parent, int position) {
                Intent it=new Intent(KnowledgeActivity.this,mListKnowledgeBean.get(position).getToActivity());
                it.putExtra("title",mListKnowledgeBean.get(position).getmStrData());
                startActivity(it);
            }
        });
    }

}
