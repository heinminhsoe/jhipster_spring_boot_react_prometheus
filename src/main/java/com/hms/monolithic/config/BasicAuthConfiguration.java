package com.hms.monolithic.config;

import com.hms.monolithic.security.AuthoritiesConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(1)
@ConditionalOnProperty(prefix = "management", name = "metrics.export.prometheus.enabled")
public class BasicAuthConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/management/prometheus/**")
            .authorizeRequests()
            .anyRequest().hasAuthority(AuthoritiesConstants.ADMIN)
            .and()
            .httpBasic().realmName("jhipster")
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable();
    }
}
