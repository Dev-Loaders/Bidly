package com.bidly.bidly.user;

import com.bidly.bidly.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.net.URI;
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

    @GetMapping("/status")
    public ResponseEntity getStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "{userSubject}/jobs")
    public List<Job> getUserJobsByJwtId(@PathVariable String userSubject) {
        return service.getUserByJwtId(userSubject).getJobs();
    }

//    @GetMapping("/{userSubject}/jobs/completed")
//    public ResponseEntity<List<Job>> getCompletedUserJobs(@PathVariable String userSubject) {
//
//        return ResponseEntity.ok(service.getCompletedUserJobs(userSubject));
//    }

    @PostMapping("/{userSubject}/jobs")
    public ResponseEntity<Job> createJobPostForUser(@PathVariable String userSubject,
                                                  @RequestParam(value = "image", required = false) MultipartFile file,
                                                  @RequestParam("title") String title,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("location") String location,
                                                  @RequestParam("materials") String materialsStr) {

        try {
            Pair<URI, Job> createdJob = service.addJobPostToUser(userSubject, file, title, description, location, materialsStr);
            return ResponseEntity.created(createdJob.getFirst()).body(createdJob.getSecond());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
