package com.furkan.study_tracker_api.util;

import com.furkan.study_tracker_api.dto.UserUpdateDto;
import com.furkan.study_tracker_api.model.AppUser;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUpdateDto(UserUpdateDto userUpdateDto, @MappingTarget AppUser appUseruser);
}
