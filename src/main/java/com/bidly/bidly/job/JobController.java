package com.bidly.bidly.job;

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
    public List<Job> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/{jobId}")
    public Job getJobById(@PathVariable String jobId) {
        System.out.println(jobId);
        return service.getJobById(jobId);
    }

    @GetMapping("/hello")
    public String returnHello() {
        return "service.getAllJobs()";
    }
}
