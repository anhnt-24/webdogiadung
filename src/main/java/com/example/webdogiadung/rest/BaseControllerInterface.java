package com.example.webdogiadung.rest;

import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface BaseControllerInterface<SearchReq,Req,Res,ID>{

    @PostMapping("create")
    ApiResponse<Res> create(Req request);


    @PostMapping("create")
    ApiResponse<Res> update(Req request);

    @PostMapping("/get/all")
    ApiResponse<PagingResponse<Res>> getAll( @RequestBody SearchReq request);

    @GetMapping("/get/{id}")
    default ApiResponse<Res> getById( @PathVariable ID id){return null;};

    @DeleteMapping("/delete/{id}/{isDeleted}")
    default ApiResponse<String> deleteById(@PathVariable(value = "id") ID id,@PathVariable("isDeleted") boolean isDeleted){return null;};

    @DeleteMapping("/delete/{isDeleted}")
    default ApiResponse<String> deleteByListId(@RequestBody List<ID> listId,@PathVariable boolean isDeleted){return null;};

}
