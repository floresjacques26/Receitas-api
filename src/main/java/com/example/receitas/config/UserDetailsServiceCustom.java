package com.example.receitas.config;

import com.example.receitas.entity.Usuario;
import com.example.receitas.service.UsuarioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    private final UsuarioService usuarioService;

    public UserDetailsServiceCustom(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorEmail(username);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }


        String role = usuario.get().getRole();
        if(!role.startsWith("ROLE_"))
            role = "ROLE_"+role;
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        Set<GrantedAuthority> authorities = new HashSet();
        authorities.add(authority);

        User user = new User(usuario.get().getEmail(), usuario.get().getSenha(), authorities);
        return user;


    }
}
