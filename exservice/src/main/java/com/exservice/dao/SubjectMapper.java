package com.exservice.dao;

import com.exservice.pojo.po.Subject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liang on 2018/5/13.
 */
@Component
@Mapper
public interface SubjectMapper {

    @Select(value = "select * from tb_subject" )
    List<Subject> queryList();

    @Select(value = "select * from tb_subject" )
    List<Subject> findAll();

    @Select(value = "select * from tb_subject where subjectid = #{subjectid}" )
    Subject findSubject(@Param("subjectid") String subjectid);

    @Select(value = "select * from tb_subject where mlevel = #{mlevel}" )
    List<Subject> findSubjectByLevel(int mlevel);

    @Select(value = "select * from tb_subject where parentid = #{parentid}" )
    List<Subject> findSubjectBypid(String parentid);

    @Insert( value="insert into tb_subject( subjectid , subjectname , mstatus , ctime , parentid , mlevel , selected ) " +
            "values (  #{subjectid} , #{subjectname} , #{mstatus} , #{ctime} , #{parentid} , #{mlevel} , #{selected} )")
    void save(Subject subject);

    @Delete(value="delete from tb_subject where subjectid = #{subjectid}")
    void delete(String subjectid);


}
