//package com.bidly.bidly.user;
//
//import com.bidly.bidly.job.JobService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ActiveProfiles("test")
//class BidlyUserTest {
//
//    @Autowired
//    BidlyUserService service;
//
//    @Autowired
//    JobService jobService;
//
//    BidlyUser user = new BidlyUser("JWT_ID_1", "Test Tester", "test@test.com", null, null);
//
//    @Test
//    void serviceTest() {
//        BidlyUser userDB = service.getUserByJwtId(user.getJwtId());
//        Assertions.assertNotNull(userDB);
//        assertEquals(user.getFullName(), userDB.getFullName());
//        assertEquals(user.getJwtId(), userDB.getJwtId());
//        assertEquals(user.getEmail(), userDB.getEmail());
//    }
//
//}