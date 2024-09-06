package com.furkan.study_tracker_api.service;

import com.furkan.study_tracker_api.dto.ActivityDto;
import com.furkan.study_tracker_api.exception.ResourceNotFoundException;
import com.furkan.study_tracker_api.model.Activity;
import com.furkan.study_tracker_api.model.User;
import com.furkan.study_tracker_api.repository.ActivityRepository;
import com.furkan.study_tracker_api.repository.UserRepository;
import com.furkan.study_tracker_api.util.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ActivityMapper activityMapper;

    @Autowired
    public ActivityService(
            ActivityRepository activityRepository,
            UserRepository userRepository,
            ActivityMapper activityMapper
    ){
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.activityMapper = activityMapper;
    }

    public Activity getActivity(Long id){
         return activityRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Activity not found"));
    }

    public List<Activity> getAllActivities(){
        return activityRepository.findAll();
    }

    public Activity createActivity(ActivityDto activityDto){

        Activity activity = new Activity();
        User user = userRepository.findById(activityDto.getCreatedById()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        activity.setCreatedBy(user);
        activity.setSuccessful(false);
        activity.setDurationMinutes(activityDto.getDurationMinutes());
        activity.setCreatedById(activityDto.getCreatedById());
        return activityRepository.save(activity);
    }

    public void deleteActivity(Long id){
        Activity activity = this.getActivity(id);
        activityRepository.deleteById(id);
    }

    // essentially letting activity be updated only once
    public Activity updateActivity(ActivityDto activityDto, Long id){
        Activity activity = this.getActivity(id);
        if(activity.getFinishedAt() == null){
            activity.setFinishedAt(LocalDateTime.now());
            activity.setSuccessful(activityDto.getSuccessful());
        }

//        activityMapper.updateActivityFromDto(activityDto, activity);
        return activityRepository.save(activity);
    }
}
