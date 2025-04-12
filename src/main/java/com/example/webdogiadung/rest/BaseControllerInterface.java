package com.example.webdogiadung.rest;

import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface BaseControllerInterface<T,Req,Res,ID>{

    ApiResponse<Res> create(T request);


    ApiResponse<Res> update(T request);

    @PostMapping("/get/all")
    ApiResponse<PagingResponse<Res>> getAll(@RequestBody(required = false) Req request);

    @GetMapping("/get/{id}")
    ApiResponse<Res> getById( @PathVariable ID id);

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deleteById(@PathVariable ID id);

    @DeleteMapping("/delete")
    ApiResponse<String> deleteByListId(@RequestBody List<ID> listId);
}
