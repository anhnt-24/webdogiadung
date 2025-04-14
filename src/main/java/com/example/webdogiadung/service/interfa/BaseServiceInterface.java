package com.example.webdogiadung.service.interfa;


import com.example.webdogiadung.dto.response.page.PagingResponse;

import java.util.List;

public interface BaseServiceInterface<SearchReq,Req,Res,ID>{
    Res create(Req data);
    PagingResponse<Res> getAll(SearchReq request);
    Res getById(ID id);
    String deleteById(ID id);
    Res update(Req data);
    String deleteByListId(List<ID> listId);
}
