package br.edu.ifsp.view.departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.edu.ifsp.controller.DepartamentoController;


public class DepartamentoAlteracao {

static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		Integer id;
		String nome;

		System.out.println("ALTERAÇÃO DE DEPARTAMENTO:");
		System.out.print("Informe o ID do departamento a ser alterado: ");
		id = Integer.parseInt(entrada.nextLine());
		System.out.print("NOME: ");
		nome = entrada.nextLine();

		List<String> erros = new ArrayList<String>();
		
		erros = new DepartamentoController().alteraDepartamento(id,
				                                              nome,
										                      DepartamentoCadastro.leGerente());
		
		if (erros.get(0) == null) { 
			System.out.println("Departamento alterado com sucesso.\n");
		} else { 
			String mensagem = "Não foi possível alterar o departamento:\n";
			for (String e : erros) 
				mensagem = mensagem + e + "\n";
			System.out.println(mensagem);
		}
	}
}
