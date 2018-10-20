package com.exservice.service.impl;

import com.excomm.IDmanager;
import com.excomm.StringUtil;
import com.excomm.SystemConfig;
import com.exservice.dao.KnowledgepointMapper;
import com.exservice.dao.repository.CrudRepository;
import com.exservice.pojo.po.KnowledgePoint;
import com.exservice.pojo.po.KnowledgePointExplain;
import com.exservice.service.KnowledgepointManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liang on 2018/10/14.
 */
@Component
public class KnowledgepointImpl implements KnowledgepointManager {

    @Resource
    KnowledgepointMapper knowledgepointMapper;

    //这个是切入的
    @Resource
    CrudRepository<KnowledgePoint> crudRepository;

    @Resource
    CrudRepository<KnowledgePointExplain> kpeRepository;


    public void save(KnowledgePoint obj){
        crudRepository.save(obj);
    }

    public void save(String subjectid , String mtitle , String explain){
        KnowledgePoint kp = new KnowledgePoint();
        String id = IDmanager.createID();
        kp.setCreatetime(System.currentTimeMillis());
        kp.setKnowledgepointid(id);
        kp.setMstatus(SystemConfig.mstatus_normal);
        kp.setMtitle(mtitle);
        kp.setSubjectid(subjectid);
        crudRepository.save(kp);

        KnowledgePointExplain kpe = new KnowledgePointExplain();
        kpe.setExplain(explain);
        kpe.setKnowledgepointid(id);

        kpeRepository.save(kpe);


    }

    public List<KnowledgePoint> viewlist(int startNumber, int pageSize,String subjectid){
        HashMap<String,Object> map = new HashMap<>();
        map.put("startNumber",startNumber);
        map.put("pageSize",pageSize);
        map.put("subjectid",subjectid);
        return knowledgepointMapper.viewlist(map);

    }

    @Override
    public long selectallcount(String subjectid) {
        HashMap<String,String> map = new HashMap<>();
        map.put("subjectid",subjectid);
        return knowledgepointMapper.selectallcount(map);
    }
}
