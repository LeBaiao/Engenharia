package br.edu.ifsp.view.funcionario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.ifsp.controller.FuncionarioController;

public class FuncionarioAlteracao {
static Scanner entrada = new Scanner(System.in);
	
	public static void exibeInterface() {
		Integer id;
		String nome;
		Character sexo = null;
		BigDecimal salario = null;
		boolean planoSaude;

		System.out.println("ALTERAÇÃO DE FUNCIONÁRIO:");
		System.out.print("Informe o ID do funcionário a ser alterado: ");
		id = Integer.parseInt(entrada.nextLine());
		System.out.print("NOME: ");
		nome = entrada.nextLine();
		System.out.print("SEXO (Digite 'm' ou 'f'): ");
		sexo = entrada.nextLine().toUpperCase().charAt(0);
		System.out.print("SALÁRIO (R$): ");
		salario = new BigDecimal(entrada.nextLine());
		System.out.print("PLANO DE SAÚDE (Digite 's' ou 'n'): ");
		if (entrada.nextLine().equals("s"))
			planoSaude = true;
		else
			planoSaude = false;

		List<String> erros = new ArrayList<String>();
		
		erros = new FuncionarioController().alteraFuncionario(id,
				                                              nome,
												              sexo,
												              salario,
										                      planoSaude,
										                      FuncionarioCadastro.leCargo());
		
		if (erros.get(0) == null) { 
			System.out.println("Funcionário alterado com sucesso.\n");
		} else { 
			String mensagem = "N�o foi possível alterar o funcionário:\n";
			for (String e : erros) 
				mensagem = mensagem + e + "\n";
			System.out.println(mensagem);
		}
	}
}
