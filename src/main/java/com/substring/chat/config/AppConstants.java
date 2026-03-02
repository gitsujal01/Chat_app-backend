package com.substring.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConstants {

    public static String FRONT_END_BASE_URL;

    @Value("${frontend.base.url}")
    public void setFrontEndBaseUrl(String url) {
        FRONT_END_BASE_URL = url;
    }
}