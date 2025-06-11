package Classes;

import java.util.Calendar;

public class Matricula {
	private int IdMatricula;
	private Calendar DataMatricula;
	private Aluno Aluno;
	private Curso Curso;
	
	public Matricula() {
		
	}
		public Matricula(int Id, Calendar DataMatricula) {
			this.IdMatricula = Id;
			this.DataMatricula = DataMatricula;
			
		}
		
	public int getIdMatricula() {
		return IdMatricula;
	}
	
	public void setIdMatricula(int IdMatricula) {
		this.IdMatricula = IdMatricula;
	}

	public Calendar getDataMatricula() {
		return DataMatricula;
	}

	public void setDataMatricula(Calendar dataMatricula) {
		DataMatricula = dataMatricula;
	}

	public Aluno getAluno() {
		return Aluno;
	}

	public void setAluno(Aluno aluno) {
		Aluno = aluno;
	}

	public Curso getCurso() {
		return Curso;
	}

	public void setCurso(Curso curso) {
		Curso = curso;
	}
	
	public String toString() {
		String str = "";
	    str += "IdMatricula:" + this.getIdMatricula() + "\r\n" ;
	    str += "DataMatricula:" + this.getDataMatricula() + "\r\n" ;
	    str += this.getAluno().toString() + "\r\n" ;
	    str += this.getCurso().toString() + "\r\n";
	    return str;
	    
	}
	public void setDisciplina(Disciplina disciplina) {
		
	}

}
