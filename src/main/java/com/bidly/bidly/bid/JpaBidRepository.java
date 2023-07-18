package com.bidly.bidly.bid;

import com.bidly.bidly.job.Job;
import org.springframework.data.repository.CrudRepository;

public interface JpaBidRepository extends CrudRepository<Bid, Long> {
}
