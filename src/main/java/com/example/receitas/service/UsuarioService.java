package com.example.receitas.service;

import com.example.receitas.entity.DTO.AlterarSenhaDTO;
import com.example.receitas.entity.DTO.AlterarUsuarioDTO;
import com.example.receitas.entity.DTO.CriarUsuarioDTO;
import com.example.receitas.entity.Usuario;
import com.example.receitas.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CriarUsuarioDTO criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(criarUsuarioDTO.getEmail());
        usuario.setNome(criarUsuarioDTO.getNome());
        usuario.setSenha(passwordEncoder.encode(criarUsuarioDTO.getSenha()));
        usuario.setRole(criarUsuarioDTO.getRole());

        usuarioRepository.save(usuario);
        return criarUsuarioDTO;
    }


    public AlterarUsuarioDTO alterarUsuario(AlterarUsuarioDTO alterarUsuarioDTO) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findById(alterarUsuarioDTO.getId());

        if (usuario.isPresent()) {
            usuario.get().setNome(alterarUsuarioDTO.getNome());
            usuario.get().setEmail(alterarUsuarioDTO.getEmail());
            usuario.get().setSenha(passwordEncoder.encode(alterarUsuarioDTO.getSenha()));
            usuario.get().setRole(alterarUsuarioDTO.getRole());

            usuarioRepository.save(usuario.get());
            return alterarUsuarioDTO;
        }
        throw new Exception("Usuário não existe");
    }


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario alterarSenha(AlterarSenhaDTO alterarSenhaDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(alterarSenhaDTO.getId());
        String senha = passwordEncoder.encode(alterarSenhaDTO.getSenha());
        usuario.get().setSenha(senha);
        return usuarioRepository.save(usuario.get());

    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

}
