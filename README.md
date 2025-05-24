# 🧮 Projeto IMC - Dockerizado

## 📚 Contexto Acadêmico

Este projeto foi desenvolvido como atividade prática da disciplina **DevOps Tools**, ministrada pelo professor **Adilson**, no curso de **Análise e Desenvolvimento de Sistemas** da **Uninassau - Embarque Digital**, 4º período (turno noite).

## 💡 Descrição do Projeto

A aplicação tem por objetivo calcular o **Índice de Massa Corporal (IMC)** a partir de dados fornecidos pelo usuário, persistindo essas informações em um banco de dados relacional. O foco da atividade está na **containerização da aplicação utilizando Docker**, com ênfase em manter o ambiente o mais leve e portátil possível, garantindo compatibilidade e reprodutibilidade entre diferentes máquinas e sistemas.

## 🔧 Tecnologias e Ferramentas Utilizadas

- **Java** – Lógica da aplicação e implementação do cálculo de IMC  
- **JDBC (Java Database Connectivity)** – Conexão com o banco de dados  
- **MySQL Workbench** – Interface para gerenciamento e consulta do banco de dados  
- **Docker** – Containerização da aplicação  
- **Linux Mint (ISO)** – Ambiente operacional configurado via **Oracle VirtualBox**  
- **Oracle VirtualBox** – Virtualização do sistema operacional utilizado para testes

## 📦 Objetivo DevOps

O projeto busca aplicar conceitos essenciais da cultura DevOps, como:

- **Padronização de ambientes** via contêineres
- **Portabilidade da aplicação** entre sistemas diferentes
- **Redução de problemas de dependência**
- **Execução isolada da aplicação** para facilitar o desenvolvimento, testes e futuras entregas
- **Gerenciamento e versionamento de código** com o Github

## 📁 Estrutura Básica do Projeto

/projeto-imc
│
├── Dockerfile
├── docker-compose.yml
├── src/
│ └── Main.java
│ └── Conexao.java
│
├── scripts/
│ └── init.sql
│
└── README.md


## 🚀 Instruções de Execução (Docker)

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/projeto-imc.git
   cd projeto-imc

2. Execute os Contêineres
docker-compose up --build

A aplicação estará disponível para testes e o banco de dados acessível conforme configuração do docker-compose.

📄 Licença
Projeto acadêmico. Uso educacional.

