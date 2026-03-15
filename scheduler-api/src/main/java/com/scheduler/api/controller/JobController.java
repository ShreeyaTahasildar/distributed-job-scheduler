package com.scheduler.api.controller;

import com.scheduler.api.entity.Job;
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
    public String submitJob(@RequestBody JobRequest request) {

        Job job = new Job();

        job.setJobType(request.getJobType());
        job.setPayload(request.getPayload());
        job.setScheduleTime(request.getScheduleTime());
        job.setStatus("PENDING");

        Job savedJob = jobService.save(job);

        return "Job accepted: " + savedJob.getId();
    }

}