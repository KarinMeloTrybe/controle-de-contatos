package br.com.km.AppPessoas.service.interfaces;


import br.com.km.AppPessoas.model.Contato;
import br.com.km.AppPessoas.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface ContatoServiceInterface {
    Contato save(Contato contato);
    Optional<Contato> getById(Long id);
    List<Contato> getAll();
    Contato update(Contato contato);
    void delete(Long id);
}