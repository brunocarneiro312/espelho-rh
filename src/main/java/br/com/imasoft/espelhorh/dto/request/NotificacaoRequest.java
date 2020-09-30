package br.com.imasoft.espelhorh.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NotificacaoRequest {

    private String text;
    private Integer funcionarioId;

}
