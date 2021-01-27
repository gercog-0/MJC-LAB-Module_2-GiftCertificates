package com.epam.esm.controller.exception;

import com.epam.esm.service.api.exception.ErrorCode;
import com.epam.esm.service.api.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        ErrorResponse errorResponse = new ErrorResponse();
        String errorCodeFromException = exception.getErrorCode();
        String responseErrorMessage;
        if (exception.getErrorReason() != null) {
            responseErrorMessage = String.format(translator.translateToLocale(errorCodeFromException),
                    exception.getErrorReason());
        } else {
            responseErrorMessage = translator.translateToLocale(errorCodeFromException);
        }
        errorResponse.setErrorMessage(responseErrorMessage);
        errorResponse.setErrorCode(errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException
                                                                                          exception) {
        String errorCodeFromException = String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(translator.translateToLocale(errorCodeFromException));
        errorResponse.setErrorCode(errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
//        String exceptionMessage = exception.getMessage();
//        String errorCodeFromException = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setErrorMessage(String.format(translator.translateToLocale(errorCodeFromException), exceptionMessage));
//        errorResponse.setErrorCode(errorCodeFromException);
//        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
//    }

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
    }
}
