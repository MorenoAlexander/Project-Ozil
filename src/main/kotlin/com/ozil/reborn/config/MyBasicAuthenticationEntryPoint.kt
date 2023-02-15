package com.ozil.reborn.config

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class MyBasicAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
       response?.addHeader("WWW-Authenticate", "Basic realm=$realmName");
        response?.status = HttpServletResponse.SC_UNAUTHORIZED;
        val writer = response?.writer;
        writer?.println("HTTP Status 401 : " + authException?.message);

    }
    override fun afterPropertiesSet() {
        this.realmName = "OZIL_REALM";
        super.afterPropertiesSet()
    }
}