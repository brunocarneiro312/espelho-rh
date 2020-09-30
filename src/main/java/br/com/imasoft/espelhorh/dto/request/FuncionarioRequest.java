package br.com.imasoft.espelhorh.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FuncionarioRequest {

    private String nome;
    private String rg;

}
