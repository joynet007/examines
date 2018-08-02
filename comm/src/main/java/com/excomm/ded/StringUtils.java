package com.excomm.ded;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2018/7/15.
 */
public class StringUtils {

    public static  void main(String args[])
    {

        List<String> list = new ArrayList<String>();

        list.add("SvPt_Bsn_Dt");
        list.add("Ret_File_Nm");
        list.add("ASPD_ECD");
        list.add("WF_FCN_ID");

        for(String str : list){
            underlineToCamel(str);
        }

    }

    public static void underlineToCamel(String underlineStr) {

        String key = underlineStr.toLowerCase();

        StringBuffer strb = new StringBuffer();

        int hengxian =0;

        for(int i=0;i<key.length();i++){
            String kk =key.substring(i,i+1);
            if(kk.equals("_")){
                hengxian=i;
            }else{
                if(hengxian+1 == i && hengxian != 0){
                    strb.append(kk.toUpperCase());
                }else{
                    strb.append(kk);
                }
            }


        }

        System.out.println(strb.toString());



//        //全部转换成小写
//        underlineStr = underlineStr.toLowerCase();
//        String header = underlineStr.substring(0, 1);
//        //去掉首字母，排除首3个字符为下划线的情况
//        underlineStr = underlineStr.substring(1);
//        int index = 0;
//        while (underlineStr.contains("_")) {
//            index = underlineStr.indexOf("_");
//            if (underlineStr.indexOf("_") == 0) {
//                underlineStr = underlineStr.substring(index + 1).substring(0, 1).toUpperCase() + underlineStr.substring(index + 1).substring(1);
//            } else {
//                underlineStr = underlineStr.substring(0, index) + (underlineStr.substring(index + 1).substring(0, 1).toUpperCase() + underlineStr.substring(index + 1).substring(1));
//            }
//        }
//        return header + underlineStr;
    }
}
