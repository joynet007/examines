package com.exweb.controller;

import com.excomm.IDmanager;
import com.excomm.StringUtil;
import com.exservice.dao.KnowledgepointMapper;
import com.exservice.dao.repository.CrudRepository;
import com.exservice.pojo.po.Choicequestion;
import com.exservice.pojo.po.KnowledgePoint;
import com.exservice.service.KnowledgepointManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2018/7/8.
 */
@Controller
@RequestMapping("web/knowledgepoint/")
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
//    @RequestMapping(value="/add")
//    public String save(Model model, @RequestParam(required = false) String subjectid ,
//                      @RequestParam(required = false) String subjectName){
//
//        System.out.println(subjectid+"---"+subjectName+"---");
//
//        model.addAttribute("subjectid",subjectid);
//        model.addAttribute("subjectName",subjectName);
//
//        return "knowledgepoint/knowledgepoint-add";
//    }

    /**
     * 根据科目编号查询知识点
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<KnowledgePoint> viewlist(@RequestParam int pageNumber , @RequestParam int pageSize ,@RequestParam  String subjectid){
        System.out.println("viewlist=="+subjectid);
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
        System.out.println("selectallcount=="+subjectid);
        long k = knowledgepointManager.selectallcount(subjectid);
        System.out.println("--查出来的条数--"+k);
        return k;
    }




}
