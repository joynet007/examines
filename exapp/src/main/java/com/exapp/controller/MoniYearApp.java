package com.exapp.controller;

import com.excomm.GsonUtil;
import com.excomm.SystemConfig;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.MoniYear;
import com.exservice.service.MoniYearManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/moniyear")
public class MoniYearApp {
    @Autowired
    MoniYearManager moniYearManager;

    /**
     * 查看某一个资源
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public MessageObject viewlist(){
        MessageObject mo = new MessageObject(SystemConfig.mess_succ,"登录成功");
        List<MoniYear> list =(List<MoniYear>)moniYearManager.findAll();
        if(list!=null){
            String content = GsonUtil.objTOjson(list);
            mo.setMcontent(content);
        }

        return mo;
    }


}
