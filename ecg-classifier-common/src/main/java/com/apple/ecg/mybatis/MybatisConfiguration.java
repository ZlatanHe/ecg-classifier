package com.apple.ecg.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 1:07 PM
 */
@SpringBootConfiguration
@EnableConfigurationProperties
public class MybatisConfiguration {

    @Autowired
    @Bean
    public HikariDataSource hikariDataSource(DataSourceConfig dataSourceConfig) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceConfig.getUrl());
        dataSource.setUsername(dataSourceConfig.getUsername());
        dataSource.setPassword(dataSourceConfig.getPassword());
        return dataSource;
    }

    @Autowired
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(HikariDataSource hikariDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(hikariDataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.apple.ecg.**.mapper.db");
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig();
    }

}
