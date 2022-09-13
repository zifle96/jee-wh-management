package jee.whmanagement.demo.security;


import jee.whmanagement.demo.config.JwtTokenUtil;
import jee.whmanagement.demo.entity.User;
import jee.whmanagement.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
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
//    @Autowired
//    UserRepository userRepo;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header !=null && ((header).isEmpty() || !header.startsWith("Bearer "))) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // Get jwt token and validate
//        if (header!= null){
//            final String token = header.split(" ")[1].trim();
//
//            User user = userRepo
//                    .findByEmail(jwtTokenUtil.getEmailFromToken(token));
//
//            if (!jwtTokenUtil.validateToken(token, user)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//        }
//
//        SecurityContext.init();
//        filterChain.doFilter(request, response);
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenUtil.resolveToken(httpServletRequest);
        if (token != null && jwtTokenUtil.validateToken(token)) {
            Authentication auth = jwtTokenUtil.getAuthentication(token);
            //SecurityContextHolder.getContext().setAuthentication(auth);
            SecurityContext.init();
            //SecurityContext.set();

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
