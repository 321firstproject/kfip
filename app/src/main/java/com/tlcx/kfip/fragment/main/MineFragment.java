package com.tlcx.kfip.fragment.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.main.mine.AnnouncedBillRecordsAct;
import com.tlcx.kfip.activity.main.mine.IPRecordsAct;
import com.tlcx.kfip.activity.main.mine.MyAddressListAct;
import com.tlcx.kfip.activity.main.mine.PersonInfoAct;
import com.tlcx.kfip.activity.main.mine.PrizedRecordsAct;
import com.tlcx.kfip.activity.main.mine.RechargeAct;
import com.tlcx.kfip.activity.setting.SettingAct;
import com.tlcx.kfip.fragment.base.BaseFragment;
import com.tlcx.kfip.ui.MineTitleValuePair;
import com.tlcx.kfip.utils.AppInfoUtils;
import com.tlcx.kfip.utils.Constants;
import com.tlcx.kfip.utils.Directorys;
import com.tlcx.library.utils.FrescoUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的碎片
 * Created by victor on 2016/10/1 20:49.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class MineFragment extends BaseFragment {

    private static final int REQUEST_CODE_UPDATE_AVATAR = 0;             //修改头像请求码
    private static final int REQUEST_CODE_RECHARGE_SUCCESS = 1;          //充值成功的请求码

    @BindView(R.id.tv_click_to_login)
    TextView clickToLoginTv;                //点击登录
    @BindView(R.id.iv_avatar)
    SimpleDraweeView mAvatarIv;             //头像
    @BindView(R.id.iv_mine_level)
    ImageView levelIv;                      //用户级别
    @BindView(R.id.widget_ip_record)
    MineTitleValuePair ipRecordTvp;         //夺宝记录
    @BindView(R.id.widget_prize_record)
    MineTitleValuePair prizeRecordTvp;      //中奖记录
    @BindView(R.id.widget_announce_bill)
    MineTitleValuePair announceBillTvp;     //晒单记录
    @BindView(R.id.widget_address)
    MineTitleValuePair addressTvp;          //我的收件地址

    private Unbinder unbinder;

    @Override
    protected int getContentView() {
        return R.layout.frag_mine;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        //用户是否登录的信息显示
        if (!Constants.hasLogin()){
            clickToLoginTv.setVisibility(View.VISIBLE);
            levelIv.setVisibility(View.GONE);
        }else {
            clickToLoginTv.setVisibility(View.GONE);
            levelIv.setVisibility(View.VISIBLE);
        }

        //设置头像
        setAvatar();

        //夺宝记录
        ipRecordTvp.setIconAndTitle(R.mipmap.icon_mine_ip_record,R.string.mine_ip_record);
        ipRecordTvp.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(IPRecordsAct.newIntent(getActivity()));
            }
        });

        //中奖记录
        prizeRecordTvp.setIconAndTitle(R.mipmap.icon_mine_prize_record,R.string.mine_prize_record);
        prizeRecordTvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(PrizedRecordsAct.newIntent(getActivity()));
            }
        });

        //晒单记录
        announceBillTvp.setIconAndTitle(R.mipmap.icon_mine_announce_bill_record,R.string.mine_announce_bill);
        announceBillTvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AnnouncedBillRecordsAct.newIntent(getActivity()));
            }
        });

        //收件地址
        addressTvp.setIconAndTitle(R.mipmap.icon_mine_address,R.string.mine_my_address);
        addressTvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyAddressListAct.newIntent(getActivity()));
            }
        });
    }

    /**
     * 跳转到设置页面
     */
   @OnClick(R.id.iv_mine_title_right)
   public void skipToSettingAct(){
       startActivity(SettingAct.newIntent(getActivity()));
   }



    /**
     * 跳转到个人信息页面
     */
    @OnClick({R.id.iv_avatar,R.id.tv_person_info})
    public void skipToPersonInfoAct(){
        if (Constants.hasLogin()){
            startActivityForResult(PersonInfoAct.newIntent(getActivity()),REQUEST_CODE_UPDATE_AVATAR);
        }
    }

    /**
     * 显示kf币
     */
    @OnClick(R.id.ly_mine_wealth)
    public void showKfCoin(){

    }

    /**
     * 充值
     */
    @OnClick(R.id.tv_mine_recharge)
    public void recharge(){
        startActivityForResult(RechargeAct.newIntent(getActivity()),REQUEST_CODE_RECHARGE_SUCCESS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode){
            //更新头像
            case REQUEST_CODE_UPDATE_AVATAR:
                setAvatar();
                break;
            //充值成功后的处理
            case REQUEST_CODE_RECHARGE_SUCCESS:
                break;
        }
    }
    /**
     * 设置头像
     */
    private void setAvatar() {
        if (new File(Directorys.AFTER_CROP_TEMP).exists()){
            //如果不清理缓存，会默认加载缓存里的图片，导致图片不刷新
            FrescoUtils.clearCacheImage();
            mAvatarIv.setImageURI(Uri.parse("file://"+Directorys.AFTER_CROP_TEMP));
        }else {
            Uri avatarUri = Uri.parse("res://"+
                    AppInfoUtils.getCurrentPkgName(getActivity())+
                    "/"+R.mipmap.icon_avatar_default_has_login);
            mAvatarIv.setImageURI(avatarUri);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
