package com.bidly.bidly.job;

import org.springframework.data.repository.CrudRepository;

public interface JpaJobRepository extends CrudRepository<Job, Long> {
    Job findJobByJobId(Long jobId);
}
