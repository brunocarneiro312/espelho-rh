package br.com.imasoft.espelhorh.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "NOTIFICACAO")
public class Notificacao implements Serializable {

    public Notificacao(Integer id, String text, Date dataInicio, Date dataFim, List<Funcionario> funcionarios) {
        this.id = id;
        this.text = text;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.funcionarios = funcionarios;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Lob
    @Column(name = "TEXT", length = 500)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;


    @ManyToMany
    @JoinTable(
            name = "NOTIFICACAO_FUNCIONARIO",
            joinColumns = @JoinColumn(name = "ID_NOTIFICACAO"),
            inverseJoinColumns = @JoinColumn(name = "ID_FUNCIONARIO"))
    private List<Funcionario> funcionarios;

    public static class Builder {
        private Integer id;
        private String text;
        private Date dataInicio;
        private Date dataFim;
        private List<Funcionario> funcionarios;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder dataInicio(Date dataInicio) {
            this.dataInicio = dataInicio;
            return this;
        }

        public Builder dataFim(Date dataFim) {
            this.dataFim = dataFim;
            return this;
        }

        public Builder funcionarios(List<Funcionario> funcionarios) {
            this.funcionarios = funcionarios;
            return this;
        }

        public Notificacao build() {
            return new Notificacao(id, text, dataInicio, dataFim, funcionarios);
        }
    }

}
