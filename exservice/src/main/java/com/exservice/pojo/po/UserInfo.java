package com.exservice.pojo.po;

/**
 * Created by liang on 2017/3/25.
 */

public class UserInfo {

    public long userid;
    public String usertel;
    public String username;
    public String userpassword;
    public String mstatus;
    public String msex;
    public long createtime;
    public long studyquestioncount;
    public String tokenid;
    public long sortnumber;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsex() {
        return msex;
    }

    public void setMsex(String msex) {
        this.msex = msex;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public long getStudyquestioncount() {
        return studyquestioncount;
    }

    public void setStudyquestioncount(long studyquestioncount) {
        this.studyquestioncount = studyquestioncount;
    }

    public long getSortnumber() {
        return sortnumber;
    }

    public void setSortnumber(long sortnumber) {
        this.sortnumber = sortnumber;
    }
}
