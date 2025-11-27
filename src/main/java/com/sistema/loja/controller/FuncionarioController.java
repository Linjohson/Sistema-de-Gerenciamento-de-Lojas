package com.sistema.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.loja.model.Funcionario;
import com.sistema.loja.repository.FuncionarioRepository;
import com.sistema.loja.repository.LojasRepository;

import jakarta.validation.Valid;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private LojasRepository lojasRepository;

    @GetMapping("/cadastroFuncionario")
    public String cadastrar(Model model) {
        if (!model.containsAttribute("funcionario")) {
            model.addAttribute("funcionario", new Funcionario());
        }
        model.addAttribute("lojas", lojasRepository.findAll());
        return "administrativo/funcionarios/cadastroFuncionario";
    }

    @PostMapping("/cadastroFuncionario")
    public String salvar(@Valid Funcionario funcionario, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("lojas", lojasRepository.findAll());
            return "administrativo/funcionarios/cadastroFuncionario";
        }

        // Verificar CPF único (se for novo ou se for edição com CPF diferente)
        if (funcionario.getId() == null) {
            Funcionario funcByCpf = funcionarioRepository.findByCpf(funcionario.getCpf());
            if (funcByCpf != null) {
                result.rejectValue("cpf", "error.cpf.duplicado", "CPF já cadastrado");
                model.addAttribute("lojas", lojasRepository.findAll());
                return "administrativo/funcionarios/cadastroFuncionario";
            }
        } else {
            Funcionario funcExistente = funcionarioRepository.findByCpf(funcionario.getCpf());
            if (funcExistente != null && !funcExistente.getId().equals(funcionario.getId())) {
                result.rejectValue("cpf", "error.cpf.duplicado", "CPF já cadastrado");
                model.addAttribute("lojas", lojasRepository.findAll());
                return "administrativo/funcionarios/cadastroFuncionario";
            }
        }

        funcionarioRepository.save(funcionario);

        if (funcionario.getId() != null) {
            ra.addFlashAttribute("mensagem", "Funcionário '" + funcionario.getNome() + "' atualizado com sucesso!");
        } else {
            ra.addFlashAttribute("mensagem", "Funcionário '" + funcionario.getNome() + "' cadastrado com sucesso!");
        }

        return "redirect:/administrativo";
    }

    @GetMapping("/editarFuncionario")
    public String editar(@RequestParam("id") Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);
        if (funcionario == null) {
            return "redirect:/administrativo";
        }
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("lojas", lojasRepository.findAll());
        return "administrativo/funcionarios/cadastroFuncionario";
    }

    @GetMapping("/excluirFuncionario")
    public String excluir(@RequestParam("id") Long id, RedirectAttributes ra) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);
        if (funcionario != null) {
            String nomeFuncionario = funcionario.getNome();
            funcionarioRepository.deleteById(id);
            ra.addFlashAttribute("mensagem", "Funcionário '" + nomeFuncionario + "' deletado com sucesso!");
        }
        return "redirect:/administrativo";
    }

    @GetMapping("/detalhesFuncionario")
    public String detalhes(@RequestParam("id") Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);
        if (funcionario == null) {
            return "redirect:/administrativo";
        }
        model.addAttribute("funcionario", funcionario);
        return "administrativo/funcionarios/detalhesFuncionario";
    }
}
