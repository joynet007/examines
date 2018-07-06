package com.exweb.controller;

import com.excomm.GsonUtil;
import com.excomm.IDmanager;
import com.excomm.StringUtil;
import com.excomm.SystemConfig;
import com.exservice.pojo.MessageObject;
import com.exservice.pojo.po.Choicequestion;
import com.exservice.pojo.po.ChoicequestionExplain;
import com.exservice.pojo.po.Subject;
import com.exservice.service.ChoicequestionExplainManager;
import com.exservice.service.ChoicequestionManager;
import com.exservice.service.SubjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */
@Controller
@RequestMapping("/web/choicequestion")
public class ChoicequestionController {


    @Autowired
    ChoicequestionManager choicequestionManager;

    @Autowired
    ChoicequestionExplainManager choicequestionExplainManager;


    @Autowired
    SubjectManager subjectManager;

    MessageObject mo;

    @RequestMapping(value="/startpage")
    public String startpage(){
        return "choicequestion/choicequestion-list";
    }


    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<Choicequestion> viewlist(@RequestParam int pageNumber , @RequestParam int pageSize , @RequestParam("moniyear") String moniname ,
                                         @RequestParam String sublevel1 , @RequestParam String sublevel2 , @RequestParam String sublevel3 ){

        if(StringUtil.isEmpty(moniname)){
            moniname=null;
        }else{
            System.out.println("moniyear Is not null aaaa !"+moniname);

        }
        List<Choicequestion> list= (List<Choicequestion>) choicequestionManager.queryList((pageNumber-1)*pageSize,pageSize,moniname,sublevel1,sublevel2,sublevel3);
        return list;
    }


    @RequestMapping(value="/add")
    public String add(Model model){
        List<Subject> subjects = (List<Subject>)subjectManager.findSubjectByLevel(1);
        if(subjects!=null){
            model.addAttribute("subjects",subjects);
        }
        return "choicequestion/choicequestion-add";
    }

    @RequestMapping(value="/save" , method = RequestMethod.POST)
    @ResponseBody
    public MessageObject save(@RequestParam(required = false) String choicequestionname ,
                              @RequestParam(required = false) String answera ,
                              @RequestParam(required = false) String answerb ,
                              @RequestParam(required = false) String answerc ,
                              @RequestParam(required = false) String answerd ,
                              @RequestParam(required = false) String sublevel1 ,
                              @RequestParam(required = false) String sublevel2 ,
                              @RequestParam(required = false) String sublevel3 ,
                              @RequestParam(required = false) String realanswer ,
                              @RequestParam(required = false) String moniname ,
                              @RequestParam(required = false) String explain ,
                              Model model){

        mo = new MessageObject();

        String questionID = IDmanager.createID();
        Choicequestion cq = new Choicequestion();
        cq.setQuestionid(questionID);
        cq.setName(choicequestionname.trim());
        cq.setCtime(System.currentTimeMillis());
        cq.setAnswera(answera.trim());
        cq.setAnswerb(answerb.trim());
        cq.setAnswerc(answerc.trim());
        cq.setAnswerd(answerd.trim());
//        int mindex = choicequestionRepository.findmaxIndex(sublevel3);
//        cq.setMindex(mindex+1);

        cq.setSublevel1(sublevel1);
        cq.setSublevel2(sublevel2);
        cq.setSublevel3(sublevel3);

        //如果用户选择了，年份那么就是查找年份试卷最大的一个题目
        long maxmcode=0 ;
        try {
            if(!StringUtil.isEmpty(moniname)){
                maxmcode = choicequestionManager.findMaxMoniChoicequestion(sublevel1,moniname);
            }else{
                //如果用户没有选择，模拟年份，则必须选择章节
                maxmcode = choicequestionManager.findSubjectMaxChoicequestion(sublevel3);
            }
        }catch (Exception ex){
//            ex.printStackTrace();
        }


        cq.setMoniname(moniname);
        cq.setMcode((maxmcode+1));
        cq.setRealanswer(realanswer);


        System.out.println("--保存 cq ："+ GsonUtil.objTOjson(cq));

        ChoicequestionExplain ce = new ChoicequestionExplain();

        ce.setMexplain(explain.trim());
        ce.setQuestionid(questionID);

        try{
            mo = choicequestionManager.docreate(cq,ce);
        }catch (Exception ex){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败111！");
//            logger.info(ex.getMessage());
//            ex.printStackTrace();
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

        Choicequestion cq = choicequestionManager.findChoiceQuestion(choicequestionid);
        if(cq == null){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("要删除的对象不存在！");
            return mo;
        }
        ChoicequestionExplain ce = choicequestionExplainManager.findChoiceQuestionExplain(choicequestionid);

        mo = choicequestionManager.dodel(cq,ce);

        return mo;
    }

    @RequestMapping(value="/view")
    public String view(){
        return "question/add";
    }

    @RequestMapping(value = "/selectallcount")
    @ResponseBody
    public long selectallcount(@RequestParam("moniyear") String moniname ,@RequestParam String sublevel1 ,@RequestParam String sublevel2 ,@RequestParam String sublevel3) {

        System.out.println("--selectAllcount---"+moniname+"--"+sublevel1+"--"+sublevel2+"--"+sublevel3);

        long allcount =0l;
        try {
            allcount = choicequestionManager.findcount(moniname,sublevel1,sublevel2,sublevel3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allcount;
    }

    @RequestMapping(value="/edit/{choicequestionid}")
    public String edit(@PathVariable String choicequestionid ,Model model){

        Choicequestion choicequestion = null;
        ChoicequestionExplain choicequestionExplain = null;
        try{
            choicequestion = choicequestionManager.findChoiceQuestion(choicequestionid);
            choicequestionExplain = choicequestionExplainManager.findChoiceQuestionExplain(choicequestionid);
            model.addAttribute("choicequestion", choicequestion);
            model.addAttribute("choicequestionExplain", choicequestionExplain);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "choicequestion/choicequestion-edit";

    }

    @RequestMapping(value="/update" , method = RequestMethod.POST)
    @ResponseBody
    public MessageObject update(@RequestParam(required = false) String choicequestionname ,
                              @RequestParam(required = false) String answera ,
                              @RequestParam(required = false) String answerb ,
                              @RequestParam(required = false) String answerc ,
                              @RequestParam(required = false) String answerd ,
                              @RequestParam(required = false) String realanswer ,
                              @RequestParam(required = false) String explain ,
                              @RequestParam(required = false) String questionid ,
                              Model model){

        mo = new MessageObject();
        System.out.println("--更新 cq questionid ==："+ questionid);

        Choicequestion cq = choicequestionManager.findChoiceQuestion(questionid);
        cq.setName(choicequestionname.trim());

        cq.setAnswera(answera.trim());
        cq.setAnswerb(answerb.trim());
        cq.setAnswerc(answerc.trim());
        cq.setAnswerd(answerd.trim());

        cq.setRealanswer(realanswer);


        System.out.println("--更新 cq ："+ GsonUtil.objTOjson(cq));
        ChoicequestionExplain ce = choicequestionExplainManager.findChoiceQuestionExplain(questionid);

        ce.setMexplain(explain.trim());
        choicequestionExplainManager.update(ce);


        try{
            mo = choicequestionManager.doupdate(cq,ce);
        }catch (Exception ex){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("更新失败111！");
        }
        return mo;
    }



    @Value("${sys.picPath}")
    private String path;

    @Value("${sys.picServerPath}")
    private String picServerPath;


    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, @RequestParam("filedata") MultipartFile file) {

        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        //获取图片后缀
        String lastSuffix = fileName.substring(index,fileName.length());

        //根据时间戳产生新的图片名称 + 后缀
        String backFileName = System.currentTimeMillis()+lastSuffix;

        System.out.println(backFileName+"=后缀="+lastSuffix+"--文件名称--"+fileName+"--fileName 的名称是--");

        /**
         * 创建文件夹
         */
        File targetFile = new File(path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            inputStream = file.getInputStream();
            fileOutputStream = new FileOutputStream(path+backFileName);
            byte[] b = new byte[1024];
            int i= 0 ;
            while((i = inputStream.read(b))!=-1){
                fileOutputStream.write(b,0,i);
            }

        } catch (IOException e) {

            e.printStackTrace();

        }finally {
            try {
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String url =picServerPath+backFileName;
        String err = "";

        String json = "{\"err\":\"" + err + "\",\"msg\":\"" + url + "\"}";

        return json;
    }



}
