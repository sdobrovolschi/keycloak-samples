package com.example.keycloack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestResource {

    @GetMapping("/test")
    String get() {
        return "Hello";
    }
}
