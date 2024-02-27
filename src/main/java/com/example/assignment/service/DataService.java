package com.example.assignment.service;

import java.util.concurrent.ExecutionException;

public interface DataService {
    String fetchDataConcurrently() throws ExecutionException, InterruptedException;
}
