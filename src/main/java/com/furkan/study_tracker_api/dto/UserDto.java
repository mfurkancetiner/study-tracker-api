package com.furkan.study_tracker_api.dto;
import com.furkan.study_tracker_api.model.Activity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
//import lombok.Getter;
//import lombok.Setter;

import java.util.List;


public class UserDto {

    @NotNull
    @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters long")
    private String username;

    @NotNull
    @Email(message = "Email must be valid")
    @Size(max = 48, message = "Email must be at least 48 characters long")
    private String email;

    @NotNull
    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long")
    private String password;
    private List<Activity> activities;



    public @NotNull @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters long") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters long") String username) {
        this.username = username;
    }

    public @NotNull @Email @Size(max = 48, message = "Email must be at least 48 characters long") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email @Size(max = 48, message = "Email must be at least 48 characters long") String email) {
        this.email = email;
    }

    public @NotNull @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long") String password) {
        this.password = password;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
