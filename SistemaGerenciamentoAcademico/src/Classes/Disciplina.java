package Classes;

public class Disciplina {
	private int Id;
	private String Nome;
	private int CargaHoraria;
	private Curso Curso;
	private Professor Professor;
	
		public Disciplina(int Id, String Nome, int CargaHoraria, Curso Curso, Professor Professor) {
			this.Id = Id;
			this.Nome = Nome;
			this.CargaHoraria = CargaHoraria;
			this.Curso = Curso;
			this.Professor = Professor;
			
		}
	
	public int getId() {
		return Id;
	}
		
	public void setId(int Id) {
		this.Id = Id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getCargaHoraria() {
		return CargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		CargaHoraria = cargaHoraria;
	}

	public Curso getCurso() {
		return Curso;
	}

	public void setCurso(Curso curso) {
		Curso = curso;
	}

	public Professor getProfessor() {
		return Professor;
	}

	public void setProfessor(Professor professor) {
		Professor = professor;
	}
	
		public String toString() {
			String str = "";
			str += "getId: " + this.getId() + "\n";
			str += "getNome: " + this.getNome() + "\n";
			str += "getCargaHoraria: " + this.getCargaHoraria() + "\n";
			str += this.getCurso().toString() + "\n";
			str += this.getProfessor().toString() + "\n";
			return str;
		}
		
}
