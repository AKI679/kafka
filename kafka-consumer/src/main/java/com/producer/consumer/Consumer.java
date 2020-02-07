package com.producer.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Description:
 *
 * @author sunxiaoxiao
 * @version v1.0
 * @date 2019/12/26 11:11 下午
 * @since JDK 1.8
 */
public class Consumer {
    private static volatile KafkaConsumer<String, String> kafkaConsumer;

    public static KafkaConsumer<String, String> createConsumer() {
        if (kafkaConsumer == null) {
            synchronized (Consumer.class) {
                if (kafkaConsumer == null) {
                    Properties props = new Properties();
                    props.put("bootstrap.servers", "localhost:9092");
                    props.put("group.id", "GroupOne");
                    props.put("enable.auto.commit", "true");
                    props.put("auto.commit.interval.ms", "1000");
                    props.put("session.timeout.ms", "30000");
                    props.put("max.poll.records", 1000);
                    props.put("auto.offset.reset", "earliest");
                    props.put("key.deserializer", StringDeserializer.class.getName());
                    props.put("value.deserializer", StringDeserializer.class.getName());
                    kafkaConsumer = new KafkaConsumer<String, String>(props);
                }
            }
        }

        return kafkaConsumer;
    }
}
