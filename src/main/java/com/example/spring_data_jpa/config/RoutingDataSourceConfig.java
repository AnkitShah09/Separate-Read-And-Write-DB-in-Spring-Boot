package com.example.spring_data_jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.spring_data_jpa",
        transactionManagerRef = "transactionManager",
        entityManagerFactoryRef = "entityManager")
public class RoutingDataSourceConfig {

    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("writeDataSource") DataSource writeDataSource,
                                 @Qualifier("readDataSource") DataSource readDataSource) throws SQLException {
        AbstractRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.WRITE, writeDataSource);
        dataSourceMap.put(DataSourceType.READ, readDataSource);

        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        return routingDataSource;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("dataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.example.spring_data_jpa.entity").persistenceUnit("transactionManager").build();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            DataSource actualDataSource) {
        return new DataSourceTransactionManager(actualDataSource);
    }
}
