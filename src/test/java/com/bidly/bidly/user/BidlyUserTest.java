package com.bidly.bidly.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BidlyUserTest {

    @Autowired
    BidlyService service;

    BidlyUser user = new BidlyUser(1L,"1", "Test Tester", "test@test.com");

    @Test
    void serviceTest() {
        BidlyUser userDB = service.getUser(1L);
        System.out.println(userDB.getFullName());
        Assertions.assertNotNull(userDB);
        assertEquals(user.getFullName(), userDB.getFullName());
        assertEquals(user.getJwtId(), userDB.getJwtId());
        assertEquals(user.getEmail(), userDB.getEmail());

    }

}