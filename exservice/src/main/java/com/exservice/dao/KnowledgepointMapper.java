package com.exservice.dao;

import com.exservice.dao.repository.CrudRepository;
import com.exservice.dao.repository.CrudRepositoryImpl;
import com.exservice.pojo.po.KnowledgePoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liang on 2018/7/8.
 */

@Component
@Mapper
public interface KnowledgepointMapper {

    public static final String tableName = "tb_knowledgepoint";

    @Select("select * from " + tableName )
    public List<KnowledgePoint> viewlist();


}
