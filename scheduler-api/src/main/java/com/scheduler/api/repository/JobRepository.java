package com.scheduler.api.repository;

import com.scheduler.core.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatusAndScheduleTimeBefore(String status, Instant time);
    List<Job> findByStatusInAndScheduleTimeBefore(
            List<String> statuses,
            Instant time);
}