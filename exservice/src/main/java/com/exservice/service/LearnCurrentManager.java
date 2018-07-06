package com.exservice.service;

import com.exservice.pojo.po.LearnCurrent;

/**
 * Created by liang on 2018/5/14.
 */
public interface LearnCurrentManager {


    /**
     * 查询用户在这个科目下，
     * @param userid
     * @param subjectid
     * @return
     */
    long findLearnCurrentMcodeBySubjectid(long userid,String subjectid);

    /**
     * 查询用户在这个科目下的当前对象，
     * @param userid
     * @param subjectid
     * @return
     */
    LearnCurrent findLearnCurrentObjBySubjectid(long userid, String subjectid);




    /**
     * 查询用户在这个类型模拟年份下的当前学习的题目编号，
     * @param userid
     * @param moniname
     * @return
     */
    long findLearnCurrentMcodeByMoniname(long userid,String subjectid,String moniname);

    /**
     * 查询用户在这个类型模拟年份下的当前学习的 当前对象，
     * @param userid
     * @param moniname
     * @return
     */
    LearnCurrent findLearnCurrentObjByMoniname(long userid,String subjectid,String moniname);


//    select * from ruankao.tb_learnquestion where pkid = (select max(pkid)  FROM ruankao.tb_learnquestion where ismistake='mistake.yes');


    void save(LearnCurrent learnCurrent);

    void update(LearnCurrent learnCurrent);

    public LearnCurrent find(long pkid);


}
