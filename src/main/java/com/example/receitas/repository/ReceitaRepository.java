package com.example.receitas.repository;

import com.example.receitas.entity.Receita;
import com.example.receitas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findByNome(String nome);
}
