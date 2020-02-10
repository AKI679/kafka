package com.spring.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author sunxiaoxiao
 * @version v1.0
 * @date 2020/2/10 9:25 下午
 * @since JDK 1.8
 */
@Configuration
public class KafkaConfig {
    @Value("${kafka.group.id}")
    private String groupId;

    @Value("${kafka.connectUrl}")
    private String brokerUrl;

    @Value("${kafka.auto.commit}")
    private String autoCommit;

    @Value("${security.protocol}")
    private String protocol;

    @Value("${sasl.mechanism}")
    private String mechanism;

    @Value("${sasl.kerberos.service.name}")
    private String kerberosService;


    @Primary
    @Bean(name = "kafkaListenerContainerFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConcurrency(6);
        containerFactory.setBatchListener(true);
        containerFactory.setConsumerFactory(kafkaScConsumerFactory());
        containerFactory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
        return containerFactory;
    }

    @Bean(name = "kafkaScConsumerFactory")
    public DefaultKafkaConsumerFactory kafkaScConsumerFactory() {
        return getMedusaKafkaConsumerFactory();
    }

    private DefaultKafkaConsumerFactory getMedusaKafkaConsumerFactory() {
        Map<String, String> config = new HashMap<>(16);
        config.put("bootstrap.servers", brokerUrl);
        config.put("group.id", groupId);
        config.put("enable.auto.commit", "false");
        config.put("session.timeout.ms", "15000");
        config.put("enable.auto.commit", autoCommit);
//        config.put("max.partition.fetch.bytes", maxFetchBytes);
//        config.put("max.poll.records", maxPollRecords);
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("auto.offset.reset", "earliest");
        config.put("security.protocol", protocol);
        config.put("sasl.mechanism", mechanism);
        config.put("sasl.kerberos.service.name", kerberosService);

        DefaultKafkaConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory(config);
        return consumerFactory;
    }


}
