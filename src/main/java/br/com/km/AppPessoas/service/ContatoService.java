package br.com.km.AppPessoas.service;

import br.com.km.AppPessoas.model.Contato;
import br.com.km.AppPessoas.model.Pessoa;
import br.com.km.AppPessoas.repository.ContatoRepository;
import br.com.km.AppPessoas.repository.PessoaRepository;
import br.com.km.AppPessoas.service.interfaces.ContatoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService implements ContatoServiceInterface {


    private ContatoRepository contatoRepository;
    private PessoaRepository pessoaRepository;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository, PessoaRepository pessoaRepository) {
        this.contatoRepository = contatoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Contato save(Long id, Contato contato) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            contato.setPessoa(pessoa);
            return contatoRepository.save(contato);
        }
        return null;
    }

    @Override
    public Optional<Contato> getById(Long id) {
        return contatoRepository.findById(id);
    }

    @Override
    public List<Contato> getAll() {
        return contatoRepository.findAll();
    }

    @Override
        public Optional<Contato> update(Long id, Contato contato) {
            Optional<Contato> existingContato = contatoRepository.findById(id);

            if (existingContato.isPresent()) {
                Contato updatedContato = existingContato.get();
                if (contato.getContato() != null) {
                    updatedContato.setContato(contato.getContato());
                }
                if (contato.getTipoDeContato() != null) {
                    updatedContato.setTipoDeContato(contato.getTipoDeContato());
                }
                contatoRepository.save(updatedContato);
            }
            return existingContato;
        }

    @Override
    public void delete(Long id) {
        contatoRepository.deleteById(id);
    }
}
