package com.qlu.phone.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，
 *
 * 遇到一个问题，前端按照平常方式传给我json对象，属性名和我一致，我在controller层也按平常方式使用@RequestBody注解，
 * 用相应的实体类接收参数，但mobile可以成功接到参数，而XM,XH,SFZ却不能，在实体类的成员变量上增加@JsonProperty（"属性名"），就解决了问题
 */
@Setter
@Getter
@Data
@NoArgsConstructor
public class Updater {
    @TableField("XM")
    @JsonProperty("XM")
    private String XM;

    @TableField("XH")
    @JsonProperty("XH")
    private String XH;

    @TableField("SFZ")
    @JsonProperty("SFZ")
    private String SFZ;

    @TableField(exist = false)
    private  String mobile;
}
