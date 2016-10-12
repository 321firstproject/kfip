package com.tlcx.kfip.entity;

import java.io.Serializable;

/**
 * 最新揭晓列表中实体
 * Created by victor on 2016/10/6 21:39.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class LastAnnounceEntity implements Serializable {

    private String name;             //名字
    private String price;            //价格
    private String iconUrl;          //icon地址

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPrice() {

        return price;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
