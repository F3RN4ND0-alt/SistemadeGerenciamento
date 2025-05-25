package Classes;
import  Dao.ConexaoBD;
public class Main {
	public static void main(String[] args) {
        ConexaoBD conexao = new ConexaoBD();
        if (conexao.getConnection()) {
            System.out.println("Conexão bem-sucedida!");
        } else {
            System.out.println("Erro na conexão.");
        }
    }
}
