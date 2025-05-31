package Classes;

public class Telefone {
	private String ddd;
	private String telefone;
	
	public Telefone() {
	}
	
	public Telefone(String ddd, String telefone) {
		this.setDdd( ddd );
		this.setTelefone( telefone );
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