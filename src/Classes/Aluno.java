package Classes;

import java.util.Calendar;

import Enuns.Sexo;

public class Aluno {
	private String CPF;
	private Calendar DataNascimento;
	private Sexo Sexo;
	private Endereco Endereco;
	private Curso Cursos;

	
		public Aluno(String CPF, Calendar DataNascimento, Sexo Sexo, Endereco Endereco, Curso Cursos) {
			this.CPF = CPF;
			this.DataNascimento = DataNascimento;
			this.Sexo = Sexo;
			this.Endereco = Endereco;
			this.Cursos = Cursos;
			
		}
		
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public Calendar getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		DataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return Sexo;
	}

	public void setSexo(Sexo sexo) {
		Sexo = sexo;
	}

	public Endereco getEndereco() {
		return Endereco;
	}

	public void setEndereco(Endereco endereco) {
		Endereco = endereco;
	}

	public Curso getCursos() {
		return Cursos;
	}

	public void setCursos(Curso cursos) {
		Cursos = cursos;
	}
	
	public void CalcularIdade() {
		
	}
	
	public void VerificarEmail() {
		
	}
	
	public void MatricularEmCurso() {
		
	}

}