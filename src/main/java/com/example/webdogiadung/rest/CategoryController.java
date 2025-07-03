package com.example.webdogiadung.rest;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.request.CategoryRequest;
import com.example.webdogiadung.dto.request.search.CategorySearchRequest;
import com.example.webdogiadung.dto.response.ApiResponse;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.SearchResponse;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.service.CategoryService;
import com.example.webdogiadung.service.ExcelExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category/")
public class CategoryController{

    private final CategoryService categoryService;
    private final ExcelExportService excelExportService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<CategoryResponse> create(CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .status(Status.OK)
                .data(categoryService.create(request))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<CategoryResponse> update(CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .status(Status.UPDATED)
                .data(categoryService.update(request))
                .build();
    }

    @PostMapping("get/all")
    public ApiResponse<PagingResponse<CategoryResponse>> getAll(@RequestBody CategorySearchRequest request) {
        return ApiResponse.<PagingResponse<CategoryResponse>>builder()
                .status(Status.OK)
                .data(categoryService.getAll(request))
                .build();
    }

    @GetMapping("get/{id}")
    public ApiResponse<CategoryResponse> getById(@PathVariable String id) {
        return ApiResponse.<CategoryResponse>builder()
                .status(Status.OK)
                .data(categoryService.getById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}/{isDeleted}")
    public ApiResponse<String> deleteById(@PathVariable("id") String id,@PathVariable("isDeleted") boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(categoryService.deleteById(id,isDeleted))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{isDeleted}")
    public ApiResponse<String> deleteByListId(@RequestBody List<String> listId, @PathVariable boolean isDeleted) {
        return ApiResponse.<String>builder()
                .status(Status.DELETED)
                .data(categoryService.deleteByListId(listId,isDeleted))
                .build();
    }
    @PutMapping("restore")
    public ApiResponse<String> restore(@RequestBody List<String> listId){
        return ApiResponse.<String>builder()
                .status(Status.UPDATED)
                .data(categoryService.restore(listId))
                .build();
    }

    @GetMapping("select")
    public ApiResponse<List<SearchResponse>> select(){
        return ApiResponse.<List<SearchResponse>>builder()
                .status(Status.OK)
                .data(categoryService.getAllForSelect())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("export")
    public ResponseEntity<byte[]> export() {
        byte[] file = excelExportService.exportToExcel(categoryService.getAllForExport(), CategoryResponse.class);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=category.xlsx")
                .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(file);
    }


}
