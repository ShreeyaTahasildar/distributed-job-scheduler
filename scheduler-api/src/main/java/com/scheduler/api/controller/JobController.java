package com.scheduler.api.controller;

import com.scheduler.api.model.JobRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @PostMapping
    public String submitJob(@RequestBody JobRequest request) {

        return "Job accepted: " + request.getJobType();
    }

}