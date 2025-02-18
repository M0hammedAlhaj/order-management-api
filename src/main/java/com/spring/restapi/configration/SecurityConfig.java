package com.spring.restapi.configration;

import com.spring.restapi.filter.JwtFilter;
import com.spring.restapi.service.CustomUserDetailsService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtFilter jwtFilter;

    public SecurityConfig(
            CustomUserDetailsService customUserDetailsService,
            JwtFilter jwtFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((config) ->
                        config.requestMatchers("api/v1/authenticated/register", "/index", "api/v1/authenticated/login")
                                .permitAll()
                                .requestMatchers("api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "api/v1/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "api/v1/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "api/v1/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "api/v1/products").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("api/v1/orders/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/swagger-ui.html").hasRole("ADMIN")
                                .requestMatchers("/swagger-resources/**").hasRole("ADMIN")
                                .requestMatchers("/api-docs/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                ).httpBasic(Customizer.withDefaults())
//                .oauth2Login(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(daoAuthenticationProvider())
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No sessions
                        .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
