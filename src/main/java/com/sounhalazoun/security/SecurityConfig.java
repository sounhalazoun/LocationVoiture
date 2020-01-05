package com.sounhalazoun.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.jdbcAuthentication().dataSource( dataSource )
                .usersByUsernameQuery(
                        "select email as principal,password as credentials,active from user where email=?" )
                .authoritiesByUsernameQuery( "select email as principal,roles as role from authorities where email=?" )
                .passwordEncoder( new BCryptPasswordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http
                .formLogin().loginPage( "/user/login" ).loginProcessingUrl(
                        "/performLogin" )
                .defaultSuccessUrl( "/user/home", true )
                .failureUrl( "/user/login?error=true" ).and().logout()
                .logoutRequestMatcher( new AntPathRequestMatcher( "/perform_logout" ) )
                .logoutSuccessUrl( "/user/login" ).invalidateHttpSession( true ).deleteCookies( "JSESSIONID" )
                .and()
                .authorizeRequests().antMatchers( "/manager/**" ).hasAuthority( "MANAGER" )
                .and()
                .authorizeRequests().antMatchers( "/client/**" ).permitAll()
                .and()
                .exceptionHandling().accessDeniedPage( "/user/403" );

    }

}
