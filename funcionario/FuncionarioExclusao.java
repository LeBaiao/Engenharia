package br.edu.ifsp.view.funcionario;

import java.util.Scanner;

import br.edu.ifsp.controller.FuncionarioController;

public class FuncionarioExclusao {
static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		Integer id;

		System.out.println("EXCLUSÃO DE FUNCIONÁRIO:");
		System.out.print("Informe o ID do funcionário a ser exclu�do: ");
		id = Integer.parseInt(entrada.nextLine());

		String erro = "";
		
		
		erro = new FuncionarioController().excluiFuncionario(id);
		
		if (erro == null)  
			System.out.println("Funcionário excluído com sucesso.\n");
		else
			System.out.println("Não foi possível excluir o funcionário:\n" + erro + "\n");
	}
}
