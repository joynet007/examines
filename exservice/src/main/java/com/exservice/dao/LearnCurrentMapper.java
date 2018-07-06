package com.exservice.dao;

import com.exservice.pojo.po.LearnCurrent;
import com.exservice.pojo.po.LearnQuestion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by liang on 2018/5/14.
 */
@Component
@Mapper
public interface LearnCurrentMapper {

    /**
     * 查询用户在这个科目下，
     * @param userid
     * @param subjectid
     * @return
     */
    @Select(value = "select mcode from tb_learncurrent where userid = #{userid} and subjectid = #{subjectid}  " )
    long findLearnCurrentMcodeBySubjectid(long userid, String subjectid);

    /**
     * 查询用户在这个科目下的当前对象，
     * @param userid
     * @param subjectid
     * @return
     */
    @Select(value = "select * from tb_learncurrent where userid = #{userid} and subjectid = #{subjectid} " )
    LearnCurrent findLearnCurrentObjBySubjectid(@Param("userid") long userid,@Param("subjectid") String subjectid);




    /**
     * 查询用户在这个类型模拟年份下的当前学习的题目编号，
     * @param userid
     * @param moniname
     * @return
     */
    @Select(value = "select mcode from tb_learncurrent where userid = #{userid} and subjectid = #{subjectid} and moniname = #{moniname} " )
    long findLearnCurrentMcodeByMoniname(@Param("userid") long userid, @Param("subjectid") String subjectid,@Param("moniname")  String moniname);

    /**
     * 查询用户在这个类型模拟年份下的当前学习的 当前对象，
     * @param userid
     * @param moniname
     * @return
     */
    @Select(value = "select * from tb_learncurrent where userid = #{userid} and subjectid = #{subjectid} and moniname = #{moniname}" )
    LearnCurrent findLearnCurrentObjByMoniname(@Param("userid") long userid, @Param("subjectid") String subjectid,@Param("moniname")  String moniname);


    /**
     * 创建对象
     * @param learnCurrent
     */
    @Insert("insert into tb_learncurrent(  userid , subjectid , mcode , createtime , moniname ) values (   #{userid} , #{subjectid} , #{mcode} , #{createtime} , #{moniname} )")
    void save(LearnCurrent learnCurrent);


    @Update("update tb_learncurrent set  pkid = #{pkid} ,  userid = #{userid} ,  subjectid = #{subjectid} ,  mcode = #{mcode} ,  createtime = #{createtime} ,  moniname = #{moniname} where pkid= #{pkid}")
    void update(LearnCurrent learnCurrent);

    @Select("select * from tb_learncurrent where pkid = #{pkid}")
    LearnCurrent find(@Param("pkid") long pkid);


}
