package Classes;

public class Endereco {
    private int idEndereco;
    private String endereco;
    private String cep;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco() {
    }

    public Endereco(int idEndereco,String endereco, String cep, String numero, String complemento,
                    String bairro, String cidade, String estado, String pais) {
        this.idEndereco = idEndereco;
    	this.endereco = endereco;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    @Override
    public String toString() {
        return "Endereco: " + endereco + "\n" +
               "Cep: " + cep + "\n" +
               "Numero: " + numero + "\n" +
               "Complemento: " + complemento + "\n" +
               "Bairro: " + bairro + "\n" +
               "Cidade: " + cidade + "\n" +
               "Estado: " + estado + "\n" +
               "Pais: " + pais + "\n";
    }
}

