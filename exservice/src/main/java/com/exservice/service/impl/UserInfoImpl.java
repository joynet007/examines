package com.exservice.service.impl;

import com.exservice.dao.UserInfoMapper;
import com.exservice.pojo.po.UserInfo;
import com.exservice.service.UserInfoManager;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liang on 2018/5/13.
 */

@EnableScheduling
@Component
public class UserInfoImpl implements UserInfoManager {


    @Resource
    UserInfoMapper userInfoMapper;


    @Override
    public UserInfo findByUsertel(String usertel) {
        return userInfoMapper.findByUsertel(usertel);
    }

    @Override
    public UserInfo findByUserid(long userid) {
        return userInfoMapper.findByUserid(userid);
    }


    @Override
    public UserInfo findUser(String usertel, String userPassword) {
        return userInfoMapper.findUserInfo(usertel,userPassword);
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoMapper.save(userInfo);

    }

    @Override
    public void delete(UserInfo userInfo) {
        userInfoMapper.delete(userInfo.getUsertel());
    }

    @Override
    public void delete(String usertel) {
        userInfoMapper.delete(usertel);
    }


    @Override
    public List<UserInfo> queryList(int startNumber, int pageSize){
       return  userInfoMapper.queryList(startNumber,pageSize);
    }

    @Override
    public long findcount(){
        return userInfoMapper.findAll();
    }


    @Override
    public void update(UserInfo userInfo){
        userInfoMapper.update(userInfo);
    }

    @Override
    public void updateStudyCount(long userid, long mcount) {
         userInfoMapper.updateStudyCount(userid,mcount);
    }

    @Override
    public UserInfo findByUserInfoBytokenid(long userid, String tokenid){
        return userInfoMapper.findByUserInfoBytokenid(userid,tokenid);
    }

    @Override
    public List<UserInfo> findMaxStudyList(){
        return userInfoMapper.findMaxStudyList();
    }

    /**
     * 用户排序算法，此排序算法不缺分用户在不同的学科下面的，是全局统一的。
     * 1查出所有用户的列表
     * 2、从目前的用户列表中找出学习数量最多的值 maxCount
     * 3、根据数量最多的值，查看当前的用户，设置用户的排序
     * 4、依次循环知道
     *
     * 每天晚上23时，59分启动线程
     */
    @PostConstruct
    @Scheduled(cron = "0 59 23 * * ? ")
    public void sortUserinfo(){
        long maxCount = userInfoMapper.findMaxStudy();
        List<UserInfo> userInfos = userInfoMapper.findAllList();
        int sortNumber = 1;//拍讯
        while(maxCount != 0){
            for(UserInfo userInfo : userInfos)
            {
                if( userInfo.getStudyquestioncount() == maxCount )
                {
                    userInfo.setSortnumber(sortNumber);
                    userInfoMapper.update(userInfo);

                    ++ sortNumber;
                }
            }
            -- maxCount;


        }


    }

}
