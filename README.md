## 🌟 Sistema de Gerenciamento de Lojas

-----

### 📖 Visão Geral

Esta é uma aplicação web completa desenvolvida com **Spring Boot 3.4** para fornecer uma solução robusta e intuitiva para o **Gerenciamento Administrativo de Lojas**. O sistema permite o controle centralizado de dados essenciais, como **lojas**, **funcionários**, **produtos** e **localidades**, facilitando as operações administrativas diárias.

A plataforma oferece uma **interface responsiva** e amigável para **cadastro, edição e exclusão** de informações, complementada por páginas de detalhes para uma visualização completa e eficiente dos dados.

-----

### ✨ Características Principais

  * **🏪 Gerenciamento de Lojas e Funcionários:** Módulos dedicados para o registro e manutenção de informações de todas as lojas e seus respectivos colaboradores.
  * **📦 Cadastro e Controle de Produtos:** Controle de inventário e detalhes de todos os produtos comercializados.
  * **🎨 Interface Responsiva (Thymeleaf):** Páginas dinâmicas e acessíveis em diferentes dispositivos, construídas com **Thymeleaf**.
  * **💾 Persistência de Dados (Spring Data JPA):** Uso do Spring Data JPA para uma manipulação eficiente e simplificada do banco de dados.
  * **✅ Validação de Dados:** Garantia da integridade dos dados através do **Spring Validation**.
  * **🗄️ Banco de Dados H2:** Configurado para utilizar o banco de dados **H2** (em memória por padrão, ideal para desenvolvimento e testes).

-----

### 💻 Stack Tecnológico

| Categoria | Tecnologia | Versão |
| :--- | :--- | :--- |
| **Framework Principal** | Spring Boot | 3.4 |
| **Persistência** | Spring Data JPA (Hibernate) | |
| **Web** | Spring Web (MVC) | |
| **Frontend/Templates** | Thymeleaf, HTML5, CSS3, JavaScript | |
| **Banco de Dados** | H2 Database | |
| **Validação** | Spring Validation (Jakarta Validation) | |
| **Build** | Maven | |

-----

### 🚀 Como Executar o Projeto

#### Pré-requisitos

  * Java Development Kit (**JDK 17** ou superior)
  * Maven

#### 1\. Clone o Repositório

```bash
git clone [URL-DO-SEU-REPOSITÓRIO]
cd sistema-gerenciamento-lojas
```

#### 2\. Compile e Execute

Utilize o Maven para empacotar e executar a aplicação:

```bash
# Compila o projeto e empacota o JAR
mvn clean install

# Executa o JAR gerado
java -jar target/[nome-do-seu-arquivo].jar
```

#### 3\. Acesse a Aplicação

A aplicação será iniciada na porta padrão do Spring Boot.

  * Abra seu navegador e acesse: **`http://localhost:8080`**

-----

### 🤝 Contribuição

Contribuições são sempre bem-vindas\! Se você tiver sugestões, reportar um bug ou quiser adicionar novos recursos, por favor, abra uma *issue* ou envie um *pull request*.
