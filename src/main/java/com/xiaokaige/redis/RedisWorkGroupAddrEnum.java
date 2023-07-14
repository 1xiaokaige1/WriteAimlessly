package com.xiaokaige.redis;

/**
 * @author ly
 * @date 2022/4/15
 */
public enum RedisWorkGroupAddrEnum {

    /* 2022/4/15
     * master
     * 172.23.11.164  6379
     * slave
     * 172.23.11.129 6379
     * 172.20.21.200 6379
     * 172.20.20.47 6379
     *
     * 香港
     * 18.166.75.1	    172.20.20.47
     * 18.166.66.233	172.20.21.200
     *
     * 德国
     * 3.68.198.143	    172.23.11.164
     * 3.120.149.158	172.23.11.129
     */
    GER_1("172.23.11.164", "德国主机"),
    GER_2("172.23.11.129", "德国从机"),
    HK_1("172.20.21.200", "香港从机1"),
    HK_2("172.20.20.47", "香港从机2"),
    ;

    private final String addr;
    private final String desc;

    RedisWorkGroupAddrEnum(String addr, String desc) {
        this.addr = addr;
        this.desc = desc;
    }

    public String getAddr() {
        return addr;
    }

    public String getDesc() {
        return desc;
    }

    public static String hkRedisHostPrefix(){
        return "172.20";
    }

    public static String gerRedisHostPrefix(){
        return "172.23";
    }
}
