package Classes;
import Classes.*;
import Dao.*;
import Enums.Especialidade;
import Enums.ModalidadeCurso;
import Enums.NivelCurso;
import Enums.Permissao;
import Enums.Sexo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


	public class Main {
	    public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/sistemagerenciamento";
	        String user = "root";
	        String password = "123456";

	        try (Connection connection = DriverManager.getConnection(url, user, password)) {

	        	CursoDAO cursoDAO = new CursoDAO(connection);
	            DisciplinaDAO disciplinaDAO = new DisciplinaDAO(connection);
	            AlunoDAO alunoDAO = new AlunoDAO(connection);
	            MatriculaDAO matriculaDAO = new MatriculaDAO(connection);
	            ProfessorDAO professorDAO = new ProfessorDAO(connection);
	            
	            // Criando curso
	            Curso curso = new Curso();
	            curso.setNome("Ciência da Computação");
	            curso.setDescricaoCurso("Curso completo de CC");
	            curso.setNivel(NivelCurso.AVANCADO);
	            curso.setModalidade(ModalidadeCurso.PRESENCIAL);
	            curso.setDisciplinas(new ArrayList<>());
	            cursoDAO.save(curso); 
	            
	            System.out.println("Curso salvo com ID: " + curso.getIdCurso());

	            Professor professor = new Professor();
	            professor.setNomeUsuario("Carlos Souza");
	            professor.setSenha("456");
	            professor.setCpf("98765432100");
	            professor.setSexo(Sexo.MASCULINO);
	            professor.setDataNascimento(new GregorianCalendar(1980, Calendar.MARCH, 10));
	            professor.setEmail("carlos@email.com");
	            professor.setTelefone(new Telefone("(11)", "98888-7777"));
	            professor.setEndereco(new Endereco(0, "Rua Professor", "99999-000", "100", "", "Bairro", "Cidade", "SP", "Brasil"));
	            professor.setAtivo(true);
	            professor.setPermissao(Permissao.PROFESSOR);
	            professor.setEspecialidade(Especialidade.INFORMATICA);
	            professorDAO.save(professor);
	            
	            // Criando disciplina vinculada ao curso
	            Disciplina disciplina = new Disciplina();
	            disciplina.setNome("Algoritmos");
	            disciplina.setCargaHoraria(60);
	            disciplina.setCurso(curso);
	            disciplina.setProfessor(professor);
	            disciplinaDAO.save(disciplina);
	            curso.adicionarDisciplina(disciplina);
	            System.out.println("Disciplina salva com ID: " + disciplina.getIdDisciplina());

	            
	            Aluno aluno = new Aluno();
	            aluno.setNomeUsuario("João Silva");
	            aluno.setSenha("123");
	            aluno.setCpf("12345678900");
	            aluno.setSexo(Sexo.MASCULINO);
	            aluno.setDataNascimento(new GregorianCalendar(2000, Calendar.JANUARY, 1));
	            aluno.setEmail("joao@email.com");
	            aluno.setTelefone(new Telefone("(11)", "91234-5678"));
	            aluno.setEndereco(new Endereco(0, "Rua A", "12345-678", "10", "", "Centro", "São Paulo", "SP", "Brasil"));
	            aluno.setAtivo(true);
	            aluno.setPermissao(Permissao.ALUNO);
	            alunoDAO.save(aluno);
	            
	            Telefone telefone = new Telefone();
		        telefone.setDdd("(11)");
		        telefone.setTelefone(" 99999-8888");
	
		        Endereco endereco = new Endereco();
		        endereco.setEndereco("Rua Exemplo");
		        endereco.setCep("12345-678");
		        endereco.setNumero("123");
		        endereco.setComplemento("Ap 101");
		        endereco.setBairro("Centro");
		        endereco.setCidade("São Paulo");
		        endereco.setEstado("SP");
		        endereco.setPais("Brasil");
		       

		        Matricula matricula = new Matricula();
	            matricula.setAluno(aluno);
	            matricula.setCurso(curso);
	            matricula.setDisciplina(disciplina);
	            matricula.setDataMatricula(Calendar.getInstance());

	            matriculaDAO.save(matricula);
	            aluno.adicionarMatricula(matricula);

	            System.out.println("Matrícula registrada: " + matricula);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}



//	public static void main(String[] args) {
//	    String url = "jdbc:mysql://localhost:3306/sistemagerenciamento";
//	    String user = "root";
//	    String password = "123456";
//
//	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
//
//	        ProfessorDAO professorDAO = new ProfessorDAO(connection);
//	        CursoDAO cursoDAO = new CursoDAO(connection);
//	        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(connection);
//
//	        Telefone telefone = new Telefone();
//	        telefone.setDdd("(11)");
//	        telefone.setTelefone(" 99999-8888");
//
//	        Endereco endereco = new Endereco();
//	        endereco.setEndereco("Rua Exemplo");
//	        endereco.setCep("12345-678");
//	        endereco.setNumero("123");
//	        endereco.setComplemento("Ap 101");
//	        endereco.setBairro("Centro");
//	        endereco.setCidade("São Paulo");
//	        endereco.setEstado("SP");
//	        endereco.setPais("Brasil");
//
//	        Curso curso = cursoDAO.findById(1);
//	        Disciplina disciplina1 = disciplinaDAO.findById(1); // já existente
//	        Disciplina disciplina2 = disciplinaDAO.findById(2); // nova disciplina a ser adicionada
//
//	        Professor professor = new Professor();
//	        professor.setNomeUsuario("João Silva");
//	        professor.setSenha("12345");
//	        professor.setCpf("12345678900");
//	        professor.setSexo(Sexo.MASCULINO);
//	        professor.setDataNascimento(new GregorianCalendar(1985, Calendar.MARCH, 20));
//	        professor.setEmail("joao.silva@example.com");
//	        professor.setTelefone(telefone);
//	        professor.setEndereco(endereco);
//	        professor.setAtivo(true);
//	        professor.setPermissao(Permissao.PROFESSOR);
//	        professor.setEspecialidade(Especialidade.MATEMATICA);
//	        professor.setDisciplinas(List.of(disciplina1));
//
//	        professorDAO.save(professor);
//	        System.out.println("Professor salvo com ID: " + professor.getIdUsuario());
//
//	        // Buscar e adicionar nova disciplina
//	        Professor buscado = professorDAO.findById(professor.getIdUsuario());
//	        System.out.println("Professor buscado: " + buscado.getNomeUsuario());
//
//	        List<Disciplina> novasDisciplinas = new ArrayList<>(buscado.getDisciplinas());
//	        novasDisciplinas.add(disciplina2); // Adiciona a nova
//	        buscado.setDisciplinas(novasDisciplinas);
//	        professorDAO.update(buscado); // Atualiza no banco
//	        System.out.println("Nova disciplina adicionada ao professor.");
//
//	        // Atualização de nome como antes (opcional)
//	        buscado.setNomeUsuario("João Atualizado");
//	        professorDAO.update(buscado);
//	        System.out.println("Professor atualizado.");
//
//	        professorDAO.delete(buscado.getIdUsuario());
//	        System.out.println("Professor deletado.");
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}

