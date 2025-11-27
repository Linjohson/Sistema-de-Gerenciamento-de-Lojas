package com.sistema.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.loja.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
