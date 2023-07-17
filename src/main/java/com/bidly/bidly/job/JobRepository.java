package com.bidly.bidly.job;

import com.bidly.bidly.user.JpaBidlyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JobRepository {

    private final JpaJobRepository repo;

    @Autowired
    public JobRepository(JpaJobRepository repo) {
        this.repo = repo;
    }
}
