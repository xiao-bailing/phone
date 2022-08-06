package com.qlu.phone.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ResultEntity {
    private  String errmsg;
    private int errcode;
    private String access_token;
    private String expires_in;
    private String userid;
}
