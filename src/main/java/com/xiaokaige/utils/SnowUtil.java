package com.xiaokaige.utils;

import com.xiaokaige.config.StlConfig;
import com.xiaokaige.snow.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zengkai
 * @date 2021/7/5 18:10
 */
@Component
public class SnowUtil {

    @Autowired
    private StlConfig stlConfig;

    private static StlConfig staticStlConfig;

    @PostConstruct
    public void postConstruct(){
        staticStlConfig = stlConfig;
    }

    /** 开始时间截 (2015-01-01) */
    private static final long twepoch = 1489111610226L;

    /** 机器id所占的位数 */
    private static final long workerIdBits = 5L;

    /** 数据标识id所占的位数 */
    private static final long dataCenterIdBits = 5L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识id，结果是31 */
    private static final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /** 序列在id中占的位数 */
    private static final long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    private static final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    private static final long dataCenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移22位(5+5+12) */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~31) */
    private static long workerId;

    /** 数据中心ID(0~31) */
    private static long dataCenterId;

    /** 毫秒内序列(0~4095) */
    private static long sequence = 0L;

    /** 上次生成ID的时间截 */
    private static long lastTimestamp = -1L;

    //private static SnowflakeIdWorker idWorker;

    /*static {
        idWorker = new SnowflakeIdWorker(getWorkId(),getDataCenterId());
    }*/

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    /*public SnowflakeIdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }*/

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized static long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    private static Long getWorkId(){
        /*String hostAddress = Inet4Address.getLocalHost().getHostAddress();
        int[] ints = StringUtils.toCodePoints(hostAddress);
        int sums = 0;
        for(int b : ints){
            sums += b;
        }*/

        return (long)(staticStlConfig.getProjectId() % 32);
    }

    private static Long getDataCenterId(){
        /*int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        for (int i: ints) {
            sums += i;
        }*/
        return (long)(staticStlConfig.getEnvId() % 32);
    }


    public static Long getUUID(){
        workerId = getWorkId();
        dataCenterId = getDataCenterId();
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        return nextId();
    }


}
