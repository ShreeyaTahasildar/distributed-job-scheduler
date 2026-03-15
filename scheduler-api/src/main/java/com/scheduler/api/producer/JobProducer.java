package com.scheduler.api.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public JobProducer(KafkaTemplate<String,String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendJob(String jobId){

        kafkaTemplate.send("jobs",jobId);

    }

}