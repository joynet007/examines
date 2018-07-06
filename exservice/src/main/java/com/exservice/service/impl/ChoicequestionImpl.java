package com.exservice.service.impl;

import com.excomm.SystemConfig;
import com.exservice.dao.ChoicequestionMapper;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.Choicequestion;
import com.exservice.pojo.po.ChoicequestionExplain;
import com.exservice.service.ChoicequestionExplainManager;
import com.exservice.service.ChoicequestionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liang on 2018/5/14.
 */

@Component
public class ChoicequestionImpl implements ChoicequestionManager {
    @Resource
    ChoicequestionMapper choicequestionMapper;

    @Resource
    ChoicequestionExplainManager choicequestionExplainManager;

    @Override
    public List<Choicequestion> findallobjes() {
        return choicequestionMapper.findallobjes();
    }

    @Override
    public long findMaxMoniChoicequestion(String sublevel1, String moniname) {
        return  choicequestionMapper.findMaxMoniChoicequestion(sublevel1,moniname);
    }

    @Override
    public long findSubjectMaxChoicequestion(String sublevel3) {

        return  choicequestionMapper.findSubjectMaxChoicequestion(sublevel3);
    }

    @Override
    public void docreate() {

    }

    @Override
    public List<Choicequestion> findallobjes(String subjectid, String moniname) {

        return choicequestionMapper.findallobjesOne(subjectid,moniname);
    }

    @Override
    public Choicequestion findChoiceQuestion(String choicequestionid) {
       return  choicequestionMapper.findChoiceQuestion(choicequestionid);
    }

    @Override
    @Transactional
    public MessageObject dodel(Choicequestion cq, ChoicequestionExplain ce) {
        MessageObject mo = new MessageObject();
        try{
            choicequestionMapper.delete(cq.getQuestionid());
            choicequestionExplainManager.delete(cq.getQuestionid());
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("删除失败！");
            e.printStackTrace();
        }finally {

        }
        return mo;

    }

    @Override
    @Transactional
    public MessageObject docreate(Choicequestion cq, ChoicequestionExplain ce) {
        MessageObject mo = new MessageObject();
        try{
            choicequestionMapper.save(cq);
            choicequestionExplainManager.save(ce);
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败");
            e.printStackTrace();
        }finally {

        }
        return mo;
    }

    @Override
    @Transactional
    public MessageObject doupdate(Choicequestion cq, ChoicequestionExplain ce) {
        MessageObject mo = new MessageObject();
        try{
            choicequestionMapper.update(cq);
            choicequestionExplainManager.update(ce);
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败");
            e.printStackTrace();
        }finally {

        }
        return mo;
    }

    @Override
    public long findcount(String moniname,String sublevel1,String sublevel2,String sublevel3) {
        long allcount =0l;
        try{
            System.out.println("---ssfds-fs-f-sdf-f"+moniname);
            HashMap map = new HashMap<String,Object>();
            map.put("moniname",moniname);
            map.put("sublevel1",sublevel1);
            map.put("sublevel2",sublevel2);
            map.put("sublevel3",sublevel3);

            allcount = choicequestionMapper.findcount(map);
        }catch (Exception ex){
                ex.printStackTrace();
        }
        System.out.println("---allcount----"+allcount);
        return allcount;

    }

    @Override
    public List<Choicequestion> queryList(int startNumber, int pageSize,String moniname, String sublevel1, String sublevel2, String sublevel3)
    {
        HashMap map = new HashMap<String,Object>();
        map.put("moniname",moniname);
        map.put("sublevel1",sublevel1);
        map.put("sublevel2",sublevel2);
        map.put("sublevel3",sublevel3);
        map.put("startNumber",startNumber);
        map.put("pageSize",pageSize);

        List<Choicequestion> list =  choicequestionMapper.queryList(map);
        return list;
    }


    public Choicequestion findSubjectChoiceQuestion(String subjectid, long mcode){
       return  choicequestionMapper.findSubjectChoiceQuestion(subjectid,mcode);
    }


    public Choicequestion findMoniChoiceQuestion(String subjectid,String moniname,long maxmonicode){

        return choicequestionMapper.findMoniChoiceQuestion(subjectid,moniname,maxmonicode);
    }


    public long findChoicequestionCount(String subjectid){
        return choicequestionMapper.findChoicequestionCount(subjectid);
    }

}
