package br.edu.ifsp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.dao.DepartamentoDao;
import br.edu.ifsp.dao.FuncionarioDao;
import br.edu.ifsp.model.cargo.Cargo;
import br.edu.ifsp.model.departamento.Departamento;
import br.edu.ifsp.model.departamento.DepartamentoValidacao;
import br.edu.ifsp.model.funcionario.Funcionario;

public class DepartamentoController {
	private Departamento departamento;
	private List<String> erros;
	
	public List<String> insereDepartamentos(String NomeDepto, Funcionario gerente) {
		recebeDadosDepartamento(null, NomeDepto, gerente);
		
		if (erros.size()==0)
			erros.add(new DepartamentoDao().insereDepartamento(departamento));
		return erros;
	}
	
	public void recebeDadosDepartamento(Integer id, String nomeDepto, Funcionario gerente) {
		departamento = new Departamento();
		erros = new ArrayList<String>();
		
		departamento.setId(id);
		departamento.setNomeDepto(nomeDepto);
		departamento.setGerente(gerente);
		
		erros = DepartamentoValidacao.validaDepartamento(departamento);
	}
	
	public List<Funcionario> recuperaFuncionarios() {
		return new DepartamentoDao().recuperaFuncionarios();
	}
	
	   public String getExcecao() {
	    	
	    	return new DepartamentoDao().getExcecao();
	    }
	   
	    public List<Departamento> consultaDepartamentos() {
			return new DepartamentoDao().consultaDepartamentos();
	    }
	
	    public List<String> alteraDepartamento(Integer id, String nome, Funcionario gerente) {
	    	recebeDadosDepartamento(id, nome, gerente);
	    	
			
			if (erros.size() == 0)
				erros.add(new DepartamentoDao().alteraDepartamento(departamento));
			return erros;
	    }
	    public String excluiDepartamento(Integer id) {
	    	String erro = new DepartamentoDao().excluiDepartamento(id);
	        return erro;
	    }
	    
}
