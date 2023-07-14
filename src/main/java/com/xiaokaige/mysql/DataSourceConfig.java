//package com.xiaokaige.mysql;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * 数据源配置
// *
// * @author ly
// * @date 2021/11/10
// */
//
//@Configuration
//public class DataSourceConfig {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
//
//    @Bean(name = "master")
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource createMaster(){
//        LOGGER.info("【开始初始主库】");
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "slave")
//    @ConfigurationProperties(prefix = "spring.datasource.slave")
//    public DataSource createSlave(){
//        LOGGER.info("【开始初始从库】");
//        return DataSourceBuilder.create().build();
//    }
//}
