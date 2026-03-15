package com.scheduler.api.repository;

import com.scheduler.api.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatusAndScheduleTimeBefore(String status, Instant time);
}