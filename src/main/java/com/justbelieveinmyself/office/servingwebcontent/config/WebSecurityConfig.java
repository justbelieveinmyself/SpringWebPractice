package com.justbelieveinmyself.office.servingwebcontent.config;

import com.justbelieveinmyself.office.servingwebcontent.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    public WebSecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    private final JpaUserDetailsService jpaUserDetailsService;
    private AntPathRequestMatcher[] antPathRequestMatchers = new AntPathRequestMatcher[]{
            AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/registration"),
            AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/registration"),
            AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/"),
            AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/static/**"),
            AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/activate/*"),
            AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/activate/*")

    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(antPathRequestMatchers).permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailsService)
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                ).logout((logout) -> logout.permitAll());

        return http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(((request, response, accessDeniedException) -> accessDeniedException.printStackTrace()))).build();
    }


}
