package br.com.imasoft.espelhorh.service.impl;

import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.repository.FuncionarioRepository;
import br.com.imasoft.espelhorh.service.FuncionarioService;
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
class FuncionarioServiceImplTest {

    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setUp() {
        this.funcionarioService = new FuncionarioServiceImpl(this.funcionarioRepository);
    }

    @Test
    @DisplayName("Given funcionario, when save, then save funcionario")
    public void givenFuncionario_whenSave_thenSaveFuncionario() throws Exception {

        // given
        Funcionario funcionario = this.getFuncionario();

        given(this.funcionarioRepository.save(funcionario))
                .willReturn(funcionario);

        // when
        Funcionario newFuncionario = this.funcionarioService.save(funcionario);

        // then
        verify(funcionarioRepository).save(funcionario);

        assertAll("Save",
                () -> assertNotNull(newFuncionario, "O funcionário não deveria estar nulo."),
                () -> assertEquals(funcionario.toString(), newFuncionario.toString()));

    }

    @Test
    @DisplayName("Given funcionario, when update, then return funcionario updated")
    public void givenFuncionario_whenUpdate_thenReturnFuncionarioUpdated() throws Exception {

        // given
        Funcionario funcionario = this.getFuncionario();

        given(this.funcionarioRepository.saveAndFlush(funcionario))
                .willReturn(getUpdatedFuncionario());

        // when
        Funcionario newFuncionario = this.funcionarioService.update(funcionario);

        // then
        verify(funcionarioRepository).saveAndFlush(funcionario);

        assertAll("Update",
                () -> assertNotNull(newFuncionario, "O funcionário não deveria estar nulo."),
                () -> assertNotEquals(funcionario.toString(), newFuncionario.toString(), "Os funcionários deveriam ter informações diferentes."),
                () -> assertEquals("7777777", newFuncionario.getRg(), "O rg do funcionário deveria ser 7777777"));
    }

    @Test
    @DisplayName("Given id funcionario, when get, then return funcionario")
    public void givenIdFuncionario_whenGet_thenReturnFuncionario() throws Exception {

        // given
        Integer funcionarioId = this.getFuncionario().getId();

        given(this.funcionarioRepository.findById(funcionarioId))
                .willReturn(Optional.of(this.getFuncionario()));

        // when
        Funcionario recoveredFuncionario = this.funcionarioService.findById(funcionarioId);

        // then
        verify(this.funcionarioRepository).findById(funcionarioId);

        assertAll("FindById",
                () -> assertNotNull(recoveredFuncionario, "O funcionário não deveria estar nulo."));
    }

    @Test
    @DisplayName("When list, then return funcionarios")
    public void whenList_thenReturnFuncionarios() throws Exception {

        // when
        given(this.funcionarioRepository.findAll())
                .willReturn(Collections.singletonList(this.getFuncionario()));

        List<Funcionario> funcionarios = this.funcionarioService.findAll();

        // then
        verify(this.funcionarioRepository).findAll();

        assertAll("FindAll",
                () -> assertNotNull(funcionarios),
                () -> assertTrue(!funcionarios.isEmpty()));
    }

    @Test
    @DisplayName("Given id funcionario, when delete, then delete funcionario")
    public void givenIdFuncionario_whenDelete_thenDeleteFuncionario() throws Exception {

        // given
        Integer funcionarioId = this.getFuncionario().getId();

        given(this.funcionarioRepository.findById(funcionarioId))
                .willReturn(Optional.of(this.getFuncionario()));

        // when
        Funcionario funcionarioDeleted = this.funcionarioService.deleteById(funcionarioId);

        // then
        assertAll("Delete", () ->
                assertNotNull(funcionarioDeleted, "O funcionário não deveria estar nulo."));
    }

    private Funcionario getFuncionario() {
        return new Funcionario.Builder()
                .rg("1234567")
                .nome("Bruno")
                .build();
    }

    private Funcionario getUpdatedFuncionario() {
        return new Funcionario.Builder()
                .rg("7777777")
                .nome("Bruno Carneiro")
                .build();
    }

}