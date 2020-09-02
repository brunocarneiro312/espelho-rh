package br.com.imasoft.springsectemplate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Float price;

    @ManyToOne
    private Cart cart;
}
