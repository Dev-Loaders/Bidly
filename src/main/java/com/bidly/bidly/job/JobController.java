package com.bidly.bidly.job;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(service.getAllJobs());
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable String jobId) {
        System.out.println(jobId);
        return ResponseEntity.ok(service.getJobById(jobId));
    }
}
