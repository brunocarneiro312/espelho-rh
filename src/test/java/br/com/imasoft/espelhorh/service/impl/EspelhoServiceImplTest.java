package br.com.imasoft.espelhorh.service.impl;

import br.com.imasoft.espelhorh.model.Espelho;
import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.repository.EspelhoRepository;
import br.com.imasoft.espelhorh.service.EspelhoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EspelhoServiceImplTest {

    private EspelhoService espelhoService;

    @Mock
    private EspelhoRepository espelhoRepository;

    @BeforeEach
    void setUp() {
        this.espelhoService = new EspelhoServiceImpl(this.espelhoRepository);
    }

    @Test
    @DisplayName("Given espelho, when save, then save espelho")
    public void givenEspelho_whenSave_thenSaveEspelho() throws Exception {

        // given
        Espelho espelho = this.getEspelho();

        given(this.espelhoRepository.save(espelho))
                .willReturn(espelho);

        // when
        Espelho newEspelho = this.espelhoService.save(espelho);

        // then
        verify(espelhoRepository).save(espelho);

        assertAll("Save",
                () -> assertNotNull(newEspelho, "O espelho não deveria estar nulo."),
                () -> assertEquals(espelho.toString(), newEspelho.toString()));

    }

    @Test
    @DisplayName("Given espelho, when update, then return espelho updated")
    public void givenEspelho_whenUpdate_thenReturnEspelhoUpdated() throws Exception {

        // given
        Espelho espelho = this.getEspelho();

        given(this.espelhoRepository.saveAndFlush(espelho))
                .willReturn(getUpdatedEspelho());

        // when
        Espelho newEspelho = this.espelhoService.update(espelho);

        // then
        verify(espelhoRepository).saveAndFlush(espelho);

        assertAll("Update",
                () -> assertNotNull(newEspelho, "O espelho não deveria estar nulo."),
                () -> assertNotEquals(espelho.toString(), newEspelho.toString(), "Os espelhos deveriam ter informações diferentes."),
                () -> assertEquals("02/2020", newEspelho.getAnoMes(), "A data deveria ser 02/2020"));
    }

    @Test
    @DisplayName("Given id espelho, when get, then return espelho")
    public void givenIdFuncionario_whenGet_thenReturnFuncionario() throws Exception {

        // given
        Integer espelhoId = this.getEspelho().getId();

        given(this.espelhoRepository.findById(espelhoId))
                .willReturn(Optional.of(this.getEspelho()));

        // when
        Espelho recoveredEspelho = this.espelhoService.findById(espelhoId);

        // then
        verify(this.espelhoRepository).findById(espelhoId);

        assertAll("FindById",
                () -> assertNotNull(recoveredEspelho, "O espelho não deveria estar nulo."));
    }

    @Test
    @DisplayName("When list, then return espelhos")
    public void whenList_thenReturnFuncionarios() throws Exception {

        // when
        given(this.espelhoRepository.findAll())
                .willReturn(Collections.singletonList(this.getEspelho()));

        List<Espelho> espelhos = this.espelhoService.findAll();

        // then
        verify(this.espelhoRepository).findAll();

        assertAll("FindAll",
                () -> assertNotNull(espelhos, "A lista de espelhos não deveria estar nulo."),
                () -> assertTrue(!espelhos.isEmpty(), "A lista de espelhos não deveria estar vazia"));
    }

    @Test
    @DisplayName("Given id espelho, when delete, then delete espelho")
    public void givenIdEspelho_whenDelete_thenDeleteEspelho() throws Exception {

        // given
        Integer espelhoId = this.getEspelho().getId();

        given(this.espelhoRepository.findById(espelhoId))
                .willReturn(Optional.of(this.getEspelho()));

        // when
        Espelho espelhoDeleted = this.espelhoService.deleteById(espelhoId);

        // then
        assertAll("Delete", () ->
                assertNotNull(espelhoDeleted, "O espelho não deveria estar nulo."));
    }

    private Espelho getEspelho() {
        return new Espelho.Builder()
                .id(1)
                .espelho("bytes".getBytes())
                .uploadedAt(new Date(554548500000L))
                .anoMes("01/2020")
                .funcionario(this.getFuncionario())
                .build();
    }

    private Espelho getUpdatedEspelho() {
        return new Espelho.Builder()
                .id(1)
                .espelho("bytes".getBytes())
                .uploadedAt(new Date(554548500000L))
                .anoMes("02/2020")
                .funcionario(this.getFuncionario())
                .build();
    }

    private Funcionario getFuncionario() {
        return new Funcionario.Builder()
                .rg("1234567")
                .nome("Bruno")
                .build();
    }
}