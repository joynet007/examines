package com.exservice.dao.repository.annotation;

import com.exservice.pojo.po.KnowledgePoint;

import java.lang.reflect.Field;

/**
 * Created by liang on 2018/7/8.
 */
public class AnTest {

    public static void main(String args[])
    {

        Class cls = KnowledgePoint.class;
        String tableID = "";
        Field[] fields = cls.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            Field f = fields[i];
            TableId tableidAn = (TableId)f.getAnnotation(TableId.class);
            if(tableidAn!=null){
                tableID = tableidAn.value();
            }
        }

        System.out.println(tableID);

    }
}
