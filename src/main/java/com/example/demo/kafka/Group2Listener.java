package com.example.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Group2Listener {
    @KafkaListener(topicPartitions = { @TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "1" }) })
//    @KafkaListener(topics = "test-topic")
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("Partition2222接收的消息: " + record.value().toString());

    }

}
