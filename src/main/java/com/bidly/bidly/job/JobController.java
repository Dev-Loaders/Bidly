package com.bidly.bidly.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import static com.bidly.bidly.util.HelperMethods.getFileUrl;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {
    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {

        List<Job> jobs = service.getAllJobs();
        if (jobs == null || jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable String jobId) {

        Job job = service.getJobById(jobId);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(job);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable String jobId,
                                         @RequestParam(value = "image", required = false) MultipartFile file,
                                         @RequestParam("title") String title,
                                         @RequestParam("description") String description,
                                         @RequestParam("location") String location,
                                         @RequestParam("materials") String materialsStr) {
        try {
            String fileUrl = getFileUrl(file);
            Job updatedJob = new Job(title, description, location, fileUrl, Boolean.parseBoolean(materialsStr));
            Job job = service.updateJob(jobId, updatedJob);
            if (job == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.accepted().body(job);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PatchMapping("/{jobId}")
    public ResponseEntity<Job> completeJob(@PathVariable String jobId){
        return ResponseEntity.accepted().body(service.completeJob(jobId));
    }


}
