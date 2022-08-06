package com.qlu.phone.entity;

import java.util.List;

public class External_profile {

    private String external_corp_name;
    private Wechat_channels wechat_channels;
    private List<External_attr> external_attr;
    public void setExternal_corp_name(String external_corp_name) {
         this.external_corp_name = external_corp_name;
     }
     public String getExternal_corp_name() {
         return external_corp_name;
     }

    public void setWechat_channels(Wechat_channels wechat_channels) {
         this.wechat_channels = wechat_channels;
     }
     public Wechat_channels getWechat_channels() {
         return wechat_channels;
     }

    public void setExternal_attr(List<External_attr> external_attr) {
         this.external_attr = external_attr;
     }
     public List<External_attr> getExternal_attr() {
         return external_attr;
     }

    @Override
    public String toString() {
        return "External_profile{" +
                "external_corp_name='" + external_corp_name + '\'' +
                ", wechat_channels=" + wechat_channels +
                ", external_attr=" + external_attr +
                '}';
    }
}