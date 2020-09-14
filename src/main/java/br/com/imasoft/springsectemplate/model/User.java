package br.com.imasoft.springsectemplate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author brunocarneiro
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "USER")
public class User implements Serializable {

    public User() {

    }

    private User(Builder builder) {
        this.id = builder.id;
        this.key = builder.key;
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
        this.birthdate = builder.birthdate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "KEY", unique = true)
    private String key;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private List<Role> roles;

    /**
     * -------
     * Builder
     * -------
     */
    public static class Builder {

        private Integer id;
        private String key;
        private String email;
        private String password;
        private String name;
        private LocalDate birthdate;

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
