package com.sistema.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.loja.model.Loja;
import com.sistema.loja.repository.LojasRepository;
import com.sistema.loja.repository.ProdutoRepository;
import com.sistema.loja.repository.FuncionarioRepository;

import jakarta.validation.Valid;

@Controller
public class LojaController {

    @Autowired
    private LojasRepository lojasRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/cadastroLoja")
    public String cadastrar(Model model) {
        if (!model.containsAttribute("loja")) {
            model.addAttribute("loja", new Loja());
        }
        return "administrativo/lojas/cadastroLoja";
    }

    @PostMapping("/cadastroLoja")
    public String salvar(@Valid Loja loja, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "administrativo/lojas/cadastroLoja";
        }

        // Verificar CNPJ único (se for novo ou se for edição com CNPJ diferente)
        if (loja.getId() == null) {
            Loja lojaByCnpj = lojasRepository.findByCnpj(loja.getCnpj());
            if (lojaByCnpj != null) {
                result.rejectValue("cnpj", "error.cnpj.duplicado", "CNPJ já cadastrado");
                return "administrativo/lojas/cadastroLoja";
            }
        } else {
            Loja lojaExistente = lojasRepository.findByCnpj(loja.getCnpj());
            if (lojaExistente != null && !lojaExistente.getId().equals(loja.getId())) {
                result.rejectValue("cnpj", "error.cnpj.duplicado", "CNPJ já cadastrado");
                return "administrativo/lojas/cadastroLoja";
            }
        }

        // Verificar Endereço (rua + número) único
        if (loja.getId() == null) {
            Loja lojaByEndereco = lojasRepository.findByEnderecoAndNumero(loja.getEndereco(), loja.getNumero());
            if (lojaByEndereco != null) {
                result.rejectValue("endereco", "error.endereco.duplicado", "Loja já cadastrada nesse endereço e número");
                return "administrativo/lojas/cadastroLoja";
            }
        } else {
            Loja lojaExistente = lojasRepository.findByEnderecoAndNumero(loja.getEndereco(), loja.getNumero());
            if (lojaExistente != null && !lojaExistente.getId().equals(loja.getId())) {
                result.rejectValue("endereco", "error.endereco.duplicado", "Loja já cadastrada nesse endereço e número");
                return "administrativo/lojas/cadastroLoja";
            }
        }

        lojasRepository.save(loja);

        if (loja.getId() != null) {
            ra.addFlashAttribute("mensagem", "Loja '" + loja.getNome() + "' atualizada com sucesso!");
        } else {
            ra.addFlashAttribute("mensagem", "Loja '" + loja.getNome() + "' cadastrada com sucesso!");
        }

        return "redirect:/administrativo";
    }

    @GetMapping("/editarLoja")
    public String editar(@RequestParam("id") Long id, Model model) {
        Loja loja = lojasRepository.findById(id).orElse(null);
        if (loja == null) {
            return "redirect:/administrativo";
        }
        model.addAttribute("loja", loja);
        return "administrativo/lojas/cadastroLoja";
    }

    @GetMapping("/excluirLoja")
    public String excluir(@RequestParam("id") Long id, RedirectAttributes ra) {
        Loja loja = lojasRepository.findById(id).orElse(null);
        if (loja != null) {
            String nomeLoja = loja.getNome();
            lojasRepository.deleteById(id);
            ra.addFlashAttribute("mensagem", "Loja '" + nomeLoja + "' deletada com sucesso!");
        }
        return "redirect:/administrativo";
    }

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/detalhesLoja")
    public String detalhes(@RequestParam("id") Long id, Model model) {
        Loja loja = lojasRepository.findById(id).orElse(null);
        if (loja == null) {
            return "redirect:/administrativo";
        }
        model.addAttribute("loja", loja);
        model.addAttribute("funcionarios", funcionarioRepository.findByLoja(loja));
        model.addAttribute("produtos", produtoRepository.findAll().stream().filter(p -> p.getLoja() != null && p.getLoja().getId().equals(loja.getId())).toList());
        return "administrativo/lojas/detalhesLoja";
    }
}
