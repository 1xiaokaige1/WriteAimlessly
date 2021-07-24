package com.xiaokaige.generatorid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @Auther: zk
 * @Date: 2019/11/21 17:49
 * @Description:
 */
@Component
public class SnowFlake {
    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;  //机器标识占用的位数
    //private final static long DATACENTER_BIT = 5;//数据中心占用的位数
    private final static long PROJECT_BIT = 3; //项目标识
    private final static long ENVIRONMENT_BIT = 2; //环境标识

    /**
     * 每一部分的最大值
     */
    private final static long MAX_PROJECT_NUM = -1L ^ (-1L << PROJECT_BIT);
    private final static long MAX_ENVIRONMENT_NUM = -1L ^ (-1L << ENVIRONMENT_BIT);
    //private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long ENVIRONMENT_LEFT = SEQUENCE_BIT;
    //private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_LEFT;
    //private final static long PROJECT_LEFT = PROJECT_BIT + DATACENTER_LEFT;
    private final static long PROJECT_LEFT = ENVIRONMENT_BIT + ENVIRONMENT_LEFT;
    private final static long MACHINE_LEFT = PROJECT_BIT + PROJECT_LEFT;
    private final static long TIMESTMP_LEFT = MACHINE_BIT + MACHINE_LEFT;

    //@Value("${stl.id.generator.datacenterId}")
    //private long datacenterId;  //数据中心
    @Value("${stl.id.generator.projectId}")
    private long projectId;
    @Value("${stl.id.generator.environmentId}")
    private long environmentId;
    private long machineId;    //机器标识

    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳


    @PostConstruct
    public void verify() throws UnknownHostException {
        if (projectId > MAX_PROJECT_NUM || projectId < 0) {
            throw new IllegalArgumentException("项目值超过最大值");
        }

        if (environmentId > MAX_ENVIRONMENT_NUM || environmentId < 0) {
            throw new IllegalArgumentException("环境值超过最大值");
        }

        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("机器ID值超过最大值");
        }
        machineId = getWorkId();
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("时钟回拨异常");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | machineId << MACHINE_LEFT            //机器标识部分
                | projectId << PROJECT_LEFT
                | environmentId << ENVIRONMENT_LEFT
                //| datacenterId << DATACENTER_LEFT      //数据中心部分
                | sequence;                            //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private Long getWorkId() throws UnknownHostException {
        String hostAddress = Inet4Address.getLocalHost().getHostAddress();
        int[] ints = StringUtils.toCodePoints(hostAddress);
        int sums = 0;
        for (int b : ints) {
            sums += b;
        }

        return (long) (sums % 32);
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }


}





