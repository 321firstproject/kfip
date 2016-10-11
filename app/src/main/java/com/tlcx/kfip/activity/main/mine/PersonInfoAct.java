package com.tlcx.kfip.activity.main.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.base.AppBaseActivity;
import com.tlcx.kfip.ui.HeaderView;
import com.tlcx.kfip.ui.PeronInfoTitleValuePair;
import com.tlcx.kfip.utils.ActionSheetUtils;
import com.tlcx.kfip.utils.AlertDialogUtils;
import com.tlcx.kfip.utils.AppInfoUtils;
import com.tlcx.kfip.utils.Directorys;
import com.tlcx.kfip.utils.GetPictureUtil;
import com.tlcx.library.ui.wheelview.dialog.SelectDateDialog;
import com.tlcx.library.utils.FrescoUtils;

import java.io.File;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料页面
 * Created by victor on 2016/10/2 18:02.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class PersonInfoAct extends AppBaseActivity {

    private static final int REQUEST_CODE_MODIFY_NICKNAME = 0;         //修改昵称请求码
    private static final int REQUEST_CODE_CROP_IMAGE = 1;              //剪切头像的请求码
    @BindView(R.id.widget_header_view)
    HeaderView mHeaderView;
    @BindView(R.id.iv_person_info_avatar)
    SimpleDraweeView mAvatarIv;                      //头像
    @BindView(R.id.widget_person_info_nickname)
    PeronInfoTitleValuePair nicknamePitvp;          //昵称
    @BindView(R.id.widget_person_info_birthday)
    PeronInfoTitleValuePair birthDayPitvp;          //生日
    @BindView(R.id.widget_person_info_sex)
    PeronInfoTitleValuePair sexPitvp;               //性别
    @BindView(R.id.widget_person_info_modify_pwd)
    PeronInfoTitleValuePair modifyPwdPitvp;         //修改密码

    private int year =2016;
    private int month = 10;
    private int day =9;

    public static Intent newIntent(Context context){
        return new Intent(context,PersonInfoAct.class);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_person_info);
        ButterKnife.bind(this);

        setHeaderView();

        //设置头像
        setAvatar();

        //昵称
        nicknamePitvp.setTitle(R.string.person_info_nickname);
        nicknamePitvp.setValue("发财啦");
        nicknamePitvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(ModifyNicknameAct.newIntent(PersonInfoAct.this),REQUEST_CODE_MODIFY_NICKNAME);
            }
        });

        //生日
        birthDayPitvp.setTitle(R.string.person_info_birthday);
        birthDayPitvp.setValue(year+"-"+month+"-"+day);
        birthDayPitvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectDateDialog();
            }
        });

        //性别
        sexPitvp.setTitle(R.string.person_info_sex);
        sexPitvp.setValue(getString(R.string.dialog_select_sex_male));
        sexPitvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogUtils.selectSexDialog(PersonInfoAct.this, new AlertDialogUtils.OnClickItemListener() {
                    @Override
                    public void onClick(int index) {
                       switch (index){
                           case 0:
                               sexPitvp.setValue(getString(R.string.dialog_select_sex_male));
                               break;
                           case 1:
                               sexPitvp.setValue(getString(R.string.dialog_select_sex_female));
                               break;
                       }
                    }
                });
            }
        });

        //修改密码
        modifyPwdPitvp.setTitle(R.string.person_info_modify_pwd);
        modifyPwdPitvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ModifyPasswordAct.newIntent(PersonInfoAct.this));
            }
        });
    }

    @Override
    protected void loadData() {

    }

    /**
     * 点击设置头像
     */
    @OnClick(R.id.rl_person_info_avatar)
    public void showSetAvatarMenu(){
        ActionSheetUtils.menuOfSetAvatar(this, new ActionSheetUtils.OnClickItemListener() {
            @Override
            public void onClick(int index) {
                switch (index){
                    //拍照
                    case 0:
                        GetPictureUtil.takePhoto(PersonInfoAct.this);
                        break;
                    //从相册中选择
                    case 1:
                        GetPictureUtil.takeFromAlbum(PersonInfoAct.this);
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){
            return;
        }
        switch (requestCode){
            //拍照回调
            case GetPictureUtil.REQUEST_CODE_TAKE_PHOTO:
                startActivityForResult(CropImageAct.newIntent(this, Directorys.TAKE_PHOTO_TEMP, 1, 1), REQUEST_CODE_CROP_IMAGE);
                break;
            //剪切头像的回调请求
            case REQUEST_CODE_CROP_IMAGE:
                setAvatar();
                setResult(RESULT_OK);
                break;
            //从相册中取图片回调
            case GetPictureUtil.REQUEST_CODE_TAKE_FROM_ALBUM:
                String imagePath = GetPictureUtil.getImagePathFromAlbum(PersonInfoAct.this,data);
                if (!TextUtils.isEmpty(imagePath)){
                    startActivityForResult(CropImageAct.newIntent(this, imagePath, 1, 1), REQUEST_CODE_CROP_IMAGE);
                }
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
                    AppInfoUtils.getCurrentPkgName(this)+
                    "/"+R.mipmap.icon_avatar_default_has_login);
            mAvatarIv.setImageURI(avatarUri);
        }
    }

    /**
     * 设置头部
     */
    private void setHeaderView(){
        mHeaderView.setBackListener(new BackListener());
        mHeaderView.setTitle(R.string.title_person_info);
    }

    /**
     * 显示选择生日日期
     */
    private void showSelectDateDialog() {
        SelectDateDialog mSelectDateDialog = new SelectDateDialog(this);
        mSelectDateDialog.setOnClickListener(new SelectDateDialog.OnClickListener() {
            @Override
            public boolean onSure(int mYear, int mMonth, int mDay, long time) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                year = mYear;
                month = mMonth+1;
                day = mDay;
                birthDayPitvp.setValue(dateFormat.format(time));
                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });
        mSelectDateDialog.show(year, month-1, day);
    }
}
