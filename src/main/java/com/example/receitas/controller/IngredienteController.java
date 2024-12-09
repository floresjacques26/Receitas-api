package com.example.receitas.controller;

import com.example.receitas.entity.DTO.AlterarIngredienteDTO;
import com.example.receitas.entity.DTO.CriarIngredienteDTO;
import com.example.receitas.entity.Ingrediente;
import com.example.receitas.service.IngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {
    private final IngredienteService ingredienteService;
    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> criarIngrediente(@RequestBody CriarIngredienteDTO ingrediente) {
        try{
            Ingrediente ingredienteCriado = ingredienteService.criarIngrediente(ingrediente);
            return ResponseEntity.ok(ingredienteCriado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarIngrediente() {
        try{
            List<Ingrediente> ingredientes = ingredienteService.listarIngredientes();
            return ResponseEntity.ok(ingredientes);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarIngredientePorId(@PathVariable Long id) {
        try{
            Optional<Ingrediente> ingrediente = ingredienteService.buscarIngredientePorId(id);
            if (ingrediente.isPresent()) {
                return ResponseEntity.ok(ingrediente.get());

            }
            else return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @GetMapping("/buscarpornome/{nome}")
    public ResponseEntity<?> buscarIngredientePorNome(@PathVariable String nome) {
        try{
            List<Ingrediente> ingredientes = ingredienteService.buscarIngredientePorNome(nome);
            return ResponseEntity.ok(ingredientes);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterarIngrediente(@RequestBody AlterarIngredienteDTO alterarIngredienteDTO) {
        try{
            ingredienteService.atualizarIngrediente(alterarIngredienteDTO);
            return ResponseEntity.ok(alterarIngredienteDTO);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarIngrediente(@PathVariable Long id) {
        try{
            ingredienteService.excluirIngrediente(id);
            return ResponseEntity.ok("Excluido com sucesso");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
