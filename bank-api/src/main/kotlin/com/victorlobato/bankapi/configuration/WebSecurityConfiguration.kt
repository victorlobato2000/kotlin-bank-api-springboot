package com.victorlobato.bankapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration : WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity) {
        http.httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val details = User.withDefaultPasswordEncoder()
            .username("victorlobato")
            .password("123")
            .roles("USER")
            .build()

        val admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .roles("ADM")
            .build()

        return InMemoryUserDetailsManager(details, admin)
    }
}