package com.bidly.bidly.job;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class JobControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    public void testGetRequestToJobEndpoint() {
        Long jobId = 1L;
        String endpoint = "http://localhost:" + port + "/api/job/" + jobId;

        WebClient webClient = webClientBuilder.baseUrl(endpoint).build();

        ResponseSpec responseSpec = webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        responseSpec.expectStatus().isOk();

        Job job = responseSpec.returnResult(Job.class).getResponseBody();

        assertNotNull(job);
        assertEquals(jobId, job.getId());
    }
}


}
