package com.exservice.dao.repository.annotation;

import java.lang.annotation.*;

/**
 * Created by liang on 2018/7/8.
 * 此注解表示 字段为表明
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {
    String value() default "" ;
}
