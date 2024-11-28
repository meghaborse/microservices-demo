package com.example.service2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TraceUtil {
    private static final Logger logger = LoggerFactory.getLogger(TraceUtil.class);
    public static void logWithTrace(String traceId, String message) {
        logger.info("[TraceId: {}] {}", traceId, message);
    }
    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

}
