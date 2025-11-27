package com.sistema.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sistema.loja.repository.LojasRepository;
// As funcionalidades de cadastro de Estados e Cidades foram removidas.
import com.sistema.loja.repository.ProdutoRepository;
import com.sistema.loja.repository.FuncionarioRepository;
import com.sistema.loja.model.Funcionario;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PrincipalController {

    @Autowired
    private LojasRepository lojasRepository;


    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/administrativo")
    public String acessarPrincipal(Model model){
        model.addAttribute("lojas", lojasRepository.findAll());
        // Removido carregamento de cidades - não mais utilizado
        model.addAttribute("produtos", produtoRepository.findAll());

        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        // Agrupa funcionários por loja.id para uso direto no template
        Map<Long, List<Funcionario>> funcionariosPorLoja = funcionarios.stream()
                .filter(f -> f.getLoja() != null)
                .collect(Collectors.groupingBy(f -> f.getLoja().getId()));

        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("funcionariosPorLoja", funcionariosPorLoja);
        return "administrativo/home";
    }
}
