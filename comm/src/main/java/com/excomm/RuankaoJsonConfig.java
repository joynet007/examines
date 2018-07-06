package com.excomm;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

/**
 * Created by liang on 2017/5/31.
 */
public class RuankaoJsonConfig{

    /**
     * object 转化成 json 字符串
     * @return
     * @param object
     */
    public static String objTOjson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 10001 成功
     * 10002 失败
     * @param code
     * @param message
     * @return
     */

    public static String responseStr(String code , String message)
    {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("code",code);
        map.put("message",message);

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;

    }

    /**
     * 10001 成功
     * 10002 失败
     * @param message
     * @return
     */

    public static String responseStrFailed( String message )
    {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("code",SysConfig.result_failed);
        map.put("message",message);

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }



    /**
     * @param message
     * @return
     */
    public static String responseStrSuccess( String message )
    {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("code",SysConfig.result_succ);
        map.put("message",message);

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return json;
    }


    /**
     *
     * @param code
     * @param message
     * @param object
     * @return
     */
    public static HashMap responseMap( String code ,String message , Object object )
    {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("code", code);
        map.put("message",message);
        map.put("content",object);
        return map;
    }



}
