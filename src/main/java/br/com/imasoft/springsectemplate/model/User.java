package br.com.imasoft.springsectemplate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private List<Role> roles;
}
