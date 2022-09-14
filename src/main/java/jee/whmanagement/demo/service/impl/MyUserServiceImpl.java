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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiredArgsConstructor
@Service
@ResponseBody
public class MyUserServiceImpl implements MyUserService {

    private final JwtTokenUtil jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsService userDetailsService;



    public String signin(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            UserDetails userDetails= userDetailsService.loadUserByUsername(email);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password,userDetails.getAuthorities()));
            if(user != null){
                //SecurityContext.set(user.getId(), email,user.getRole());
                return jwtTokenProvider.createToken(user.getId(), email,  userDetails);
            }
       return null;
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Invalid username/password supplied");
        }
    }
}
