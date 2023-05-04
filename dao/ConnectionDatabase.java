package br.edu.ifsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {
	private static Connection conexao; 
	
	
	private static final String SERVER = "jdbc:mysql://localhost";
	private static final String DATABASE = "/aula4_Atividade";
	private static final String USER = "?user=root";
	private static final String PASSWORD = "&password=1234";
	

	private static final String USE_SSL = "&useSSL=false";
	

	private static final String USE_TIMEZONE = "&useTimezone=true";
	private static final String SERVER_TIMEZONE = "&serverTimezone=UTC";
	
	private static final String STRING_CONNECTION = SERVER + DATABASE + USER + PASSWORD + USE_SSL + USE_TIMEZONE + SERVER_TIMEZONE;

    public static String conectaBd() { 
        try {
            conexao = DriverManager.getConnection(STRING_CONNECTION);
        } catch (Exception e) {
 
            return "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage(); 
        }
        return null; 
    }

    public static Connection getConexaoBd() { 
        return conexao;
    }
}
