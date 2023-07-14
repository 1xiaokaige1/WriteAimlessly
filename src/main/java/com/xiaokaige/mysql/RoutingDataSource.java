//package com.xiaokaige.mysql;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.HashMap;
//
///**
// * @author ly
// * @date 2021/11/10
// */
//
//@Primary
//@Configuration
//public class RoutingDataSource extends AbstractRoutingDataSource {
//
//    /**
//     * 只写数据源
//     */
//    @Resource(name = "master")
//    private DataSource master;
//
//    /**
//     * 只读数据库
//     */
//    @Resource(name = "slave")
//    private DataSource slave;
//
//    /**
//     * @return 返回threadLocal当前持有的数据源
//     */
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return DataSourceContextHolder.getDataSourceKey();
//    }
//
//    /**
//     * 设置数据源
//     */
//    @Override
//    public void afterPropertiesSet() {
//        HashMap<Object, Object> dataSourceMap = new HashMap<>(2);
//        dataSourceMap.put(DataSourceType.master, master);
//        dataSourceMap.put(DataSourceType.slave, slave);
//        setTargetDataSources(dataSourceMap);
//        setDefaultTargetDataSource(master);
//        super.afterPropertiesSet();
//    }
//}
