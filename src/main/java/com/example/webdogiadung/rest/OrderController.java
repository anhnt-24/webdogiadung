package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.dto.request.search.OrderSearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.dto.response.OrderResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.ExcelExportService;
import com.example.webdogiadung.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order/")
@RequiredArgsConstructor
public class OrderController{

    private final OrderService orderService;
    private final ExcelExportService excelExportService;

    @PostMapping(value = "create")
    public ApiResponse<OrderResponse> create(@RequestBody OrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .status(Status.OK)
                .data(orderService.create(request))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "update")
    public ApiResponse<OrderResponse> update(@RequestBody  OrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .status(Status.UPDATED)
                .data(orderService.update(request))
                .build();
    }
    @PostMapping("get/all")
    public ApiResponse<PagingResponse<OrderResponse>> getAll(@RequestBody OrderSearchRequest request) {
        return ApiResponse.<PagingResponse<OrderResponse>>builder()
                .status(Status.OK)
                .data(orderService.getAll(request))
                .build();
    }

    @GetMapping("get/{id}")
    public ApiResponse<OrderResponse> getById(@PathVariable  String id) {
        return ApiResponse.<OrderResponse>builder()
                .status(Status.OK)
                .data(orderService.getById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ApiResponse<String> deleteById(@PathVariable String id, boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(orderService.deleteById(id,isDeleted))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("export")
    public ResponseEntity<byte[]> export() {
        byte[] file = excelExportService.exportToExcel(orderService.getAllForExport(), OrderResponse.class);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=category.xlsx")
                .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(file);
    }

}
