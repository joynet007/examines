package com.exservice.service;

import com.exservice.pojo.po.KnowledgePoint;

import java.util.List;

/**
 * Created by liang on 2018/10/14.
 */
public interface KnowledgepointManager {

   public void save(KnowledgePoint obj);

   public List<KnowledgePoint> viewlist(int startNumber, int pageSize,String subjectid);

   public long selectallcount(String subjectid);

   public void save(String subjectid , String mtitle , String explain);

}
