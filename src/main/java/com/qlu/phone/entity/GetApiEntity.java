
package com.qlu.phone.entity;
import java.util.Arrays;
import java.util.List;

public class GetApiEntity {

    private int errcode;
    private String errmsg;
    private String userid;
    private String name;
    private String[] department;
    private String[] order;
    private String position;
    private String mobile;
    private String gender;
    private String email;
    private String biz_mail;
    private List<Integer> is_leader_in_dept;
    private List<String> direct_leader;
    private String avatar;
    private String thumb_avatar;
    private String telephone;
    private String alias;
    private String address;
    private String open_userid;
    private int main_department;
    private Extattr extattr;
    private int status;
    private String qr_code;
    private String external_position;
    private External_profile external_profile;
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    public int getErrcode() {
        return errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getErrmsg() {
        return errmsg;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUserid() {
        return userid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDepartment(String[] department) {
        this.department = department;
    }
    public String[] getDepartment() {
        return department;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }
    public String[] getOrder() {
        return order;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getPosition() {
        return position;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobile() {
        return mobile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setBiz_mail(String biz_mail) {
        this.biz_mail = biz_mail;
    }
    public String getBiz_mail() {
        return biz_mail;
    }

    public void setIs_leader_in_dept(List<Integer> is_leader_in_dept) {
        this.is_leader_in_dept = is_leader_in_dept;
    }
    public List<Integer> getIs_leader_in_dept() {
        return is_leader_in_dept;
    }

    public void setDirect_leader(List<String> direct_leader) {
        this.direct_leader = direct_leader;
    }
    public List<String> getDirect_leader() {
        return direct_leader;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setThumb_avatar(String thumb_avatar) {
        this.thumb_avatar = thumb_avatar;
    }
    public String getThumb_avatar() {
        return thumb_avatar;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getAlias() {
        return alias;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setOpen_userid(String open_userid) {
        this.open_userid = open_userid;
    }
    public String getOpen_userid() {
        return open_userid;
    }

    public void setMain_department(int main_department) {
        this.main_department = main_department;
    }
    public int getMain_department() {
        return main_department;
    }

    public void setExtattr(Extattr extattr) {
        this.extattr = extattr;
    }
    public Extattr getExtattr() {
        return extattr;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }
    public String getQr_code() {
        return qr_code;
    }

    public void setExternal_position(String external_position) {
        this.external_position = external_position;
    }
    public String getExternal_position() {
        return external_position;
    }

    public void setExternal_profile(External_profile external_profile) {
        this.external_profile = external_profile;
    }
    public External_profile getExternal_profile() {
        return external_profile;
    }

    @Override
    public String toString() {
        return "GetApiEntity{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", department=" + Arrays.toString(department) +
                ", order=" + Arrays.toString(order) +
                ", position='" + position + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", biz_mail='" + biz_mail + '\'' +
                ", is_leader_in_dept=" + is_leader_in_dept +
                ", direct_leader=" + direct_leader +
                ", avatar='" + avatar + '\'' +
                ", thumb_avatar='" + thumb_avatar + '\'' +
                ", telephone='" + telephone + '\'' +
                ", alias='" + alias + '\'' +
                ", address='" + address + '\'' +
                ", open_userid='" + open_userid + '\'' +
                ", main_department=" + main_department +
                ", extattr=" + extattr +
                ", status=" + status +
                ", qr_code='" + qr_code + '\'' +
                ", external_position='" + external_position + '\'' +
                ", external_profile=" + external_profile +
                '}';
    }
}