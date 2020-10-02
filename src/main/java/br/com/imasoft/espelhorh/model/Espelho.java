package br.com.imasoft.espelhorh.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ESPELHO")
public class Espelho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Lob
    private byte[] espelho;

    @Column(name = "UPLOAD_AT")
    private Date uploadedAt;

    @Column(name = "ANO_MES", length = 7)
    private String anoMes;

    @ManyToOne
    private Funcionario funcionario;

    @Override
    public String toString() {
        return "Espelho{" +
                "id=" + id +
                ", uploadedAt=" + uploadedAt +
                ", anoMes='" + anoMes + '\'' +
                '}';
    }

    public Espelho(Integer id, byte[] espelho, Date uploadedAt, String anoMes, Funcionario funcionario) {
        this.id = id;
        this.espelho = espelho;
        this.uploadedAt = uploadedAt;
        this.anoMes = anoMes;
        this.funcionario = funcionario;
    }

    public static class Builder {
        private Integer id;
        private byte[] espelho;
        private Date uploadedAt;
        private String anoMes;
        private Funcionario funcionario;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder espelho(byte[] espelho) {
            this.espelho = espelho;
            return this;
        }

        public Builder uploadedAt(Date uploadedAt) {
            this.uploadedAt = uploadedAt;
            return this;
        }

        public Builder anoMes(String anoMes) {
            this.anoMes = anoMes;
            return this;
        }

        public Builder funcionario(Funcionario funcionario) {
            this.funcionario = funcionario;
            return this;
        }

        public Espelho build() {
            return new Espelho(id, espelho, uploadedAt, anoMes, funcionario);
        }
    }

}
