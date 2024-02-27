package com.example.assignment.interceptor;

import com.example.assignment.annotation.RateLimited;
import com.example.assignment.util.TokenBucket;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RateLimitInterceptor {
    private final Map<String, TokenBucket> tokenBuckets = new HashMap<>();

    @Around("@annotation(rateLimited)")
    public Object intercept(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        String key = rateLimited.key();
        int tokens = rateLimited.tokens();
        int timeWindowInSeconds = rateLimited.timeWindowInSeconds();

        TokenBucket tokenBucket = tokenBuckets.computeIfAbsent(key, k -> new TokenBucket(tokens, timeWindowInSeconds));
        if (tokenBucket.tryConsume()) {
            return joinPoint.proceed();
        } else {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Rate limit exceeded");
        }
    }
}
