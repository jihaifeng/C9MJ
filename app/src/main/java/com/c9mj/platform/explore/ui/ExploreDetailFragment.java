package com.c9mj.platform.explore.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c9mj.platform.R;
import com.c9mj.platform.demo.mvp.model.bean.DemoBean;
import com.c9mj.platform.explore.adapter.ExploreDetailListAdapter;
import com.c9mj.platform.explore.mvp.model.bean.ExploreDetailBean;
import com.c9mj.platform.explore.mvp.presenter.impl.ExploreDetailPresenterImpl;
import com.c9mj.platform.explore.mvp.view.IExploreDetailView;
import com.c9mj.platform.util.ToastUtil;
import com.c9mj.platform.widget.animation.CustionAnimation;
import com.c9mj.platform.widget.fragment.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 快速构建MVP的模板
 * Created by Administrator on 2016/11/16.
 */

public class ExploreDetailFragment extends BaseFragment implements IExploreDetailView {

    private static final String KEY = "key";

    Context context;

    ExploreDetailPresenterImpl presenter;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    List<ExploreDetailBean.DetailBean.TopiclistNewsBean> list = new ArrayList<>();
    ExploreDetailListAdapter adapter;


    public static ExploreDetailFragment newInstance() {
        return newInstance("");
    }

    public static ExploreDetailFragment newInstance(String game_type) {
        ExploreDetailFragment fragment = new ExploreDetailFragment();
        Bundle args = new Bundle();
        args.putString(KEY, game_type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore_detail, container, false);
        ButterKnife.bind(this, view);

        context = view.getContext();

        initView();

        return attachToSwipeBack(view);
    }

    private void initView() {
        //初始化MVP
        presenter = new ExploreDetailPresenterImpl(this);

        //设置RefreshLayout

        //设置RecyclerView
        for (int i = 0; i < 5; i++) {
            list.add(new ExploreDetailBean.DetailBean.TopiclistNewsBean());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ExploreDetailListAdapter(list);
        adapter.openLoadAnimation(new CustionAnimation());
        adapter.isFirstOnly(true);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuikAdapter, View view, int pos) {
                switch (view.getId()){

                }
            }
        });
        //设置其他View
    }

    @Override
    public void showError(String message) {
        ToastUtil.show(message);
    }

}
