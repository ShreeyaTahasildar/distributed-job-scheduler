package com.scheduler.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    private String jobType;

    private String payload;

    private Instant scheduleTime;

    private String status;

    private int retryCount;

    private int maxRetries;

    private Instant lastAttemptTime;

}