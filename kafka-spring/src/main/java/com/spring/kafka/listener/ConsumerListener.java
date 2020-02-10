package com.spring.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.BatchAcknowledgingMessageListener;
import org.springframework.kafka.listener.adapter.AbstractMessageListenerAdapter;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Description:
 *
 * @author sunxiaoxiao
 * @version v1.0
 * @date 2020/2/10 9:45 下午
 * @since JDK 1.8
 */
@Service
public class ConsumerListener implements BatchAcknowledgingMessageListener<Integer, String> {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = "${kafka.topic}")
    @Override
    public void onMessage(List<ConsumerRecord<Integer, String>> consumerRecords, Acknowledgment acknowledgment) {
        consumerRecords.forEach(record -> {
            logger.info("value:{}", record.value());
        });
    }


}
