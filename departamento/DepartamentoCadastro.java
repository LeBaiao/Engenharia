package br.edu.ifsp.view.departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.edu.ifsp.controller.DepartamentoController;
import br.edu.ifsp.model.funcionario.Funcionario;

public class DepartamentoCadastro {
	static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		String nomeDepto;
		
		System.out.println("\nCADASTRO DE DEPARTAMENTO:");
		System.out.print("NOME DO DEPARTAMENTO: ");
		nomeDepto = entrada.nextLine();

		List<String> erros = new ArrayList<String>();

		erros = new DepartamentoController().insereDepartamentos(nomeDepto, leGerente());
		
		if (erros.get(0) == null) { 
			System.out.println("Departamento cadastrado com sucesso.\n");
		} else { 
			String mensagem = "Não foi possível cadastrar o departamento:\n";
			for (String e : erros) 
				mensagem = mensagem + e + "\n";
			System.out.println(mensagem);
		}
	}
	
	public static Funcionario leGerente() {
		int codFunc;
		Funcionario gerente = new Funcionario();
	
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		funcionarios = new DepartamentoController().recuperaFuncionarios();		
		String erro = new DepartamentoController().getExcecao();
		if (erro != null) 
			System.out.println("Não foi possível recuperar os dados dos funcionarios:\n" + erro);
		
		if (funcionarios.size() != 0) { 
			System.out.println("FUNCIONARIOS CADASTRADOS: ");
			for (Funcionario f : funcionarios)
				System.out.println(f.getId() + " - " + f.getNome());
			
			System.out.print("GERENTE (Digite o código do funcionario gerente): ");
			codFunc = Integer.parseInt(entrada.nextLine());
			for (Funcionario f : funcionarios)
				if (f.getId() == codFunc)
					gerente = f;
		}
		return gerente;
	}
}
