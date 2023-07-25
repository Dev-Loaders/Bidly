package com.bidly.bidly.job;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public Job getJobById(String jobId) {
        return jobRepo.getJobById(jobId);
    }

    public Job updateJob(String jobId, Job updatedJob) {

        Job originalJob = jobRepo.getJobById(jobId);
        if (originalJob == null) {
            return null;
        }

        updateIfExists(updatedJob::getTitle, originalJob::setTitle);
        updateIfExists(updatedJob::getDescription, originalJob::setDescription);
        updateIfExists(updatedJob::getLocation, originalJob::setLocation);
        updateIfExists(updatedJob::getImageUrl, originalJob::setImageUrl);
        updateIfExists(updatedJob::isMaterials, originalJob::setMaterials);
//        originalJob.setMaterials(updatedJob.isMaterials());

        return jobRepo.updateJob(originalJob);
    }

//    public Job completeJob(String jobId) {
//        Job job = jobRepo.getJobById(jobId);
//        if (job == null) {
//            return null;
//        }
//        job.setCompleted(true);
//        return job;
//    }


    private <T> void updateIfExists(Supplier<T> getter, Consumer<T> setter) {
        T value = getter.get();
        System.out.println(value);
        if (value != null && !(value instanceof String && ((String) value).isEmpty())) {
            setter.accept(value);
        }
    }
}
