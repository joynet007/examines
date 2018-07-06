package com.exservice.service;

import com.exservice.pojo.po.UserInfo;

import java.util.List;

/**
 * Created by liang on 2018/5/13.
 */
public interface UserInfoManager {

    UserInfo findByUsertel(String usertel);

    UserInfo findByUserid(long userid);

    UserInfo findUser(String usertel, String userPassword);

    public void save(UserInfo userInfo);

    public void delete(UserInfo userInfo);

    public void delete(String usertel);


    public void update(UserInfo userInfo);
    public void updateStudyCount(long userid , long mcount);


    List<UserInfo> queryList(int startNumber, int pageSize);

    long findcount();


    UserInfo findByUserInfoBytokenid(long userid,String tokenid);

    /**
     * 查询前十名最多的学习者
     * @return
     */
    List<UserInfo> findMaxStudyList();


}
