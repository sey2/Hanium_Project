package com.fishing.demo.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fishing.demo.api.service.UsersService;

import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UsersService usersService;
    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return (SecurityFilterChain)((HttpSecurity)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)((HttpSecurity)httpSecurity.httpBasic().disable()).csrf().disable()).cors().and()).authorizeRequests().antMatchers(new String[]{"/join", "/login", "/v2/api-docs", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/check/**", "/reissue"})).permitAll().antMatchers(new String[]{"/golden-ratio/**"})).authenticated().anyRequest()).authenticated().and()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()).addFilterBefore(new JwtFilter(this.usersService, this.secretKey), UsernamePasswordAuthenticationFilter.class).build();
    }

    public SecurityConfig(UsersService usersService) {
        this.usersService = usersService;
    }
}
