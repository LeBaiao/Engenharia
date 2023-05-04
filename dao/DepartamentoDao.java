package br.edu.ifsp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.model.departamento.Departamento;
import br.edu.ifsp.model.funcionario.Funcionario;

public class DepartamentoDao extends GenericDao{
		private String instrucaoSql; 
		private PreparedStatement comando; 
		private ResultSet registros; 
		private static String excecao = null; 

	    public String insereDepartamento(Departamento departamento) {
	        instrucaoSql = "INSERT INTO Departamento (nomeDepto, IdFuncGerente) VALUES (?,?)";
	        return insereAlteraExclui(instrucaoSql, departamento.getNomeDepto(), departamento.getGerente().getId());
	    }
	    
	    public List<Departamento> recuperaDepartamentos() {
	        Departamento departamento;
	        List<Departamento> departamentos = new ArrayList<Departamento>();
	        instrucaoSql = "SELECT * FROM Departamento";
	        
	        try {
	        	excecao = ConnectionDatabase.conectaBd(); 
	        	if (excecao == null) {
	               
	                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
	                
	               
	                registros = comando.executeQuery();
	                
	                if (registros.next()) { 
	                    registros.beforeFirst(); 
	        	        while (registros.next()) {

	        	            departamento = new Departamento();
	        	            departamento.setId(registros.getInt("id"));
	        	            departamento.setNomeDepto(registros.getString("NomeDepto"));
	        	            departamentos.add(departamento);
	        	        }
	        	    }
	                registros.close(); 
	                comando.close(); 
	               
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	departamentos = null; 
	        }
	        return departamentos; 
	    }
	    
	    
		public String getExcecao() {
			return excecao;
		}
		
		public List<Funcionario> recuperaFuncionarios() {
	    	Funcionario funcionario;
	    	
	        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	        instrucaoSql = "SELECT * FROM Funcionario";
	        
	        try {
	        	excecao = ConnectionDatabase.conectaBd(); 
	        	if (excecao == null) {
	                
	                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
	                
	               
	                registros = comando.executeQuery();
	                
	                if (registros.next()) { 
	                    registros.beforeFirst(); 
	        	        while (registros.next()) {
	              
	                      
	        	        	funcionario = new Funcionario();
	        	            funcionario.setId(registros.getInt("Id"));
	        	            
	        	            funcionario.setNome(registros.getString("Nome"));
	        	            
	        	            funcionarios.add(funcionario);
	        	          
	        	        }
	        	    }
	                registros.close(); 
	                comando.close(); 
	               
	                ConnectionDatabase.getConexaoBd().close(); 
	            }
	        } catch (Exception e) {
	        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
	        	funcionarios = null; 
	        }
	        return funcionarios; 
	    }
		
		 public List<Departamento> consultaDepartamentos() {
		     Departamento departamento;
		        //Cargo cargo;
		        List<Departamento> departamentos = new ArrayList<Departamento>();
		        //List<Cargo> cargos = new ArrayList<Cargo>();
		        
		        String instrucaoSql = "SELECT * FROM Departamento";
		    	PreparedStatement comando;
		    	ResultSet registros;
		  
		        try {
		        	excecao = ConnectionDatabase.conectaBd(); 
		        	if (excecao == null) {
		
		                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
		                
		                
		                registros = comando.executeQuery();
		                
		                if (registros.next()) { 
		                    registros.beforeFirst(); 
		                    
		                    
		    	            //cargos = recuperaCargos();
		    	            
		        	        while (registros.next()) {
		                        
		        	            departamento = new Departamento();
		        	            departamento.setId(registros.getInt("Id"));
		        	            departamento.setNomeDepto(registros.getString("nomeDepto"));
		        	            //não vou colocar o gerente
		        	            departamentos.add(departamento);
		        	        }
		        	    }
		                registros.close();
		                comando.close(); 
		                
		                ConnectionDatabase.getConexaoBd().close();
		            }
		        } catch (Exception e) {
		        	excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
		        	departamentos = null;
		        }
		        return departamentos; 
		    }
		 
		 public String alteraDepartamento(Departamento departamento) {
		    	instrucaoSql = "UPDATE DEPARTAMENTO SET nomeDepto = ?, IdFuncGerente = ? WHERE Id = ?";
		    	return insereAlteraExclui(instrucaoSql, departamento.getNomeDepto(), departamento.getGerente().getId(),departamento.getId());
		 }
		        public String excluiDepartamento(int id) {
		        	instrucaoSql = "DELETE FROM DEPARTAMENTO WHERE Id = ?";
		        	return insereAlteraExclui(instrucaoSql, id);
		        }
	}


