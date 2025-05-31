package Classes;

import java.util.Calendar;
import Enuns.Sexo;

public class Professor extends Usuario{
	private String CPF;
	private Calendar DataNascimento;
	private Sexo Sexo;
	private Endereco Endereco;
	private String Especialidade;
	private Disciplina Disciplina;
	
		public Professor(String CPF, Calendar DataNascimento, Sexo Sexo, Endereco Endereco, String Especialidade, Disciplina Disciplina) {
			this.CPF = CPF;
			this.DataNascimento = DataNascimento;
			this.Sexo = Sexo;
			this.Endereco = Endereco;
			this.Especialidade = Especialidade;
			this.Disciplina = Disciplina;
			
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

	public String getEspecialidade() {
		return Especialidade;
	}

	public void setEspecialidade(String especialidade) {
		Especialidade = especialidade;
	}

	public Disciplina getDisciplina() {
		return Disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		Disciplina = disciplina;
	}

}
