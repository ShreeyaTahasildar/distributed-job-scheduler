package com.scheduler.worker.service;

import com.scheduler.worker.repository.JobRepository;
import com.scheduler.core.entity.Job;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JobExecutionService {

    private final JobRepository repository;

    public JobExecutionService(JobRepository repository) {
        this.repository = repository;
    }

    public void executeJob(Long jobId){

        Job job = repository.findById(jobId).orElseThrow();

        job.setStatus("RUNNING");
        repository.save(job);

        try {

            System.out.println("Executing job " + jobId);

            Thread.sleep(2000);

            job.setStatus("SUCCESS");

        } catch(Exception e){

            job.setRetryCount(job.getRetryCount() + 1);

            if(job.getRetryCount() >= job.getMaxRetries()){

                job.setStatus("FAILED");

            } else {

                job.setStatus("RETRYING");

                long delay = (long) Math.pow(2, job.getRetryCount());

                job.setScheduleTime(
                        Instant.now().plusSeconds(delay)
                );

            }

        }

        repository.save(job);
    }
}