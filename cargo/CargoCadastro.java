package br.edu.ifsp.view.cargo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.ifsp.controller.CargoController;
import br.edu.ifsp.model.departamento.Departamento;

public class CargoCadastro {
static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		String descricao;

		System.out.println("\nCADASTRO DE CARGOS:");
		System.out.print("DESCRIÇÃO: ");
		descricao = entrada.nextLine();

		List<String> erros = new ArrayList<String>();
		
	
		erros = new CargoController().insereCargo(descricao, leDepartamento());
		
		if (erros.get(0) == null) { 
			System.out.println("Cargo cadastrado com sucesso.\n");
		} else { 
			String mensagem = "Não foi possível cadastrar o Cargo:\n";
			for (String e : erros) 
				mensagem = mensagem + e + "\n";
			System.out.println(mensagem);
		}
	}
	
	public static Departamento leDepartamento() {
		int codDepto;
		Departamento departamento = new Departamento();
		
		List<Departamento> departamentos = new ArrayList<Departamento>();
	
		departamentos = new CargoController().recuperaDepartamentos();		
		String erro = new CargoController().getExcecao();
		if (erro != null) 
			System.out.println("Não foi possível recuperar os dados dos departamentos:\n" + erro);
		
		if (departamentos.size() != 0) { 
			System.out.println("DEPARTAMENTOS CADASTRADOS: ");
			for (Departamento d : departamentos)
				System.out.println(d.getId() + " - " + d.getNomeDepto());
			
			System.out.print("CARGO (Digite o código do cargo): ");
			codDepto = Integer.parseInt(entrada.nextLine());
			for (Departamento d : departamentos)
				if (d.getId() == codDepto)
					departamento = d;
		}
		return departamento;
	}
}

