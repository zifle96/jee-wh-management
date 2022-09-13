package jee.whmanagement.demo.security;

import jee.whmanagement.demo.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SecurityBean {

    private Long userId;
    private String email;
    private Set<Role> roles;
}
