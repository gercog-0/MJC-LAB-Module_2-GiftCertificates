package com.epam.esm.controller.exception;

import com.epam.esm.service.api.exception.ErrorCode;
import com.epam.esm.service.api.exception.ServiceException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    private final ExceptionMessageTranslator translator;
    private final Map<String, HttpStatus> complianceMap = new HashMap<>();

    @Autowired
    public RestExceptionHandler(ExceptionMessageTranslator translator) {
        this.translator = translator;
        initializeComplianceMap();
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException exception) {
        String errorCodeFromException = exception.getErrorCode();
        String responseErrorMessage;
        if (exception.getErrorReason() != null) {
            responseErrorMessage = String.format(translator.translateToLocale(errorCodeFromException),
                    exception.getErrorReason());
        } else {
            responseErrorMessage = translator.translateToLocale(errorCodeFromException);
        }
        ErrorResponse errorResponse = new ErrorResponse(responseErrorMessage, errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exp) {
        String errorCodeFromException = ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH;
        String errorResponseMessage = String.format(translator.translateToLocale(errorCodeFromException),
                exp.getName(), exp.getRequiredType());
        ErrorResponse errorResponse = new ErrorResponse(errorResponseMessage, errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException
                                                                                          exception) {
        String errorCodeFromException = String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        ErrorResponse errorResponse = createErrorResponse(errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exp) {
        ErrorResponse errorResponse = createErrorResponse(ErrorCode.INVALID_TYPE_PARAMETERS);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exp) {
        String errorCode = String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value());
        String errorMessage = exp.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorMessage, errorCode);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException exp) {
        String errorCodeFromException = ErrorCode.UNAUTHORIZED;
        String errorResponseMessage = exp.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorResponseMessage, errorCodeFromException);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exp) {
        String errorCodeFromException = String.valueOf(HttpStatus.FORBIDDEN.value());
        ErrorResponse errorResponse = createErrorResponse(errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exp) {
        String errorCodeFromException = String.valueOf(HttpStatus.FORBIDDEN.value());
        ErrorResponse errorResponse = createErrorResponse(errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
        String exceptionMessage = exception.getMessage();
        String errorCodeFromException = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String formatErrorMessage = String.format(translator.translateToLocale(errorCodeFromException), exceptionMessage);
        ErrorResponse errorResponse = new ErrorResponse(formatErrorMessage, errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

    private ErrorResponse createErrorResponse(String errorCode) {
        String errorMessage = translator.translateToLocale(errorCode);
        return new ErrorResponse(errorMessage, errorCode);
    }

    private void initializeComplianceMap() {
        complianceMap.put(ErrorCode.TAG_NAME_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.TAG_WITH_SUCH_NAME_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.TAG_POPULAR_NOT_FOUND, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_NAME_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_DESCRIPTION_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_PRICE_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_DURATION_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.TAG_WITH_SUCH_ID_NOT_EXIST, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.TAG_WITH_SUCH_NAME_NOT_EXIST, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.TAG_ID_SPECIFIED_WHILE_CREATING, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_ID_SPECIFIED_WHILE_CREATING, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.USER_NAME_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.USER_WITH_SUCH_ID_NOT_EXIST, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.ORDER_ID_SPECIFIED_WHILE_CREATING, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.ORDER_WITH_SUCH_ID_NOT_EXIST, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.UNSUPPORTED_MEDIA_TYPE, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        complianceMap.put(ErrorCode.INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        complianceMap.put(ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.PAGINATION_INCORRECT_PAGE_NUMBER, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.PAGINATION_INCORRECT_SIZE, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.ORDER_CREATING_ERROR, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.USER_LOGIN_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.USER_PASSWORD_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.USER_WITH_SUCH_LOGIN_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.ROLE_WITH_SUCH_NAME_NOT_EXIST, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.USER_WITH_SUCH_LOGIN_NOT_EXIST, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.INCORRECT_LOGIN_OR_PASSWORD, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        complianceMap.put(ErrorCode.FORBIDDEN_ACCESS, HttpStatus.FORBIDDEN);
    }
}
