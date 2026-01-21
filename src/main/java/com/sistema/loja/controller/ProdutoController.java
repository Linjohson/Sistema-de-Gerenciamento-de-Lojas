package com.sistema.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.loja.model.Loja;
import com.sistema.loja.model.Produto;
import com.sistema.loja.repository.LojasRepository;
import com.sistema.loja.repository.ProdutoRepository;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LojasRepository lojasRepository;

    @GetMapping("/cadastroProduto")
    public ModelAndView cadastrar(Produto produto){
        ModelAndView mv = new ModelAndView("administrativo/produtos/cadastroProduto");
        mv.addObject("produto", produto);
        mv.addObject("lojas", lojasRepository.findAll());
        return mv;
    }

    @PostMapping("/cadastroProduto")
    public String salvar(Produto produto, @RequestParam(value = "lojaId", required = false) Long lojaId, RedirectAttributes ra){
        if(produto.getNome() == null || produto.getNome().isBlank()){
            ra.addFlashAttribute("erro", "O nome do produto é obrigatório.");
            return "redirect:/cadastroProduto";
        }
        if(produto.getValor() == null || produto.getValor().doubleValue() < 0){
            ra.addFlashAttribute("erro", "O valor deve ser um valor não negativo.");
            ra.addFlashAttribute("produto", produto);
            return "redirect:/cadastroProduto";
        }
        if(lojaId != null){
            Loja loja = lojasRepository.findById(lojaId).orElse(null);
            produto.setLoja(loja);
        } else {
            produto.setLoja(null);
        }
        produtoRepository.save(produto);
        boolean isEdicao = produto.getId() != null;
        String mensagem = isEdicao ? "Produto atualizado com sucesso!" : "Produto cadastrado com sucesso!";
        ra.addFlashAttribute("mensagem", mensagem);
        return "redirect:/home";
    }

    @GetMapping("/editarProduto")
    public ModelAndView editar(@RequestParam("id") Long id){
        ModelAndView mv = new ModelAndView("administrativo/produtos/cadastroProduto");
        Produto p = produtoRepository.findById(id).orElse(new Produto());
        mv.addObject("produto", p);
        mv.addObject("lojas", lojasRepository.findAll());
        return mv;
    }

    @GetMapping("/detalhesProduto")
    public ModelAndView detalhes(@RequestParam("id") Long id){
        ModelAndView mv = new ModelAndView("administrativo/produtos/detalhesProduto");
        Produto p = produtoRepository.findById(id).orElse(new Produto());
        mv.addObject("produto", p);
        return mv;
    }

    @GetMapping("/excluirProduto")
    public String excluir(@RequestParam("id") Long id, RedirectAttributes ra){
        produtoRepository.deleteById(id);
        ra.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
        return "redirect:/home";
    }
}
