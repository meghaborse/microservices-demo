package com.example.service1.controller;

import com.example.service1.service.Service1Service;
import com.example.service1.utils.TraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/service1")
public class Service1Controller {
    private static final Logger logger = LoggerFactory.getLogger(TraceUtil.class);


    private final Service1Service service;
    public Service1Controller(Service1Service service) {
        this.service = service;
    }
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        String traceId = TraceUtil.generateTraceId();
        TraceUtil.logWithTrace(traceId, "GET /status calle");
        return ResponseEntity.ok("Up");
    }

    @PostMapping("/process")
    public ResponseEntity<Object> process(@RequestBody Map<String, String> payload) {
        String traceId = TraceUtil.generateTraceId();
        TraceUtil.logWithTrace(traceId, "POST /process called");
        Map<String, Object> response = new HashMap<>();
        try {
            String concatName = service.orchestrate(payload);
            response.put("concatName", concatName);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e ){
            logger.error("Error occurred while processing request: {}", e.getMessage());
            response.put("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }


    }
}
