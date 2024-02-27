package com.example.assignment.service.implementation;

import com.example.assignment.service.DataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class DataServiceImpl implements DataService {

    @Value("${jsonplaceholder.users.url}")
    private String usersUrl;

    @Value("${jsonplaceholder.posts.url}")
    private String postsUrl;

    private final RestTemplate restTemplate;

    public DataServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchDataConcurrently() throws ExecutionException, InterruptedException {
        Instant start = Instant.now();
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(fetchUserData(), fetchPostData());
        combinedFuture.get();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        return "Data fetched concurrently in " + duration.toMillis() + " milliseconds.";
    }

    private CompletableFuture<String> fetchUserData() {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject(usersUrl, String.class));
    }

    private CompletableFuture<String> fetchPostData() {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject(postsUrl, String.class));
    }
}
