package com.furkan.study_tracker_api.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String timestamp;
    private int status;
    private String path;
    private Map<String, String > errors;  // To store multiple errors



}
