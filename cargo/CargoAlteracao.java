package br.edu.ifsp.view.cargo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.ifsp.controller.CargoController;

public class CargoAlteracao {

static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		Integer id;
		String descricao;
		Integer idDepto = null;

		System.out.println("ALTERAÇÃO DE CARGOS:");
		System.out.print("Informe o ID do cargo a ser alterado: ");
		id = Integer.parseInt(entrada.nextLine());
		System.out.print("DESCRIÇÃO: ");
		descricao = entrada.nextLine();
		System.out.print("ID do Departamento: ");
	    idDepto = Integer.parseInt(entrada.nextLine());

		List<String> erros = new ArrayList<String>();
		
		erros = new CargoController().alteraCargo(id,
				                                              descricao,
										                      CargoCadastro.leDepartamento());
		
		if (erros.get(0) == null) { 
			System.out.println("Cargo alterado com sucesso.\n");
		} else { 
			String mensagem = "Não foi possível alterar o cargo:\n";
			for (String e : erros) 
				mensagem = mensagem + e + "\n";
			System.out.println(mensagem);
		}
	}
}
