package com.exservice.pojo.po;

/**
 * Created by liang on 2017/6/9.
 *
 * 选择题
 */

public class Choicequestion {

    public String questionid;
    public String name;
    public String sublevel1;
    public String sublevel2;
    public String sublevel3;
    public String answera;
    public String answerb;
    public String answerc;
    public String answerd;
    public String realanswer;
    public long ctime;
    public String moniname;
    public long mcode;//序号

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSublevel1() {
        return sublevel1;
    }

    public void setSublevel1(String sublevel1) {
        this.sublevel1 = sublevel1;
    }

    public String getSublevel2() {
        return sublevel2;
    }

    public void setSublevel2(String sublevel2) {
        this.sublevel2 = sublevel2;
    }

    public String getSublevel3() {
        return sublevel3;
    }

    public void setSublevel3(String sublevel3) {
        this.sublevel3 = sublevel3;
    }

    public String getAnswera() {
        return answera;
    }

    public void setAnswera(String answera) {
        this.answera = answera;
    }

    public String getAnswerb() {
        return answerb;
    }

    public void setAnswerb(String answerb) {
        this.answerb = answerb;
    }

    public String getAnswerc() {
        return answerc;
    }

    public void setAnswerc(String answerc) {
        this.answerc = answerc;
    }

    public String getAnswerd() {
        return answerd;
    }

    public void setAnswerd(String answerd) {
        this.answerd = answerd;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getRealanswer() {
        return realanswer;
    }

    public void setRealanswer(String realanswer) {
        this.realanswer = realanswer;
    }

    public String getMoniname() {
        return moniname;
    }

    public void setMoniname(String moniname) {
        this.moniname = moniname;
    }

    public long getMcode() {
        return mcode;
    }

    public void setMcode(long mcode) {
        this.mcode = mcode;
    }
}
