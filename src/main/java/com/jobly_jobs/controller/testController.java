package com.jobly_jobs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/")
public class testController {

    @GetMapping("test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World this endpoint is working :)");
    }
}
