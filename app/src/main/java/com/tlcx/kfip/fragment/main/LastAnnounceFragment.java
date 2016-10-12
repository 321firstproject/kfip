package com.tlcx.kfip.fragment.main;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.tlcx.kfip.R;
import com.tlcx.kfip.adapter.LastAnnounceListAdapter;
import com.tlcx.kfip.entity.LastAnnounceEntity;
import com.tlcx.kfip.fragment.base.BaseFragment;
import com.tlcx.kfip.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 最新揭晓碎片
 * Created by victor on 2016/10/1 20:46.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class LastAnnounceFragment extends BaseFragment {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_refresh)
    ListView mListView;

    private int mPage = 1;                                            //加载页数
    private Handler mHandler;
    private Unbinder unbinder;
    private List<LastAnnounceEntity> mData;                     //数据源
    private LastAnnounceListAdapter mAdapter;
    private SwipeRefreshHelper mSwipeRefreshHelper;


    @Override
    protected int getContentView() {
        return R.layout.frag_last_announce;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        mHandler = new Handler();
        mData = new ArrayList<>();
        mAdapter = new LastAnnounceListAdapter(getActivity());

        //设置适配器
        mListView.setAdapter(mAdapter);

        //设置刷新控件的颜色
        mRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW);

        mSwipeRefreshHelper = new SwipeRefreshHelper(mRefreshLayout);

        //设置自动刷新
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHelper.autoRefresh();
            }
        });

        //设置下拉手动刷新
        mSwipeRefreshHelper.setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
            @Override
            public void onfresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadFirstPageData();
                    }
                },1000);

            }
        });

        //设置上拉加载更多
        mSwipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage ++;
                        loadNextPageData(mPage);
                        mSwipeRefreshHelper.loadMoreComplete(true);//设置加载完成
                    }
                },1000);
            }
        });

    }

    /**
     * 获得第一页数据
     */
    private void loadFirstPageData(){
        mData.clear();
        mPage = 1;
        for (int i = 0; i < Constants.LIST_LOAD_COUNT; i++) {
            LastAnnounceEntity entity = new LastAnnounceEntity();
            entity.setName("第 "+(i+1)+" 件商品");
            entity.setPrice("价值¥ "+(i+10));
            mData.add(entity);
        }
        mAdapter.addAllAndNotify(mData);
        mSwipeRefreshHelper.refreshComplete();          //刷新完成
        if (mData.size()>=Constants.LIST_LOAD_COUNT){
            mSwipeRefreshHelper.setLoadMoreEnable(true);//设置可加载更多
        }
    }

    /**
     * 加载第page页数据
     * @param page
     */
    private void loadNextPageData(int page){
        List<LastAnnounceEntity> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LastAnnounceEntity entity = new LastAnnounceEntity();
            entity.setName("第 "+page+" 页第"+(i+1)+"件商品");
            entity.setPrice("价值¥ "+(i+10));
            data.add(entity);
        }
        mAdapter.addAllAndNotify(data);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
