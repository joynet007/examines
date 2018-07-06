package com.exservice.dao;

import com.exservice.pojo.po.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liang on 2018/5/13.
 */

@Component
@Mapper
public interface UserInfoMapper {

    @Select("select * from tb_userinfo where usertel = #{usertel} and userpassword = #{userpassword}")
    UserInfo findUserInfo(@Param("usertel") String usertel, @Param("userpassword") String userpassword);

    @Select("select * from tb_userinfo where usertel = #{usertel}")
    UserInfo findByUsertel(String usertel);

    @Select("select * from tb_userinfo where userid = #{userid}")
    UserInfo findByUserid(long userid);

    @Select("select count(*) from tb_userinfo ")
    long findAll();

    @Select("select * from tb_userinfo ")
    List<UserInfo> findAllList();

    @Delete("delete from tb_userinfo where usertel = #{usertel}")
    void delete(String usertel);

    @Update("update tb_userinfo set  userid = #{userid} ,  usertel = #{usertel} ,  username = #{username} , " +
            " userpassword = #{userpassword} ,  mstatus = #{mstatus} ,  createtime = #{createtime} ,  msex = #{msex} , " +
            " tokenid = #{tokenid} , studyquestioncount = #{studyquestioncount} , sortnumber = #{sortnumber} where userid= #{userid}")
    void update(UserInfo userInfo);

    @Update("update tb_userinfo set studyquestioncount = #{studyquestioncount} where userid= #{userid}")
    void updateStudyCount(@Param("userid") long userid , @Param("studyquestioncount") long studyquestioncount);

    @Insert("insert into tb_userinfo( userid , usertel , username , userpassword , mstatus , createtime , msex , tokenid ,studyquestioncount ) " +
            "values (  #{userid} , #{usertel} , #{username} , #{userpassword} , #{mstatus} , #{createtime} , #{msex} , #{tokenid} ,#{studyquestioncount} )")
    void save(UserInfo userInfo);

    @Select("select * from tb_userinfo order by createtime desc limit #{startNumber},#{pageSize} " )
    List<UserInfo> queryList(@Param("startNumber") int startNumber, @Param("pageSize") int pageSize);

    @Select("select * from tb_userinfo where userid = #{userid} and tokenid=#{tokenid}")
    UserInfo findByUserInfoBytokenid(long userid,String tokenid);


    @Select(value="select * from tb_userinfo order by studyquestioncount , id limit 0,10")
    List<UserInfo> findMaxStudyList();

    @Select("select max(studyquestioncount) from tb_userinfo ")
    long findMaxStudy();

}
