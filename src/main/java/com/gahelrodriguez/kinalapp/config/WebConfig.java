package com.gahelrodriguez.kinalapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // Spring Security reemplaza al LoginInterceptor.
    // Configuración de seguridad en: config/SecurityConfig.java
}