package com.exweb.controller;

import com.exservice.pojo.po.MoniYear;
import com.exservice.service.MoniYearManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
 */

@Controller
@RequestMapping("/web/moniyear")
public class MoniYearController {

    @Autowired
    MoniYearManager moniYearRepository;

    /**
     * 查看某一个资源
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<MoniYear> viewlist(){

        System.out.println("--查看历年的模拟题列表--");
        List<MoniYear> list =(List<MoniYear>)moniYearRepository.findAll();

        System.out.println("---list.size()---"+list.size());

        return list;
    }



}
