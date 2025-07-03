package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.request.ClientRequest;
import com.example.webdogiadung.dto.request.search.ClientSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.ClientService;
import com.example.webdogiadung.service.ExcelExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client/")
@RequiredArgsConstructor
public class ClientController{

    private final ClientService clientService;
    private final ExcelExportService excelExportService;

    @PostMapping(value = "create")
    public ApiResponse<ClientResponse> create(@RequestBody ClientRequest request) {
        return ApiResponse.<ClientResponse>builder()
                .status(Status.OK)
                .data(clientService.create(request))
                .build();
    }

    @PutMapping(value = "update")
    public ApiResponse<ClientResponse> update(@RequestBody ClientRequest request) {
        return ApiResponse.<ClientResponse>builder()
                .status(Status.UPDATED)
                .data(clientService.update(request))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("get/all")
    public ApiResponse<PagingResponse<ClientResponse>> getAll(@RequestBody ClientSearchRequest request) {
        return ApiResponse.<PagingResponse<ClientResponse>>builder()
                .status(Status.OK)
                .data(clientService.getAll(request))
                .build();
    }

    @GetMapping("get/{id}")
    public ApiResponse<ClientResponse> getById(@PathVariable  String id) {
        return ApiResponse.<ClientResponse>builder()
                .status(Status.OK)
                .data(clientService.getById(id))
                .build();
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ApiResponse<String> deleteById(@PathVariable String id, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(clientService.deleteById(id,isDeleted))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("export")
    public ResponseEntity<byte[]> export() {
        byte[] file = excelExportService.exportToExcel(clientService.getAllForExport(), ClientResponse.class);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=category.xlsx")
                .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(file);
    }
}
