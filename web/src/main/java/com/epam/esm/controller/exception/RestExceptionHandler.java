package com.epam.esm.controller.exception;

import com.epam.esm.service.impl.exception.ErrorCode;
import com.epam.esm.service.impl.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

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
    public ResponseEntity<ErrorResponse> customHandle(ServiceException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        String errorCodeFromException = exception.getErrorCode();
        String responseErrorMessage = String.format(translator.translateToLocale(errorCodeFromException),
                exception.getErrorReason());
        errorResponse.setErrorMessage(responseErrorMessage);
        errorResponse.setErrorCode(errorCodeFromException);
        return new ResponseEntity<>(errorResponse, complianceMap.get(errorCodeFromException));
    }

    private void initializeComplianceMap() {
        complianceMap.put(ErrorCode.TAG_NAME_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.TAG_WITH_SUCH_NAME_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_NAME_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_DESCRIPTION_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_PRICE_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_DURATION_INCORRECT, HttpStatus.BAD_REQUEST);
        complianceMap.put(ErrorCode.TAG_WITH_SUCH_ID_NOT_EXIST, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.TAG_WITH_SUCH_NAME_NOT_EXIST, HttpStatus.NOT_FOUND);
        complianceMap.put(ErrorCode.GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST, HttpStatus.NOT_FOUND);

    }
}
