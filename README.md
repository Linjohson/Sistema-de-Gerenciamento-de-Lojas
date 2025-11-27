# Sistema de Gerenciamento de Lojas - Linjohson | Desenvolvedor Java Full Stack

<div align="center">

  ![Badge](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
  ![Badge](https://img.shields.io/badge/Spring%20Boot-Backend-brightgreen?style=for-the-badge&logo=springboot)
  ![Badge](https://img.shields.io/badge/Thymeleaf-Templates-blue?style=for-the-badge&logo=thymeleaf)
  ![Badge](https://img.shields.io/badge/H2-Database-lightgrey?style=for-the-badge&logo=h2database)
  ![Badge](https://img.shields.io/badge/Status-Ativo-success?style=for-the-badge)

  **Aplicação web para gerenciar lojas, funcionários e produtos com interface administrativa.**

</div>

---

## 🎯 Sobre o Projeto

Este projeto é um sistema administrativo simples para cadastro e gerenciamento de:
- Lojas (dados de endereço, contato e identificação)
- Funcionários (vínculo com lojas, cargos e data de admissão)
- Produtos (pertencentes a lojas, com preço e informações básicas)

A aplicação foi construída com **Spring Boot** (backend) e **Thymeleaf** para as views, visando um sistema leve, didático e fácil de adaptar.

---

## 🛠️ Stack Técnico

### Backend
- Linguagem: **Java 17+**
- Framework: **Spring Boot 3.x**
- Persistência: **Spring Data JPA (Hibernate)**
- Banco de Dados em memória: **H2** (padrão de desenvolvimento)
- Validação: **Spring Validation**
- Build: **Maven (mvnw incluído)**

### Frontend
- Templates: **Thymeleaf**
- Estilos: HTML5 + CSS (arquivos em `src/main/resources/static`)
- JavaScript: interações básicas em `src/main/resources/static/js`

### Ferramentas
- Versionamento: Git
- IDE recomendada: IntelliJ IDEA / VS Code

---

## 📂 Estrutura do Projeto (resumida)

```
loja/
├── src/
│   ├── main/
│   │   ├── java/com/sistema/loja/        # Código fonte
│   │   │   ├── controller/               # Controllers MVC
│   │   │   ├── model/                    # Entidades JPA
│   │   │   └── repository/               # Repositórios Spring Data
│   │   └── resources/
│   │       ├── templates/administrativo/ # Views Thymeleaf
│   │       └── static/                   # CSS, JS, imagens
├── mvnw
├── pom.xml
└── README.md
```

---

## 🚀 Como Rodar Localmente

Pré-requisitos:
- Java 17+
- Maven (ou use o wrapper `mvnw` incluído)

Passos rápidos (PowerShell):

```powershell
cd C:\Workspace\loja
# Build e empacotar (skip tests para desenvolvimento)
.\mvnw -DskipTests package
# Rodar a aplicação
.\mvnw spring-boot:run
```

Acesse a aplicação em: `http://localhost:8080/administrativo`

> Observação: a aplicação vem configurada para usar H2 em desenvolvimento. Para persistência em produção, configure um banco (Postgres, MySQL) em `application.properties`.

---

## 🔍 Endpoints Principais

- `GET /administrativo` — Página principal (dashboard)
- `GET /cadastroLoja` — Formulário de cadastro/edição de Loja
- `GET /detalhesLoja?id={id}` — Página de detalhes da loja
- `GET /cadastroFuncionario` — Formulário de cadastro/edição de Funcionário
- `GET /detalhesFuncionario?id={id}` — Página de detalhes do funcionário
- `GET /cadastroProduto` — Formulário de cadastro/edição de Produto
- `GET /editarProduto?id={id}` — Editar produto
- `GET /excluirProduto?id={id}` — Excluir produto

(Alguns endpoints de Estados/Cidades foram descontinuados).

---

## ✅ Funcionalidades

- Cadastro, edição e exclusão de Lojas, Funcionários e Produtos
- Páginas de detalhe com informações completas e ações de editar/excluir
- Validação básica de formulários
- Layout simples e responsivo com Thymeleaf

---

## 🧪 Testes e Build

Para rodar os testes e gerar o pacote:

```powershell
.\mvnw package
```

Para rodar testes unitários:

```powershell
.\mvnw test
```

---

## ♻️ Considerações e Próximas Melhorias

- Mover H2 para profile `dev` e configurar Postgres/MySQL para `prod`.
- Adicionar autenticação/autorização (Spring Security).
- Melhorar a interface com Bootstrap ou frontend em React/Vue.
- Adicionar paginação e filtros nas listas (produtos, funcionários, lojas).
- Adicionar testes de integração e CI (GitHub Actions).

---

## 📬 Contato

- Desenvolvedor: **Linjohson**
- Email: `linjohson1@email.com`
- GitHub: https://github.com/Linjohson

---

## 📄 Licença

Projeto licenciado sob **MIT** — veja `LICENSE` se presente.

---

<div align="center">
  **Desenvolvido com ❤️ por Linjohson**  
  ⭐ Se este projeto foi útil, considere dar uma estrela no GitHub!
</div>
