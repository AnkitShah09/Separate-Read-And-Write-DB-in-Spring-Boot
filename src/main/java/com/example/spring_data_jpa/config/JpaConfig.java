package com.example.spring_data_jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.spring_data_jpa",
        transactionManagerRef = "transactionManager",
        entityManagerFactoryRef = "entityManager")
public class JpaConfig {

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
