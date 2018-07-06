package com.exservice.service.impl;

import com.exservice.dao.MoniYearMapper;
import com.exservice.pojo.po.MoniYear;
import com.exservice.service.MoniYearManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liang on 2018/5/14.
 */

@Component
public class MoniYearImpl implements MoniYearManager {

    @Resource
    MoniYearMapper moniYearMapper;

    @Override
    public List<MoniYear> findAll() {
        return moniYearMapper.findall();
    }
}
