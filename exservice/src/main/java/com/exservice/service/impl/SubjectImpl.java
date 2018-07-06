package com.exservice.service.impl;

import com.exservice.dao.SubjectMapper;
import com.exservice.pojo.po.Subject;
import com.exservice.service.SubjectManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liang on 2018/5/13.
 */

@Component
public class SubjectImpl implements SubjectManager {


    @Resource
    SubjectMapper subjectMapper;

    @Override
    public Subject findSubject(String subjectID) {

        return subjectMapper.findSubject(subjectID);
    }

    @Override
    public void save(Subject subject) {

        subjectMapper.save(subject);
    }

    @Override
    public List<Subject> findSubjectBypid(String parentID) {
        return subjectMapper.findSubjectBypid(parentID);
    }

    @Override
    public List<Subject> findAll() {
        return subjectMapper.findAll();
    }

    @Override
    public void delete(Subject subject) {
        subjectMapper.delete(subject.getSubjectid());
    }

    @Override
    public List<Subject> findSubjectByLevel(int mlevel) {
        return subjectMapper.findSubjectByLevel(mlevel);
    }
}
