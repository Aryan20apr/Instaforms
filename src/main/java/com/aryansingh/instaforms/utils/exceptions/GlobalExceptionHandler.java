package com.aryansingh.instaforms.utils.exceptions;


import com.aryansingh.instaforms.utils.AppConstants;
import com.aryansingh.instaforms.utils.api.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<String>> handleApiException(ApiException e){
        ApiResponse<String> apiResponse=new ApiResponse<>(AppConstants.ERROR_CODE,AppConstants.ERROR_MESSAGE,e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<String>> handleExpiredJwtException(ExpiredJwtException e){
        ApiResponse<String> apiResponse=new ApiResponse<>(AppConstants.ACCESS_TOKEN_EXPIRED,AppConstants.ERROR_MESSAGE,e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    

    @ExceptionHandler(RefreshTokenException.class)
    public ResponseEntity<ApiResponse<String>> handleRefreshTokenExpiredException(RefreshTokenException e){
        ApiResponse<String> apiResponse=new ApiResponse<>(AppConstants.REFRESH_TOKEN_EXPIRED,AppConstants.ERROR_MESSAGE,e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiResponse<String>> handleMalformedJwtException(MalformedJwtException e){
        ApiResponse<String> apiResponse=new ApiResponse<>(AppConstants.ERROR_CODE,AppConstants.ERROR_MESSAGE,e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse<String>> handleSignatureException(SignatureException e){
        ApiResponse<String> apiResponse=new ApiResponse<>(AppConstants.ERROR_CODE,AppConstants.ERROR_MESSAGE,e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ApiResponse<String>> handleUnsupportedJwtException(UnsupportedJwtException e){
        ApiResponse<String> apiResponse=new ApiResponse<>(AppConstants.ERROR_CODE,AppConstants.ERROR_MESSAGE,e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException e){
        ApiResponse<String> apiResponse=new ApiResponse<>(AppConstants.ERROR_CODE,AppConstants.ERROR_MESSAGE,e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(InsufficientRolesException.class)
    public ResponseEntity<ApiResponse<String>> handleInsufficientRolesException(InsufficientRolesException e){
        ApiResponse<String> apiResponse=new ApiResponse<>(AppConstants.ACCESS_DENIED_CODE,AppConstants.ACCESS_DENIED_MESSAGE,e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Handle @Valid validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Extract field errors and create a map of field names and error messages
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(new ApiResponse(AppConstants.ERROR_CODE,AppConstants.ERROR_MESSAGE,errors), HttpStatus.BAD_REQUEST);
    }

}
