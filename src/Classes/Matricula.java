package Classes;

import java.util.Calendar;

public class Matricula {
	private int Id;
	private Calendar DataMatricula;
	private Aluno Aluno;
	private Curso Curso;
	
		public Matricula(int Id, Calendar DataMatricula) {
			this.Id = Id;
			this.DataMatricula = DataMatricula;
			
		}
		
	public int getId() {
		return Id;
	}
	
	public void setId(int Id) {
		this.Id = Id;
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
	    str += "Id:" + this.getId() + "/r/n" ;
	    str += "DataMatricula:" + this.getDataMatricula() + "/r/n" ;
	    str += this.getAluno().toString() + "/r/n" ;
	    str += this.getCurso().toString() + "/r/n";
	    return str;
	    
	}

}
