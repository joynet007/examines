package com.exservice.dao.repository;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by liang on 2018/7/8.
 * 定义需要的几个接口
 * 1、增加对象  2、删除对象  3、更改对象 、4 查询对象
 */
@Component
public interface CrudRepository<T> {

    public void save(T t);

    public void delete(T t);

    public void update(T t);

    public T findOne(T t);

}
