package com.qlu.phone.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 获取成员api信息存入对应的数据库的实体类
 */
@Data
@Setter
@Getter
@TableName("getapimessage")
public class GetApiMessage {
    @TableField("userid")
    private  String userid;
    @TableField("getapiinfo")
    private  String getApiInfo;
    @TableField("id")
    private  int id;
}
