package br.edu.ifsp.view.departamento;

import java.util.List;
import java.util.Scanner;

import br.edu.ifsp.controller.DepartamentoController;
import br.edu.ifsp.model.departamento.Departamento;
import br.edu.ifsp.view.funcionario.FuncionarioAlteracao;
import br.edu.ifsp.view.funcionario.FuncionarioExclusao;


public class DepartamentoConsulta {
	static Scanner entrada = new Scanner(System.in);
	public static void exibeInterface() {
		String excecaoDepartamentos = null;
		
		
		List<Departamento> departamentos = new DepartamentoController().consultaDepartamentos();
		excecaoDepartamentos = new DepartamentoController().getExcecao();
		String formato = "%1$-6s %2$-20s";
		
		if (excecaoDepartamentos != null)
			System.out.println("Não foi possível recuperar os dados dos departamentos:\n" + excecaoDepartamentos);
		else {
			System.out.println("\nCONSULTA DE DEPARTAMENTOS:");
			System.out.printf(formato, "CÓDIGO", " | DESCRIÇÃO");
			
			for (Departamento d : departamentos) {
				System.out.printf(formato, d.getId(), 
						           " | " + d.getNomeDepto());
			}
			System.out.println();
		}
	}
	
	public static void exibeOpcoesAlterarExcluir() {
		int opcaoOperacao = 0;
		
		do {
			System.out.println("ALTERAÇÃO / EXCLUSÃO DE DEPARTAMENTO:");
			System.out.println("1) Alterar");
			System.out.println("2) Excluir");
			System.out.print("Digite uma opção (0 para voltar): ");
		
		    opcaoOperacao = Integer.parseInt(entrada.nextLine());
		    System.out.println();
		    
			switch (opcaoOperacao) {
			case 0:
				break;
			case 1:
				DepartamentoAlteracao.exibeInterface();
				break;
			case 2:	
				DepartamentoExclusao.exibeInterface();
				break;
			default:
				if (opcaoOperacao != 1 && opcaoOperacao != 2)
					System.out.println("Digite uma opção válida.");
				break;
			}
		} while (opcaoOperacao != 0 && opcaoOperacao != 1 && opcaoOperacao != 2);
	}
}
