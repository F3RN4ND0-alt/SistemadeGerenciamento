package Classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import Enums.Permissao;
import Enums.Sexo;

public class Aluno extends Usuario {
    private List<Curso> cursos = new ArrayList<>();

    public Aluno() {}

    public Aluno(Integer idUsuario, String nomeUsuario, String senha, String cpf, Sexo sexo,
                 Calendar dataNascimento, String email, Telefone telefone,
                 Boolean ativo, Permissao permissao) {
        super(idUsuario, nomeUsuario, senha, cpf, sexo, dataNascimento, email, telefone, ativo, permissao);
    }

    public void matricularCurso(Curso curso) {
        cursos.add(curso);
        System.out.println("Aluno matriculado no curso: " + curso.getNome());
    }

    public void cancelarMatricula(Curso curso) {
        if (cursos.remove(curso)) {
            System.out.println("Matrícula cancelada com sucesso no curso: " + curso.getNome());
        } else {
            System.out.println("O aluno não estava matriculado neste curso: " + curso.getNome());
        }
    }

  
    public void verificarEmail() {
        if (getEmail() != null && getEmail().contains("@")) {
            System.out.println("Email do aluno verificado.");
        } else {
            System.out.println("Email inválido.");
        }
    }

   
    public String toString() {
        String str = "";
        str += super.toString(); 
        str += "Cursos matriculados:\r\n";
        
        if (cursos != null && !cursos.isEmpty()) {
            for (Curso curso : cursos) {
                str += "  - " + curso.getNome() + "\r\n";
            }
        } else {
            str += "  Nenhum curso matriculado.\r\n";
        }

        return str;
    }
}
