package com.eightbyte.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@Slf4j
@PropertySource("classpath:druid.properties")
public class MybatisConfig {

    @Autowired
    private Environment environment;

    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(environment.getProperty("express.datasource.url"));
        druidDataSource.setUsername(environment.getProperty("express.datasource.username"));
        druidDataSource.setPassword(environment.getProperty("express.datasource.password"));
        druidDataSource.setDriverClassName(environment.getProperty("express.datasource.driverClassName"));
        return druidDataSource;

    }


    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(druidDataSource());
            // 添加XML目录
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources1 = resolver.getResources("classpath:mybatis/mapper/*.xml");

            bean.setMapperLocations(resources1);
            bean.setConfigLocation(resolver.getResources("classpath:mybatis/mybatis-config.xml")[0]);
            return bean.getObject();
        } catch (Exception e) {
            log.error("error", e);
        }
        return null;
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource());

        return dataSourceTransactionManager;
    }

}
