package jee.whmanagement.demo.security;


import jee.whmanagement.demo.config.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenUtil.resolveToken(httpServletRequest);
        if (token != null && jwtTokenUtil.validateToken(token)) {
            Authentication auth = jwtTokenUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
