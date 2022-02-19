package az.mycompany.shopping_store_users.controller;

import az.mycompany.shopping_store_users.dto.ErrorResponse;
import az.mycompany.shopping_store_users.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        return buildResponseEntity(ex, request);
    }

    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request) {
        String message;
        HttpStatus status;
        if (ex instanceof UserNotFoundException) {
            message = "User not found";
            status = HttpStatus.NOT_FOUND;
        }

        else {
            message = HttpStatus.INTERNAL_SERVER_ERROR.name();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return getResponseEntity(ex, status, request, message);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponseEntity(ex, status, request, "Internal exception message");
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponseEntity(ex, status, request, "Validation failed");
    }

    private ResponseEntity<Object> getResponseEntity(Exception ex,
                                                     HttpStatus status,
                                                     WebRequest request,
                                                     String message) {
        ErrorResponse errorResponseDto = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .errorDetail(ex.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponseDto, status);
    }
}
