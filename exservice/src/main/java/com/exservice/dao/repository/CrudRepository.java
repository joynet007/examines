package com.exservice.dao.repository;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by liang on 2018/7/8.
 */
@Component
public interface CrudRepository<T> {

    public void save(T t);

    public void delete(T t);

    public void update(T t);

    public T findOne(T t);

}
