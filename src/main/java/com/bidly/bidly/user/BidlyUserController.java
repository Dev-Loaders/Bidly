package com.bidly.bidly.user;

import com.bidly.bidly.job.Job;
import com.bidly.bidly.job.JobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class BidlyUserController {

    private final BidlyUserService service;

    @Autowired
    public BidlyUserController(BidlyUserService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping(path = "{userSubject}/jobs")
    public List<Job> getUserJobsByJwtId(@PathVariable String userSubject) {
        return service.getUserByJwtId(userSubject).getJobs();
    }

    @GetMapping(path = "{userSubject}")
    public BidlyUser getUserByJwtId(@PathVariable String userSubject) {
        return service.getUserByJwtId(userSubject);
    }

    @PostMapping(path = "{userSubject}/jobs")
    public ResponseEntity<Job> createJobPostForUser(@PathVariable String userSubject,
                                                    //  @AuthenticationPrincipal OidcUser oidcUser,
                                                    @RequestBody JobRequest job) {
        return service.addJobPostToUser(userSubject, job);
    }

}
