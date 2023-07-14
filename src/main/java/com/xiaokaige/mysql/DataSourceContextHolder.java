//package com.xiaokaige.mysql;
//
///**
// * 线程持有对象
// * @author ly
// * @date 2021/11/10
// */
//
//public class DataSourceContextHolder {
//
//    /**
//     * 初始化默认设置为主库
//     */
//    private static final ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal.withInitial(() -> DataSourceType.master);
//
//    /**
//     * 设置当前线程持有的数据源key
//     * @param key 数据源key
//     */
//    public static void setDataSourceKey(Object key) {
//        CONTEXT_HOLDER.set(key);
//    }
//
//    /**
//     * 获取当前线程持有的数据源key
//     * @return 数据源key
//     */
//    public static Object getDataSourceKey() {
//        return CONTEXT_HOLDER.get();
//    }
//
//    /**
//     * 清除资源
//     */
//    public static void clearDataSourceKey() {
//        CONTEXT_HOLDER.remove();
//    }
//}