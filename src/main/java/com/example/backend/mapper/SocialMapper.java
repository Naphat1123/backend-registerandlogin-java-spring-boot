package com.example.backend.mapper;

import com.example.backend.entity.Social;
import com.example.backend.model.SocialDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialMapper {
    SocialDto toSocial(Social social);
}
