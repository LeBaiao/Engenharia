
package br.edu.ifsp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dao.FuncionarioDao;
import br.edu.ifsp.model.cargo.Cargo;
import br.edu.ifsp.model.funcionario.Funcionario;
import br.edu.ifsp.model.funcionario.FuncionarioValidacao;

public class FuncionarioController {
	private Funcionario funcionario;
	private List<String> erros;

    public List<String> insereFuncionario(String nome, Character sexo, BigDecimal salario, Boolean planoSaude, Cargo cargo) {
    	recebeDadosFuncionario(null, nome, sexo, salario, planoSaude, cargo);
    	
		
		if (erros.size() == 0)
			erros.add(new FuncionarioDao().insereFuncionario(funcionario));
		
		return erros; 
    }
    
    
    public void recebeDadosFuncionario(Integer id, String nome, Character sexo, BigDecimal salario, Boolean planoSaude, Cargo cargo) {
    	funcionario = new Funcionario();
    	erros = new ArrayList<String>();

		
    	funcionario.setId(id);
    	funcionario.setNome(nome);
		funcionario.setSexo(sexo);
		funcionario.setSalario(salario);
		funcionario.setPlanoSaude(planoSaude);
		funcionario.setCargo(cargo);
        
		
		erros = FuncionarioValidacao.validaFuncionario(funcionario);
    }
    
    public List<Cargo> recuperaCargos() {
    	
		return new FuncionarioDao().recuperaCargos();
    }
    
    public String getExcecao() {
    	return new FuncionarioDao().getExcecao();
    }
    
    public List<Funcionario> consultaFuncionarios() {
		return new FuncionarioDao().consultaFuncionarios();
    }
    
    public List<String> alteraFuncionario(Integer id, String nome, Character sexo, BigDecimal salario, Boolean planoSaude, Cargo cargo) {
    	recebeDadosFuncionario(id, nome, sexo, salario, planoSaude, cargo);
    	
		
		if (erros.size() == 0)
			erros.add(new FuncionarioDao().alteraFuncionario(funcionario));
		return erros;
    }
    public String excluiFuncionario(Integer id) {
    	String erro = new FuncionarioDao().excluiFuncionario(id);
        return erro;
    }
}

