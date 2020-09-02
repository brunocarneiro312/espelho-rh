package br.com.imasoft.springsectemplate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    @OneToOne
    private User cart;
}
