package com.br.marzo.gerenciaBanco;

import java.util.Scanner;

import com.br.marzo.gerenciaBanco.models.PessoaModel;

public class App {

	public static void main(String[] args) {
		App.exbirMenu();
	}

	public static void exbirMenu() {
		PessoaModel pessoa = null;
		Boolean cadastroPessoaInvalido = true;
		while (cadastroPessoaInvalido) {
			pessoa = App.ObterPessoa();
			cadastroPessoaInvalido = pessoa == null;
		}

		StringBuilder menuBuilder = new StringBuilder();
		menuBuilder.append("Selecione um item: \n");
		menuBuilder.append("1 - Dados conta. \n");
		menuBuilder.append("2 - Realizar saque. \n");
		menuBuilder.append("3 - Realizar depósito. \n");
		menuBuilder.append("4 - Consultar saldo. \n");
		menuBuilder.append("5 - Finalizar operação. \n");

		Scanner scanner = new Scanner(System.in);
		// Habilita menu
		Boolean menuAtivo = true;
		while (menuAtivo) {
			try {
				System.out.print(menuBuilder.toString());
				Integer valueMenu = scanner.nextInt();
				switch (valueMenu) {
				case 1:
					System.out.println(pessoa.getInfoConta());
					break;
				case 2:
					System.out.println("Digite o valor a depositar: (use \",\")");
					Float saque = scanner.nextFloat();
					pessoa.getContaBancaria().sacar(saque);
					System.out.printf("Saldo atual: %.2f \n", pessoa.getContaBancaria().getSaldo());
					break;
				case 3:
					System.out.println("Digite o valor a depositar: (use \",\")");
					Float deposito = scanner.nextFloat();
					pessoa.getContaBancaria().depositar(deposito);
					System.out.printf("Saldo atual: %.2f \n", pessoa.getContaBancaria().getSaldo());
					break;
				case 4:
					System.out.printf("Saldo atual: %.2f \n", pessoa.getContaBancaria().getSaldo());
					break;
				case 5:
					System.out.println("Finalizando operação.");
					menuAtivo = false;
					continue;
				default:
					continue;
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}

	}

	private static PessoaModel ObterPessoa() {
		try {
			Scanner scanner = new Scanner(System.in);

			PessoaModel pessoa = new PessoaModel();
			System.out.println("Bem vindo ao Gerência Banco!");
			System.out.println("Digite seu CPF:");
			pessoa.setCpf(scanner.nextLine());

			System.out.println("Digite seu nome:");
			pessoa.setNome(scanner.nextLine());

			System.out.println("Digite seu sobrenome:");
			pessoa.setSobrenome(scanner.nextLine());

			System.out.printf("Seja bem vindo %s!\n", pessoa.getNomeCompleto());
			return pessoa;
		} catch (Exception e) {
			System.out.printf("Erro ao obter cadastro: %s \n", e.getMessage());
			return null;
		}
	}
}
