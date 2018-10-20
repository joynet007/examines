package com.exservice.dao;

import com.excomm.StringUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

/**
 * Created by liang on 2018/10/14.
 */
public class KnowledgepointProvider {

    public static final String tableName = "tb_knowledgepoint";

    public String queryList(HashMap<String,String> map){

        return new SQL(){{

            SELECT("*");
            FROM(tableName);
            WHERE("1=1  ");

            if(!StringUtil.isEmpty(map.get("subjectid"))){
                AND();
                WHERE("subjectid = #{subjectid}");
            }
            ORDER_BY("createtime desc limit #{startNumber},#{pageSize} ");

        }}.toString();

    }

    public String selectallcount(HashMap<String,String> map){

        return new SQL(){{
            SELECT("count(*)");
            FROM(tableName);
            WHERE("1=1  ");

            if(!StringUtil.isEmpty(map.get("subjectid"))){
                AND();
                WHERE("subjectid = #{subjectid}");
            }

        }}.toString();
    }

//    long selectallcount(String subjectid);


}
