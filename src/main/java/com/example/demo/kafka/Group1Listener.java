package com.example.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
/**
 * 两个接收端可以同时收到相同消息
 */
public class Group1Listener {

    @KafkaListener(topicPartitions = { @TopicPartition(topic ="${kafka.consumer.topic}",partitions = { "0" }) })
//    @KafkaListener(topics = "test-topic")
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("Partition1111接收的消息: " + record.value().toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
