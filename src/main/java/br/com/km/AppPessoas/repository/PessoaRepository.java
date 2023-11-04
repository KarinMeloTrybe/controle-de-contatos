package br.com.km.AppPessoas.repository;

import br.com.km.AppPessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

}