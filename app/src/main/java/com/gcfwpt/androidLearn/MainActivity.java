package com.gcfwpt.androidLearn;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.gcfwpt.androidLearn.adapter.MainAdapter;
import com.gcfwpt.androidLearn.base.BaseActivity;
import com.gcfwpt.androidLearn.bean.MainBean;
import com.gcfwpt.androidLearn.callback.OnRecycleClick;
import com.gcfwpt.androidLearn.learn.KnowledgeActivity;
import com.gcfwpt.androidLearn.utils.view.HeaderUtils;
import com.gcfwpt.androidLearn.view.recycleview.DividerItemDecoration;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.yzl.permissionhelper.anotation.PermissionAgree;
import cn.yzl.permissionhelper.anotation.PermissionNoAsk;
import cn.yzl.permissionhelper.anotation.PermissionRefuse;
import cn.yzl.permissionhelper.library.PermissionHelper;

public class MainActivity extends BaseActivity {


    @BindView(R.id.rv_learn)
    RecyclerView rvLearn;
    @BindView(R.id.fb_blog)
    FloatingActionButton fbBlog;

    private List<MainBean> mListMainBean;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getRunPermission();
        initData();
        initView();
    }

    private void initData() {
        mListMainBean = new ArrayList<>();
        mListMainBean.add(new MainBean("第三方知识点", KnowledgeActivity.class, MainBean.Type.TYPE_OTHER));
        mListMainBean.add(new MainBean("界面相关", KnowledgeActivity.class, MainBean.Type.TYPE_VIEW));
    }

    private void initView() {
        initHeader();
        initRecycle();
    }

    private void initHeader() {
        showHeadView();
        HeaderUtils headerUtils = getHeaderUtils();
        headerUtils.setModuleTitle("首页");
    }

    private void initRecycle() {
        mainAdapter = new MainAdapter(MainActivity.this, mListMainBean);
        rvLearn.setAdapter(mainAdapter);
        rvLearn.setItemAnimator(new DefaultItemAnimator());
        rvLearn.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvLearn.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));
        mainAdapter.setOnRecycleClick(new OnRecycleClick() {
            @Override
            public void onItemClick(View parent, int position) {
                Intent it = new Intent(MainActivity.this, mListMainBean.get(position).getToActivity());
                it.putExtra("knowledge_type", mListMainBean.get(position).getmIntType());
                startActivity(it);
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        getRunPermission();
    }

    /**
     * 获取运行时权限
     * author LH
     * create at 2017/10/13 15:13
     */
    private void getRunPermission() {
        PermissionHelper.init(MainActivity.this)
                .request(1, Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .excute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionHelper.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionAgree(1)
    public void agreeMulPermission() {
        Logger.e("获取权限成功");
    }

    @PermissionRefuse(1)
    public void refuseMulPermission() {
        showPermissionAgree();
    }

    @PermissionNoAsk(1)
    public void noask(List<String> diedPermissions) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < diedPermissions.size(); i++) {
            String permission = diedPermissions.get(i);
            if (permission.equals(Manifest.permission.READ_PHONE_STATE)) {
                sb.append("电话,");
            } else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                sb.append("存储,");
            }
        }
        showPermissionSetting(sb.toString());
    }

    /**
     * 打开权限管理设置
     * author LH
     * create at 2017/10/13 16:02
     */
    private void showPermissionAgree() {
        final MaterialDialog dialog = new MaterialDialog(MainActivity.this);
        dialog.btnNum(1)
                .content("为了您更好的使用本应用，请允许本应用获取权限")//
                .btnText("确定")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setCancelable(false);
        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
                getRunPermission();
            }
        });
    }

    /**
     * 打开权限管理设置
     * author LH
     * create at 2017/10/13 16:02
     */
    private void showPermissionSetting(String permission) {
        final MaterialDialog dialog = new MaterialDialog(MainActivity.this);
        dialog//
                .btnNum(1)
                .content("请授予" + permission + "权限，点击[权限]按钮进行设置")//
                .btnText("确定")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
                PermissionHelper.showPermissionEditAct(MainActivity.this);
            }
        });
    }

    @OnClick(R.id.fb_blog)
    public void onViewClicked() {
          Intent it=new Intent(MainActivity.this,WebActivity.class);
          it.putExtra(WebActivity.TITLE,"欢迎来到我的博客");
          it.putExtra(WebActivity.URL,"http://blog.csdn.net/u012483116");
          startActivity(it);
    }
}
