package com.scheduler.worker.repository;

import com.scheduler.worker.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {

}