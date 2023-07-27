package com.bidly.bidly.user;

import com.bidly.bidly.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BidlyUserRepository {
    private final JpaBidlyUserRepository repo;

    @Autowired
    public BidlyUserRepository(JpaBidlyUserRepository repo) {
        this.repo = repo;
    }

    public BidlyUser getUserByJwtId(String userSubject) {
        return repo.findByJwtIdEquals(userSubject).orElse(null);
    }

    public void createUser(OidcUser oidcUser) {
        repo.save(new BidlyUser(oidcUser.getSubject(),
                oidcUser.getFullName(),
                oidcUser.getEmail()
                ));
    }

    public void updateUser(Job job, String userSubject) {
       BidlyUser user = repo.findByJwtIdEquals(userSubject)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
       user.addJobs(job);
       repo.save(user);
    }


//    public List<Job> geCompletedJobsByUserId(String userSubject) {
//       List<Job> jobs =  repo.findByJwtIdEquals(userSubject)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"))
//               .getJobs();
//
//        return jobs.stream()
//                .filter(Job::isCompleted)
//                .collect(Collectors.toList());
//
//    }
}
