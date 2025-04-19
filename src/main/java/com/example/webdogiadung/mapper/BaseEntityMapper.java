package com.example.webdogiadung.mapper;


import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
public interface BaseEntityMapper<E, D, R> {


    E toEntity(D d);

    R toResponse(E e);

    void updateEntity(@MappingTarget E target, D source);

    List<R> toResponse(List<E> e);

    List<E> toEntity(List<D> d);

}