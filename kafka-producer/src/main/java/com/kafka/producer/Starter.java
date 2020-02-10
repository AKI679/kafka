package com.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Description:
 *
 * @author sunxiaoxiao
 * @version v1.0
 * @date 2019/12/26 11:19 下午
 * @since JDK 1.8
 */
public class Starter {
    public static void main(String[] args) {
        //指定jaas配置文件
        System.setProperty("java.security.auth.login.config", "/usr/ndp/current/kafka/");
        KafkaProducer<String, String> kafkaProducer = Producer.createProducer();

        for (int var1 = 0; var1 < 100; ++var1) {
            kafkaProducer.send(new ProducerRecord<String, String>("topicOne", "test", "test" + var1));
        }

    }
}
