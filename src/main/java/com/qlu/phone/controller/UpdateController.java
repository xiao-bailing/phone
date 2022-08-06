package com.qlu.phone.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qlu.phone.entity.GetApiEntity;
import com.qlu.phone.entity.GetApiMessage;
import com.qlu.phone.entity.ResultEntity;
import com.qlu.phone.entity.Updater;
import com.qlu.phone.service.GetApiMessageService;
import com.qlu.phone.service.UpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UpdateController {
    @Autowired
    GetApiMessageService getApiMessageService;

    @Autowired
    UpdateService updateService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 登陆验证,从数据库拿数据确保本人登陆
     */
    @PostMapping("/update")
    public String update(@RequestBody Updater updater) {
        UpdateWrapper<Updater> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("XH", updater.getXH())
                .eq("XM", updater.getXM())
                .eq("SFZ", updater.getSFZ());
        Map map = updateService.getMap(updateWrapper);
        if (map != null) {        //姓名,学号,身份证校验通过
            /**
             * 在确保用户登陆以后,调用api获取access_token
             * 注:access_token有效期是两个小时
             * 请求方式： GET（HTTPS）
             * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
             */
            String corpid = "wxd3c4dfce7fd17e58";
            String corpsecret = "8NWgApAtHPNK4bdQRW2RwJ3Ydl7zxYtXlEem07BJ7uk";
            ResponseEntity<ResultEntity> access_tokenEntity = restTemplate.getForEntity("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret, ResultEntity.class);
            String access_token = access_tokenEntity.getBody().getAccess_token();

            /**
             *调用手机号获取userid的api校验新填写的手机号是否已经存在
             *请求方式：POST（HTTPS）
             * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=ACCESS_TOKEN
             *
             * 下面的业务逻辑中,不管是否激活企业微信,如果用户填写的新手机号已经在企业微信中存在,都是不可以的(即手机号码要唯一)
             *
             * 而且如果不校验手机号是否已经存在,存在一个bug:当用户激活企业微信,但ta填写的新手机已经存在,那么在删除掉ta的成员信息以后无法为ta创建
             *                                        新的成员信息,导致查无此人!!!
             */
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
            headers.setContentType(type);
            HashMap<String, String> getUser = new HashMap<>();
            getUser.put("mobile",updater.getMobile());
            HttpEntity<HashMap<String, String>> getUserEntity = new HttpEntity<>(getUser, headers);
            ResponseEntity<ResultEntity> userEntity = restTemplate.postForEntity("https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=" + access_token, getUserEntity, ResultEntity.class);
            String user= userEntity.getBody().getUserid();
            if (user==null){  //说明新手机号唯一
                /**
                 * 	读取成员
                 * 	请求方式：GET（HTTPS）
                 * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID
                 */
                String userid = updater.getXH();
                ResponseEntity<GetApiEntity> getEntity = restTemplate.getForEntity("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + access_token + "&userid=" + userid, GetApiEntity.class);
                int status = getEntity.getBody().getStatus();
                String mobile = getEntity.getBody().getMobile();
                if (status == 1) {
                    /**
                     * status==1表示已经激活企业微信
                     * 已经激活企业微信的人不能使用更新成员api修改
                     * 若成员已激活企业微信，则需成员自行修改（此情况下该参数被忽略，但不会报错）
                     * 通过删除成员,读取成员,创建成员api修改
                     */

                    //把获取的成员信息保存到数据库
                    GetApiMessage getApiMessageDB = new GetApiMessage();
                    getApiMessageDB.setGetApiInfo(String.valueOf(getEntity));
                    getApiMessageDB.setUserid(getEntity.getBody().getUserid());
                    UpdateWrapper<GetApiMessage> userid1 = new UpdateWrapper<>();
                    userid1.eq("userid", getEntity.getBody().getUserid());
                    GetApiMessage one = getApiMessageService.getOne(userid1);
                    if (one == null) {
                        getApiMessageService.save(getApiMessageDB);
                    }
                    /**
                     * 调用删除成员api
                     * 请求方式：GET（HTTPS）
                     * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID
                     */
                    ResponseEntity<ResultEntity> deleteEntity = restTemplate.getForEntity("https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" + access_token + "&userid=" + userid, ResultEntity.class);

                    if (deleteEntity.getBody().getErrcode() == 0) {
                        /**
                         * 调用创建成员api
                         * 请求方式：POST（HTTPS）
                         * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN
                         */

                        HttpHeaders headers1 = new HttpHeaders();
                        MediaType type1 = MediaType.parseMediaType("application/json;charset=UTF-8");
                        headers.setContentType(type1);
                        //使用fastJSON把对象转为string
                        String jsonString = JSON.toJSONString(getEntity.getBody());
                        //使用fastJSON把string转为map集合
                        HashMap<String, String> createMap = JSON.parseObject(jsonString, HashMap.class);
                        //修改手机号为前端传过来用户需要的
                        createMap.put("mobile", updater.getMobile());
                        //删掉多余的元素(errcode,errmsg)
                        createMap.remove("errcode");
                        createMap.remove("errmsg");
                        HttpEntity<HashMap<String, String>> creatEntity = new HttpEntity<>(createMap, headers1);
                        //调用创建成员api
                        ResponseEntity<ResultEntity> createEntity = restTemplate.postForEntity("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + access_token, creatEntity, ResultEntity.class);
                        if (createEntity.getBody().getErrcode() == 0) {
//                        创建成员成功就删掉数据库里面的信息
                            getApiMessageService.removeById(userid);
                            return "激活企业微信用户成功修改手机号";
                        }
                        return "激活企业微信用户修改手机号失败";
                    }
                } else if (status == 4) {
                    /**
                     * status==4表示未激活企业微信
                     * 那么就可以通过更新成员api修改手机号
                     * 调用更新成员api
                     */


                    HttpHeaders headers2 = new HttpHeaders();
                    MediaType type2 = MediaType.parseMediaType("application/json;charset=UTF-8");
                    headers.setContentType(type2);

                    //使用fastJSON把对象转为string
                    String jsonString = JSON.toJSONString(getEntity.getBody());
                    //使用fastJSON把string转为map集合
                    HashMap<String, String> updateMap = JSON.parseObject(jsonString, HashMap.class);

                    //删掉多余的元素(errcode,errmsg,)
                    updateMap.remove("errcode");
                    updateMap.remove("errmsg");
                    updateMap.remove("open_userid");

                    updateMap.put("mobile", updater.getMobile());
                    HttpEntity<HashMap<String, String>> request = new HttpEntity<>(updateMap, headers2);
                    ResponseEntity<ResultEntity> responseEntity = restTemplate.postForEntity("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + access_token, request, ResultEntity.class);
                    int errcode = responseEntity.getBody().getErrcode();
                    if (errcode == 0) {
                        return "未激活企业微信用户成功修改手机号";
                    }
                    return "未激活企业微信用户修改手机号失败";
                }
            }else if(user!=null){
                return "填写的手机号码已存在";
            }
        }
        return "学号或身份证或姓名有误,请仔细检查~";
    }


}
