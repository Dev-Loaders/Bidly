package com.bidly.bidly.user;

import com.bidly.bidly.job.Job;
import com.bidly.bidly.job.JobRequestDto;
import com.bidly.bidly.job.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Service
@Transactional
public class BidlyUserService {

    private final BidlyUserRepository userRepo;
    private final JobRepository jobRepo;

    @Autowired
    public BidlyUserService(BidlyUserRepository userRepo, JobRepository jobRepo) {
        this.userRepo = userRepo;
        this.jobRepo = jobRepo;
    }

    public BidlyUser getUserByJwtId(String userSubject) {
        return userRepo.getUserByJwtId(userSubject);
    }

    public void createUser(OidcUser oidcUser) {
        userRepo.createUser(oidcUser);
    }

    public ResponseEntity<Object> addJobPostToUser(String userSubject, OidcUser oidcUser, JobRequestDto jobPost, MultipartFile file) throws IOException {

        if (!oidcUser.getSubject().equals(userSubject)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This action is forbidden.");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String directory = "src/main/resources/static/job-images/";
        String filePath = Paths.get(directory, fileName).toString();
        String fileUrl = "job-images/" + fileName;

        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }

        Job job = jobRepo.createJob(jobPost, fileUrl);
        userRepo.updateUser(job, userSubject);
        return ResponseEntity.ok(job);

    }
}
