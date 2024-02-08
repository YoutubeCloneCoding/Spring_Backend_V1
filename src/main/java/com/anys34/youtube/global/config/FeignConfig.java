package com.anys34.youtube.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.anys34.youtube.global.feign")
public class FeignConfig {
}
