package com.example.receitas.controller;

import com.example.receitas.entity.DTO.AlterarReceitaDTO;
import com.example.receitas.entity.DTO.AlterarSenhaDTO;
import com.example.receitas.entity.DTO.AlterarUsuarioDTO;
import com.example.receitas.entity.DTO.CriarUsuarioDTO;
import com.example.receitas.entity.Usuario;
import com.example.receitas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> criarUsuario(@RequestBody CriarUsuarioDTO usuario) {
        try{
            CriarUsuarioDTO usuarioCriado = usuarioService.criarUsuario(usuario);
            return ResponseEntity.ok(usuarioCriado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarUsuario(){
        try{
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Long id) {
        try {
            Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/alterar/senha")
    public ResponseEntity<?> alterarSenha(@RequestBody AlterarSenhaDTO usuario){
        try{
            Usuario usuarioCriado = usuarioService.alterarSenha(usuario);
            return ResponseEntity.ok(usuarioCriado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterarUsuario(@RequestBody AlterarUsuarioDTO usuario) {
        try{
            usuarioService.alterarUsuario(usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        try{
            usuarioService.excluirUsuario(id);
            return ResponseEntity.ok("Usuario excluido com sucesso");
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
