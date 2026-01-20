package com.sistema.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.loja.model.Funcionario;
import com.sistema.loja.model.Loja;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByCpf(String cpf);
    List<Funcionario> findByLoja(Loja loja);
}
