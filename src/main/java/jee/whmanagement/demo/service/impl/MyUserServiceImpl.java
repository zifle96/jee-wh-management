package jee.whmanagement.demo.service.impl;

import jee.whmanagement.demo.config.JwtTokenUtil;
import jee.whmanagement.demo.entity.User;
import jee.whmanagement.demo.repository.UserRepository;
import jee.whmanagement.demo.security.SecurityContext;
import jee.whmanagement.demo.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MyUserServiceImpl implements MyUserService {

    private final JwtTokenUtil jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    public String signin(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            if(user != null){
                SecurityContext.set(user.getId(), email,user.getRoles());
                return jwtTokenProvider.createToken(user.getId(), email, user.getRoles());
            }
       return null;
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Invalid username/password supplied");
        }
    }
}
