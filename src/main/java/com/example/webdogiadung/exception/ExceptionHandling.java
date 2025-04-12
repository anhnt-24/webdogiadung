package com.example.webdogiadung.exception;

import com.example.webdogiadung.constants.Status;
import com.example.webdogiadung.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> exception(Exception e) {
        return ResponseEntity.<ApiResponse<?>>internalServerError()
                .body(ApiResponse.builder()
                .status(Status.INTERNAL_SERVER_ERROR.withDetail(e.getMessage()))
                        .build()
                );

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.<ApiResponse<?>>badRequest()
                .body(ApiResponse.builder()
                        .status(Status.METHOD_ARGUMENT_NOT_VALID.withDetail(String.join("; ", errors)))
                        .build()
                );
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.<ApiResponse<?>>status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.builder()
                        .status(Status.FORBIDDEN)
                        .build()
                );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.<ApiResponse<?>>status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.builder()
                        .status(Status.UNAUTHORIZED)
                        .build()
                );
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(BusinessException e) {
        return ResponseEntity.<ApiResponse<?>>status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .status(Status.BAD_REQUEST.withDetail(e.getMessage()))
                        .build()
                );
    }
}
