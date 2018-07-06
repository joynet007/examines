package com.exservice.service;

import com.exservice.pojo.po.Subject;

import java.util.List;

/**
 * Created by liang on 2018/5/13.
 */

public interface SubjectManager {


    public Subject findSubject(String subjectID);

    public void save(Subject subject);

    public List<Subject> findSubjectBypid(String parentID);

    public List<Subject> findAll();

    public void delete(Subject subject);

    public List<Subject> findSubjectByLevel(int mlevel);



}
