package com.example.spring_data_jpa.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ReadingDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.read")
    public DataSourceProperties readingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "readDataSource")
    public DataSource readingDataSource() {
        return readingDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
