package com.bidly.bidly.bid;

import com.bidly.bidly.job.Job;
import com.bidly.bidly.user.BidlyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaBidRepository extends CrudRepository<Bid, Long> {
    List<Bid> findByUserId(String userSubject);
}
