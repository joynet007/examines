package com.exweb.controller;

import com.excomm.StringUtil;
import com.exservice.dao.KnowledgepointMapper;
import com.exservice.dao.repository.CrudRepository;
import com.exservice.pojo.po.KnowledgePoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    KnowledgepointMapper knowledgepointMapper;

    @Resource
    CrudRepository<KnowledgePoint> crudRepository;

    @Value("${spring.datasource.url}")
    private String key;

    /**
     * 进入的开始页
     * @return
     */
    @RequestMapping(value="/startpage")
    public String viewfirstpage(){

        System.out.println(key);
        KnowledgePoint knowledgePoint = new KnowledgePoint();
        knowledgePoint.setCreatetime(System.currentTimeMillis());
        knowledgePoint.setMstatus("mstatus.stop");
        knowledgePoint.setMtitle("--biaoti--");
        knowledgePoint.setKnowledgepointid(1);

        crudRepository.save(knowledgePoint);
        crudRepository.delete(knowledgePoint);
        crudRepository.findOne(knowledgePoint);

        return "knowledgepoint/knowledgepoint-list";
    }

    @RequestMapping(value="/add")
    public String add(Model model, @RequestParam(required = false) String subjectid ,
                      @RequestParam(required = false) String subjectName){

        System.out.println(subjectid+"---"+subjectName+"---");

        model.addAttribute("subjectid",subjectid);
        model.addAttribute("subjectName",subjectName);

        return "knowledgepoint/knowledgepoint-add";
    }

    /**
     * 根据科目编号、年份查询题目
     * @param subjectid
     * @param moniname
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<KnowledgePoint> viewlist(@RequestParam(required = false) String subjectid ,
                                         @RequestParam(required = false) String moniname){

        List<KnowledgePoint> list = new ArrayList<KnowledgePoint>();
        if(StringUtil.isEmpty(subjectid)){
            return list;
        }
        if(StringUtil.isEmpty(moniname)){
            return list;
        }
        list= (List<KnowledgePoint>) knowledgepointMapper.viewlist();
        return list;
    }




}
