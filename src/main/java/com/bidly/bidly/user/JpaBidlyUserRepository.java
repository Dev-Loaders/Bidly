package com.bidly.bidly.user;

import org.springframework.data.repository.CrudRepository;

public interface JpaBidlyUserRepository extends CrudRepository<BidlyUser, Long> {
}
