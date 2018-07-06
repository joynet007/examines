package com.exservice.service.impl;

import com.exservice.dao.ChoicequestionExplainMapper;
import com.exservice.pojo.po.ChoicequestionExplain;
import com.exservice.service.ChoicequestionExplainManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by liang on 2018/5/14.
 */
@Component
public class ChoicequestionExplainImpl implements ChoicequestionExplainManager {

    @Resource
    ChoicequestionExplainMapper choicequestionExplainMapper;

    @Override
    public void delete(String choicequestionid) {
        choicequestionExplainMapper.delete(choicequestionid);
    }

    @Override
    public ChoicequestionExplain findChoiceQuestionExplain(String choicequestionid) {
        return choicequestionExplainMapper.findChoiceQuestionExplain(choicequestionid);
    }

    @Override
    public void save(ChoicequestionExplain cqe) {
        choicequestionExplainMapper.save(cqe);
    }

    @Override
    public void update(ChoicequestionExplain cqe) {
        choicequestionExplainMapper.update(cqe);
    }
}
