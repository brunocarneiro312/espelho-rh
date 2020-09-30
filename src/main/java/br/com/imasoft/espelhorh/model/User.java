package br.com.imasoft.espelhorh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

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

    public User(String email, String password, String name, LocalDate birthdate, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.roles = roles;
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.key = builder.key;
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
        this.birthdate = builder.birthdate;
        this.avatar = builder.avatar;
        this.roles = builder.roles;
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

    @Lob
    private byte[] avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private List<Role> roles;

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

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
        private byte[] avatar;
        private List<Role> roles;

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

        public Builder avatar(byte[] avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder roles(List<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
