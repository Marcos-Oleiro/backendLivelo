package com.oleirosoftware.backendlivelo.handlers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.oleirosoftware.backendlivelo.exceptions.ExistingCityException;
import com.oleirosoftware.backendlivelo.exceptions.InvalidCityIdException;
import com.oleirosoftware.backendlivelo.exceptions.InvalidRequestBodyException;
import com.oleirosoftware.backendlivelo.exceptions.InvalidUrlException;
import com.oleirosoftware.backendlivelo.exceptions.NotExistingIdException;


@ControllerAdvice
public class ExceptionsErrorHandler extends ResponseEntityExceptionHandler {


  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<String> errors = new ArrayList<>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ":" + error.getDefaultMessage());
    }

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors);


    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }


  @ExceptionHandler(ExistingCityException.class)
  protected ResponseEntity<ApiError> handleExistingCity(ExistingCityException ex) {

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());

    return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(InvalidUrlException.class)
  protected ResponseEntity<ApiError> handleInvalidUrl(InvalidUrlException ex) {

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());

    return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(InvalidCityIdException.class)
  protected ResponseEntity<ApiError> handleInvalidCityId(InvalidCityIdException ex) {

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());

    return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(NotExistingIdException.class)
  protected ResponseEntity<ApiError> handleNotExistingId(NotExistingIdException ex) {

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());

    return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(InvalidRequestBodyException.class)
  protected ResponseEntity<ApiError> handleInvalidRequestBody(InvalidRequestBodyException ex) {

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());

    return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
  }
}
