package com.producer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author sunxiaoxiao
 * @version v1.0
 * @date 2019/12/26 11:28 下午
 * @since JDK 1.8
 */
public class ConsumerStarter {
    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = Consumer.createConsumer();

        consumer.subscribe(Arrays.asList("topicOne"));

        ConsumerRecords<String, String> msgList;
        for (;;) {
            msgList = consumer.poll(1000);

            if (msgList == null || msgList.count() == 0) {
                continue;
            }

            for (ConsumerRecord<String, String> record : msgList) {
                System.out.println("get date: " + record.key() + "   " + record.value());

            }
            consumer.commitSync();
        }
    }
}
