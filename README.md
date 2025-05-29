
# 🎓 Sistema de Gerenciamento Acadêmico

## 📝 Descrição

O **Sistema de Gerenciamento Acadêmico** é uma aplicação desenvolvida em Java que permite gerenciar informações acadêmicas, incluindo usuários (professores, alunos, etc.), telefones, endereços, matrículas, cursos, disciplinas. O sistema utiliza banco de dados MySQL para persistência dos dados e oferece funcionalidades de cadastro, atualização, consulta e exclusão dos registros.

---

## 🚀 Funcionalidades

- 🧑‍💼 Cadastro e gerenciamento de usuários, com autenticação básica.
- 👩‍🏫 Cadastro e gerenciamento de professores e alunos.
- 📚 Gerenciamento de matrículas, cursos e disciplinas.
- 📞 Armazenamento e vinculação de telefones e endereços a usuários.
- ✏️ Atualização e exclusão de registros.
- 🔍 Consulta detalhada dos dados acadêmicos.
- 🔗 Uso de relacionamentos entre tabelas para organizar dados comuns e específicos.

---

## 🛠️ Tecnologias Utilizadas

- ☕ Java SE (JDK 8+)
- 🔌 JDBC para acesso ao banco de dados
- 🐬 MySQL como sistema gerenciador de banco de dados
- 🖥️ Eclipse IDE para desenvolvimento
- 🐙 Git para controle de versão

---

## 🏗️ Estrutura do Projeto

### 📂 Classes (Model)

- **Usuario**: Classe base para usuários do sistema.
- **Professor**: Extensão de `Usuario` com informações acadêmicas.
- **Aluno**: Extensão de `Usuario` com informações acadêmicas.
- **Telefone**: Representa os dados de telefone.
- **Endereco**: Representa os dados de endereço.
- **Matricula**: Representa as matrículas dos alunos nos cursos.
- **Curso**: Representa os cursos disponíveis.
- **Disciplina**: Representa as disciplinas de cada curso.

### 🔢 Enums

- **Especialidade**: Especialidades dos professores.
- **ModalidadeCurso**: Modalidades dos cursos.
- **NivelCurso**: Níveis dos cursos.
- **Permissao**: Permissões dos usuários no sistema.
- **Sexo**: Sexo do usuário.

### 🖼️ Views (Interfaces Gráficas)

- **CadastroUsuario**: Tela para cadastro de usuários.
- **TelaAluno**: Interface para gerenciamento de alunos.
- **TelaCursos**: Interface para gerenciamento dos cursos.
- **TelaLoginUsuario**: Tela de login do usuário.
- **TelaPrincipal**: Tela principal do sistema.
- **TelaProfessor**: Interface para gerenciamento de professores.
- **TelaUsuario**: Interface geral para gerenciamento de usuários.

---

## 💾 Banco de Dados

O banco de dados possui as seguintes tabelas principais:

- **usuario**: Armazena os dados genéricos dos usuários do sistema.
- **professor**: Contém dados específicos dos professores.
- **aluno**: Contém dados específicos dos alunos.
- **matricula**: Registra as matrículas dos alunos nos cursos.
- **curso**: Armazena informações dos cursos.
- **disciplina**: Armazena as disciplinas dos cursos.
- **telefone**: Armazena os números de telefone.
- **endereco**: Armazena os endereços vinculados aos usuários.

---

## 🏃‍♂️ Instruções para Rodar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```
2. Importe o projeto no Eclipse.

3. Configure a conexão com o banco de dados MySQL no arquivo de configuração de conexão (ou diretamente na classe DAO).

4. Execute a classe principal Main para testar as funcionalidades.

5. Use o terminal ou interface gráfica do MySQL para criar as tabelas e os relacionamentos necessários (scripts SQL disponíveis na pasta scripts).

---

## 🤝 Colaboradores

Agradeço ao seguinte colaborador deste projeto:

| ![Erhard William](https://img.quizur.com/f/img633c85c5da28c7.71464372.jpg ) |
|:--:|
| [**Erhard William**](https://github.com/Erhard-Willian)  <br>Responsável pelo design e implementação das telas. |


  
---

## 😄 Contribuições

Contribuições são bem-vindas! Para contribuir:

Faça um fork deste repositório.

Crie uma branch para sua feature:

```bash
git checkout -b minha-feature
```
Faça commit das suas alterações:

```bash
git commit -m 'Minha feature'
```
Envie para a branch remota:
```bash
git push origin minha-feature
```
Abra um Pull Request no GitHub para revisão.

---

## 📝 Licença

Esse projeto está sob licença. Veja o arquivo [LICENÇA](LICENSE.md) para mais detalhes.
