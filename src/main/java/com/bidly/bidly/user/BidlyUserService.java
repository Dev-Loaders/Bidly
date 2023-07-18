package com.bidly.bidly.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BidlyUserService {

    private final BidlyUserRepository userRepo;

    @Autowired
    public BidlyUserService(BidlyUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public BidlyUser getUser(Long jwtId) {
        return userRepo.getUser(jwtId);
    }
}
