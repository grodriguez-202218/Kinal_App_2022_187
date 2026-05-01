package com.gahelrodriguez.kinalapp.config;

import com.gahelrodriguez.kinalapp.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // Rutas que SÍ están protegidas (requieren login)
                .addPathPatterns(
                        "/",
                        "/dashboard",
                        "/clientes/**",
                        "/productos/**",
                        "/ventas/**",
                        "/usuarios/**",
                        "/detalles/**"
                )
                // Rutas que NO requieren login
                .excludePathPatterns(
                        "/login",
                        "/logout",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**"
                );
    }
}