package com.example.spring_data_jpa.aop;

import com.example.spring_data_jpa.config.DataSourceContextHolder;
import com.example.spring_data_jpa.config.DataSourceType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before("execution(* com.example.spring_data_jpa.repository.*.save*(..)) || execution(* com.example.spring_data_jpa.repository.*.insert*(..)) || execution(* com.example.spring_data_jpa.repository.*.update*(..)) || execution(* com.example.spring_data_jpa.repository.*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setDataSourceType(DataSourceType.WRITE);
    }

    @Before("execution(* com.example.spring_data_jpa.repository.*.find*(..)) || execution(* com.example.spring_data_jpa.repository.*.get*(..)) || execution(* com.example.spring_data_jpa.repository.*.read*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.setDataSourceType(DataSourceType.READ);
    }
}