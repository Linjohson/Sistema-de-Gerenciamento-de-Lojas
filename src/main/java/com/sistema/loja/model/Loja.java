package com.sistema.loja.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "lojas", uniqueConstraints = {
    @UniqueConstraint(columnNames = "cnpj"),
    @UniqueConstraint(columnNames = {"endereco", "numero"})
})
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da loja é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "CNPJ é obrigatório")
    @Column(nullable = false, unique = true, length = 18)
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ deve estar no formato: XX.XXX.XXX/XXXX-XX")
    private String cnpj;


    @NotBlank(message = "Endereço (rua) é obrigatório")
    @Column(nullable = false, length = 200)
    private String endereco;

    @NotBlank(message = "Número é obrigatório")
    @Column(nullable = false, length = 10)
    private String numero;

    @NotBlank(message = "Bairro é obrigatório")
    @Column(nullable = false, length = 100)
    private String bairro;

    @NotBlank(message = "CEP é obrigatório")
    @Column(nullable = false, length = 10)
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato: XXXXX-XXX")
    private String cep;

    @NotBlank(message = "Telefone é obrigatório")
    @Column(nullable = false, length = 15)
    @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Telefone deve estar no formato: (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
    private String telefone;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String cidade;

    @Column(length = 2)
    private String estado;

    public Loja() {
    }

    public Loja(String nome, String cnpj, String endereco, String cep, String telefone) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.cep = cep;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
