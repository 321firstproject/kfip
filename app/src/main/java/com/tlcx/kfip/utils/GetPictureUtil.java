package com.tlcx.kfip.utils;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.tlcx.kfip.R;
import com.tlcx.library.activity.BaseActivity;

import java.io.File;

/**
 * 获得拍照或相册图片
 * Created by victor on 2016/10/10 10:37.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class GetPictureUtil {
    public static final int REQUEST_CODE_TAKE_PHOTO = 1000;            //拍照
    public static final int REQUEST_CODE_TAKE_FROM_ALBUM = 1001;       //从相册中选择

    /**
     * 拍照
     */
    public static void takePhoto(BaseActivity activity) {
        //判断sd卡是否挂载
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            activity.showToast(R.string.common_no_sd_card);
            return;
        }
        //判断是是否有相机权限
        if (!PermissionCheckUtils.canUseCamera(activity)) {
            activity.showToast(R.string.common_no_camera_permission);
            return;
        }
        try {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            Uri u = Uri.fromFile(new File(Directorys.TAKE_PHOTO_TEMP));
            intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
            activity.startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调取系统相册
     */
    public static void takeFromAlbum(BaseActivity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, REQUEST_CODE_TAKE_FROM_ALBUM);
    }

    /**
     * 获得从相册中选择的图片路径
     * @param activity
     * @param intent
     * @return
     */
    public static String getImagePathFromAlbum(BaseActivity activity, Intent intent) {
        if (intent != null) {
            Uri uri = intent.getData();
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                Cursor cursor = activity.getContentResolver().query(
                        uri, new String[]{MediaStore.Images.Media.DATA},
                        null, null, null);
                if (null == cursor) {
                    activity.showToast(R.string.common_get_image_failure);
                    return "";
                }
                cursor.moveToFirst();
                String path = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
                return path;
            }else {
                return uri.getPath();
            }
        }
        return "";
    }
}
