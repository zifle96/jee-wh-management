package jee.whmanagement.demo.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privilege",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privilege;

    @ManyToMany(mappedBy = "role")
    private Set<User> user;

    public Role(Long id, String roleName, Set<Privilege> privilege, Set<User> user) {
        this.id = id;
        this.roleName = roleName;
        this.privilege = privilege;
        this.user = user;
    }
}
