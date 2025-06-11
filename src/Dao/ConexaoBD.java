package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBNAME = "sistemagerenciamento";  
    private static final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
    private static final String LOGIN = "root"; 
    private static final String SENHA = "123456"; 

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, LOGIN, SENHA);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar: " + e.getMessage());
        }
    }
}

