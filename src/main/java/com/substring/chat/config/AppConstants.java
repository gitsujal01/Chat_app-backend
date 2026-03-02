package com.substring.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConstants {

    @Value("${frontend.base.url}")
    public String FRONT_END_BASE_URL;
}