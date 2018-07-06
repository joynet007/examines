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
public interface LearnQuestionMapper {

    /**
     * 查询这个用户着这个科目下总共学了多少题目
     * @param userid
     * @param subjectid
     * @return
     */
    @Select(value = "select count(*) from tb_learnquestion where userid = #{userid} and subjectid = #{subjectid} " )
    long findcountLearnquestion(@Param("userid") long userid, @Param("subjectid") String subjectid);

    /**
     * 查询这个用户着这个科目下总共错（对）了多少题目
     * @param userid
     * @param subjectid
     * @param ismistake
     * @return
     */
    @Select(value = "select count(*) from tb_learnquestion where userid = #{userid} and subjectid = #{subjectid} and ismistake = #{ismistake} " )
    long findcountismistakeLearnquestion(@Param("userid") long userid, @Param("subjectid") String subjectid,@Param("ismistake") String ismistake);


    /**
     * 根据用户编码和题目编码查询用户是否存在对象
     * @param userid
     * @param questionid
     * @return
     */
    @Select(value = "select * from tb_learnquestion where userid = #{userid} and questionid = #{questionid} " )
    LearnQuestion findLearnquestion(@Param("userid") long userid,@Param("questionid") String questionid);




//    select * from ruankao.tb_learnquestion where pkid = (select max(pkid)  FROM ruankao.tb_learnquestion where ismistake='mistake.yes');

    /**
     *  查询当前用户在此科目下的，最近的一个错题
     * @param userid
     * @param subjectid
     * @return
     */
    @Select(value = "select * from tb_learnquestion where pkid = " +
            "(select max(pkid)  FROM tb_learnquestion where ismistake='mistake.yes' and userid = #{userid} and subjectid = #{subjectid})" )
    LearnQuestion findLearnquestionMaxIsMitake(@Param("userid") long userid, @Param("subjectid") String subjectid);


    /**
     *  查询当前用户在此科目下的，最近的一个错题 的下一题目
     * @param userid
     * @param subjectid
     * @param pkid
     * @return
     */
    @Select(value = "select * from tb_learnquestion a where a.pkid = (select max(pkid)  FROM tb_learnquestion t where t.ismistake='mistake.yes' and t.userid = #{userid} and t.subjectid = #{subjectid} and t.pkid < #{pkid})" )
    LearnQuestion findNextLearnquestionMaxIsMitake(@Param("userid") long userid, @Param("subjectid") String subjectid,@Param("pkid") long pkid);
    /**
     *  查询当前用户在此科目下的，最近的一个错题 的上一题目
     * @param userid
     * @param subjectid
     * @param pkid
     * @return
     */
    @Select(value = "select * from tb_learnquestion a where a.pkid = (select min(pkid)  FROM tb_learnquestion t where t.ismistake='mistake.yes' and t.userid = #{userid} and t.subjectid = #{subjectid} and t.pkid > #{pkid})" )
    LearnQuestion findUpLearnquestionMaxIsMitake(@Param("userid") long userid, @Param("subjectid") String subjectid,@Param("pkid") long pkid);


    /**
     * 创建对象
     * @param learnQuestion
     */
    @Insert("insert into tb_learnquestion( userid , questionid , createtime ,  subjectid , ismistake , moniname ) values (  #{userid} , #{questionid} , #{createtime} , #{subjectid} , #{ismistake} , #{moniname} )")
    void save(LearnQuestion learnQuestion);

    @Update("update tb_learnquestion set  userid = #{userid} ,  questionid = #{questionid} ,  createtime = #{createtime} ,  pkid = #{pkid} ,  subjectid = #{subjectid} ,  ismistake = #{ismistake} ,  moniname = #{moniname} where  userid = #{userid} and questionid = #{questionid} ")
    void update(LearnQuestion learnQuestion);

    @Select("select * from tb_learnquestion where pkid = #{pkid}")
    LearnQuestion find(@Param("pkid") long pkid);

}
