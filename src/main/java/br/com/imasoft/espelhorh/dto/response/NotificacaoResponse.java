package br.com.imasoft.espelhorh.dto.response;

import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.model.Notificacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NotificacaoResponse implements Serializable {

    private Integer id;
    private String text;
    private Date dataInicio;
    private Date dataFim;
    private List<Funcionario> funcionarios;

    public NotificacaoResponse(Notificacao notificacao) {
        this.id = notificacao.getId();
        this.text = notificacao.getText();
        this.dataInicio = notificacao.getDataInicio();
        this.dataFim = notificacao.getDataFim();
        this.funcionarios = notificacao.getFuncionarios();
    }
}
