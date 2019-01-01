package com.exweb.controller;

import com.excomm.MD5Util;
import com.excomm.SystemConfig;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.UserInfo;
import com.exservice.service.UserInfoManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liang on 2017/3/25.
 * @author yuan_liang
 */

@Controller
@RequestMapping("/web/userinfo")
public class UserInfoController {

    @Resource
    UserInfoManager userInfoManager;
    /**
     * 开始页面
     * @return
     */
    @RequestMapping(value="/startpage" , method = RequestMethod.GET)
    public String startpage(){
        return "userinfo/userinfolist";
    }


    /**
     * 新增
     * @return
     */
    @RequestMapping(value="/add" , method = RequestMethod.GET)
    public String add(){
        return "userinfo/userino-add";
    }

    /**
     * 查询用户信息
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/finduserinfo" , method = RequestMethod.GET)
    @ResponseBody
    public UserInfo findUserInfo(Model model, HttpSession session){
        UserInfo userInfo =null;
        try {
            if(null != session.getAttribute(SystemConfig.Session_key))
            {
                userInfo = (UserInfo)session.getAttribute(SystemConfig.Session_key);
            }
            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return userInfo;
        }
    }

    /**
     * 创建用户信息
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/createuserinfo", method = RequestMethod.POST)
    @ResponseBody
    public MessageObject createUserinfo(@RequestParam(required = false) String usertel ,
                                        @RequestParam(required = false) String username ,
                                        @RequestParam(required = false) String userpassword ,
                                        @RequestParam(required = false) String usertype ,
                                        @RequestParam(required = false) String msex ,
                                        @RequestParam(required = false) String mstatus ,
                                        Model model, HttpSession session){
        UserInfo userInfo =  userInfoManager.findByUsertel(usertel);
        MessageObject messageObject = new MessageObject(SystemConfig.mess_succ,"执行成功！");
        try {
            if(userInfo == null){

                userInfo = new UserInfo();
                userpassword = MD5Util.getMD5Code(userpassword);
                userInfo.setUserpassword(  MD5Util.getMD5Code(userpassword) );
                userInfo.setUsertype( usertype );
                userInfo.setUsertel(usertel);
                userInfo.setCreatetime(System.currentTimeMillis());
                userInfo.setMstatus(mstatus);
                userInfo.setUsername(username);
                userInfo.setMsex(msex);
                userInfo.setStudyquestioncount(0);
                userInfoManager.save(userInfo);
            }else{
                messageObject.setCode(SystemConfig.mess_failed);
                messageObject.setMdesc("创建失败，存在相同的对象！");
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
     * 查询用户列表
     * @return
     */
    @RequestMapping(value="/userinfolist")
    @ResponseBody
    public List<UserInfo> list(@RequestParam int pageNumber ,  @RequestParam int pageSize ){
        List<UserInfo> userInfos = (List<UserInfo>)userInfoManager.queryList((pageNumber-1)*pageSize,pageSize);
        return userInfos;
    }

    @RequestMapping(value = "/selectallcount")
    @ResponseBody
    public long selectallcount() {
        long allcount =0l;
        try {
            allcount = userInfoManager.findcount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allcount;
    }





    @RequestMapping(method= RequestMethod.GET , value="/del/{usertel}")
    @ResponseBody
    public void del(@PathVariable String usertel ){

        UserInfo userInfo = userInfoManager.findByUsertel(usertel);
        if(userInfo != null){
            userInfoManager.delete(userInfo);
        }
    }


    /**
     * 更改用户的状态（停用或者启用）
     * @param usertel
     */
    @RequestMapping(method= RequestMethod.GET , value="/upatestatus/{usertel}")
    @ResponseBody
    public void upatestatus(@PathVariable String usertel ){

        UserInfo userInfo = userInfoManager.findByUsertel(usertel);

        if(userInfo != null){
            if(SystemConfig.mstatus_normal.equals(userInfo.getMstatus())){
                userInfo.setMstatus(SystemConfig.mstatus_stop);
            }else{
                userInfo.setMstatus(SystemConfig.mstatus_normal);
            }

            userInfoManager.update(userInfo);
        }
    }

}
