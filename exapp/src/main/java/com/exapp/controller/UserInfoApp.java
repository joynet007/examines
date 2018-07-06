package com.exapp.controller;

import com.exapp.TokenManager;
import com.excomm.GsonUtil;
import com.excomm.MD5Util;
import com.excomm.StringUtil;
import com.excomm.SystemConfig;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.UserInfo;
import com.exservice.service.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/userinfo")
public class UserInfoApp {
    @Autowired
    UserInfoManager userInfoManager;

    private MessageObject mo = new MessageObject(SystemConfig.mess_succ,"执行成功");

    @RequestMapping(value="/dologin",method = RequestMethod.POST)
    public MessageObject dologin(@RequestParam(required = false) String usertel,
                                 @RequestParam(required = false) String userpassword,
                                 HttpServletRequest request){

        try {

            if(StringUtil.isEmpty(usertel) || StringUtil.isEmpty(userpassword)){
                mo.setMdesc("手机号，用户密码不能为空！");
                mo.setCode(SystemConfig.mess_succ);
                return mo;
            }
            userpassword = MD5Util.getMD5Code(userpassword);
            UserInfo userinfo = userInfoManager.findUser(usertel,userpassword);

//            userInfoManager.save(user);
            if(userinfo==null){
                mo.setMdesc("手机号或者用户密码错误");
                mo.setCode(SystemConfig.mess_succ);
                return mo;
            }else{
                userinfo.setTokenid(TokenManager.getInstance().createToken(userinfo.getUsertel()));
                userInfoManager.update(userinfo);
            }
            String content = GsonUtil.objTOjson(userinfo);
            mo.setMcontent(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mo;
    }

    /**
     * 创建用户信息
     * @param model
     * @return
     */
    @RequestMapping(value="/doregister", method = RequestMethod.POST)
    @ResponseBody
    public MessageObject doregister(@RequestParam(required = false) String usertel ,
                                        @RequestParam(required = false) String userpassword ,
                                        @RequestParam(required = false) String username ,
                                        Model model){
        System.out.println("usertel"+usertel);
        UserInfo userInfo =  userInfoManager.findByUsertel(usertel);
        MessageObject messageObject = new MessageObject(SystemConfig.mess_succ,"执行成功！");
        try {
            if(userInfo == null){
                userInfo = new UserInfo();
                userpassword = MD5Util.getMD5Code(userpassword);
                userInfo.setUserpassword( userpassword );
                userInfo.setUsertel(usertel.trim());
                userInfo.setCreatetime(System.currentTimeMillis());
                userInfo.setMstatus(SystemConfig.mstatus_normal);
                userInfo.setUsername(username);
                userInfo.setMsex(SystemConfig.sex_male);
                userInfo.setTokenid(TokenManager.getInstance().createToken(usertel.trim()));

                userInfo.setSortnumber(0);
                userInfo.setStudyquestioncount(0);

                userInfoManager.save(userInfo);

                userInfo = userInfoManager.findByUsertel(usertel.trim());

                String content = GsonUtil.objTOjson(userInfo);
//                System.out.println("***="+content);
                messageObject.setMcontent(content);

            }else{
                messageObject.setCode(SystemConfig.mess_failed);
                messageObject.setMdesc("您已经完成注册,请直接去登录！");
                return messageObject;
            }
            return messageObject;
        } catch (Exception e) {
            e.printStackTrace();
            messageObject.setCode(SystemConfig.mess_failed);
            messageObject.setMdesc("创建失败，执行异常！");
            return messageObject;
        }
    }

    /**
     * 用户退出
     * @param userid
     * @param tokenid
     * @return
     */
    @RequestMapping(value="/doout", method = RequestMethod.POST)
    @ResponseBody
    public MessageObject doout(@RequestParam(required = false) long userid,
                               @RequestParam(required = false) String tokenid){
//        System.out.println("tokenid"+tokenid);
        UserInfo userInfo =  userInfoManager.findByUserInfoBytokenid(userid,tokenid);
        MessageObject messageObject = new MessageObject(SystemConfig.mess_succ,"执行成功！");
        try {
            if(userInfo != null){
               userInfo.setTokenid("");
               userInfoManager.save(userInfo);
            }
            return messageObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageObject;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value="/findMaxStudyList", method = RequestMethod.POST)
    public  MessageObject findMaxStudyList(){

            MessageObject messageObject = new MessageObject(SystemConfig.mess_succ,"执行成功！");
            List<UserInfo> list = userInfoManager.findMaxStudyList();
            String content = GsonUtil.objTOjson(list);
            messageObject.setMcontent(content);
            return messageObject;
    }


}
