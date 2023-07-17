package com.bidly.bidly;

import com.bidly.bidly.user.BidlyService;
import com.bidly.bidly.user.BidlyUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BidlyApplicationTests {

    @Autowired
    BidlyService service;

    @Test
    void serviceTest() {
        BidlyUser var = service.getUser(1L);
        assertNotEquals(var, 1L);

    }

}