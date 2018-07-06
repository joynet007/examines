package com.exservice.service;

import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.LearnCurrent;
import com.exservice.pojo.po.LearnQuestion;

/**
 * Created by liang on 2018/6/23.
 */
public interface LearnQuestionManager {


    /**
     * 查询这个用户着这个科目下总共学了多少题目
     * @param userid
     * @param subjectid
     * @return
     */
    long findcountLearnquestion(long userid,String subjectid);

    /**
     * 查询这个用户着这个科目下总共错（对）了多少题目
     * @param userid
     * @param subjectid
     * @param ismistake
     * @return
     */
    long findcountismistakeLearnquestion(long userid,String subjectid,String ismistake);


    /**
     * 根据用户编码和题目编码查询用户是否存在对象
     * @param userid
     * @param questionid
     * @return
     */
    LearnQuestion findLearnquestion(long userid, String questionid);




//    select * from ruankao.tb_learnquestion where pkid = (select max(pkid)  FROM ruankao.tb_learnquestion where ismistake='mistake.yes');

    /**
     *  查询当前用户在此科目下的，最近的一个错题
     * @param userid
     * @param subjectid
     * @return
     */
    LearnQuestion findLearnquestionMaxIsMitake(long userid,String subjectid);


    /**
     *  查询当前用户在此科目下的，最近的一个错题 的下一题目
     * @param userid
     * @param subjectid
     * @param pkid
     * @return
     */
    LearnQuestion findNextLearnquestionMaxIsMitake(long userid,String subjectid,long pkid);
    /**
     *  查询当前用户在此科目下的，最近的一个错题 的上一题目
     * @param userid
     * @param subjectid
     * @param pkid
     * @return
     */
    LearnQuestion findUpLearnquestionMaxIsMitake(long userid,String subjectid,long pkid);



    public MessageObject createLearnObj(LearnCurrent lc , LearnQuestion lq);


    public void save(LearnQuestion lq);


    public void update(LearnQuestion lq);


}
