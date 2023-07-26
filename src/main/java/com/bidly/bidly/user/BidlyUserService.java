package com.bidly.bidly.user;

import com.bidly.bidly.azure.AzureBlobService;
import com.bidly.bidly.job.Job;
import com.bidly.bidly.job.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Service
@Transactional
public class BidlyUserService {

    private final BidlyUserRepository userRepo;
    private final JobRepository jobRepo;
    private final AzureBlobService azureBlobService;

    @Autowired
    public BidlyUserService(BidlyUserRepository userRepo, JobRepository jobRepo, AzureBlobService azureBlobService) {
        this.userRepo = userRepo;
        this.jobRepo = jobRepo;
        this.azureBlobService = azureBlobService;
    }

    public BidlyUser getUserByJwtId(String userSubject) {
        return userRepo.getUserByJwtId(userSubject);
    }

    public void createUser(OidcUser oidcUser) {
        userRepo.createUser(oidcUser);
    }

    public Pair<URI, Job> addJobPostToUser(String userSubject, MultipartFile file, String title, String description,
                                           String location, String materialsStr) throws IOException {

//        if (!oidcUser.getSubject().equals(userSubject)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This action is forbidden.");
//        }

        boolean materials = Boolean.parseBoolean(materialsStr);
        String azureFileUrl = azureBlobService.uploadMultipartFileToBlob(file, file.getOriginalFilename() + UUID.randomUUID());
        Job job = jobRepo.createJob(azureFileUrl, title, description, location, materials);
        userRepo.updateUser(job, userSubject);
        URI locationUri = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path("/api/jobs/{id}")
                .buildAndExpand(job.getJobId())
                .toUri();
        return Pair.of(locationUri, job);

    }

//    public List<Job> getCompletedUserJobs(String userSubject) {
//        return userRepo.geCompletedJobsByUserId(userSubject);
//    }
}
