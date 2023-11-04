package br.com.km.AppPessoas.resource;


import br.com.km.AppPessoas.model.Contato;
import br.com.km.AppPessoas.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {

    private ContatoService contatoService;

    @Autowired
    public ContatoResource(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @Operation(summary = "Este endpoint lista todos os contatos de uma pessoa")
    @GetMapping
    public ResponseEntity<List<Contato>> getAll() {
        List<Contato> contatos = contatoService.getAll();
        if (contatos == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(contatos);
    }

    @Operation(summary = "Este endpoint busca um contato pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.getById(id);
        if(contato == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(contato);
    }

    @Operation(summary = "Este endpoint cria um contato")
    @PostMapping
    public ResponseEntity<Contato> save(@RequestBody Contato contato) {
        return new ResponseEntity<>(contatoService.save(contato), HttpStatus.CREATED);
    }

    @Operation(summary = "Este endpoint atualiza um contato")
    @PutMapping
    public ResponseEntity<Contato> update(@RequestBody Contato contato) {
        return new ResponseEntity<>(contatoService.update(contato), HttpStatus.CREATED);
    }

    @Operation(summary = "Este endpoint deleta um contato")
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id) {
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
