package com.qlu.phone.entity;

public class Wechat_channels {

    private String nickname;
    private int status;
    public void setNickname(String nickname) {
         this.nickname = nickname;
     }
     public String getNickname() {
         return nickname;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    @Override
    public String toString() {
        return "Wechat_channels{" +
                "nickname='" + nickname + '\'' +
                ", status=" + status +
                '}';
    }
}