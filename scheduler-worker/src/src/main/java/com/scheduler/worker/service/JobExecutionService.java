package com.scheduler.worker.service;

import com.scheduler.worker.repository.JobRepository;
import com.scheduler.worker.entity.Job;
import org.springframework.stereotype.Service;

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

        try{

            System.out.println("Executing job " + jobId);

            // simulate execution
            Thread.sleep(2000);

            job.setStatus("SUCCESS");

        }
        catch(Exception e){

            job.setStatus("FAILED");

        }

        repository.save(job);

    }

}