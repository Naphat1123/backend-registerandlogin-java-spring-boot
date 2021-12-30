package com.example.backend.mapper;

import com.example.backend.entity.User;
import com.example.backend.model.ProfileDto;
import com.example.backend.model.RegisterResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterResponseDto toRegisterResponse(User user);

    ProfileDto toProfile(User user);

}
