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

    public Job createJob(JobRequestDto jobPost, String fileUrl) {
        Job job = new Job(jobPost.title(), jobPost.description(),  jobPost.location(), jobPost.image_url(), jobPost.materials());
        job.setImageUrl(fileUrl);
        System.out.println(job);
        repo.save(job);
        return job;
    }

    public Job getJobById(String jobId) {
        Long jobIdLong = Long.parseLong(jobId);
        return repo.findJobByJobId(jobIdLong);
    }
}
