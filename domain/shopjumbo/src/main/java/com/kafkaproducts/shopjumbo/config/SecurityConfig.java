package com.kafkaproducts.shopjumbo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {
    @Bean
    MapReactiveUserDetailsService usersDetails() throws Exception {
        List<UserDetails> users = List.of(
            User.withUsername("felipe").password("{noop}pass1").roles("USER").build(),
            User.withUsername("mora").password("{noop}pass2").roles("USER").build(),
            User.withUsername("admin").password("{noop}admin").roles("USER", "ADMIN").build()
        );

        return new MapReactiveUserDetailsService(users);
    }

    @Bean
    SecurityWebFilterChain filter(ServerHttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
            .authorizeExchange(auth -> auth
                .pathMatchers(HttpMethod.POST, "/create").hasAnyRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/delete/**").hasAnyRole("ADMIN")
                .pathMatchers(HttpMethod.GET, "/products/**").hasAnyRole("USER"))
            .httpBasic(Customizer.withDefaults());
        return http.build();
    } 
}
