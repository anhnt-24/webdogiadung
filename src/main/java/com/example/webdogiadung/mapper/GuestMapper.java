package com.example.webdogiadung.mapper;

import com.example.webdogiadung.dto.request.GuestRequest;
import com.example.webdogiadung.dto.response.GuestResponse;
import com.example.webdogiadung.entity.GuestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper extends BaseEntityMapper<GuestEntity, GuestRequest, GuestResponse>{
}
