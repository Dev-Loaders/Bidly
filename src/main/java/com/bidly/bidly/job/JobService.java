package com.bidly.bidly.job;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class JobService {

    private final JobRepository jobRepo;

    @Autowired
    public JobService(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    public List<Job> getAllJobs() {
        List jobs = new ArrayList<>();
        jobRepo.getAllJobs().forEach(jobs::add);
        return jobs;
    }
}
