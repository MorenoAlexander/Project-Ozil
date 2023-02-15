package com.ozil.reborn.config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.reactive.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfiguration  : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var authenticationEntryPoint : MyBasicAuthenticationEntryPoint;


    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.inMemoryAuthentication()
            ?.withUser("ozil")
            ?.password(passwordEncoder().encode("test"))
            ?.authorities("ROLE_USER");
    }

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()?.antMatchers("/**")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.httpBasic()
            ?.authenticationEntryPoint(authenticationEntryPoint);

        // http?.addFilterAfter(CustomFilter(), BasicAuthenticationFilter.class)
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }


}

