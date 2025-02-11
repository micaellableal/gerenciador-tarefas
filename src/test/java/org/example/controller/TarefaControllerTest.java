package org.example.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.util.Optional;

import org.example.entity.Tarefa;
import org.example.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TarefaController.class})
@ExtendWith(SpringExtension.class)
class TarefaControllerTest {
    @Autowired
    private TarefaController tarefaController;

    @MockBean
    private TarefaService tarefaService;

    /**
     * Method under test: {@link TarefaController#atualizarTarefa(Long, Tarefa)}
     */
    @Test
    void testAtualizarTarefa() throws Exception {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Descricao");
        tarefa.setId(1L);
        tarefa.setStatus("Status");
        tarefa.setTitulo("Titulo");
        when(tarefaService.atualizar(Mockito.<Long>any(), Mockito.<Tarefa>any())).thenReturn(tarefa);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDescricao("Descricao");
        tarefa2.setId(1L);
        tarefa2.setStatus("Status");
        tarefa2.setTitulo("Titulo");
        String content = (new ObjectMapper()).writeValueAsString(tarefa2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/tarefas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(tarefaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"titulo\":\"Titulo\",\"descricao\":\"Descricao\",\"status\":\"Status\"}"));
    }

    /**
     * Method under test: {@link TarefaController#buscarTarefaPorId(Long)}
     */
    @Test
    void testBuscarTarefaPorId() throws Exception {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Descricao");
        tarefa.setId(1L);
        tarefa.setStatus("Status");
        tarefa.setTitulo("Titulo");
        Optional<Tarefa> ofResult = Optional.of(tarefa);
        when(tarefaService.buscarPorId(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tarefas/{id}", 1L);
        MockMvcBuilders.standaloneSetup(tarefaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"titulo\":\"Titulo\",\"descricao\":\"Descricao\",\"status\":\"Status\"}"));
    }

    /**
     * Method under test: {@link TarefaController#buscarTarefaPorId(Long)}
     */
    @Test
    void testBuscarTarefaPorId2() throws Exception {
        when(tarefaService.buscarPorId(Mockito.<Long>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tarefas/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tarefaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link TarefaController#criarTarefa(Tarefa)}
     */
    @Test
    void testCriarTarefa() throws Exception {
        when(tarefaService.listarTodas()).thenReturn(new ArrayList<>());

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Descricao");
        tarefa.setId(1L);
        tarefa.setStatus("Status");
        tarefa.setTitulo("Titulo");
        String content = (new ObjectMapper()).writeValueAsString(tarefa);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(tarefaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TarefaController#deletarTarefa(Long)}
     */
    @Test
    void testDeletarTarefa() throws Exception {
        doNothing().when(tarefaService).deletar(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/tarefas/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tarefaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TarefaController#deletarTarefa(Long)}
     */
    @Test
    void testDeletarTarefa2() throws Exception {
        doNothing().when(tarefaService).deletar(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/tarefas/{id}", 1L);
        requestBuilder.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tarefaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TarefaController#listarTarefas()}
     */
    @Test
    void testListarTarefas() throws Exception {
        when(tarefaService.listarTodas()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tarefas");
        MockMvcBuilders.standaloneSetup(tarefaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TarefaController#listarTarefas()}
     */
    @Test
    void testListarTarefas2() throws Exception {
        when(tarefaService.listarTodas()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tarefas");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(tarefaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

