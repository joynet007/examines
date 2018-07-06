package com.exapp.controller;

/**
 * Created by liang on 2017/8/13.
 */

import com.excomm.GsonUtil;
import com.excomm.StringUtil;
import com.excomm.SystemConfig;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.Choicequestion;
import com.exservice.pojo.po.LearnCurrent;
import com.exservice.pojo.po.LearnQuestion;
import com.exservice.pojo.po.UserInfo;

import com.exservice.service.ChoicequestionManager;
import com.exservice.service.LearnCurrentManager;
import com.exservice.service.LearnQuestionManager;
import com.exservice.service.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by liang on 2017/7/11.
 */
@RestController
@RequestMapping("/app/learnquestion")
public class LearnquestionApp {


    private MessageObject mo ;
    @Autowired
    ChoicequestionManager choicequestionManager;

    @Autowired
    LearnCurrentManager learnCurrentManager;

    @Autowired
    LearnQuestionManager learnQuestionManager;

    @Autowired
    UserInfoManager userInfoManager;


    /**
     * 每次用户在界面上，选择答案的时候调用。
     * 创建一个学习的题目（可能学习的结果是错误或者正确）
     * 创建当前学习的题目对象
     * @param userid
     * @param questionid
     * @param ismistake
     * @return
     */
    @RequestMapping(value="/create")
    @ResponseBody
    public MessageObject createlearnquestion(@RequestParam(required = false) long userid ,
                                             @RequestParam(required = false) String questionid ,
                                             @RequestParam(required = false) String ismistake
                                             ){
        mo = new MessageObject();

        Choicequestion cq = null;
        if(StringUtil.isEmpty(questionid)){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("你学习的题目编号不存在，请从头再来！");
            return mo;
        }

        cq = choicequestionManager.findChoiceQuestion(questionid);
        if(cq == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("你学习的题目不存在，请重新选择一题！");
            return mo;
        }

        System.out.println(userid+"---"+questionid);

        LearnQuestion learnQuestion = learnQuestionManager.findLearnquestion(userid,questionid);
        if(null == learnQuestion){
            learnQuestion = new LearnQuestion();
            learnQuestion.setCreatetime(System.currentTimeMillis());
            learnQuestion.setIsmistake(ismistake);
            learnQuestion.setMoniname(cq.getMoniname());
            learnQuestion.setQuestionid(questionid);
            learnQuestion.setSubjectid(cq.getSublevel1());
            learnQuestion.setUserid(userid);

            learnQuestionManager.save(learnQuestion);

        }else{
            learnQuestion.setIsmistake(ismistake);
            learnQuestionManager.update(learnQuestion);
        }

        /**
         * 创建当前模拟年份学习的对象，如果对象已经存在则修改mcode
         */
        LearnCurrent learnCurrent = learnCurrentManager.findLearnCurrentObjByMoniname(userid,cq.getSublevel1(),cq.getMoniname());

        if(learnCurrent == null){

            learnCurrent = new LearnCurrent();
            learnCurrent.setUserid(userid);
            learnCurrent.setSubjectid(cq.getSublevel1());
            learnCurrent.setMoniname(cq.getMoniname());
            learnCurrent.setCreatetime(System.currentTimeMillis());
            learnCurrent.setMcode(cq.getMcode());

            learnCurrentManager.save(learnCurrent);

        }else{
            //确保每次学习顺序正确
            if(cq.getMcode() > learnCurrent.getMcode()){
                learnCurrent.setMcode(cq.getMcode());
            }

            learnCurrentManager.update(learnCurrent);

        }

//       mo =  learnQuestionManager.createLearnObj(learnCurrent,learnQuestion);
//        learnQuestionRepository.save(learnQuestion);

        return mo;

    }

    /**
     * 查询当前用户的 学习题目的数量
     * 包括此科目的总题目数量、此用户已经学习的数量、错误的题数、正确的题目数量
     * @param userid
     * @param subjectid
     * @return
     */
    @RequestMapping(value="/learncount")
    @ResponseBody
    public MessageObject learncount(@RequestParam(required = false) long userid ,
                                    @RequestParam(required = false) String subjectid ){
        mo = new MessageObject();
        UserInfo userInfo =  userInfoManager.findByUserid(userid);
        if(userInfo == null)
        {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }

        HashMap<String,String> map = new HashMap<String,String>();

        long allquestion =0;//科目下面的总条数
        long learnall = 0 ;//已经学习总条数
        long mistakeno = 0;//已经学会
        long mistakeyes=0;//我的错题

        try{
            allquestion = choicequestionManager.findChoicequestionCount(subjectid);
        }catch (Exception ex){
//            System.out.println(this.getClass().getName()+"--没有题目数据--");
        }

        try{
            mistakeno = learnQuestionManager.findcountismistakeLearnquestion(userid,subjectid,SystemConfig.mistake_no);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            mistakeyes = learnQuestionManager.findcountismistakeLearnquestion(userid,subjectid,SystemConfig.mistake_yes);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        learnall = mistakeno+mistakeyes;

        //更新用户表中学习的总条数
        userInfoManager.updateStudyCount(userInfo.getUserid(),learnall);

        map.put("allquestion",allquestion+"");
        map.put("mistakeno",mistakeno+"");
        map.put("mistakeyes",mistakeyes+"");
        map.put("learnall",learnall+"");
        map.put("sortnumber",userInfo.getSortnumber()+"");//用户在系统的排名

        String content = GsonUtil.objTOjson(map);
        mo.setMcontent(content);
        return mo;

    }


    /**
     * 这个科目下查找，当前用户的错误的题目，不是列表是一个一个来
     * 创建时间最大的那个
     *
     * @param userid
     * @param subjectid
     * @return
     */
    @RequestMapping(value="/finduserMistkeQuestion")
    @ResponseBody
    public MessageObject finduserMistkeQuestion(@RequestParam(required = false) long userid ,
                                    @RequestParam(required = false) String subjectid ){
        mo = new MessageObject();
        UserInfo userInfo =  userInfoManager.findByUserid(userid);
        if(userInfo == null)
        {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }

        LearnQuestion lq = learnQuestionManager.findLearnquestionMaxIsMitake(userid,subjectid);
        Choicequestion cq;

        if(lq!=null){
            cq = choicequestionManager.findChoiceQuestion(lq.questionid);
            if(cq!=null){

                long mistakeyes = 0;
                try{
                   mistakeyes = learnQuestionManager.findcountismistakeLearnquestion(userid,subjectid,SystemConfig.mistake_yes);
                }catch (Exception ex){
                    ex.printStackTrace();
                    System.out.println(this.getClass().getName()+"--没有错误题目数据--");
                }

                HashMap<String,String> map = GsonUtil.tomap(cq);
                map.put("pkid",lq.getPkid()+"");
                map.put("mistakeyes",mistakeyes+"");
                String content = GsonUtil.objTOjson(map);
                mo.setMcontent(content);
            }else{
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("查询题目失败！");
                return mo;
            }
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("您已经没有错误的题目了！");
            return mo;
        }

        return mo;

    }


    /**
     * 这个科目下查找，当前用户的正在查看错题目，的下一个题目，不是列表,是一个一个来
     * 创建时间最大的那个
     * @param userid
     * @param subjectid
     * @return
     */
    @RequestMapping(value="/findNextLearnquestionMaxIsMitake")
    @ResponseBody
    public MessageObject findNextLearnquestionMaxIsMitake(@RequestParam(required = false) String userid ,
                                                          @RequestParam(required = false) String subjectid,
                                                          @RequestParam(required = false) String pkid ){

        System.out.println(userid+"--"+subjectid+"--"+pkid);

        mo = new MessageObject();
        UserInfo userInfo =  userInfoManager.findByUserid(Long.parseLong(userid));
        if(userInfo == null)
        {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }


        LearnQuestion lq = learnQuestionManager.findNextLearnquestionMaxIsMitake(Long.parseLong(userid),subjectid,Long.parseLong(pkid));

        Choicequestion cq;

        if(lq!=null){
            cq = choicequestionManager.findChoiceQuestion(lq.questionid);
            if(cq!=null){

                long mistakeyes = 0;
                try{
                    mistakeyes = learnQuestionManager.findcountismistakeLearnquestion(Long.parseLong(userid),subjectid,SystemConfig.mistake_yes);
                }catch (Exception ex){
                    ex.printStackTrace();
                    System.out.println(this.getClass().getName()+"--没有错误题目数据--");
                }

                HashMap<String,String> map = GsonUtil.tomap(cq);
                map.put("pkid",lq.getPkid()+"");
                map.put("mistakeyes",mistakeyes+"");
                String content = GsonUtil.objTOjson(map);
                mo.setMcontent(content);
            }else{
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("已经是最后一题了。");
                return mo;
            }
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("已经是最后一题了！");
            return mo;
        }

        return mo;

    }

    /**
     * 这个科目下查找，当前用户的正在查看错题目，的下一个题目，不是列表,是一个一个来
     * 创建时间最大的那个
     * @param userid
     * @param subjectid
     * @return
     */
    @RequestMapping(value="/findUpLearnquestionMaxIsMitake")
    @ResponseBody
    public MessageObject findUpLearnquestionMaxIsMitake(@RequestParam(required = false) String userid ,
                                                          @RequestParam(required = false) String subjectid,
                                                          @RequestParam(required = false) String pkid ){

        System.out.println(userid+"--"+subjectid+"--"+pkid);

        mo = new MessageObject();
        UserInfo userInfo =  userInfoManager.findByUserid(Long.parseLong(userid));
        if(userInfo == null)
        {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }


        LearnQuestion lq = learnQuestionManager.findUpLearnquestionMaxIsMitake(Long.parseLong(userid),subjectid,Long.parseLong(pkid));

        Choicequestion cq;

        if(lq!=null){
            cq = choicequestionManager.findChoiceQuestion(lq.questionid);
            if(cq!=null){

                long mistakeyes = 0;
                try{
                    mistakeyes = learnQuestionManager.findcountismistakeLearnquestion(Long.parseLong(userid),subjectid,SystemConfig.mistake_yes);
                }catch (Exception ex){
                    System.out.println(this.getClass().getName()+"--没有错误题目数据--");
                }

                HashMap<String,String> map = GsonUtil.tomap(cq);
                map.put("pkid",lq.getPkid()+"");
                map.put("mistakeyes",mistakeyes+"");
                String content = GsonUtil.objTOjson(map);
                mo.setMcontent(content);
            }else{
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("题目没有查到。");
                return mo;
            }
        }else{
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("已经是第一题目了！");
            return mo;
        }

        return mo;

    }




}
