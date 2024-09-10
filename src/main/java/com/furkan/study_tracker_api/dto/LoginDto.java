package com.furkan.study_tracker_api.dto;

import com.furkan.study_tracker_api.model.Activity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginDto {

    @NotNull
    @Email(message = "Email must be valid")
    @Size(max = 48, message = "Invalid email")
    private String email;

    @NotNull
    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long")
    private String password;
}
