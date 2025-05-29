package Classes;

import Classes.Professor;
import Classes.Telefone;
import Classes.Endereco;
import Enums.Permissao;
import Enums.Sexo;
import Enums.Especialidade;
import Dao.ProfessorDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        // Configurações de conexão - ajuste para seu banco
        String url = "jdbc:mysql://localhost:3306/sistemagerenciamento";
        String user = "root";
        String password = "123456";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            ProfessorDAO professorDAO = new ProfessorDAO(connection);

            // Criar um objeto Professor para teste
            Professor professor = new Professor();
            professor.setNomeUsuario("João Silva");
            professor.setSenha("123456");
            professor.setCpf("123.456.789-00");
            professor.setSexo(Sexo.MASCULINO);
            Calendar dataNasc = Calendar.getInstance();
            dataNasc.set(1980, Calendar.JANUARY, 15);
            professor.setDataNascimento(dataNasc);
            professor.setEmail("joao.silva@email.com");
            professor.setTelefone(new Telefone("11", "99999-8888"));
            professor.setEndereco(new Endereco("Rua A", "12345-678", "100", "Apto 10", "Centro", "São Paulo", "SP", "Brasil"));
            professor.setAtivo(true);
            professor.setPermissao(Permissao.PROFESSOR);
            professor.setEspecialidade(Especialidade.MATEMATICA);

            // Salvar no banco
            professorDAO.save(professor);
            System.out.println("Professor salvo com ID: " + professor.getIdUsuario());

            // Buscar pelo ID
            Professor profBuscado = professorDAO.findById(professor.getIdUsuario());
            if (profBuscado != null) {
                System.out.println("Professor encontrado: " + profBuscado.getNomeUsuario());
            } else {
                System.out.println("Professor não encontrado");
            }

            // Atualizar o professor
            profBuscado.setEspecialidade(Especialidade.FISICA);
            professorDAO.update(profBuscado);
            System.out.println("Professor atualizado.");

            // Deletar o professor
//            professorDAO.delete(profBuscado.getIdUsuario());
//            System.out.println("Professor deletado.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}