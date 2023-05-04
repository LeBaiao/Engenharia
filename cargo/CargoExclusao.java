package br.edu.ifsp.view.cargo;

import java.util.Scanner;

import br.edu.ifsp.controller.CargoController;

public class CargoExclusao {
static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		Integer id;

		System.out.println("EXCLUSÃO DE CARGO:");
		System.out.print("Informe o ID do cargo a ser excluído: ");
		id = Integer.parseInt(entrada.nextLine());

		String erro = "";
		
		
		erro = new CargoController().excluiCargo(id);
		
		if (erro == null)  
			System.out.println("Cargo excluído com sucesso.\n");
		else
			System.out.println("Não foi possível excluir o cargo:\n" + erro + "\n");
	}
}
