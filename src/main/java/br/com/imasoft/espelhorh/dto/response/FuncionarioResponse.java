package br.com.imasoft.espelhorh.dto.response;

import br.com.imasoft.espelhorh.model.Espelho;
import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.model.Notificacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FuncionarioResponse implements Serializable {

    private Integer id;
    private String nome;
    private String rg;
    private List<Notificacao> notificacoes;
    private List<Espelho> espelhos;

    public FuncionarioResponse(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.rg = funcionario.getRg();
        this.nome = funcionario.getNome();
        this.notificacoes = funcionario.getNotificacoes();
        this.espelhos = funcionario.getEspelhos();
    }
}
