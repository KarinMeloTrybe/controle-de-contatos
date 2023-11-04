package br.com.km.AppPessoas.service.interfaces;

import br.com.km.AppPessoas.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaServiceInterface {
    Pessoa save(Pessoa pessoa);
    Optional<Pessoa> getById(Long id);
    List<Pessoa> getAll();
    Pessoa update(Pessoa pessoa);
    void delete(Long id);
}