package com.exservice.service.impl;

import com.excomm.StringUtil;
import com.exservice.dao.KnowledgepointMapper;
import com.exservice.dao.repository.CrudRepository;
import com.exservice.pojo.po.KnowledgePoint;
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


    public void save(KnowledgePoint obj){
        crudRepository.save(obj);
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
