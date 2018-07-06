package com.exservice.service.impl;

import com.exservice.dao.LearnCurrentMapper;
import com.exservice.pojo.po.LearnCurrent;
import com.exservice.service.LearnCurrentManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by liang on 2018/5/14.
 */
@Component
public class LearnCurrentImpl implements LearnCurrentManager {


    @Resource
    LearnCurrentMapper learnCurrentMapper;


    @Override
    public long findLearnCurrentMcodeBySubjectid(long userid, String subjectid) {
        return learnCurrentMapper.findLearnCurrentMcodeBySubjectid(userid,subjectid);
    }

    @Override
    public LearnCurrent findLearnCurrentObjBySubjectid(long userid, String subjectid) {
        return learnCurrentMapper.findLearnCurrentObjBySubjectid(userid,subjectid);
    }

    @Override
    public long findLearnCurrentMcodeByMoniname(long userid, String subjectid, String moniname) {
        return learnCurrentMapper.findLearnCurrentMcodeByMoniname(userid,subjectid,moniname);
    }

    @Override
    public LearnCurrent findLearnCurrentObjByMoniname(long userid, String subjectid, String moniname) {
        return learnCurrentMapper.findLearnCurrentObjByMoniname(userid,subjectid,moniname);
    }

    @Override
    public void save(LearnCurrent learnCurrent) {
        learnCurrentMapper.save(learnCurrent);
    }


    @Override
    public void update(LearnCurrent learnCurrent){
        learnCurrentMapper.update(learnCurrent);
    }

    @Override
    public LearnCurrent find(long pkid){
        return learnCurrentMapper.find(pkid);
    }

}
