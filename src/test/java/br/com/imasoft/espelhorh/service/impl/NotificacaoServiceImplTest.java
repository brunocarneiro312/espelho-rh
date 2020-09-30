package br.com.imasoft.espelhorh.service.impl;

import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.model.Notificacao;
import br.com.imasoft.espelhorh.repository.NotificacaoRepository;
import br.com.imasoft.espelhorh.service.NotificacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificacaoServiceImplTest {

    private NotificacaoService notificacaoService;

    @Mock
    private NotificacaoRepository notificacaoRepository;

    @BeforeEach
    void setUp() {
        this.notificacaoService = new NotificacaoServiceImpl(this.notificacaoRepository);
    }

    @Test
    @DisplayName("Given notificacao, when save, then save notificacao")
    public void givenNotificacao_whenSave_thenSaveNotificacao() throws Exception {

        // given
        Notificacao notificacao = this.getNotificacao();

        given(this.notificacaoRepository.save(notificacao))
                .willReturn(notificacao);

        // when
        Notificacao newNotificacao = this.notificacaoService.save(notificacao);

        // then
        verify(notificacaoRepository).save(notificacao);

        assertAll("Save",
                () -> assertNotNull(newNotificacao, "A notificação não deveria estar nula."),
                () -> assertEquals(notificacao.toString(), newNotificacao.toString()));

    }

    @Test
    @DisplayName("Given notificação, when update, then return notificacao updated")
    public void givenNotificacao_whenUpdate_thenReturnNotificacaoUpdated() throws Exception {

        // given
        Notificacao notificacao = this.getNotificacao();

        given(this.notificacaoRepository.saveAndFlush(notificacao))
                .willReturn(getUpdatedNotificacao());

        // when
        Notificacao newNotificacao = this.notificacaoService.update(notificacao);

        // then
        verify(notificacaoRepository).saveAndFlush(notificacao);

        assertAll("Update",
                () -> assertNotNull(newNotificacao, "A notificação não deveria estar nula."),
                () -> assertNotEquals(notificacao.toString(), newNotificacao.toString(), "As notificações deveriam ter informações diferentes."),
                () -> assertEquals("test notification - updated", newNotificacao.getText(), "A notificação deveria ser alterada"));
    }

    @Test
    @DisplayName("Given id notificacao, when get, then return notificacao")
    public void givenIdNotificacao_whenGet_thenReturnNotificacao() throws Exception {

        // given
        Integer notificacaoId = this.getNotificacao().getId();

        given(this.notificacaoRepository.findById(notificacaoId))
                .willReturn(Optional.of(this.getNotificacao()));

        // when
        Notificacao recoveredNotificacao = this.notificacaoService.findById(notificacaoId);

        // then
        verify(this.notificacaoRepository).findById(notificacaoId);

        assertAll("FindById",
                () -> assertNotNull(recoveredNotificacao, "A notificação não deveria estar nula."));
    }

    @Test
    @DisplayName("When list, then return notificacoes")
    public void whenList_thenReturnNotificacoes() throws Exception {

        // when
        given(this.notificacaoRepository.findAll())
                .willReturn(Collections.singletonList(this.getNotificacao()));

        List<Notificacao> notificacoes = this.notificacaoService.findAll();

        // then
        verify(this.notificacaoRepository).findAll();

        assertAll("FindAll",
                () -> assertNotNull(notificacoes),
                () -> assertTrue(!notificacoes.isEmpty()));
    }

    @Test
    @DisplayName("Given id notificacao, when delete, then delete notificacao")
    public void givenIdNotificacao_whenDelete_thenDeleteNotificacao() throws Exception {

        // given
        Integer notificacaoId = this.getNotificacao().getId();

        given(this.notificacaoRepository.findById(notificacaoId))
                .willReturn(Optional.of(this.getNotificacao()));

        // when
        Notificacao notificacaoDeleted = this.notificacaoService.deleteById(notificacaoId);

        // then
        assertAll("Delete", () ->
                assertNotNull(notificacaoDeleted, "A notificação não deveria estar nula."));
    }

    private Funcionario getFuncionario() {
        return new Funcionario.Builder()
                .rg("1234567")
                .nome("Bruno")
                .build();
    }

    private Notificacao getNotificacao() {
        return new Notificacao.Builder()
                .id(1)
                .text("test notification")
                .dataInicio(new Date(522979200000L))
                .dataFim(new Date(554515200000L))
                .funcionarios(Collections.singletonList(this.getFuncionario()))
                .build();
    }

    private Notificacao getUpdatedNotificacao() {
        return new Notificacao.Builder()
                .id(1)
                .text("test notification - updated")
                .dataInicio(new Date(522979200000L))
                .dataFim(new Date(554515200000L))
                .funcionarios(Collections.singletonList(this.getFuncionario()))
                .build();
    }
}