package org.example.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.entity.Tarefa;
import org.example.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TarefaService.class})
@ExtendWith(SpringExtension.class)
class TarefaServiceTest {
    @MockBean
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaService tarefaService;

    /**
     * Method under test: {@link TarefaService#listarTodas()}
     */
    @Test
    void testListarTodas() {
        ArrayList<Tarefa> tarefaList = new ArrayList<>();
        when(tarefaRepository.findAll()).thenReturn(tarefaList);
        List<Tarefa> actualListarTodasResult = tarefaService.listarTodas();
        assertSame(tarefaList, actualListarTodasResult);
        assertTrue(actualListarTodasResult.isEmpty());
        verify(tarefaRepository).findAll();
    }

    /**
     * Method under test: {@link TarefaService#listarTodas()}
     */
    @Test
    void testListarTodas2() {
        when(tarefaRepository.findAll()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> tarefaService.listarTodas());
        verify(tarefaRepository).findAll();
    }

    /**
     * Method under test: {@link TarefaService#buscarPorId(Long)}
     */
    @Test
    void testBuscarPorId() {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Descricao");
        tarefa.setId(1L);
        tarefa.setStatus("Status");
        tarefa.setTitulo("Titulo");
        Optional<Tarefa> ofResult = Optional.of(tarefa);
        when(tarefaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<Tarefa> actualBuscarPorIdResult = tarefaService.buscarPorId(1L);
        assertSame(ofResult, actualBuscarPorIdResult);
        assertTrue(actualBuscarPorIdResult.isPresent());
        verify(tarefaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TarefaService#buscarPorId(Long)}
     */
    @Test
    void testBuscarPorId2() {
        when(tarefaRepository.findById(Mockito.<Long>any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> tarefaService.buscarPorId(1L));
        verify(tarefaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TarefaService#salvar(Tarefa)}
     */
    @Test
    void testSalvar() {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Descricao");
        tarefa.setId(1L);
        tarefa.setStatus("Status");
        tarefa.setTitulo("Titulo");
        when(tarefaRepository.save(Mockito.<Tarefa>any())).thenReturn(tarefa);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDescricao("Descricao");
        tarefa2.setId(1L);
        tarefa2.setStatus("Status");
        tarefa2.setTitulo("Titulo");
        assertSame(tarefa, tarefaService.salvar(tarefa2));
        verify(tarefaRepository).save(Mockito.<Tarefa>any());
    }

    /**
     * Method under test: {@link TarefaService#salvar(Tarefa)}
     */
    @Test
    void testSalvar2() {
        when(tarefaRepository.save(Mockito.<Tarefa>any())).thenThrow(new RuntimeException("foo"));

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Descricao");
        tarefa.setId(1L);
        tarefa.setStatus("Status");
        tarefa.setTitulo("Titulo");
        assertThrows(RuntimeException.class, () -> tarefaService.salvar(tarefa));
        verify(tarefaRepository).save(Mockito.<Tarefa>any());
    }

    /**
     * Method under test: {@link TarefaService#atualizar(Long, Tarefa)}
     */
    @Test
    void testAtualizar() {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Descricao");
        tarefa.setId(1L);
        tarefa.setStatus("Status");
        tarefa.setTitulo("Titulo");
        Optional<Tarefa> ofResult = Optional.of(tarefa);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDescricao("Descricao");
        tarefa2.setId(1L);
        tarefa2.setStatus("Status");
        tarefa2.setTitulo("Titulo");
        when(tarefaRepository.save(Mockito.<Tarefa>any())).thenReturn(tarefa2);
        when(tarefaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Tarefa tarefaAtualizada = new Tarefa();
        tarefaAtualizada.setDescricao("Descricao");
        tarefaAtualizada.setId(1L);
        tarefaAtualizada.setStatus("Status");
        tarefaAtualizada.setTitulo("Titulo");
        assertSame(tarefa2, tarefaService.atualizar(1L, tarefaAtualizada));
        verify(tarefaRepository).save(Mockito.<Tarefa>any());
        verify(tarefaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TarefaService#atualizar(Long, Tarefa)}
     */
    @Test
    void testAtualizar2() {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Descricao");
        tarefa.setId(1L);
        tarefa.setStatus("Status");
        tarefa.setTitulo("Titulo");
        Optional<Tarefa> ofResult = Optional.of(tarefa);
        when(tarefaRepository.save(Mockito.<Tarefa>any())).thenThrow(new RuntimeException("Tarefa não encontrada!"));
        when(tarefaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Tarefa tarefaAtualizada = new Tarefa();
        tarefaAtualizada.setDescricao("Descricao");
        tarefaAtualizada.setId(1L);
        tarefaAtualizada.setStatus("Status");
        tarefaAtualizada.setTitulo("Titulo");
        assertThrows(RuntimeException.class, () -> tarefaService.atualizar(1L, tarefaAtualizada));
        verify(tarefaRepository).save(Mockito.<Tarefa>any());
        verify(tarefaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TarefaService#atualizar(Long, Tarefa)}
     */
    @Test
    void testAtualizar3() {
        when(tarefaRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Tarefa tarefaAtualizada = new Tarefa();
        tarefaAtualizada.setDescricao("Descricao");
        tarefaAtualizada.setId(1L);
        tarefaAtualizada.setStatus("Status");
        tarefaAtualizada.setTitulo("Titulo");
        assertThrows(RuntimeException.class, () -> tarefaService.atualizar(1L, tarefaAtualizada));
        verify(tarefaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TarefaService#deletar(Long)}
     */
    @Test
    void testDeletar() {
        doNothing().when(tarefaRepository).deleteById(Mockito.<Long>any());
        tarefaService.deletar(1L);
        verify(tarefaRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TarefaService#deletar(Long)}
     */
    @Test
    void testDeletar2() {
        doThrow(new RuntimeException("foo")).when(tarefaRepository).deleteById(Mockito.<Long>any());
        assertThrows(RuntimeException.class, () -> tarefaService.deletar(1L));
        verify(tarefaRepository).deleteById(Mockito.<Long>any());
    }
}

