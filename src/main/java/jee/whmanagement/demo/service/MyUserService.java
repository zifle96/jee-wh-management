package jee.whmanagement.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MyUserService {

    String signin(String username, String password) throws UsernameNotFoundException;
}
