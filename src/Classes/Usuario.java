package Classes;

import Enums.Permissao;
import Enums.Sexo;
import java.util.Calendar;

public abstract  class  Usuario {
    protected Integer idUsuario;
    protected String nomeUsuario;
    protected String senha;
    protected String cpf;
    protected Sexo sexo;
    protected Calendar dataNascimento;
    protected String email;
    protected Telefone telefone;
    protected Endereco endereco;
    protected Boolean ativo;
    protected Permissao permissao;

    
    public Usuario() {
    }

 
    public Usuario(Integer idUsuario, String nomeUsuario, String senha, String cpf, Sexo sexo,
                   Calendar dataNascimento, String email, Telefone telefone,Endereco endereco,
                   Boolean ativo, Permissao permissao) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.ativo = ativo;
        this.permissao = permissao;
    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    public Endereco getEndereco() {
    	return endereco;
    }
    public void setEndereco(Endereco endereco) {
    	this.endereco = endereco;
    }
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }


    public abstract void verificarEmail();

 
    public void autenticar() {
        System.out.println("Usuário: " + nomeUsuario + ", senha: " + senha + " autenticado com sucesso.");
    }

    
    public boolean temPermissao(Permissao p) {
        return this.permissao == p;
    }

    public int calcularIdade() {
        if (dataNascimento == null) return -1;

        Calendar hoje = Calendar.getInstance();
        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH) ||
           (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) &&
            hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade;
    }

    public String toString() {
        String str = "";
        str += "Id: " +  this.getIdUsuario() + "\r\n";
        str += "Nome: " + this.getNomeUsuario() + "\r\n";
        str += "Senha: " + this.getSenha() + "\r\n";
        str += "Cpf:" + this.getCpf() + "\r\n";
        str += "Sexo: " + this.getSexo() + "\r\n";
        str += "DataNascimento: " + this.getDataNascimento() + "\r\n";
        str += "Email: " + this.getEmail() + "\r\n";
        str += "Telefone: " + this.getTelefone() + "\r\n";
        str += "Endereco:"	+ this.getEndereco() + "\r\n";
        str += "Ativo: " + this.getAtivo() + "\r\n";
        str += "Permissao: " + this.getPermissao() + "\r\n";
        return str;
    }
}