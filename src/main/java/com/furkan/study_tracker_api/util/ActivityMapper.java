package com.furkan.study_tracker_api.util;

import com.furkan.study_tracker_api.dto.ActivityDto;
import com.furkan.study_tracker_api.model.Activity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateActivityFromDto(ActivityDto activityDto, @MappingTarget Activity activity);
}
