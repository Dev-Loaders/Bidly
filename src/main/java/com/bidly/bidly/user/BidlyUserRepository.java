package com.bidly.bidly.user;

import com.bidly.bidly.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Repository;

@Repository
public class BidlyUserRepository {
    private final JpaBidlyUserRepository repo;

    @Autowired
    public BidlyUserRepository(JpaBidlyUserRepository repo) {
        this.repo = repo;
    }

    public BidlyUser getUserId(Long id) {
        System.out.println(repo.findById(id).orElse(null));
        return repo.findById(id).orElse(null);
    }

    public BidlyUser getUserByJwtId(String userSubject) {
        return repo.findByJwtIdEquals(userSubject).orElse(null);
    }

    public void createUser(OidcUser oidcUser) {
        repo.save(new BidlyUser(oidcUser.getSubject(),
                oidcUser.getFullName(),
                oidcUser.getEmail(),
                null,
                null));
    }

    public void updateUser(Job job, String userSubject) {
//        repo.updateJobsByJwtIdEquals(job, userSubject);
       BidlyUser user = repo.findByJwtIdEquals(userSubject).orElseThrow();
       user.addJobs(job);
       repo.save(user);
    }
}
