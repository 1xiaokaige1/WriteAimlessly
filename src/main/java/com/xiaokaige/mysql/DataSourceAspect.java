//package com.xiaokaige.mysql;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * 数据库切面保证查询方法调用的是主库,修改调用从库
// *
// * @author ly
// * @date 2021/11/10
// */
//
//@Aspect
//@Order(0)
//@Lazy(false)
//@Component
//@ConditionalOnBean({RoutingDataSource.class, DataSourceConfig.class})
//public class DataSourceAspect {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);
//
//    private static DataSourceType logType = DataSourceType.master;
//    /**
//     * Mapper所有查询接口前缀
//     */
//    private static final String GET_PREFIX = "get";
//    private static final String FIND_PREFIX = "find";
//    private static final String SELECT_PREFIX = "select";
//    private static final String SEARCH_PREFIX = "search";
//    private static final String FILTER_PREFIX = "filter";
//
//    /**
//     * 环绕切面保证调用方法并在线程调用完毕之后清除本地ThreadLocal变量
//     * 从库为读取数据库
//     *
//     * @param point 切点
//     * @return 方法调用返回值
//     */
//    @Around("execution( * com.stl.bv..mapper..*(..))")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        Boolean isQueryMethod = isSelectMethod(point.getSignature().getName());
//        DataSourceType type = isQueryMethod ? DataSourceType.slave : DataSourceType.master;
//        DataSourceContextHolder.setDataSourceKey(type);
//        try {
//            return point.proceed();
//        } finally {
//            DataSourceContextHolder.clearDataSourceKey();
//        }
//    }
//
//    /**
//     * 判定监控的方法是否是select方法
//     *
//     * @param methodName 方法名
//     * @return 判断结果
//     */
//    private Boolean isSelectMethod(String methodName) {
//        return methodName.startsWith(GET_PREFIX) ||
//                methodName.startsWith(FIND_PREFIX) ||
//                methodName.startsWith(SELECT_PREFIX) ||
//                methodName.startsWith(SEARCH_PREFIX) ||
//                methodName.startsWith(FILTER_PREFIX);
//    }
//
//    private void printLog(DataSourceType type, Signature signature) {
//        if (!logType.equals(type)) {
//            LOGGER.info("切换数据源到【{}】在方法【{}】中", type, signature);
//        }
//        logType = type;
//    }
//}
