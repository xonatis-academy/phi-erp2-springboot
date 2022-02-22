package com.taskdash.taskdash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    
    // Endpoint: GET /ping
    @GetMapping("/ping")
    public @ResponseBody String ping() {
        return "Success";
    }

}
