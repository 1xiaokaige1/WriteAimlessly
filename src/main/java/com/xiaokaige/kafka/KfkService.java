package com.xiaokaige.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author lpg
 * @description: kfk生产者与消费者
 * @date 2020-12-2317:57
 */

@Service
@Slf4j
public class KfkService {
    @Autowired
    private KafkaTemplate<Integer,String> kafkaTemplate;


    //消费者：监听topic1，groupId1
    /*@KafkaListener(topics = {"rongcloud-p2-e0"})
    public void consumer1(ConsumerRecord<Integer,String> record){
        log.info("consumer1 kfk consume message start...");
        log.info("consumer1 kfk consume message topic:{},msg:{}",record.topic(),record.value());
        log.info("consumer1 kfk consume message end...");
    }*/
    //消费者：监听topic1，groupId2
    @KafkaListener(topics = {"topic1"},groupId = "groupId" + "#{T(java.util.UUID).randomUUID()}")
    public void consumer3(ConsumerRecord<Integer,String> record){
        log.info("consumer3 kfk consume message start...");
        log.info("consumer3 kfk consume message topic:{},msg:{}",record.topic(),record.value());
        log.info("consumer3 kfk consume message end...");
    }
    //消费者：监听topic1，groupId2
    @KafkaListener(topics = {"topic1"},groupId = "groupId" + "#{T(java.util.UUID).randomUUID()}")
    public void consumer2(ConsumerRecord<Integer,String> record){
        log.info("consumer2 kfk consume message start...");
        log.info("consumer2 kfk consume message topic:{},msg:{}",record.topic(),record.value());
        log.info("consumer2 kfk consume message end...");
    }

    //生产者
    public void sendMsg(String topic , String msg){
        log.info("开始发送kfk消息,topic:{},msg:{}",topic,msg);
        kafkaTemplate.send(topic, msg);
        /*ListenableFuture<SendResult<Integer, String>> sendMsg = kafkaTemplate.send(topic, msg);

        //消息确认
        sendMsg.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("send error,ex:{},topic:{},msg:{}",throwable,topic,msg);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> stringStringSendResult) {
                log.info("send success,topic:{},msg:{}",topic,msg);
            }
        });*/
        log.info("kfk send end!");
    }

}
