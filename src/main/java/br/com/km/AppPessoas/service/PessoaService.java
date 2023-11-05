package br.com.km.AppPessoas.service;

import br.com.km.AppPessoas.model.Pessoa;
import br.com.km.AppPessoas.repository.PessoaRepository;
import br.com.km.AppPessoas.service.interfaces.PessoaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService implements PessoaServiceInterface {

    private PessoaRepository pessoaRepository;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> getById(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa update(Long id, Pessoa pessoa) {
        Optional<Pessoa> upPessoa = pessoaRepository.findById(id);

        if (upPessoa.isPresent()) {
            Pessoa existingPessoa = upPessoa.get();
            existingPessoa.setNome(pessoa.getNome());
            existingPessoa.setEndereco(pessoa.getEndereco());
            existingPessoa.setCep(pessoa.getCep());
            existingPessoa.setCidade(pessoa.getCidade());
            existingPessoa.setUf(pessoa.getUf());
            return pessoaRepository.save(existingPessoa);
        }
        return null;
    }
    @Override
    public void delete(Long id) {
pessoaRepository.deleteById(id);
    }
}
