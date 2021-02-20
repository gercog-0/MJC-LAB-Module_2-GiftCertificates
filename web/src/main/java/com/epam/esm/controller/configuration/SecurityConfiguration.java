package com.epam.esm.controller.configuration;

import com.epam.esm.controller.exception.SecurityAuthenticationEntryPoint;
import com.epam.esm.controller.security.filter.ExceptionHandlerFilter;
import com.epam.esm.controller.security.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenFilter jwtTokenFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    @Autowired
    public SecurityConfiguration(JwtTokenFilter jwtTokenFilter, ExceptionHandlerFilter exceptionHandlerFilter,
                                 SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
        this.securityAuthenticationEntryPoint = securityAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(securityAuthenticationEntryPoint)
                .and()
                .oauth2Login()
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, SecurityContextPersistenceFilter.class);
    }
}
