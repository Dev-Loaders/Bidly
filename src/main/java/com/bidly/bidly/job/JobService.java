package com.bidly.bidly.job;

import com.bidly.bidly.user.BidlyUser;
import com.bidly.bidly.user.BidlyUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JobService {

    private final JobRepository jobRepo;

    @Autowired
    public JobService(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    public BidlyUser getAllJobs() {
    }
}
