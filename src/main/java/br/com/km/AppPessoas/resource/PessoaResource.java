package br.com.km.AppPessoas.resource;


import br.com.km.AppPessoas.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.km.AppPessoas.model.Pessoa;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {

    private PessoaService pessoaService;

    @Autowired
    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Este endpoint lista todas as pessoas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.getAll();
        if(pessoas == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pessoas);

    }

    @Operation(summary = "Este endpoint busca uma pessoa pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.getById(id);
        if (!pessoaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Este endpoint cria uma nova pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
        if (pessoa == null || pessoa.getNome() == null) {
            return ResponseEntity.badRequest().build();
        }
        Pessoa newPessoa = pessoaService.save(pessoa);
        return ResponseEntity.ok(newPessoa);
    }

    @Operation(summary = "Este endpoint atualiza o cadastro de uma pessoa")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        if (!pessoaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (pessoa.getNome() != null) {
            Pessoa newPessoa = pessoaService.update(id, pessoa);
            return ResponseEntity.ok(newPessoa);
        }
        return ResponseEntity.badRequest().build();

    }

    @Operation(summary = "Este endpoint deleta o cadastro de uma pessoa")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!pessoaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
