package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class ConexaoBD {
	    public Connection connection = null;
	    
	    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private final String DBNAME = "sistemagerenciamento";  
	    private final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	    private final String LOGIN = "root"; 
	    private final String SENHA = "123456"; 

	    public boolean getConnection() {
	        try {
	            Class.forName(DRIVER);
	            connection = DriverManager.getConnection(URL, LOGIN, SENHA);
	            System.out.println("Conectou com sucesso ao banco de dados.");
	            return true;
	        } catch (ClassNotFoundException erro) {
	            System.out.println("Driver não encontrado: " + erro.toString());
	            return false;
	        } catch (SQLException erro) {
	            System.out.println("Falha ao conectar: " + erro.toString());
	            return false;
	        }
	    }

	    public void close() {
	        try {
	            if (connection != null && !connection.isClosed()) {
	                connection.close();
	                System.out.println("Desconectou do banco de dados.");
	            }
	        } catch (SQLException erro) {
	            System.out.println("Erro ao desconectar: " + erro.toString());
	        }
	    }
	    public Connection getConnectionObject(){
	    	return connection;
	    }
}

