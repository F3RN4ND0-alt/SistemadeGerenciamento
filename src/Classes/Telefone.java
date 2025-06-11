package Classes;

public class Telefone {
	private int IdTelefone;
	private String ddd;
	private String telefone;
	
	public Telefone() {
	}
	
	public Telefone(String ddd, String telefone) {
		
		this.setDdd( ddd );
		this.setTelefone( telefone );
	}
	public void setIdTelefone(int IdTelefone){
		this.IdTelefone = IdTelefone;
	}
	public int getIdTelefone() {
		return this.IdTelefone;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public String getDdd() {
		return this.ddd;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String toString() {
		return "Telefone: (" + this.getDdd() + ") " + this.getTelefone();
	}
}