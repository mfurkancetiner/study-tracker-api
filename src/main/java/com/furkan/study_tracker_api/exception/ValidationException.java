package com.furkan.study_tracker_api.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}

