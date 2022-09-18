package jee.whmanagement.demo.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "privilege")
@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "privilege_name")
    private String privilegeName;

    @ManyToMany(mappedBy = "privilege")
    private Set<Role> role;

    public Privilege(Long id, String name) {
        this.id = id;
        this.privilegeName = name;
    }

    public Privilege(Long id, String privilegeName, Set<Role> role) {
        this.id = id;
        this.privilegeName = privilegeName;
        this.role = role;
    }
}
