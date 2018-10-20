package com.exservice.dao;

import com.exservice.pojo.po.KnowledgePoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liang on 2018/7/8.
 */

@Component
@Mapper
public interface KnowledgepointMapper{

    public static final String tableName = "tb_knowledgepoint";


    @SelectProvider(type = KnowledgepointProvider.class, method = "queryList")
    List<KnowledgePoint> viewlist(HashMap<String,Object> map);

    @SelectProvider(type = KnowledgepointProvider.class, method = "selectallcount")
    long selectallcount(HashMap<String,String> map);


}
