package jee.whmanagement.demo.security;


import jee.whmanagement.demo.entity.Role;

import java.util.Set;


public class SecurityContext {

    private static ThreadLocal<SecurityBean> currentSecurityContext = new ThreadLocal<>();

    public static void init(){
        currentSecurityContext.set(new SecurityBean());
    }

    public static SecurityBean get(){
        return currentSecurityContext.get();
    }

    public static void set(Long userId, String email, Set<Role> roles){
        SecurityBean securityBean = get();
        securityBean.setUserId(userId);
        securityBean.setEmail(email);
        securityBean.setRoles(roles);
    }

    public static void clear(){
        currentSecurityContext.set(null);
    }
}
