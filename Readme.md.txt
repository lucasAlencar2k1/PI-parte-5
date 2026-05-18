# MyStockSystem(mss) - Sistema de Controle de Estoque

## Status do projeto - Em desenvolvimento

## Tecnologias Utilizadas

* **Linguagem:** Java
* **Interface Gráfica:** Java Swing (componentes visuais e formulários)
* **Mapeamento Objeto-Relacional (ORM):** JPA / Hibernate
* **Gerenciamento de Dependências:** Maven
* **Banco de Dados:** Compatível com bancos relacionais (MySQL / PostgreSQL via Persistence Unit `MyStockSystem-PU`)
---

## Desenvolvedores

* Lucas - lucasAlencar2k1

---

## Objetivo do software

Este é um sistema desktop desenvolvido em Java para o controle e gerenciamento de estoque, usuários e fornecedores. O projeto utiliza interface gráfica com **Java Swing** e persistência de dados utilizando a especificação **JPA (Jakarta Persistence)** com o framework **Hibernate**.

---

## Funcionalidades Implementadas

### 1. Autenticação e Controle de Acesso
* Tela de Login integrada com validação via banco de dados (`UsuarioDAO`).
* Verificação de credenciais antes de liberar o acesso às telas operacionais do sistema.

### 2. Cadastro e Gerenciamento de Usuários
* Inclusão de novos operadores no sistema com definição de cargos (`role`).
* Listagem dinâmica em tempo real utilizando componentes de tabela.

### 3. Controle de Estoque (Produtos)
* Cadastro completo de produtos com Nome, Descrição, Quantidade, Preço (`BigDecimal`) e Data de Cadastro (`LocalDate`).
* Associação direta com fornecedores cadastrados através de componentes de seleção combinada (`JComboBox`).
* Listagem utilizando consultas JPQL

### 4. Gerenciamento de Fornecedores
* Mapeamento de dados corporativos (Nome da Empresa, CNPJ, Telefone, E-mail e Endereço).
* Vinculação com o produtos.

---