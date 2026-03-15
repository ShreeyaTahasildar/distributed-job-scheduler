package com.scheduler.api.model;

import java.time.Instant;

public class JobRequest {

    private String jobType;
    private String payload;
    private Instant scheduleTime;

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Instant getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Instant scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}