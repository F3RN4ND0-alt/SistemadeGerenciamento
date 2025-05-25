package Classes;

import java.util.List;

import Enums.ModalidadeCurso;
import Enums.NivelCurso;

public class Curso {
    private int Id;
    private String Nome;
    private String DescricaoCurso;
    private NivelCurso Nivel;
    private ModalidadeCurso Modalidade;
    private List<Disciplina> Disciplinas;

    public Curso(int Id, String Nome, String DescricaoCurso, NivelCurso Nivel, ModalidadeCurso Modalidade, List<Disciplina> Disciplinas) {
        this.Id = Id;
        this.Nome = Nome;
        this.DescricaoCurso = DescricaoCurso;
        this.Nivel = Nivel;
        this.Modalidade = Modalidade;
        this.Disciplinas = Disciplinas;
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

    public String getDescricaoCurso() {
        return DescricaoCurso;
    }

    public void setDescricaoCurso(String descricaoCurso) {
        DescricaoCurso = descricaoCurso;
    }

    public NivelCurso getNivel() {
        return Nivel;
    }

    public void setNivel(NivelCurso nivel) {
        Nivel = nivel;
    }

    public ModalidadeCurso getModalidade() {
        return Modalidade;
    }

    public void setModalidade(ModalidadeCurso modalidade) {
        Modalidade = modalidade;
    }

    public List<Disciplina> getDisciplinas() {
        return Disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        Disciplinas = disciplinas;
    }
    
    public void adicionarDisciplina(Disciplina disciplina) {
        this.Disciplinas.add(disciplina);
    }
    
    public boolean removerDisciplina(Disciplina disciplina) {
        return this.Disciplinas.remove(disciplina);
    }
    
    	public String toString() {
    			String str = "";
    			str += "Id:" + this.getId() + "\r\n";
    			str += "Nome:" + this.getNome() + "\r\n";
    			str += "DescricaoCurso:" + this.getDescricaoCurso() + "\r\n";
    			str += "Nivel:" + this.getNivel() + "\r\n";
    			str += "Modalidade:" + this.getModalidade() + "\r\n";
    			str += "Disciplinas:" + "\r\n";
    	if (this.getDisciplinas() != null) {
    		for (Disciplina d : this.getDisciplinas()) {
    			str += "  - " + d.toString() + "\r\n";
    			}
    				} else {
    					str += "  Nenhuma disciplina cadastrada.\r\n";
    		}
		return str;
    	}
}