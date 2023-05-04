package br.edu.ifsp.view.funcionario;

import java.util.List;
import java.util.Scanner;
import br.edu.ifsp.controller.FuncionarioController;
import br.edu.ifsp.model.funcionario.Funcionario;

public class FuncionarioConsulta {
	static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		
		String excecaoFuncionarios = null;
		String planoSaude;
		
		List<Funcionario> funcionarios = new FuncionarioController().consultaFuncionarios();
		excecaoFuncionarios = new FuncionarioController().getExcecao();
		String formato = "%1$-6s %2$-20s %3$-7s %4$-15s %5$-17s %6$-25s%n";
		
		if (excecaoFuncionarios != null)
			System.out.println("Não foi possível recuperar os dados dos funcionários:\n" + excecaoFuncionarios);
		else {
			System.out.println("\nCONSULTA DE FUNCIONÁRIOS:");
			System.out.printf(formato, "CÓDIGO", " | NOME", " | SEXO", " | SALÁRIO (R$)", " | PLANO DE SA�DE", " | CARGO");
			
			for (Funcionario f : funcionarios) {
				if (f.isPlanoSaude())
					planoSaude = "Sim";
				else
					planoSaude = "Não";
				
				System.out.printf(formato, f.getId(), 
						           " | " + f.getNome(),
						           " | " + f.getSexo(),
						           " | " + f.getSalario(),
						           " | " + planoSaude,
						           " | " + f.getCargo().getDescricao());
			}
			System.out.println();
		}
	}
	
	public static void exibeOpcoesAlterarExcluir() {
		int opcaoOperacao = 0;
		
		do {
			System.out.println("ALTERAÇÃO / EXCLUSÃO DE FUNCIONÁRIO:");
			System.out.println("1) Alterar");
			System.out.println("2) Excluir");
			System.out.print("Digite uma opção (0 para voltar): ");
		
		    opcaoOperacao = Integer.parseInt(entrada.nextLine());
		    System.out.println();
		    
			switch (opcaoOperacao) {
			case 0:
				break;
			case 1:
				FuncionarioAlteracao.exibeInterface();
				break;
			case 2:	
				FuncionarioExclusao.exibeInterface();
				break;
			default:
				if (opcaoOperacao != 1 && opcaoOperacao != 2)
					System.out.println("Digite uma opção válida.");
				break;
			}
		} while (opcaoOperacao != 0 && opcaoOperacao != 1 && opcaoOperacao != 2);
	}
}
