package com.bidly.bidly.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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

    public Job createJob(String fileUrl, String title, String description,
                         String location, boolean materials) {
        Job job = new Job(title, description,  location, fileUrl, materials);
        repo.save(job);
        return job;
    }

    public Job getJobById(String jobId) {
        Long jobIdLong = Long.parseLong(jobId);
        return repo.findJobByJobId(jobIdLong);
    }

    public Job updateJob(Job job){
        repo.save(job);
        return job;
    }
}
