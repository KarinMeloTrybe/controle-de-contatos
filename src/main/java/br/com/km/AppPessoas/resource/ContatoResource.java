package br.com.km.AppPessoas.resource;


import br.com.km.AppPessoas.model.Contato;
import br.com.km.AppPessoas.model.Pessoa;
import br.com.km.AppPessoas.service.ContatoService;
import br.com.km.AppPessoas.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContatoResource {

    private ContatoService contatoService;
    private PessoaService pessoaService;

    @Autowired
    public ContatoResource(ContatoService contatoService, PessoaService pessoaService) {
        this.contatoService = contatoService;
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Este endpoint lista todos os contatos de uma pessoa")
    @GetMapping("/contatos")
    public ResponseEntity<List<Contato>> getAll() {
        List<Contato> contatos = contatoService.getAll();
        if (contatos == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(contatos);
    }

    @Operation(summary = "Este endpoint busca um contato pelo seu id")
    @GetMapping("/contatos/{id}")
    public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.getById(id);
        if(contato == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(contato);
    }

    @Operation(summary = "Este endpoint cria um contato")
    @PostMapping("/pessoas/{id}/contatos")
    public ResponseEntity<Contato> save(@PathVariable Long id, @RequestBody Contato contato) {
        if (!pessoaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (contato == null || contato.getContato() == null || contato.getTipoDeContato() == null){
            return ResponseEntity.notFound().build();
        }
        Contato newContato = contatoService.save(id, contato);
        return new ResponseEntity<>(newContato, HttpStatus.CREATED);
    }

    @Operation(summary = "Este endpoint atualiza um contato")
    @PutMapping("/contatos/{id}")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato) {
        Optional<Contato> existingContato = contatoService.update(id, contato);

        if (existingContato.isPresent()) {
            return ResponseEntity.ok(existingContato.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Este endpoint deleta um contato")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!contatoService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
