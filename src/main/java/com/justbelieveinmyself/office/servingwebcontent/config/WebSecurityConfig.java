package com.justbelieveinmyself.office.servingwebcontent.config;

import com.justbelieveinmyself.office.servingwebcontent.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Lazy
    private final JpaUserDetailsService jpaUserDetailsService;
    @Autowired
    private DataSource dataSource;
    public WebSecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

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
                .rememberMe(remember -> {
                    remember.tokenRepository(getPersistentTokenRepository()).tokenValiditySeconds(24 * 60 * 60).key("adminloh123haha");
                })
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                ).logout((logout) -> logout.permitAll());

        return http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(((request, response, accessDeniedException) -> accessDeniedException.printStackTrace()))).build();
    }

    public PersistentTokenRepository getPersistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
