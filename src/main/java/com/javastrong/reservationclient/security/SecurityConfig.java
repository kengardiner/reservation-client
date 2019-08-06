package com.javastrong.reservationclient.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//Added this websecurity config to remove security on all URIs
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.enabled:true}")
    private boolean securityEnabled;

    @Override
    public void configure(WebSecurity web) throws Exception {
        if (securityEnabled)
            web.ignoring().antMatchers("/upload/**");
        else
            web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (securityEnabled)
            http.csrf().disable().authorizeRequests()
                    .anyRequest().authenticated()
                    .and().httpBasic();
    }
}
