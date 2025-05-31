package Classes;

public class Usuario {
	private Integer IdUsuario;
	private String NomeUsuario;
	private String Senha;
	private String NomeCompleto;
	private String Email;
	private Telefone Telefone;
	private Boolean Ativo;
	private String Permissoes;
	
	public Usuario() {
		
	}
	
	public Usuario(Integer IdUsuario, String NomeUsuario, String Senha, String NomeCompleto, String Email, Boolean Ativo, String Permissoes) {
			this.IdUsuario = IdUsuario;
			this.NomeUsuario = NomeUsuario;
			this.Senha = Senha;
			this.NomeCompleto  = NomeCompleto;
			this.Email = Email;
			this.Ativo = Ativo;
			this.Permissoes = Permissoes;
			
		}
		
	public Integer getIdUsuario() {
			return IdUsuario;
	}
		
	public void setIdUsuario(Integer IdUsuario) {
			this.IdUsuario = IdUsuario;
	}

	public String getNomeUsuario() {
		return NomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		NomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getNomeCompleto() {
		return NomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		NomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Telefone getTelefone() {
		return Telefone;
	}

	public void setTelefone(Telefone telefone) {
		Telefone = telefone;
	}

	public Boolean getAtivo() {
		return Ativo;
	}

	public void setAtivo(Boolean ativo) {
		Ativo = ativo;
	}

	public String getPermissoes() {
		return Permissoes;
	}

	public void setPermissoes(String permissoes) {
		Permissoes = permissoes;
	}
	
	public void autenticar() {
		System.out.println("Usuário: " + NomeUsuario + ", Senha: " + Senha + " autenticado com sucesso.");
	}
	
	public boolean temPermissao(String permissaoNecessaria) {
	    return this.Permissoes.equalsIgnoreCase(permissaoNecessaria);
	}
	
	
	public String toString() {
		String str = "";
	    str += "IdUsuario:" + this.getIdUsuario() + "/r/n" ;
	    str += "NomeUsuario:" + this.getNomeUsuario() + "/r/n" ;
	    str += "Senha:" + this.getSenha() + "/r/n";
	    str += "NomeCompleto:" + this.getNomeCompleto() + "/r/n";
	    str += "Email:" + this.getEmail() + "/r/n";
	    str += this.getTelefone().toString() + "/r/n" ;
	    str += "Ativo:" + this.getAtivo() + "/r/n";
	    str += "Permissoes:" + this.getPermissoes() + "/r/n";
	    return str;
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
	
