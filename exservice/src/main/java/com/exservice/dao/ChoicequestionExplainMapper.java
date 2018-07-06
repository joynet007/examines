package com.exservice.dao;

import com.exservice.pojo.po.ChoicequestionExplain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by liang on 2018/5/13.
 */

@Component
@Mapper
public interface ChoicequestionExplainMapper {

    @Select(value = "select * from tb_choicequestion_explain where questionid = #{questionid}" )
    ChoicequestionExplain findChoiceQuestionExplain(String questionid);

    @Delete(value="delete from tb_choicequestion_explain where questionid = #{choicequestionid}")
    void delete(String choicequestionid);

    @Insert("insert into tb_choicequestion_explain( questionid , mexplain ) values (  #{questionid} , #{mexplain} )")
    void save(ChoicequestionExplain choicequestionExplain);

    @Update("update tb_choicequestion_explain set  mexplain = #{mexplain} where questionid= #{questionid}")
    void update(ChoicequestionExplain choicequestionExplain);
}
