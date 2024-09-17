package com.aryansingh.instaforms.models.entities.userAndAuth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id",nullable = false)
    private int roleId;

    private String roleName;
    @ManyToMany
    @JoinTable(name = "role_privileges",joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id"),inverseJoinColumns = @JoinColumn(name = "privilege_id",referencedColumnName = "privilege_id"))
    private Set<Privilege> privileges = new HashSet<>();
}
