package com.anys34.youtube.global.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@Slf4j
public class testController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/test/t")
    public String test(Principal principal) {
        log.info(principal.getName());
        return "test";
    }
}
