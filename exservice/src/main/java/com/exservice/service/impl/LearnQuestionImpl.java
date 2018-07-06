package com.exservice.service.impl;

import com.excomm.SystemConfig;
import com.exservice.dao.LearnQuestionMapper;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.LearnCurrent;
import com.exservice.pojo.po.LearnQuestion;
import com.exservice.service.LearnCurrentManager;
import com.exservice.service.LearnQuestionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by liang on 2018/6/23.
 */

@Component
public class LearnQuestionImpl implements LearnQuestionManager {


    @Resource
    LearnQuestionMapper learnQuestionMapper;

    @Resource
    LearnCurrentManager learnCurrentManager;


    @Override
    public long findcountLearnquestion(long userid, String subjectid) {
       return learnQuestionMapper.findcountLearnquestion(userid,subjectid);
    }

    @Override
    public long findcountismistakeLearnquestion(long userid, String subjectid, String ismistake) {
        return learnQuestionMapper.findcountismistakeLearnquestion(userid,subjectid,ismistake);
    }

    @Override
    public LearnQuestion findLearnquestion(long userid, String questionid) {
        return learnQuestionMapper.findLearnquestion(userid,questionid);
    }

    @Override
    public LearnQuestion findLearnquestionMaxIsMitake(long userid, String subjectid) {
        return learnQuestionMapper.findLearnquestionMaxIsMitake(userid,subjectid);
    }

    @Override
    public LearnQuestion findNextLearnquestionMaxIsMitake(long userid, String subjectid, long pkid) {
        return learnQuestionMapper.findNextLearnquestionMaxIsMitake(userid,subjectid,pkid);
    }

    @Override
    public LearnQuestion findUpLearnquestionMaxIsMitake(long userid, String subjectid, long pkid) {
        return learnQuestionMapper.findUpLearnquestionMaxIsMitake(userid,subjectid,pkid);
    }


    MessageObject mo;

    @Transactional
    @Override
    public MessageObject createLearnObj(LearnCurrent lc , LearnQuestion lq){

        mo = new MessageObject();
        try{
            LearnCurrent learnCurrent = learnCurrentManager.findLearnCurrentObjBySubjectid(lc.userid,lc.subjectid);

            LearnQuestion learnQuestion = learnQuestionMapper.findLearnquestion(lq.userid,lq.getQuestionid());


            if(learnCurrent!=null){
                learnCurrentManager.save(lc);
            }else{
               // learnCurrentManager.save(lc);
            }

            if(learnQuestion!=null){
                learnQuestionMapper.save(lq);
            }else{
                learnQuestionMapper.update(lq);
            }

        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败！");
            e.printStackTrace();
        }
        return mo;

    }

    @Override
    public void save(LearnQuestion lq){
        LearnQuestion learnQuestion = learnQuestionMapper.findLearnquestion(lq.getUserid(),lq.getQuestionid());
        if(learnQuestion == null){
            learnQuestionMapper.save(lq);
        }
    }

    @Override
    public void update(LearnQuestion lq){
        learnQuestionMapper.update(lq);
    }

}
