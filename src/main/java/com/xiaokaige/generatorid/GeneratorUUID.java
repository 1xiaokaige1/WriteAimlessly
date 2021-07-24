package com.xiaokaige.generatorid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GeneratorUUID {

    @Autowired
    private SnowFlake snowFlake;

    private static SnowFlake snowFlakeTwo;

    @PostConstruct
    public void postConstruct(){
        snowFlakeTwo = snowFlake;
    }

    public static Long getUUID(){
        return snowFlakeTwo.nextId();
    }
}
