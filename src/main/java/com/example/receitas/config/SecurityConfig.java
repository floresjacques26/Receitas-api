package com.example.receitas.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(c -> c.disable())
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/usuario/add").permitAll();
                            authorizeConfig.requestMatchers("/usuario/alterar").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/usuario/alterar/senha").permitAll();
                            authorizeConfig.requestMatchers("/usuario/buscar").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/usuario/deletar").hasRole("ADMIN");

                            authorizeConfig.requestMatchers("/ingrediente/add").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/ingrediente/alterar").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/ingrediente/buscar").permitAll();
                            authorizeConfig.requestMatchers("/ingrediente/buscarpornome").permitAll();
                            authorizeConfig.requestMatchers("/ingrediente/deletar").hasRole("ADMIN");


                            authorizeConfig.requestMatchers("/receita/add").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/receita/alterar").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/receita/buscar").permitAll();
                            authorizeConfig.requestMatchers("/receita/buscarpornome").permitAll();
                            authorizeConfig.requestMatchers("/receita/deletar").hasRole("ADMIN");


                            authorizeConfig.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();

                            authorizeConfig.anyRequest().authenticated();

                        }
                ).httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}