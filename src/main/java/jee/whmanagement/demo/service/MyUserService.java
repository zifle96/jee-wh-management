package jee.whmanagement.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface MyUserService {

    String signin(String username, String password) throws UsernameNotFoundException;
}
