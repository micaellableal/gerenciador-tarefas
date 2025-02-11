package org.example.controller;

import org.example.entity.Tarefa;
import org.example.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public List<Tarefa> listarTarefas(){
        return tarefaService.listarTodas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id){
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(id);
        return tarefa.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa){
        return ResponseEntity.ok(tarefaService.salvar(tarefa));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada){
        return ResponseEntity.ok(tarefaService.atualizar(id,tarefaAtualizada));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa (@PathVariable Long id){
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
