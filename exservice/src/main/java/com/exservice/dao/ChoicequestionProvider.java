package com.exservice.dao;

import com.excomm.StringUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

/**
 * Created by liang on 2018/6/8.
 */
public class ChoicequestionProvider {

    private static final String tableName= "tb_choicequestion";

    public String findcount(HashMap<String,String> map){
        return new SQL(){{

            SELECT("count(*)");
            FROM(tableName);
            WHERE("1=1  ");

            if(!StringUtil.isEmpty(map.get("moniname"))){
                AND();
                WHERE("moniname = #{moniname}");
            }

            if(!StringUtil.isEmpty(map.get("sublevel1"))){
                AND();
                WHERE("sublevel1 = #{sublevel1}");
            }

            if(!StringUtil.isEmpty(map.get("sublevel2"))){
                AND();
                WHERE("sublevel2 = #{sublevel2}");
            }

            if(!StringUtil.isEmpty(map.get("sublevel3"))){
                AND();
                WHERE("sublevel3 = #{sublevel3}");
            }

        }}.toString();

    }
    public String queryList(HashMap<String,String> map){

//        @Select({"<script>",
//                "SELECT * FROM tb_choicequestion ",
//                "WHERE 1=1",
//                "<if test = 'moniname!=null '> ",
//                "AND moniname = #{moniname} ",
//                "</if>",
//                "order by ctime desc ",
//                "limit #{startNumber},#{pageSize} ",
//                "</script>"})

        return new SQL(){{

            SELECT("*");
            FROM(tableName);
            WHERE("1=1  ");


            if(!StringUtil.isEmpty(map.get("moniname"))){
                AND();
                WHERE("moniname = #{moniname}");
            }

            if(!StringUtil.isEmpty(map.get("sublevel1"))){
                AND();
                WHERE("sublevel1 = #{sublevel1}");
            }

            if(!StringUtil.isEmpty(map.get("sublevel2"))){
                AND();
                WHERE("sublevel2 = #{sublevel2}");
            }

            if(!StringUtil.isEmpty(map.get("sublevel3"))){
                AND();
                WHERE("sublevel3 = #{sublevel3}");
            }
            ORDER_BY("ctime desc limit #{startNumber},#{pageSize} ");


        }}.toString();

    }


}
