package com.exservice.service;

import com.exservice.pojo.po.ChoicequestionExplain;

/**
 * Created by liang on 2018/5/14.
 */
public interface ChoicequestionExplainManager {


    ChoicequestionExplain findChoiceQuestionExplain(String choicequestionid);

    void delete(String choicequestionid);

    void save(ChoicequestionExplain cqe);

    void update(ChoicequestionExplain cqe);
}
