package com.example.service2.controller;


import com.example.service2.utils.TraceUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service2")
public class Service2Controller {

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        String traceId = TraceUtil.generateTraceId();
        TraceUtil.logWithTrace(traceId, "GET /hello called");
        return ResponseEntity.ok("Hello");
    }
}
