package com.justbelieveinmyself.office.servingwebcontent.config;

import com.justbelieveinmyself.office.servingwebcontent.accessingdatamysql.SecurityUser;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserRepository;
import com.justbelieveinmyself.office.servingwebcontent.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    public WebSecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    private final JpaUserDetailsService jpaUserDetailsService;




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(AntPathRequestMatcher.antMatcher( HttpMethod.POST,"/registration"), AntPathRequestMatcher.antMatcher( HttpMethod.GET,"/registration"), AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/")).permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailsService)
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                ).logout((logout) -> logout.permitAll());

        return http.build();
    }


}
