package com.scheduler.api.controller;

import com.scheduler.core.entity.Job;
import com.scheduler.api.model.JobRequest;
import com.scheduler.api.service.JobService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public Job submitJob(@RequestBody JobRequest request) {

        Job job = new Job();

        job.setJobType(request.getJobType());
        job.setPayload(request.getPayload());
        job.setScheduleTime(request.getScheduleTime());
        job.setStatus("PENDING");
        job.setRetryCount(0);
        job.setMaxRetries(3);
        return jobService.save(job);
    }

}