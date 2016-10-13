package com.tlcx.kfip.entity;

/**
 * Created by victor on 2016/10/12 15:21.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class KfipListEntity {

    private String name;           //标题名字
    private String iconUrl;        //图片icon
    private int progress;          //进度

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public int getProgress() {
        return progress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
