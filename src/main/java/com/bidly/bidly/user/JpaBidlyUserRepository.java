package com.bidly.bidly.user;

import com.bidly.bidly.job.Job;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface JpaBidlyUserRepository extends CrudRepository<BidlyUser, Long> {
    Optional<BidlyUser> findByJwtIdEquals(String jwtId);
}
