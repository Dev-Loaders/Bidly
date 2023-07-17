package com.bidly.bidly.job;

import com.bidly.bidly.user.BidlyUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService service;

    JobController(JobService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Job> getUser() {
        return service.getAllJobs();
    }
}
