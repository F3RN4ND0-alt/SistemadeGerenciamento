package Classes;

public class Endereco {
	private String Endereco;
	private String Cep;
	private String Numero;
	private String Complemento;
	private String Bairro;
	private String Cidade;
	private String Estado;
	private String Pais;
	
		public Endereco(String Endereco, String Cep, String Numero, String Complemento, String Bairro, String Cidade, String Estado, String Pais) {
			this.Endereco = Endereco;
			this.Cep = Cep;
			this.Numero = Numero;
			this.Complemento = Complemento;
			this.Bairro = Bairro;
			this.Cidade = Cidade;
			this.Estado = Estado;
			this.Pais = Pais;
			
		}
	
	public String getEndereco() {
		return Endereco;
	}
	
	public void setEndereco(String Endereco) {
		this.Endereco = Endereco;
	}

	public String getCep() {
		return Cep;
	}

	public void setCep(String cep) {
		Cep = cep;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		Pais = pais;
	}
	
	public String toString() {
		String str = "";
		str += "Endereco: " + this.getEndereco() + "\n";
		str += "Cep: " + this.getCep() + "\n";
		str += "Numero: " + this.getNumero() + "\n";
		str += "Complemento: " + this.getComplemento() + "\n";
		str += "Bairro: " + this.getBairro() + "\n";
		str += "Cidade: " + this.getCidade() + "\n";
		str += "Estado: " + this.getEstado() + "\n";
		str += "Pais: " + this.getPais();
		return str;
	}

}
