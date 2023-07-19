package com.bidly.bidly.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JobRepository {

    private final JpaJobRepository repo;

    @Autowired
    public JobRepository(JpaJobRepository repo) {
        this.repo = repo;
    }

    public Iterable<Job> getAllJobs() {
        return repo.findAll();
    }

    public Job createJob(String userSubject, JobRequest jobPost) {
        Job job = new Job(jobPost.title(), jobPost.description(),  jobPost.location(), jobPost.image_url(), jobPost.materials());
        System.out.println(job);
        repo.save(job);
        return job;
    }
}
