package Classes;

public class Disciplina {
    private int IdDisciplina;
    private String nome;
    private int cargaHoraria;
    private Curso curso;
    private Professor professor;
    
    public Disciplina() {
        
    }

    public Disciplina(int id, String nome, int cargaHoraria, Curso curso, Professor professor) {
        this.IdDisciplina = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.curso = curso;
        this.professor = professor;
       
    }

    public int getIdDisciplina() {
        return IdDisciplina;
    }

    public void setIdDisciplina(int id) {
        this.IdDisciplina = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
   

    @Override
    public String toString() {
        String str = "";
        str += "Id: " + this.getIdDisciplina() + "\n";
        str += "Nome: " + this.getNome() + "\n";
        str += "Carga Horária: " + this.getCargaHoraria() + "\n";
        if (this.getCurso() != null) {
            str += "Curso: " + this.getCurso().getNome() + "\n";
        }
        if (this.getProfessor() != null) {
            str += "Professor: " + this.getProfessor().getNomeUsuario() + "\n";
        }
		return str;
     
    }
}

