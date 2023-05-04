package br.edu.ifsp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.model.cargo.Cargo;
import br.edu.ifsp.model.departamento.Departamento;
import br.edu.ifsp.model.funcionario.Funcionario;

public class CargoDao extends GenericDao{

		private String instrucaoSql; 
		private PreparedStatement comando; 
		private ResultSet registros; 
		private static String excecao = null; 

		public String insereCargo(Cargo cargo) {
			instrucaoSql = "INSERT INTO Cargo (Descricao, IdDepto) VALUES (?,?)";
	        return insereAlteraExclui(instrucaoSql, cargo.getDescricao(), cargo.getDepartamento().getId());

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
	        	            departamento.setNomeDepto(registros.getString("nomeDepto"));
	        	            //sem o gerente2
	        	            
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
		
		 public List<Cargo> consultaCargos() {
		    	Cargo cargo;
		        Departamento departamento;
		        List<Cargo> cargos = new ArrayList<Cargo>();
		        List<Departamento> departamentos = new ArrayList<Departamento>();
		        
		        String instrucaoSql = "SELECT * FROM Cargo";
		    	PreparedStatement comando;
		    	ResultSet registros;
		  
		        try {
		        	excecao = ConnectionDatabase.conectaBd();
		        	if (excecao == null) {
		                
		                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);
		                
		                
		                registros = comando.executeQuery();
		                
		                if (registros.next()) { 
		                    registros.beforeFirst(); 
		                    
		                    
		    	            departamentos = recuperaDepartamentos();
		    	            
		        	        while (registros.next()) {
		        	            cargo = new Cargo();
		        	            cargo.setId(registros.getInt("Id"));
		        	            cargo.setDescricao(registros.getString("Descricao"));
		        	            departamento = new Departamento();
		        	            departamento.setId(registros.getInt("IdDepto"));
		        				for (Departamento d : departamentos)
		        					if (d.getId() == departamento.getId()) {
		        						departamento.setNomeDepto(d.getNomeDepto());
		        						break;
		        					}
		        	            cargo.setDepartamento(departamento);
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
		 
		 public String alteraCargo(Cargo cargo) {
		    	instrucaoSql = "UPDATE CARGO SET Descricao = ?, IdDepto = ? WHERE Id = ?";
		    	return insereAlteraExclui(instrucaoSql, cargo.getDescricao(), cargo.getDepartamento().getId(), cargo.getId());
		 }
		 
	        public String excluiCargo(int id) {
	        	instrucaoSql = "DELETE FROM CARGO WHERE Id = ?";
	        	return insereAlteraExclui(instrucaoSql, id);
	        }
		 
		 
		 
}

