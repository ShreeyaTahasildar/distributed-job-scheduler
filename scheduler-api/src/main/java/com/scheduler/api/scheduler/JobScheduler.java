package com.scheduler.api.scheduler;

import com.scheduler.api.entity.Job;
import com.scheduler.api.repository.JobRepository;
import com.scheduler.api.producer.JobProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class JobScheduler {

    private final JobRepository repository;
    private final JobProducer producer;

    public JobScheduler(JobRepository repository, JobProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Scheduled(fixedRate = 5000)
    public void scheduleJobs(){

        List<Job> jobs = repository.findByStatusInAndScheduleTimeBefore(
                List.of("PENDING","RETRYING"),
                Instant.now()
        );

        for(Job job : jobs){

            producer.sendJob(job.getId().toString());

            job.setStatus("QUEUED");

            repository.save(job);

        }

    }

}