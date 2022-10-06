package jee.whmanagement.demo.config;

import jee.whmanagement.demo.security.JwtTokenFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = false, securedEnabled = false, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.csrf().disable();
        // Set session management to stateless
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();
        http.authorizeHttpRequests((authorization) -> authorization
                .antMatchers("/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/client-order/create").hasRole("CLIENT")
                .antMatchers("manager/all-items").hasRole("WAREHOUSE_MANAGER")
                .antMatchers("manager/items-create").hasRole("WAREHOUSE_MANAGER")
                .anyRequest().authenticated()
        );


        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenUtil);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
