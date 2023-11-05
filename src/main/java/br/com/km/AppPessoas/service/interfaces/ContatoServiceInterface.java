package br.com.km.AppPessoas.service.interfaces;


import br.com.km.AppPessoas.model.Contato;
import br.com.km.AppPessoas.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface ContatoServiceInterface {
    Contato save(Long id, Contato contato);
    Optional<Contato> getById(Long id);
    List<Contato> getAll();
    Optional<Contato> update(Long id, Contato contato);
    void delete(Long id);
}