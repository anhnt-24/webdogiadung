package com.example.webdogiadung.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum Status {
    OK(200,"Thành công","success"),
    CREATED(201,"Tạo thành công","success"),
    UPDATED(202,"Cập nhật thành công","success"),
    DELETED(203,"Xóa thành công","success"),
    BAD_REQUEST(400,"Yêu cầu thất bại","error"),
    METHOD_ARGUMENT_NOT_VALID(402,"Không đúng định dạng","error"),
    INTERNAL_SERVER_ERROR(500,"Lỗi không xác định","error"),
    UNAUTHORIZED(401,"Không được phép","error"),
    FORBIDDEN(403,"Cấm truy nhập","error")
    ;


    int code;
    String message;
    String type;
    String detail;
    Status(int code, String message, String type) {
        this.code = code;
        this.message = message;
        this.type = type;
    }
    Status(Status status) {
        this.code = status.code;
        this.message = status.message;
        this.type = status.type;
        this.detail = detail;
    }
    public Status withDetail(String detail) {
        this.detail = detail;
        return this;
    }
}
