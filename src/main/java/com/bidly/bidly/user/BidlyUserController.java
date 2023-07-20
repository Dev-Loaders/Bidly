package com.bidly.bidly.user;

import com.bidly.bidly.job.Job;
import com.bidly.bidly.job.JobRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/{userSubject}/jobs")
    public ResponseEntity<?> createJobPostForUser(@PathVariable String userSubject,
//                                                  @AuthenticationPrincipal OidcUser oidcUser,
                                                  @RequestParam("image") MultipartFile file,
                                                  @RequestParam("title") String title,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("location") String location,
                                                  @RequestParam("materials") String materialsStr) {
        boolean materials = Boolean.parseBoolean(materialsStr);
        JobRequestDto job = new JobRequestDto(title, description, location, "http-test", materials);
        try {
            return service.addJobPostToUser("109019647377227797987", null , job, file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
