package com.excomm;

/**
 * Created by liang on 2018/8/28.
 */
public class Test {

   public static void main(String args[]){

       try{

           methodone();
           methodtwo();


       }catch (Exception e){

           e.printStackTrace();
       }


       System.out.println("---kkk---");
   }


   public static  void  methodone(){
       try{
           int i=0;
           int k = 10;

           int t = k/i;

           System.out.println("----");


       }catch (Exception e){

           e.printStackTrace();
       }
   }

    public static void  methodtwo(){
        try{
            int i=2;
            int k = 10;

            int t = k/i;

            System.out.println("methodtwo=="+t+"  ----");


        }catch (Exception e){

            e.printStackTrace();
        }
    }

}
