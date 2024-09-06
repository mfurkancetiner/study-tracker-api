package com.furkan.study_tracker_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserUpdateDto {


    @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters long")
    private String username;

    @Email(message = "Email must be valid")
    @Size(max = 48, message = "Email must be at least 48 characters long")
    private String email;

    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long")
    private String password;

    public @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters long") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters long") String username) {
        this.username = username;
    }

    public @Email @Size(max = 48, message = "Email must be at least 48 characters long") String getEmail() {
        return email;
    }

    public void setEmail(@Email @Size(max = 48, message = "Email must be at least 48 characters long") String email) {
        this.email = email;
    }

    public @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long") String password) {
        this.password = password;
    }
}
