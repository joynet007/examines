package com.exservice.pojo.po;


/**
 * Created by liang on 2017/8/13.
 */
public class LearnQuestion {
    public long pkid;
    public long userid;
    public String questionid;
    public long createtime;
    public String subjectid;
    public String ismistake;
    public String moniname;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getIsmistake() {
        return ismistake;
    }

    public void setIsmistake(String ismistake) {
        this.ismistake = ismistake;
    }

    public long getPkid() {
        return pkid;
    }

    public void setPkid(long pkid) {
        this.pkid = pkid;
    }

    public String getMoniname() {
        return moniname;
    }

    public void setMoniname(String moniname) {
        this.moniname = moniname;
    }
}
