package com.gcfwpt.androidLearn.learn.learn1;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.adapter.OkGoAdapter;
import com.gcfwpt.androidLearn.base.BaseActivity;
import com.gcfwpt.androidLearn.bean.JokeBean;
import com.gcfwpt.androidLearn.bean.LzyResponse;
import com.gcfwpt.androidLearn.bean.ResultBean;
import com.gcfwpt.androidLearn.callback.BaseLeftCallBack;
import com.gcfwpt.androidLearn.callback.BaseReplayCallBack;
import com.gcfwpt.androidLearn.callback.JsonCallback;
import com.gcfwpt.androidLearn.config.Constants;
import com.gcfwpt.androidLearn.utils.common.TimeUtils;
import com.gcfwpt.androidLearn.utils.view.HeaderUtils;
import com.gcfwpt.androidLearn.view.recycleview.DividerItemDecoration;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OkGoActivity extends BaseActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_learn)
    RecyclerView rvLearn;

    private int mIntIndex = 1;
    private boolean isInitCache = false;
    private ArrayList<JokeBean> mListJokeBean;
    private OkGoAdapter okGoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_go);
        ButterKnife.bind(this);
        initView();
        onHttpRefresh(true);
    }

    private void initView() {
        initHeader();
        initRefreshLayout();
        initRecycleView();
        setReplayCallBack(new BaseReplayCallBack() {
            @Override
            public void onReplayNetClick(View view) {
                hideEmptyLayout();
                hideErrorLayout();
                onHttpRefresh(true);
            }
        });
    }

    private void initRecycleView() {
        mListJokeBean = new ArrayList<>();
        okGoAdapter = new OkGoAdapter(OkGoActivity.this,mListJokeBean);
        rvLearn.setAdapter(okGoAdapter);
        rvLearn.setItemAnimator(new DefaultItemAnimator());
        rvLearn.setLayoutManager(new LinearLayoutManager(OkGoActivity.this));
        rvLearn.addItemDecoration(new DividerItemDecoration(OkGoActivity.this,DividerItemDecoration.VERTICAL_LIST));
    }

    private void initRefreshLayout() {
        refreshLayout.setRefreshHeader(new DeliveryHeader(OkGoActivity.this));
        refreshLayout.setRefreshFooter(new FalsifyFooter(OkGoActivity.this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mIntIndex = 1;
                onHttpRefresh(false);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                onHttpRefresh(false);
            }
        });
    }


    /**
     * 联网请求
     * author LH
     * create at 2017/11/22 16:10
     * @param isShowLoading 是否显示加载中布局
     */
    private void onHttpRefresh(final Boolean isShowLoading) {
        OkGo.<LzyResponse<ResultBean<JokeBean>>>get(Constants.JOKE_URL)
                .tag(this)
                .cacheKey("OkGoActivity")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .params("sort", "desc")
                .params("page", mIntIndex)
                .params("pagesize", 12)
                .params("time", TimeUtils.getTenCurTimeMills())
                .params("key", Constants.JH_APP_KEY)
                .execute(new JsonCallback<LzyResponse<ResultBean<JokeBean>>>() {

                    @Override
                    public void onStart(Request<LzyResponse<ResultBean<JokeBean>>, ? extends Request> request) {
                        if(isShowLoading) {
                            showLoadingBar(false);
                        }
                    }

                    @Override
                    public void onSuccess(Response<LzyResponse<ResultBean<JokeBean>>> response) {
                        Logger.e(response.body().result.getData().toString());
                        ArrayList<JokeBean> listJokeBean = response.body().result.getData();
                        mListJokeBean.addAll(listJokeBean);
                        okGoAdapter.notifyDataSetChanged();
                        mIntIndex++;
                    }

                    @Override
                    public void onCacheSuccess(Response<LzyResponse<ResultBean<JokeBean>>> response) {
                        if (!isInitCache) {
                            onSuccess(response);
                            isInitCache = true;
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<ResultBean<JokeBean>>> response) {
                        showErrorLayout();
                    }

                    @Override
                    public void onFinish() {
                        if(isShowLoading) {
                            hideLoadingBar();
                        }
                        stopRefresh();
                    }
                });
    }

    private void stopRefresh(){
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }


    private void initHeader() {
        HeaderUtils headerUtils = getHeaderUtils();
        headerUtils.setModuleTitle("OkGo");
        headerUtils.showTopLeftButton();
        headerUtils.setLeftCallBack(new BaseLeftCallBack() {
            @Override
            public void onHeaderLeftClick(View view) {
                finish();
            }
        });
    }

}
