package jee.whmanagement.demo.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jee.whmanagement.demo.entity.Privilege;
import jee.whmanagement.demo.entity.Role;
import jee.whmanagement.demo.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.userId = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : user.getRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//            role.getPrivilege().stream()
//                    .map(privilege -> new SimpleGrantedAuthority(privilege.getPrivilegeName()))
//                    .forEach(authorities::add);
        }
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user.getRole()));
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        return authorities;
//    }


    private static Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private static List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getRoleName());
            collection.addAll(role.getPrivilege());
        }
        for (Privilege item : collection) {
            privileges.add(item.getPrivilegeName());
        }
        return privileges;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;}

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
