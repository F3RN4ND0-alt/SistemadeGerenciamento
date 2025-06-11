# 🎓 Sistema de Gerenciamento Acadêmico

## 📘 Descrição

O **Sistema de Gerenciamento Acadêmico** é uma aplicação desktop desenvolvida em **Java**, que permite gerenciar dados acadêmicos como usuários (professores, alunos), cursos, disciplinas, endereços, telefones e matrículas. Utiliza **MySQL** para persistência e apresenta uma **interface gráfica com Java Swing** para interação do usuário.

---

## 🚀 Funcionalidades

- ✅ Cadastro e login de usuários (com autenticação).
- ✅ Gerenciamento de professores e alunos.
- ✅ Cadastro e visualização de cursos e disciplinas.
- ✅ Matrícula de alunos em cursos.
- ✅ Armazenamento de telefones e endereços vinculados aos usuários.
- ✅ Atualização e remoção de registros.
- ✅ Navegação através de telas intuitivas via Swing.

---

## 🛠️ Tecnologias Utilizadas

- ☕ **Java SE** (JDK 8+)
- 🐬 **MySQL** (banco de dados relacional)
- 🔌 **JDBC** (conexão com o banco)
- 🖥️ **Java Swing** (interface gráfica)
- 💻 **Eclipse IDE**
- 🗂️ **Git/GitHub** (controle de versão)

---

## 🧱 Estrutura do Projeto

### 📦 Classes (Model)

- `Usuario` – Classe base.
- `Professor` – Herda de `Usuario`.
- `Aluno` – Herda de `Usuario`.
- `Telefone` – Dados de telefone.
- `Endereco` – Dados de endereço.
- `Curso` – Informações dos cursos.
- `Disciplina` – Disciplinas vinculadas aos cursos.
- `Matricula` – Matrícula de alunos em cursos.

### 📂 Enums

- `Especialidade` – Especialidades dos professores.
- `ModalidadeCurso` – Presencial, EAD etc.
- `NivelCurso` – Técnico, Superior, etc.
- `Permissao` – Acesso de usuários.
- `Sexo` – Masculino ou Feminino.

### 🧩 DAOs (Data Access Objects)

Isolam a lógica de acesso ao banco:

- `UsuarioDAO`
- `ProfessorDAO`
- `AlunoDAO`
- `TelefoneDAO`
- `EnderecoDAO`
- `CursoDAO`
- `DisciplinaDAO`
- `MatriculaDAO`

Cada DAO oferece os métodos típicos: `inserir`, `listar`, `buscarPorId`, `atualizar`, `remover`.

### 🖼️ Views (Interfaces Gráficas)

- `TelaLogin` – Tela de autenticação.
- `TelaPrincipal` – Tela inicial com navegação.
- `CadastroUsuario` – Tela de cadastro.
- `TelaProfessor` – Gerenciamento de professores.
- `TelaAluno` – Gerenciamento de alunos.
- `TelaCursos` – Cadastro e listagem de cursos e disciplinas.

---

## 🗃️ Banco de Dados

O sistema utiliza um banco **relacional** com tabelas:

- `usuario`
- `professor`
- `aluno`
- `telefone`
- `endereco`
- `curso`
- `disciplina`
- `matricula`

Relacionamentos com **chaves estrangeiras** garantem integridade entre entidades como `curso ↔ disciplina`, `aluno ↔ matricula`, `usuario ↔ telefone/endereco`.

---

## ⚙️ Como Executar o Projeto

1. 📥 Clone o repositório:
   ```bash
   git clone https://github.com/F3RN4ND0-alt/SistemadeGerenciamento.git
   ```

2. 🧩 Importe o projeto no Eclipse.

3. 🔧 Configure sua conexão com o MySQL nas classes DAO:
   - URL, usuário e senha devem estar corretos.

4. 💽 Execute os **scripts SQL** na pasta `scripts` para criar o banco.

5. ▶️ Execute a classe `Main` ou a `TelaPrincipal` para iniciar o sistema.

---

## 🤝 Colaboradores

Agradeço ao seguinte colaborador deste projeto:

| ![Erhard William](https://img.quizur.com/f/img633c85c5da28c7.71464372.jpg) |
|:--:|
| [**Erhard William**](https://github.com/Erhard-Willian)  <br>Responsável pelo design e implementação das telas. |

---

## 💡 Contribuições

Contribuições são super bem-vindas! Para contribuir:

1. Faça um fork do repositório.
2. Crie sua branch:  
   ```bash
   git checkout -b minha-feature
   ```
3. Faça commit das suas alterações:  
   ```bash
   git commit -m 'Adicionando nova feature'
   ```
4. Envie para o repositório remoto:  
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request!

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Consulte o arquivo `LICENSE` para mais detalhes.
