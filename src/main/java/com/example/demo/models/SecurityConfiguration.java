package com.example.demo.models;

import com.example.demo.svcs.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by daniel on 6/22/17.
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserWithRoles.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsLoader userDetails;

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/posts") // USERS HOME PAGE IT CAN BE ANY URL
                .permitAll() // ANYONE CAN GO TO THE LOGIN PAGE

                    .and()

                .authorizeRequests()
                .antMatchers("/", "/logout") // ANYONE CAN SEE THE HOME AND LOGOUT
                .permitAll()

                    .and()

                .logout()
                .logoutSuccessUrl("/login?logut") // APPEND A QUERY STRING VALUE

                    .and()

                .authorizeRequests()
                .antMatchers("/posts/create", "/posts/?/edit") // ONLY AUTHENTICATED USERS CAN CREATE
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());

    }

}
