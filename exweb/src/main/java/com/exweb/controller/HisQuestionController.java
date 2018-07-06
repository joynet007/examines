package com.exweb.controller;

import com.excomm.GsonUtil;
import com.excomm.IDmanager;
import com.excomm.StringUtil;
import com.excomm.SystemConfig;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.Choicequestion;
import com.exservice.pojo.po.ChoicequestionExplain;
import com.exservice.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/9/8.
 *
 * 这个类主要处理，历年考题的 新增 、 删除 、更新
 *
 */
@Controller
@RequestMapping("/web/hisquestion")
public class HisQuestionController {


    @Resource
    ChoicequestionExplainManager choicequestionExplainManager;

    @Resource
    LearnCurrentManager learnCurrentManager;

    @Resource
    UserInfoManager userInfoManager;

    @Resource
    SubjectManager subjectManager;

    @Resource
    ChoicequestionManager choiceQuestionManager;

    MessageObject mo;


    /**
     * 进入的开始页
     * @return
     */
    @RequestMapping(value="/startpage")
    public String viewfirstpage(){
        return "hisquestion/hisquestion-list";
    }

    @RequestMapping(value="/add")
    public String add(Model model, @RequestParam(required = false) String subjectid ,
                      @RequestParam(required = false) String subjectName,
                      @RequestParam(required = false) String moniname){

        System.out.println(subjectid+"---"+subjectName+"---"+moniname);

        model.addAttribute("subjectid",subjectid);
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("moniname",moniname);

        return "hisquestion/hisquestion-add";
    }

    /**
     * 根据科目编号、年份查询题目
     * @param subjectid
     * @param moniname
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<Choicequestion> viewlist(@RequestParam(required = false) String subjectid ,
                                         @RequestParam(required = false) String moniname){

        List<Choicequestion> list = new ArrayList<Choicequestion>();
        if(StringUtil.isEmpty(subjectid)){
            return list;
        }
        if(StringUtil.isEmpty(moniname)){
            return list;
        }
        list= (List<Choicequestion>) choiceQuestionManager.findallobjes(subjectid,moniname);
        return list;
    }


    /**
     * 编辑
     * @return
     */
    @RequestMapping(value="/edit/{questionid}/{subjectName}")
    public String edit(@PathVariable String questionid, @PathVariable String subjectName, Model model){

        Choicequestion cq = choiceQuestionManager.findChoiceQuestion(questionid);

        if(cq!=null ){
            model.addAttribute("cq",cq);
            model.addAttribute("subjectid",cq.getSublevel1());
            model.addAttribute("subjectName",subjectName);
            model.addAttribute("moniname",cq.getMoniname());
        }

        ChoicequestionExplain ce = choicequestionExplainManager.findChoiceQuestionExplain(questionid);

        if(ce!=null){
            model.addAttribute("ce",ce);
        }

        return "hisquestion/hisquestion-edit";
    }

    /**
     *
     * @return
     * 更新数据
     */
    @RequestMapping(value="/update" , method = RequestMethod.POST)
    @ResponseBody
    public MessageObject update(@RequestParam(required = false) String questionid ,
                              @RequestParam(required = false) String choicequestionname ,
                              @RequestParam(required = false) String answera ,
                              @RequestParam(required = false) String answerb ,
                              @RequestParam(required = false) String answerc ,
                              @RequestParam(required = false) String answerd ,
                              @RequestParam(required = false) String realanswer ,
                              @RequestParam(required = false) String explain ,
                              Model model){

        mo = new MessageObject();

        Choicequestion cq =  choiceQuestionManager.findChoiceQuestion(questionid);
        cq.setName(choicequestionname);
        cq.setAnswera(answera);
        cq.setAnswerb(answerb);
        cq.setAnswerc(answerc);
        cq.setAnswerd(answerd);
        cq.setRealanswer(realanswer);

        ChoicequestionExplain ce = choicequestionExplainManager.findChoiceQuestionExplain(questionid);
        if(ce!=null){
            ce.setMexplain(explain);
        }else if(!StringUtil.isEmpty(explain)){
            ce = new ChoicequestionExplain();
            ce.setQuestionid(questionid);
            ce.setMexplain(explain);
        }


        try{
            mo = choiceQuestionManager.docreate(cq,ce);
        }catch (Exception ex){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("更新失败！");
        }
        return mo;
    }


    /**
     * 根据科目编号、年份查询题目
     *
     * @return
     */
    @RequestMapping(value="/save" , method = RequestMethod.POST)
    @ResponseBody
    public MessageObject save(@RequestParam(required = false) String choicequestionname ,
                              @RequestParam(required = false) String answera ,
                              @RequestParam(required = false) String answerb ,
                              @RequestParam(required = false) String answerc ,
                              @RequestParam(required = false) String answerd ,
                              @RequestParam(required = false) String realanswer ,
                              @RequestParam(required = false) String subjectid ,
                              @RequestParam(required = false) String moniname ,
                              @RequestParam(required = false) String explain ,
                              Model model){

        mo = new MessageObject();

        String questionID = IDmanager.createID();
        Choicequestion cq = new Choicequestion();
        cq.setQuestionid(questionID);
        cq.setName(choicequestionname);
        cq.setCtime(System.currentTimeMillis());
        cq.setAnswera(answera);
        cq.setAnswerb(answerb);
        cq.setAnswerc(answerc);
        cq.setAnswerd(answerd);

        cq.setSublevel1(subjectid);

        //如果用户选择了，年份那么就是查找年份试卷最大的一个题目
        long maxmcode=0 ;
        try {
            if(!StringUtil.isEmpty(moniname))
            {
                maxmcode = choiceQuestionManager.findMaxMoniChoicequestion(subjectid,moniname);
            }
        }catch (Exception ex){
//            ex.printStackTrace();
        }


        cq.setMoniname(moniname);
        cq.setMcode((maxmcode+1));
        cq.setRealanswer(realanswer);


        System.out.println("--保存 cq ："+ GsonUtil.objTOjson(cq));

        ChoicequestionExplain ce = new ChoicequestionExplain();

        ce.setMexplain(explain);
        ce.setQuestionid(questionID);

        try{
            mo = choiceQuestionManager.docreate(cq,ce);
        }catch (Exception ex){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败111！");
        }
        return mo;
    }


    @RequestMapping(value="/del/{choicequestionid}")
    @ResponseBody
    public MessageObject del(@PathVariable String choicequestionid){
        mo = new MessageObject();
        if(StringUtil.isEmpty(choicequestionid)){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("要删除的选择题编码不能为空！");
            return mo;
        }

        Choicequestion cq = choiceQuestionManager.findChoiceQuestion(choicequestionid);
        if(cq == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("要删除的对象不存在！");
            return mo;
        }
        ChoicequestionExplain ce = choicequestionExplainManager.findChoiceQuestionExplain(choicequestionid);

        choiceQuestionManager.dodel(cq,ce);
        return mo;
    }
}
