package com.example.spring_data_jpa.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class WritingDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.write")
    public DataSourceProperties writingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writingDataSource() {
        return writingDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
