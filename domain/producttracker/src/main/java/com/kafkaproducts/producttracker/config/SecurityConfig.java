package com.kafkaproducts.producttracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain filter(ServerHttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
            .authorizeExchange(auth -> auth
                .pathMatchers("/**").permitAll());
        return http.build();
    } 
}
