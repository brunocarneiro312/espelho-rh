package br.com.imasoft.springsectemplate.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public static class Builder {

        private Integer id;
        private String roleName;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        public Role build() {
            return new Role(this.id, this.roleName);
        }

    }
}
