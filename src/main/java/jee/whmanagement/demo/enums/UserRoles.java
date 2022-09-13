package jee.whmanagement.demo.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {

    CLIENT, WAREHOUSE_MANAGER, SYSTEM_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
