package br.edu.ifsp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.model.cargo.Cargo;
import br.edu.ifsp.model.funcionario.Funcionario;

public class FuncionarioDao extends GenericDao {
	private String instrucaoSql; 
	private PreparedStatement comando; 
	private ResultSet registros; 
	private static String excecao = null; 

    public String insereFuncionario(Funcionario funcionario) {
        instrucaoSql = "INSERT INTO Funcionario (Nome, Sexo, Salario, PlanoSaude, IdCargo) VALUES (?,?,?,?,?)";
        return  insereAlteraExclui(instrucaoSql, funcionario.getNome(), funcionario.getSexo().toString(), funcionario.getSalario(),
        	                        funcionario.isPlanoSaude(), funcionario.getCargo().getId());
    }
    
    public List<Cargo> recuperaCargos() {
        Cargo cargo;
        List<Cargo> cargos = new ArrayList<Cargo>();
        instrucaoSql = "SELECT * FROM Cargo";
        
        try {
        	excecao = ConnectionDatabase.conectaBd(); 
        	if (excecao == null) {
               
                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
                
               
                registros = comando.executeQuery();
                
                if (registros.next()) { 
                    registros.beforeFirst(); 
        	        while (registros.next()) {

        	            cargo = new Cargo();
        	            cargo.setId(registros.getInt("Id"));
        	            cargo.setDescricao(registros.getString("Descricao"));
        	            cargos.add(cargo);
        	        }
        	    }
                registros.close(); 
                comando.close(); 
               
                ConnectionDatabase.getConexaoBd().close(); 
            }
        } catch (Exception e) {
        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
        	cargos = null; 
        }
        return cargos; 
    }
    
    
	public String getExcecao() {
		return excecao;
	}
	
	 public List<Funcionario> consultaFuncionarios() {
	    	Funcionario funcionario;
	        Cargo cargo;
	        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	        List<Cargo> cargos = new ArrayList<Cargo>();
	        
	        String instrucaoSql = "SELECT * FROM FUNCIONARIO";
	    	PreparedStatement comando;
	    	ResultSet registros;
	  
	        try {
	        	excecao = ConnectionDatabase.conectaBd(); 
	        	if (excecao == null) {
	                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
	                
	                
	                registros = comando.executeQuery();
	                
	                if (registros.next()) { 
	                    registros.beforeFirst(); 
	                    
	                    
	    	            cargos = recuperaCargos();
	    	            
	        	        while (registros.next()) {
	                       
	        	            funcionario = new Funcionario();
	        	            funcionario.setId(registros.getInt("Id"));
	        	            funcionario.setNome(registros.getString("Nome"));
	        	            funcionario.setSexo(registros.getString("Sexo").charAt(0));
	        	            funcionario.setSalario(registros.getBigDecimal("Salario"));
	        	            funcionario.setPlanoSaude(registros.getBoolean("PlanoSaude"));
	        	            
	        	            cargo = new Cargo();
	        	            cargo.setId(registros.getInt("IdCargo"));
	        				for (Cargo c : cargos)
	        					if (c.getId() == cargo.getId()) {
	        						cargo.setDescricao(c.getDescricao());
	        						break;
	        					}
	        	            funcionario.setCargo(cargo);
	        	            funcionarios.add(funcionario);
	        	        }
	        	    }
	                registros.close(); 
	                comando.close(); 
	                
	                ConnectionDatabase.getConexaoBd().close();
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	funcionarios = null; 
	        }
	        return funcionarios; 
	    }
	 
	 public String alteraFuncionario(Funcionario funcionario) {
	    	instrucaoSql = "UPDATE FUNCIONARIO SET Nome = ?, Sexo = ?, Salario = ?, PlanoSaude = ?, IdCargo = ? " +
	                       "WHERE Id = ?";
	    	return insereAlteraExclui(instrucaoSql, funcionario.getNome(), funcionario.getSexo().toString(), funcionario.getSalario(), 
	        		                                funcionario.isPlanoSaude(), funcionario.getCargo().getId(), funcionario.getId());
	 }
	        public String excluiFuncionario(int id) {
	        	instrucaoSql = "DELETE FROM FUNCIONARIO WHERE Id = ?";
	        	return insereAlteraExclui(instrucaoSql, id);
	        }
	 
	 
	 
	 
	 
	 
	 
}
