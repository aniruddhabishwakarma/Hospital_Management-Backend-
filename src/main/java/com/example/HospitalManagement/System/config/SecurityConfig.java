package com.example.HospitalManagement.System.config;

import com.example.HospitalManagement.System.jwt.JwtAuthenticationEntryPoint;
import com.example.HospitalManagement.System.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().disable()
            .and()
                .csrf(csrf-> csrf.disable())
                .cors(cors->cors.disable())
            .authorizeHttpRequests(authorizeRequests ->
                    authorizeRequests
                            .requestMatchers(
                                    new AntPathRequestMatcher("/auth/**"),
                                    new AntPathRequestMatcher("/h2-console/**"),
                                    new AntPathRequestMatcher("/api/users/**"),
                                    new AntPathRequestMatcher("/v3/api-docs/**"),
                                    new AntPathRequestMatcher("/swagger-ui/**"),
                                    new AntPathRequestMatcher("/swagger-ui.html"),
                                    new AntPathRequestMatcher("/api/contact"),
                                    new AntPathRequestMatcher("/api/contact/news-letter"),
                                    new AntPathRequestMatcher("/ws-api/**")
                            ).permitAll()
                            .requestMatchers(
                                    new AntPathRequestMatcher("/api/admin/**"),
                                    new AntPathRequestMatcher("/bye")
                            ).hasRole("ADMIN")
                            .requestMatchers("/api/user/**").hasAnyRole("USER","ADMIN")
                            .requestMatchers("/goodbye").hasRole("HOSPITAL_MANAGER")
                            .anyRequest().authenticated()
            )
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}
