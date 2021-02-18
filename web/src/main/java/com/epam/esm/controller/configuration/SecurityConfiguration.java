package com.epam.esm.controller.configuration;

import com.epam.esm.controller.exception.SecurityAccessDeniedExceptionHandler;
import com.epam.esm.controller.exception.SecurityAuthenticationEntryPoint;
import com.epam.esm.controller.security.filter.ExceptionHandlerFilter;
import com.epam.esm.controller.security.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenFilter jwtTokenFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;
    private final SecurityAccessDeniedExceptionHandler securityAccessDeniedExceptionHandler;

    @Autowired
    public SecurityConfiguration(JwtTokenFilter jwtTokenFilter, ExceptionHandlerFilter exceptionHandlerFilter,
                                 SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint,
                                 SecurityAccessDeniedExceptionHandler securityAccessDeniedExceptionHandler) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
        this.securityAuthenticationEntryPoint = securityAuthenticationEntryPoint;
        this.securityAccessDeniedExceptionHandler = securityAccessDeniedExceptionHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/tags/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/v1/authentication/authorize").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/registration").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/gift-certificates/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/tags/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/users/{\\d+}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/orders/{\\d+}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/orders/{\\d+}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/orders/user/{\\d+}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/orders/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(securityAuthenticationEntryPoint)
                .accessDeniedHandler(securityAccessDeniedExceptionHandler)
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, SecurityContextPersistenceFilter.class);
    }
}
