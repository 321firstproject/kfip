package com.tlcx.kfip.fragment.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.GridViewWithHeaderAndFooter;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.tlcx.kfip.R;
import com.tlcx.kfip.adapter.KfipListAdapter;
import com.tlcx.kfip.entity.KfipListEntity;
import com.tlcx.kfip.fragment.base.BaseFragment;
import com.tlcx.kfip.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 功夫夺宝碎片
 * Created by victor on 2016/10/1 20:44.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class KFIPFragment extends BaseFragment{

    @BindView(R.id.fl_kfip_layout)
    PtrClassicFrameLayout mFrameLayout;
    @BindView(R.id.gv_kfip_grid_view)
    GridViewWithHeaderAndFooter mGridView;

    private int mPage = 1;
    private Unbinder unbinder;
    private Handler mHanlder;
    private KfipListAdapter mAdapter;
    private List<KfipListEntity> mData;              //数据源

    @Override
    protected int getContentView() {
        return R.layout.frag_kfip;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        mData = new ArrayList<>();
        mHanlder = new Handler();
        mAdapter = new KfipListAdapter(getActivity());


        mGridView.setAdapter(mAdapter);
        //设置自动刷新
        mFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFrameLayout.autoRefresh();
            }
        },150);

        //设置手动下拉刷新
        mFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadFirstPageData();
                    }
                },1000);
            }
        });

        //设置手动上拉加载下一页数据
        mFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mPage++;
                loadNextPageData(mPage);
            }
        });
    }

    /**
     * 刷新加载第一页数据
     */
    private void loadFirstPageData(){
        mPage = 1;
        mData.clear();
        for (int i = 0; i < Constants.LIST_LOAD_COUNT; i++) {
            mData.add(new KfipListEntity());
        }
        mAdapter.addAllAndNotify(mData);
        mFrameLayout.refreshComplete();
        mFrameLayout.setLoadMoreEnable(true);
    }

    /**
     * 获得第page页数据
     * @param page
     */
    private void loadNextPageData(int page){
        List<KfipListEntity> data =new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            data.add(new KfipListEntity());
        }
        mAdapter.addAllAndNotify(data);
        mFrameLayout.loadMoreComplete(true);
    }
    /**
     * 跳转搜索页
     */
    @OnClick(R.id.iv_frag_kfip_search)
    public void skipToSearch(){

    }

    /**
     * 跳转消息页
     */
    @OnClick(R.id.iv_frag_kfip_message)
    public void skipToMessage(){

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
