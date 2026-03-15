package com.scheduler.api.service;

import com.scheduler.core.entity.Job;
import com.scheduler.api.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public Job save(Job job) {
        return repository.save(job);
    }

}