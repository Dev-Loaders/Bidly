//package com.bidly.bidly.user;
//
//import com.bidly.bidly.job.Job;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//public class BidlyUserControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//    BidlyUser user = new BidlyUser("JWT_ID_1", "Test Tester", "test@test.com", null, null);
//
//    Job testJob = new Job("test title", "test description", "stockholm",
//            "https://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRyQm5KEbQq_UcD2ov-f3-HkPL49WielK_fvsCOh8EXmP-rsNP_KZnow56OTiOqVJl5",
//            true);
//
//    @Test
//    @Order(1)
//    public void postNewJobToUserTest() {
//        String endpoint = "http://localhost:" + port + "/api/users/" + user.getJwtId() + "/jobs";
//        WebClient webClient = webClientBuilder.baseUrl(endpoint).build();
//
//        Mono<Job> postResponse = webClient.post()
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(testJob)
//                .retrieve()
//                .bodyToMono(Job.class);
//
//        Job createdJob = postResponse.block();
//        Assertions.assertNotNull(createdJob);
//        Assertions.assertNotNull(createdJob.getJobId());
//        Assertions.assertEquals("test title", createdJob.getTitle());
//    }
//
//    @Test
//    @Order(2)
//    public void getSpecificUserTest(){
//        String endpoint = "http://localhost:" + port + "/api/users/" + user.getJwtId();
//        WebClient webClient = webClientBuilder.baseUrl(endpoint).build();
//        Mono<BidlyUser> response = webClient.get()
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(BidlyUser.class);
//
//        BidlyUser testUser = response.block();
//
//        assertNotNull(testUser);
//        assertEquals(user.getFullName(), testUser.getFullName());
//    }
//
//    @Test
//    @Order(2)
//    public void getAllJobsTest(){
//        String endpoint = "http://localhost:" + port + "/api/jobs";
//        WebClient webClient = webClientBuilder.baseUrl(endpoint).build();
//        Mono<List<Job>> response = webClient.get()
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(Job.class)
//                .collectList();
//
//        List<Job> testJobs = response.block();
//
//        assertNotNull(testJobs);
//        assertEquals(1, testJobs.size());
//        assertEquals(testJob.getTitle(), testJobs.get(0).getTitle());
//        assertEquals(1, testJobs.get(0).getJobId());
//    }
//
//    @Test
//    @Order(3)
//    public void getJobsForSpecificUserTest(){
//        String endpoint = "http://localhost:" + port + "/api/users/" + user.getJwtId();
//        WebClient webClient = webClientBuilder.baseUrl(endpoint).build();
//        Mono<BidlyUser> response = webClient.get()
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(BidlyUser.class);
////                .collectList();
//
//        BidlyUser testUser = response.block();
//
//        assertNotNull(testUser);
//        assertEquals(1, testUser.getJobs().size());
////        assertEquals(1, testJobs.get(0).getJobId());
//    }
//
//}
