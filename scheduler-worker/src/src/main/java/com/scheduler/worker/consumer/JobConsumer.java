package com.scheduler.worker.consumer;

import com.scheduler.worker.service.JobExecutionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JobConsumer {

    private final JobExecutionService jobExecutionService;

    public JobConsumer(JobExecutionService jobExecutionService) {
        this.jobExecutionService = jobExecutionService;
    }

    @KafkaListener(topics = "jobs", groupId = "scheduler-workers")
    public void consume(String jobId){

        jobExecutionService.executeJob(Long.parseLong(jobId));

    }

}