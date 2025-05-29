package Classes;

public class Endereco {
	private String endereco;
	private String cep;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	
		public Endereco(String Endereco, String Cep, String Numero,String Complemento,String Bairro, String Cidade, String Estado,String Pais) {
			this.endereco = Endereco;
			this.numero = Numero;
			this.cep = Cep;
			this.bairro = Bairro;
			this.complemento = Complemento;
			this.cidade = Cidade;
			this.estado = Estado;
			this.pais = Pais;
			
		}

	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String Endereco) {
		this.endereco = Endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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
		str += "Pais: " + this.getPais() + "\n";
		return str;
	}

}
