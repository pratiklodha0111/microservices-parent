package com.pratik.discoveryservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ✅ new syntax for disabling CSRF
                .csrf(csrf -> csrf.disable())

                // ✅ modern request authorization syntax
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/eureka/**").permitAll() // allow Eureka clients
                        .anyRequest().authenticated()              // require auth for dashboard or others
                )

                // ✅ use built-in basic authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
