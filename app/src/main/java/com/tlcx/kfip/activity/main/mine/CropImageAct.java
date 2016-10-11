package com.tlcx.kfip.activity.main.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.tlcx.kfip.R;
import com.tlcx.kfip.activity.base.AppBaseActivity;
import com.tlcx.kfip.utils.BitmapUtils;
import com.tlcx.kfip.utils.Constants;
import com.tlcx.kfip.utils.Directorys;
import com.tlcx.library.ui.imageCrop.CropImage;
import com.tlcx.library.ui.imageCrop.CropImageView;
import com.tlcx.library.ui.imageCrop.Messages;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手动裁剪图片(现在仅用于裁剪头像)
 * Created by victor on 2016/10/10 12:13.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class CropImageAct extends AppBaseActivity{

    @BindView(R.id.gl_modify_avatar_image)
    CropImageView mImageView;

    private Bitmap mBitmap;
    private CropImage mCrop;
    private String mPath = "CropImageAct";
    private int ptWidth;                  //要截取的宽度比例
    private int ptHeight;                 //要截取的高度比例
    private ProgressBar mProgressBar;

    public static Intent newIntent(Context context, String path, int ptWidth, int ptHeight){
        Intent intent = new Intent(context, CropImageAct.class);
        intent.putExtra("path", path);
        intent.putExtra("ptWidth", ptWidth);
        intent.putExtra("ptHeight", ptHeight);
        return intent;
    }

    private Handler mHandler;
    @Override
    protected void initVariables() {
        mHandler = new MyHandler(this);

        if (getIntent()!=null){
            mPath = getIntent().getStringExtra("path");
            ptWidth = getIntent().getIntExtra("ptWidth",1);
            ptHeight = getIntent().getIntExtra("ptHeight",1);
        }else {
            return;
        }
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.act_crop_image);
        ButterKnife.bind(this);

        if (!TextUtils.isEmpty(mPath)){
            try {
                mBitmap = createBitmap(mPath, Constants.screenWidth, Constants.screenHeight);
                if (null == mBitmap || mBitmap.isRecycled()) {
                    showToast(R.string.common_crop_image_no_image);
                    finish();
                } else {
                    resetImageView(mBitmap, ptWidth, ptHeight);
                }
                int degree = BitmapUtils.readPictureDegree(mPath);
                if (degree != 0) {
                    mCrop.startRotate(degree);
                }
            } catch (Exception e) {
                showToast(R.string.common_crop_image_no_image);
                finish();
            }
            addProgressbar();
        }else {
            showToast(R.string.common_crop_image_no_image);
            finish();
        }
    }

    @Override
    protected void loadData() {

    }
    static class MyHandler extends Handler{
        private WeakReference<Context> weakReference ;
        private CropImageAct mContext;
        public MyHandler(Context context){
            weakReference = new WeakReference<>(context);
            mContext = (CropImageAct) weakReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            if (mContext == null) {
                return;
            }
            switch (msg.what) {
                case Messages.SHOW_PROGRESS: // 显示等待
                    mContext.mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case Messages.REMOVE_PROGRESS: // 隐藏等待
                    removeMessages(Messages.SHOW_PROGRESS);
                    mContext.mProgressBar.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mContext.showToast("图片上传失败");
                    break;
                case 2:
                    // 修改头像
                    Intent intent = new Intent();
                    intent.putExtra("result", (String) msg.obj);
                    mContext.setResult(RESULT_OK, intent);
                    mContext.finish();
                    break;
            }

        }
    }
    /**
     * 设置图片
     *
     * @param b        位图
     * @param ptWidth  宽比例
     * @param ptHeight 高比例
     */
    private void resetImageView(Bitmap b, int ptWidth, int ptHeight) {
        mImageView.clear();
        mImageView.setImageBitmap(b);
        mImageView.setImageBitmapResetBase(b, true);
        mCrop = new CropImage(this, mImageView, mHandler, ptWidth, ptHeight);
        mCrop.crop(b);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
        }
        mBitmap = null;
    }

    /**
     * 添加加载进度
     */
    protected void addProgressbar() {
        mProgressBar = new ProgressBar(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addContentView(mProgressBar, params);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * 创建位图
     *
     * @param path 保存路径
     * @param w    位图宽
     * @param h    位图高
     * @return
     */
    private Bitmap createBitmap(String path, int w, int h) {
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            // 这里是整个方法的关键，inJustDecodeBounds设为true时将不为图片分配内存。
            BitmapFactory.decodeFile(path, opts);
            int srcWidth = opts.outWidth;   // 获取图片的原始宽度
            int srcHeight = opts.outHeight; // 获取图片原始高度
            int destWidth = 0;
            int destHeight = 0;
            // 缩放的比例
            double ratio = 0.0;
            if (srcWidth < w || srcHeight < h) {
                ratio = 0.0;
                destWidth = srcWidth;
                destHeight = srcHeight;
            } else if (srcWidth > srcHeight) {// 按比例计算缩放后的图片大小，maxLength是长或宽允许的最大长度
                ratio = (double) srcWidth / w;
                destWidth = w;
                destHeight = (int) (srcHeight / ratio);
            } else {
                ratio = (double) srcHeight / h;
                destHeight = h;
                destWidth = (int) (srcWidth / ratio);
            }
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 缩放的比例，缩放是很难按准备的比例进行缩放的，目前我只发现只能通过inSampleSize来进行缩放，其值表明缩放的倍数，SDK中建议其值是2的指数值
            newOpts.inSampleSize = (int) ratio + 1;
            // inJustDecodeBounds设为false表示把图片读进内存中
            newOpts.inJustDecodeBounds = false;
            // 设置大小，这个一般是不准确的，是以inSampleSize的为准，但是如果不设置却不能缩放
            newOpts.outHeight = destHeight;
            newOpts.outWidth = destWidth;
            // 获取缩放后图片
            return BitmapFactory.decodeFile(path, newOpts);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    /**
     * 取消
     */
    @OnClick(R.id.gl_modify_avatar_cancel)
    void cancel(){
        mCrop.cropCancel();
        finish();
    }

    /**
     * 保存
     */
    @OnClick(R.id.gl_modify_avatar_save)
    void save(){
        Directorys.makeDirectoriesIngoreMedia();
        //截取后图片路径
        final String path = mCrop.saveToLocal(mCrop.cropAndSave(), Directorys.AFTER_CROP_TEMP);
        setResult(RESULT_OK);
        finish();
//        fileUpload(path);
    }

    /**
     * 旋转图片
     */
    @OnClick(R.id.gl_modify_avatar_rotate_right)
    void rotate(){
        mCrop.startRotate(90.f);
    }

    private void fileUpload(String path) {
    }
}
