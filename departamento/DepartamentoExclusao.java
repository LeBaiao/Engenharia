package br.edu.ifsp.view.departamento;

import java.util.Scanner;

import br.edu.ifsp.controller.DepartamentoController;

public class DepartamentoExclusao {

static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		Integer id;

		System.out.println("EXCLUSÃO DE DEPARTAMENTO:");
		System.out.print("Informe o ID do departamento a ser excluído: ");
		id = Integer.parseInt(entrada.nextLine());

		String erro = "";
		
		
		erro = new DepartamentoController().excluiDepartamento(id);
		
		if (erro == null)  
			System.out.println("Departamento excluído com sucesso.\n");
		else
			System.out.println("Não foi possível excluir o departamento:\n" + erro + "\n");
	}
}
