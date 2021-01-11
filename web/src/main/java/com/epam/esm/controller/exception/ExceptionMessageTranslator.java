package com.epam.esm.controller.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ExceptionMessageTranslator {

    private final ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    public ExceptionMessageTranslator(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public String translateToLocale(String messageCode) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return resourceBundleMessageSource.getMessage(messageCode, null, currentLocale);
    }
}
