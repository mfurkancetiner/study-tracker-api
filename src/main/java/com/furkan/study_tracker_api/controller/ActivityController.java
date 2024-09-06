package com.furkan.study_tracker_api.controller;

import com.furkan.study_tracker_api.dto.ActivityDto;
import com.furkan.study_tracker_api.model.Activity;
import com.furkan.study_tracker_api.service.ActivityService;
import com.furkan.study_tracker_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService, UserService userService){
        this.activityService = activityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable("id") Long id) {
        Activity activity = activityService.getActivity(id);
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivity(){
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody @Valid ActivityDto activityDto){
        Activity activity = activityService.createActivity(activityDto);
        return new ResponseEntity<>(activity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable("id") Long id){
        Activity activity = activityService.getActivity(id);
        activityService.deleteActivity(id);
        return new ResponseEntity<>("Activity has been deleted", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable("id") Long id, @RequestBody ActivityDto activityDto){
        Activity activity = this.activityService.updateActivity(activityDto, id);
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }
}
