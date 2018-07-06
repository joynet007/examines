package com.exservice.dao;

import com.exservice.pojo.po.MoniYear;
import com.exservice.pojo.po.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liang on 2018/5/14.
 */

@Component
@Mapper
public interface MoniYearMapper {

    @Select(value = "select * from tb_moniyear order by mindex desc" )
    List<MoniYear> findall();

    @Select(value = "select * from tb_moniyear where moniid = #{moniid}" )
    Subject findMoniYear(String moniid);
    
}
