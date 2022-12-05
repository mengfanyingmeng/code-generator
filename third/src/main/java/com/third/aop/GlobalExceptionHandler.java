package com.third.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class GlobalExceptionHandler {
    @Value("${spring.application.name}")
    private String projectName;

    private void printLog(boolean noPrintErr, Exception e, HttpServletRequest request) {
        if (noPrintErr) {
            log.warn("{} module uri={} Exception=", projectName, request.getRequestURI(), e);
        } else {
            log.error("{} module uri={} Exception=", projectName, request.getRequestURI(), e);
        }
    }
}
