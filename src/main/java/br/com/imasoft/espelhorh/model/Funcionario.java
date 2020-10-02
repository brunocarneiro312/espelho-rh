package br.com.imasoft.espelhorh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;


    @Column(name = "RG", unique = true, nullable = false)
    private String rg;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "funcionarios")
    private List<Notificacao> notificacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Espelho> espelhos;

    @OneToOne(cascade = { CascadeType.ALL })
    private User user;

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", rg='" + rg + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

    public Funcionario(Builder builder) {
        this.rg = builder.rg;
        this.nome = builder.nome;
        this.notificacoes = builder.notificacoes;
        this.espelhos = builder.espelhos;
        this.user = builder.user;
    }
    
    public static class Builder {

        private String rg;
        private String nome;
        private List<Notificacao> notificacoes;
        private List<Espelho> espelhos;
        private User user;

        public Builder rg(String rg) {
            this.rg = rg;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder notificacoes(List<Notificacao> notificacoes) {
            this.notificacoes = notificacoes;
            return this;
        }

        public Builder espelhos(List<Espelho> espelhos) {
            this.espelhos = espelhos;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Funcionario build() {
            return new Funcionario(this);
        }
    }

}
