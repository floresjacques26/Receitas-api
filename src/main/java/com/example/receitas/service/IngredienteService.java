package com.example.receitas.service;

import com.example.receitas.entity.DTO.AlterarIngredienteDTO;
import com.example.receitas.entity.DTO.CriarIngredienteDTO;
import com.example.receitas.entity.Ingrediente;
import com.example.receitas.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {
    private IngredienteRepository ingredienteRepository;
    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public Ingrediente criarIngrediente(CriarIngredienteDTO criarIngredienteDTO) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome(criarIngredienteDTO.getNome());
        ingrediente.setDescricao(criarIngredienteDTO.getDescricao());

        return ingredienteRepository.save(ingrediente);
    }

    public List<Ingrediente> listarIngredientes() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> buscarIngredientePorId(Long id) {
        return ingredienteRepository.findById(id);
    }

    public AlterarIngredienteDTO atualizarIngrediente(AlterarIngredienteDTO alterarIngredienteDTO) throws Exception{
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(alterarIngredienteDTO.getId());
        if (ingrediente.isPresent()) {
            ingrediente.get().setNome(alterarIngredienteDTO.getNome());
            ingrediente.get().setDescricao(alterarIngredienteDTO.getDescricao());

            ingredienteRepository.save(ingrediente.get());
            return alterarIngredienteDTO;
        }

        throw new Exception("Ingrediente não encontrado");
    }

    public void excluirIngrediente(Long id) {
        ingredienteRepository.deleteById(id);
    }

    public List<Ingrediente> buscarIngredientePorNome(String nome) {
        return ingredienteRepository.findByNome(nome);
    }

}
