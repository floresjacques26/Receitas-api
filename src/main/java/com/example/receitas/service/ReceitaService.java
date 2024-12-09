package com.example.receitas.service;

import com.example.receitas.entity.DTO.AlterarReceitaDTO;
import com.example.receitas.entity.DTO.CriarReceitaDTO;
import com.example.receitas.entity.Ingrediente;
import com.example.receitas.entity.Receita;
import com.example.receitas.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {
    private ReceitaRepository receitaRepository;
    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Receita criarReceita(CriarReceitaDTO criarReceitaDTO) throws Exception{
        Receita receita = new Receita();
        receita.setNome(criarReceitaDTO.getNome());
        receita.setIngredientes(criarReceitaDTO.getIngredientes());
        receita.setModoPreparo(criarReceitaDTO.getModoPreparo());
        receita.setTempoPreparo(criarReceitaDTO.getTempoPreparo());

        return receitaRepository.save(receita);

    }

    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    public Optional<Receita> buscarReceitaPorId(Long id) {
        return receitaRepository.findById(id);
    }

    public void excluirReceita(Long id) {
        receitaRepository.deleteById(id);
    }

    public AlterarReceitaDTO atualizarReceita(AlterarReceitaDTO alterarReceitaDTO) throws Exception{
        Optional<Receita> receita = receitaRepository.findById(alterarReceitaDTO.getId());
        if (receita.isPresent()) {

            receita.get().setNome(alterarReceitaDTO.getNome());
            receita.get().setIngredientes(alterarReceitaDTO.getIngredientes());
            receita.get().setModoPreparo(alterarReceitaDTO.getModoPreparo());
            receita.get().setTempoPreparo(alterarReceitaDTO.getTempoPreparo());

            receitaRepository.save(receita.get());
            return alterarReceitaDTO;
        }

        throw new Exception("Receita não encontrada");

    }

    public List<Receita> buscarReceitasPorNome(String nome) {
        return receitaRepository.findByNome(nome);
    }

}
