package br.com.km.AppPessoas.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.List;


@Entity
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(nullable = false)
    private Integer tipoDeContato;

@Column(nullable = false)
    private String contato;

    @ManyToOne()
    @JoinColumn(name = "pessoa_Id", referencedColumnName = "id")
    private Pessoa pessoa;

    public Contato() {}
    public Contato(Long id, Integer tipoDeContato, String contato, Pessoa pessoa) {
        this.id = id;
        this.tipoDeContato = tipoDeContato;
        this.contato = contato;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipoDeContato() {
        return tipoDeContato;
    }

    public void setTipoDeContato(Integer tipoDeContato) {
        this.tipoDeContato = tipoDeContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id);
    }

}
