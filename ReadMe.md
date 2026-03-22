# Distributed Job Scheduler

A distributed, fault-tolerant job scheduling system built to understand how real-world backend infrastructure handles asynchronous execution, retries, and scalability.

---

## Overview

This project simulates a production-style job scheduler where:

* clients submit jobs via an API
* jobs are persisted for durability
* a scheduler detects runnable jobs
* jobs are dispatched to a queue
* distributed workers execute them asynchronously
* failures are retried with exponential backoff
* system metrics are monitored in real-time

---

## Architecture

```
Client
  |
  v
scheduler-api
  |
  v
Postgres (job store)
  |
  v
Scheduler Loop
  |
  v
Kafka (job queue)
  |
  v
scheduler-worker (distributed)
  |
  v
Execution + Retry Logic
```

---

## Components

### 1. Scheduler API (`scheduler-api`)

* Accepts job submissions via REST
* Persists jobs in Postgres
* Periodically scans for runnable jobs
* Publishes jobs to Kafka

---

### 2. Scheduler Core (`scheduler-core`)

* Shared domain models (`Job`, `JobStatus`)
* Retry logic & scheduling policies
* Used by both API and Worker

---

### 3. Worker Service (`scheduler-worker`)

* Consumes jobs from Kafka
* Executes jobs asynchronously
* Updates job status (RUNNING → SUCCESS / FAILED)
* Handles retry logic

---

### 4. Infrastructure

* Kafka → decouples producers and consumers
* Postgres → durable job storage
* Docker Compose → runs entire system locally
* Prometheus + Grafana → monitoring and metrics

---

## Job Lifecycle

```
PENDING
  ↓
QUEUED
  ↓
RUNNING
  ↓
SUCCESS
```

Failure path:

```
RUNNING
  ↓
RETRYING
  ↓
QUEUED
  ↓
RUNNING
  ↓
FAILED (after max retries)
```

---

## ⚡ Key Features

### Durable Job Storage

Jobs are persisted before being queued to prevent data loss.

---

### Asynchronous Processing

Kafka-based queue enables:

* decoupling between API and workers
* horizontal scalability
* buffering during traffic spikes

---

### Distributed Workers

Multiple workers can consume jobs concurrently.

---

### Retry with Exponential Backoff

Failures are retried with increasing delay:

```
2s → 4s → 8s → ...
```

Prevents system overload during repeated failures.

---

### Fault Tolerance

* failures are expected and handled
* jobs are not lost on crashes
* retry limits prevent infinite loops

---

### Observability

* metrics exposed via `/actuator/prometheus`
* Prometheus scrapes metrics
* Grafana visualizes system behavior

---

## Metrics & Monitoring

Metrics tracked include:

* API request count
* JVM memory usage
* CPU usage
* job processing activity

Access:

* Prometheus → http://localhost:9090
* Grafana → http://localhost:3000

---

## Running the System

### 1. Build the project

```
mvn clean package
```

---

### 2. Start services

```
docker compose up
```

This starts:

* Postgres
* Kafka + Zookeeper
* scheduler-api
* scheduler-worker
* Prometheus
* Grafana

---

### 3. Submit a Job

```
curl -X POST http://localhost:8080/jobs \
-H "Content-Type: application/json" \
-d '{
 "jobType":"email",
 "payload":"user=123",
 "scheduleTime":"2030-03-20T10:00:00Z"
}'
```

---

## Design Decisions & Tradeoffs

### 1. DB Polling vs Event-Driven Scheduling

* Used polling (every few seconds) for simplicity
* Tradeoff: slight delay vs easier implementation

---

### 2. Queue-Based Execution

* Kafka used to decouple scheduling from execution
* Improves scalability and resilience

---

### 3. Retry Strategy

* exponential backoff avoids retry storms
* capped retries prevent infinite loops

---

### 4. Service Separation

* API and Worker are separate services
* allows independent scaling and failure isolation

---

## Future Improvements

* idempotent job execution
* dead-letter queue for failed jobs
* priority-based scheduling
* distributed locking / leader election
* better scheduling algorithms (e.g., time wheels)

---

## Key Learnings

* failures are not edge cases — they are the system
* observability is critical in distributed systems
* separating concerns (API vs worker) simplifies scaling
* persistence before queueing is essential for reliability

---

## Tech Stack

* Java + Spring Boot
* Apache Kafka
* PostgreSQL
* Docker / Docker Compose
* Prometheus
* Grafana

---

## Why This Project

This project was built to move beyond theoretical system design and understand:

* how distributed systems behave under failure
* how queues enable scalability
* how retry strategies impact system stability
* how observability helps debug real systems

---

## Contributions / Feedback

Happy to discuss improvements, tradeoffs, or alternative designs.
