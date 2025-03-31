package com.br.marzo.gerenciaBanco.models;

public class PessoaModel {
	public ContaBancariaModel getContaBancaria() {
		if (this.contaBancaria == null)
			this.contaBancaria = new ContaBancariaModel();
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancariaModel contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	public String getInfoConta() {
		StringBuilder infoBuilder = new StringBuilder();
		infoBuilder.append(String.format("Nome completo: %s \n", this.getNomeCompleto()));
		infoBuilder.append(String.format("CPF: %s \n", this.getCpf()));
		return infoBuilder.toString();
	}
	public String getNomeCompleto() {
		return String.format("%s %s", this.nome, this.sobrenome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		this.validaNome(nome, "Nome");
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) throws Exception {
		this.validaNome(sobrenome, "Sobrenome");
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws Exception {
		this.cpf = this.cpfValido(cpf);
	}

	private String nome;
	private String sobrenome;
	private String cpf;
	private ContaBancariaModel contaBancaria;

	public void informarDados() {
		// Semelhante ao usado no C#
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("Nome: %s \n", this.nome));
		builder.append(String.format("Sobrenome: %s \n", this.sobrenome));
		builder.append(String.format("CPF: %s \n", this.cpf));
		System.out.println(builder.toString());
	}

	public String cpfValido(String cpf) throws Exception {
		if (cpf == null)
			throw new Exception("CPF não deve ser vazio.");

		// Remover formtacao
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verifica se tem os 11 digitos
		if (cpf.length() != 11)
			throw new Exception("CPF inválido.");

		// Valida primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += (cpf.charAt(i) - '0') * (10 - i);
		}
		int primeiroDigito = (soma * 10) % 11;
		if (primeiroDigito == 10)
			primeiroDigito = 0;

		// Valida segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += (cpf.charAt(i) - '0') * (11 - i);
		}
		int segundoDigito = (soma * 10) % 11;
		if (segundoDigito == 10)
			segundoDigito = 0;

		// Verifica digitos verificadores
		if (!(primeiroDigito == (cpf.charAt(9) - '0')) && (segundoDigito == (cpf.charAt(10) - '0')))
			throw new Exception("CPF inválidos.");
		return cpf;
	}

	public void validaNome(String nome, String property) throws Exception {
		if (nome == null)
			throw new Exception(String.format("%s não pode ser nulo.", property));
		if (nome.equals(""))
			throw new Exception(String.format("%s não pode ser vazio.", property));
	}
}
