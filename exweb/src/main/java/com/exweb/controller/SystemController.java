package com.exweb.controller;

import com.excomm.MD5Util;
import com.excomm.StringUtil;
import com.excomm.SystemConfig;
import com.exservice.pojo.po.UserInfo;
import com.exservice.service.UserInfoManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 袁亮
 * @data 2017年7月10日
 *
 */
@Controller
@RequestMapping(value="/sys")
public class SystemController {

    @Resource
    UserInfoManager userInfoManager;

    @RequestMapping(value="/dologin")
    public ModelAndView dologin(@RequestParam(required = false) String usertel ,
                                @RequestParam(required = false) String userpassword ,
                                HttpServletRequest request){
//        System.out.println("--"+usertel+"--"+userpassword);
        ModelAndView modelAndView = new ModelAndView();
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute(SystemConfig.Session_key) != null){
                modelAndView.setViewName("sys/main");
                return modelAndView;
            }
            if(StringUtil.isEmpty(usertel) || StringUtil.isEmpty(userpassword))
            {
                modelAndView.setViewName("index");
                return modelAndView;
            }
            UserInfo user =  userInfoManager.findByUsertel(usertel);
            if(null == user)
            {

                modelAndView.setViewName("index");
                return modelAndView;
            }

            //变成md5 在查询
            userpassword = MD5Util.getMD5Code(userpassword);

            user = userInfoManager.findUser(usertel,userpassword);
            if(user==null){
                modelAndView.setViewName("index");
                return modelAndView;
            }

            if(SystemConfig.mstatus_stop.equals(user.getMstatus())){
                modelAndView.setViewName("index");
                return modelAndView;
            }
            session.setAttribute(SystemConfig.Session_key, user);
            modelAndView.setViewName("sys/main");
            return modelAndView;

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("index");
            return modelAndView;
        }
    }

    /**
     * 系统启动访问
     * http://ip:port
     * 直接进入此方法
     * @return
     */
    @RequestMapping(value="/outlogin")
    public String outlogin(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }



}