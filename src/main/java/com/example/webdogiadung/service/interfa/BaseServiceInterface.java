package com.example.webdogiadung.service.interfa;


import com.example.webdogiadung.dto.response.page.PagingResponse;

import java.util.List;

public interface BaseServiceInterface<T,Req,Res,ID>{
    Res create(T data);
    PagingResponse<Res> getAll(Req request);
    Res getById(ID id);
    String deleteById(ID id);
    Res update(T data);
    String deleteByListId(List<ID> listId);
}
