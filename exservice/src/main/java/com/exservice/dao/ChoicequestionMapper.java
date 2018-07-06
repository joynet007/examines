package com.exservice.dao;

import com.exservice.pojo.po.Choicequestion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liang on 2018/5/13.
 */

@Component
@Mapper
public interface ChoicequestionMapper {

    @Select(value = "select * from tb_choicequestion where questionid = #{questionid}" )
    Choicequestion findChoiceQuestion(String questionid);

    /**
     *
     * @param subjectid 这里一定是第三个 项目编号
     * @param mcode
     * 根据科目、题目编码查询题目
     * @return
     */
    @Select(value = "select * from tb_choicequestion where sublevel3 = #{subjectid}  and mcode = #{mcode} " )
    Choicequestion findSubjectChoiceQuestion(String subjectid, long mcode);


    /**
     * 查询当前的科目、当前模拟题下的最大题目编码
     * 新增题目的时候题目编码自动增加一
     * @param subjectid
     * @return
     */
    @Select(value = "select max(mcode) from tb_choicequestion  where sublevel3= #{subjectid} " )
    long findSubjectMaxChoicequestion(String subjectid);


    /**
     *
     * @param subjectid
     * @param moniname
     * @param mcode
     * 根据科目、模拟题、题目编码查询题目
     * @return
     */
    @Select(value = "select * from tb_choicequestion where sublevel1 = #{subjectid} and  moniname=#{moniname} and mcode =#{mcode}" )
    Choicequestion findMoniChoiceQuestion(@Param("subjectid") String subjectid, @Param("moniname") String moniname,@Param("mcode")  long mcode);


    /**
     * 查询当前的科目、当前模拟题下的最大题目编码
     * 新增题目的时候题目编码自动增加一
     * @param subjectid
     * @param moniname
     * @return
     */
    @Select(value = "select max(mcode) from tb_choicequestion   where sublevel1 = #{subjectid} and moniname=#{moniname} " )
    long findMaxMoniChoicequestion(@Param("subjectid") String subjectid, @Param("moniname") String moniname);


    /**
     * 查询当前的科目下的所有习题总数
     * 新增题目的时候题目编码自动增加一
     * @param subjectid
     * @return
     */
    @Select(value = "select count(*) from tb_choicequestion   where sublevel1 = #{subjectid} " )
    long findChoicequestionCount(String subjectid);


    /**
     * 根据时间倒序排列
     * @return
     */
    @Select(value = "select * from tb_choicequestion where sublevel1 = #{subjectid} and moniname=#{moniname} order by ctime desc" )
    List<Choicequestion> findallobjesOne(String subjectid, String moniname);

    /**
     * 根据时间倒序排列
     * @return
     */

    @Select({"<script>",
            "SELECT * FROM tb_choicequestion",
            "WHERE 1=1",
            "<if test = 'moniname!=null'>",
            "AND moniname = #{moniname}",
            "</if>",
            "order by ctime desc",
            "</script>"})
    List<Choicequestion> findallobjes();


    @Delete(value="delete from tb_choicequestion where questionid = #{questionid}")
    void delete(String questionid);

    /**
     * 查询总的个数
     * @return
     */

    @SelectProvider(type = ChoicequestionProvider.class, method = "findcount")
    long findcount(HashMap<String, Object> map);

    @SelectProvider(type = ChoicequestionProvider.class, method = "queryList")
    List<Choicequestion> queryList(HashMap<String, Object> map);


    @Insert("insert into tb_choicequestion( questionid , name , answera , answerb , answerc , answerd , realanswer , ctime , sublevel1 , sublevel2 , sublevel3 , moniname , mcode ) values (  #{questionid} , #{name} , #{answera} , #{answerb} , #{answerc} , #{answerd} , #{realanswer} , #{ctime} , #{sublevel1} , #{sublevel2} , #{sublevel3} , #{moniname} , #{mcode} )")
    void save(Choicequestion cq);

    @Insert("update tb_choicequestion set  questionid = #{questionid} ,  name = #{name} ,  answera = #{answera} ,  answerb = #{answerb} ,  answerc = #{answerc} ,  answerd = #{answerd} ,  realanswer = #{realanswer} ,  ctime = #{ctime} ,  sublevel1 = #{sublevel1} ,  sublevel2 = #{sublevel2} ,  sublevel3 = #{sublevel3} ,  moniname = #{moniname} ,  mcode = #{mcode} where questionid= #{questionid}")
    void update(Choicequestion cq);
    
}
