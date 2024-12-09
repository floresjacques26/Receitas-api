package com.example.receitas.controller;

import com.example.receitas.entity.DTO.AlterarReceitaDTO;
import com.example.receitas.entity.DTO.CriarReceitaDTO;
import com.example.receitas.entity.Receita;
import com.example.receitas.service.ReceitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> criarReceita(@RequestBody CriarReceitaDTO receita) {
        try{
            Receita receitaCriada = receitaService.criarReceita(receita);
            return ResponseEntity.ok(receitaCriada);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarReceita() {
        try{
            List<Receita> receitas = receitaService.listarReceitas();
            return ResponseEntity.ok(receitas);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarReceitaPorId(@PathVariable Long id) {
        try{
            Optional<Receita> receita = receitaService.buscarReceitaPorId(id);
            if(receita.isPresent()) {
                return ResponseEntity.ok(receita.get());
            }
            else return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/buscarpornome/{nome}")
    public ResponseEntity<?> buscarReceitaPorNome(@PathVariable String nome) {
        try{
            List<Receita> receitas = receitaService.buscarReceitasPorNome(nome);
            return ResponseEntity.ok(receitas);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterarReceita(@RequestBody AlterarReceitaDTO receita) {
        try{
            receitaService.atualizarReceita(receita);
            return ResponseEntity.ok(receita);
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarReceita(@PathVariable Long id) {
        try{
            receitaService.excluirReceita(id);
            return ResponseEntity.ok("Excluido com sucesso");
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }



}
