package com.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
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
public class Producer {
    private static volatile KafkaProducer<String, String> kafkaProducer;

    public static KafkaProducer createProducer() {
        if (kafkaProducer == null) {
            synchronized (Producer.class) {
                if (kafkaProducer == null) {
                    Properties props = new Properties();
                    //修改broker地址
                    props.put("bootstrap.servers", "localhost:9092");
                    props.put("acks", "all");
                    props.put("retries", 0);
                    props.put("batch.size", 16384);
                    props.put("key.serializer", StringSerializer.class.getName());
                    props.put("value.serializer", StringSerializer.class.getName());

                    props.put("security.protocol", "SASL_PLAINTEXT");
                    props.put("sasl.mechanism", "GSSAPI");
                    props.put("sasl.kerberos.service.name", "kafka");

                    kafkaProducer = new KafkaProducer<String, String>(props);
                }
            }
        }

        return kafkaProducer;
    }
}
