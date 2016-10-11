package com.tlcx.kfip.entity;

import java.io.Serializable;

/**
 * 最新揭晓列表中实体
 * Created by victor on 2016/10/6 21:39.
 * Email:zhuzhaoyang@yuanchuangyun.com
 */
public class LastAnnounceEntity implements Serializable {

    private String name;

    public LastAnnounceEntity(String name){
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
