package com.exservice.service;

import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.Choicequestion;
import com.exservice.pojo.po.ChoicequestionExplain;

import java.util.List;

/**
 * Created by liang on 2018/5/14.
 */
public interface ChoicequestionManager {

    List<Choicequestion> findallobjes();
    List<Choicequestion> findallobjes(String subjectid, String moniname);

    long findMaxMoniChoicequestion(String sublevel1, String moniname);

    long findSubjectMaxChoicequestion(String sublevel3);

    void docreate();

    Choicequestion findChoiceQuestion(String choicequestionid);

    MessageObject dodel(Choicequestion cq, ChoicequestionExplain ce);

    MessageObject docreate(Choicequestion cq, ChoicequestionExplain ce);

    MessageObject doupdate(Choicequestion cq, ChoicequestionExplain ce);

    /**
     * 查询总条数
     * @return
     */
    long findcount(String moniyear, String sublevel1, String sublevel2, String sublevel3);

    /**
     * 分页查询
     * @return
     */
    List<Choicequestion> queryList(int startNumber, int pageSize, String moniname, String sublevel1, String sublevel2, String sublevel3);



//    @Query(value = "select * from tb_choicequestion where sublevel3 = ?1  and mcode =?2 " ,nativeQuery=true)
    Choicequestion findSubjectChoiceQuestion(String subjectid, long mcode);


    Choicequestion findMoniChoiceQuestion(String subjectid,String moniname,long maxmonicode);


    long  findChoicequestionCount(String subjectid);


}
