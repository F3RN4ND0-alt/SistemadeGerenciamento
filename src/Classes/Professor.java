package Classes;

import java.util.List;
import Enums.Sexo;
import Enums.Permissao;
import java.util.Calendar;

public class Professor extends Usuario{
	private String especialidade;
	private List<Disciplina> disciplinas;
	
	public Professor() {
		
	}
	public Professor(Integer idUsuario, String nomeUsuario, String senha, String cpf, Sexo sexo,
            Calendar dataNascimento, String email, Telefone telefone,
            Boolean ativo, Permissao permissao,
            String especialidade, List<Disciplina> disciplinas) {
super(idUsuario, nomeUsuario, senha, cpf, sexo, dataNascimento, email, telefone, ativo, permissao);
this.especialidade = especialidade;
this.disciplinas = disciplinas;
}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	 public List<Disciplina> getDisciplinas() {
	        return disciplinas;
	    }

	    public void setDisciplinas(List<Disciplina> disciplinas) {
	        this.disciplinas = disciplinas;
	    }
	    
	    public void verificarEmail() {
	        if (getEmail() != null && getEmail().contains("@")) {
	            System.out.println("Email do professor verificado.");
	        } else {
	            System.out.println("Email inválido.");
	        }
	    }
	    public void listarDisciplinas() {
	    	if(disciplinas == null || disciplinas.isEmpty()) {
	    		System.out.println("O professor nao esta ensinando nenhuma disciplina");
	    	}else {
	    		System.out.println("Disciplinas ensinas:");
	    		for (Disciplina d: disciplinas ) {
	    			System.out.println("-" + d.getNome());
	    		}
	    	}
	    }
	    public String toString() {
	        String str = "";
	        str += super.toString(); 
	        str += "Especialidade: " + this.getEspecialidade() + "\r\n";
	        str += "Disciplinas:\r\n";

	        if (disciplinas != null && !disciplinas.isEmpty()) {
	            for (Disciplina d : disciplinas) {
	                str += "  - " + d.getNome() + "\r\n";
	            }
	        } else {
	            str += "  Nenhuma disciplina atribuída.\r\n";
	        }

	        return str;
	    }
}