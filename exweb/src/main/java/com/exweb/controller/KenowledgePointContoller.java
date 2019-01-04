package com.exweb.controller;

import com.excomm.IDmanager;
import com.excomm.StringUtil;
import com.excomm.SystemConfig;
import com.exservice.dao.KnowledgepointMapper;
import com.exservice.dao.repository.CrudRepository;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.Choicequestion;
import com.exservice.pojo.po.KnowledgePoint;
import com.exservice.service.KnowledgepointManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2018/7/8.
 */
@Controller
@RequestMapping("web/knowledgepoint")
public class KenowledgePointContoller {

    @Resource
    KnowledgepointManager knowledgepointManager;

    @Value("${spring.datasource.url}")
    private String key;

    /**
     * 进入的开始页
     * @return
     */
    @RequestMapping(value="/startpage")
    public String viewfirstpage(){
        System.out.println(key);
        return "knowledgepoint/knowledgepoint-list";
    }

    @RequestMapping(value="/add")
    public String add(){
        return "knowledgepoint/knowledgepoint-add";
    }

    @RequestMapping(value="/save")
    @ResponseBody
    public MessageObject save(Model model, @RequestParam(required = false) String subjectid ,
                       @RequestParam(required = false) String mtitle,
                              @RequestParam(required = false) String explain){

        System.out.println(subjectid+"--"+mtitle+"--"+explain);

        MessageObject mo = new MessageObject();

        try{
            if(StringUtil.isEmpty(subjectid)){
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("科目编码不能为空！");
                return mo;
            }
            if(StringUtil.isEmpty(mtitle)){
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("知识点标题不能为空！");
                return mo;
            }
            if(StringUtil.isEmpty(explain)){
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("知识点详解不能为空！");
                return mo;
            }

            knowledgepointManager.save(subjectid,mtitle,explain);
        }catch(Exception e){
            e.printStackTrace();
        }

        return mo;
    }
    @RequestMapping(value="/delete/{knowledgepointid}")
    @ResponseBody
    public MessageObject delete(Model model, @PathVariable String knowledgepointid ){
        System.out.println(knowledgepointid+"--");
        MessageObject mo = new MessageObject();
        try{
            if(StringUtil.isEmpty(knowledgepointid)){
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("科目编码不能为空！");
                return mo;
            }
            knowledgepointManager.delete(knowledgepointid);
        }catch(Exception e){
            e.printStackTrace();
        }

        return mo;
    }

    /**
     * 根据科目编号查询知识点
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<KnowledgePoint> viewlist(@RequestParam int pageNumber ,
                                         @RequestParam int pageSize ,
                                         @RequestParam  String subjectid){
        List<KnowledgePoint> list = knowledgepointManager.viewlist((pageNumber-1)*pageSize,pageSize,subjectid);
        return list;
    }

    /**
     * 根据科目编号知识点
     * @return
     */
    @RequestMapping(value="/selectallcount")
    @ResponseBody
    public long selectallcount(@RequestParam  String subjectid){

        long k = knowledgepointManager.selectallcount(subjectid);
        System.out.println("kkk=== 的个数"+k);

        return k;
    }




}
