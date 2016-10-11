package com.tlcx.kfip.activity.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.tlcx.kfip.R;
import com.tlcx.kfip.fragment.base.BaseFragment;
import com.tlcx.kfip.fragment.main.BillFragment;
import com.tlcx.kfip.fragment.main.KFIPFragment;
import com.tlcx.kfip.fragment.main.LastAnnounceFragment;
import com.tlcx.kfip.fragment.main.MineFragment;
import com.tlcx.kfip.fragment.main.WulinPartyFragment;
import com.tlcx.kfip.listener.OnMainMenuChangedListener;
import com.tlcx.kfip.ui.MainBottomView;
import com.tlcx.library.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    MainBottomView mainBottomView;            //底部菜单控件

    private int currentFragIndex;        //被选中的碎片下标(默认第一个被选中)
    private List<BaseFragment> fragments;     //碎片集合

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void initVariables() {
        //填充碎片数据源
        fragments = new ArrayList<>();
        fragments.add(new KFIPFragment());
        fragments.add(new LastAnnounceFragment());
        fragments.add(new WulinPartyFragment());
        fragments.add(new BillFragment());
        fragments.add(new MineFragment());
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_main);
        mainBottomView = (MainBottomView) findViewById(R.id.widget_main_bottom_view);

        mainBottomView.setListener(new OnMainMenuChangedListener() {
            @Override
            public void onCheckedChanged(int checkedIndex) {
                checkFragment(checkedIndex);
            }
        });

        //产生沉浸效果
        initWindow();
    }

    @Override
    protected void loadData() {
        //默认加载第一页
        checkFragment(currentFragIndex);

    }
    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    /**
     * 切换碎片
     */
    private void checkFragment(int selectedFragIndex) {

        if (fragments == null || fragments.size() < selectedFragIndex) {
            return;
        }

        BaseFragment currentFrag = fragments.get(currentFragIndex);      //获得当前碎片
        BaseFragment selectedFrag = fragments.get(selectedFragIndex);        //获得被选中的碎片
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //如果被选中的碎片不是当前碎片，那么将当前碎片onPause
        if (currentFragIndex != selectedFragIndex){
            currentFrag.onPause();
        }

        //如果被选中的碎片还没有加入回退栈中，就add
        if (selectedFrag.isAdded()){
            selectedFrag.onResume();
        }else {
            fragmentTransaction.add(R.id.fl_container,selectedFrag);
        }

        //如果被选中的碎片不是当前碎片，那么将当前碎片隐藏
        if (currentFragIndex != selectedFragIndex){
            fragmentTransaction.hide(currentFrag);
        }

        //显示并提交
        fragmentTransaction.show(selectedFrag);
        fragmentTransaction.commit();
        currentFragIndex = selectedFragIndex;     //更新被选中碎片下标
    }

}
