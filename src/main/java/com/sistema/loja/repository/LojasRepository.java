package com.sistema.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.loja.model.Loja;

public interface LojasRepository extends JpaRepository<Loja, Long> {
    Loja findByCnpj(String cnpj);
    Loja findByEndereco(String endereco);
    Loja findByEnderecoAndNumero(String endereco, String numero);
}
