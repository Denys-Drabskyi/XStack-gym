package com.example.trainersservice;

import lombok.experimental.StandardException;

@StandardException
public class TrainingSummaryNotFoundException extends RuntimeException {
    public static TrainingSummaryNotFoundException withUsername(String username) {
        return new TrainingSummaryNotFoundException(String.format("Summary for trainer:%s not found", username));
    }
}
