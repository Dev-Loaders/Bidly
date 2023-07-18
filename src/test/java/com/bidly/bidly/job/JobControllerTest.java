package com.bidly.bidly.job;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class JobControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    public void testGetRequestToJobEndpoint() {
        String endpoint = "http://localhost:" + port + "/api/jobs";
        WebClient webClient = webClientBuilder.baseUrl(endpoint).build();
        Mono<List<Job>> response = webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                .bodyToFlux(Job.class)
                .collectList();

        List<Job> testJobs = response.block();

        assertNotNull(testJobs);
        assertEquals(2, testJobs.size());
        assertEquals(1, testJobs.get(0).getId());
        assertEquals(2, testJobs.get(1).getId());
    }
}
