package com.br.marzo.gerenciaBanco.models;

public class ContaBancariaModel {
	public Float getSaldo() {
		if (this.saldo == null)
			this.saldo = 0F;
		return saldo;
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	private Float saldo;

	public void depositar(Float valor) {
		this.saldo = this.saldo == null ? valor : (this.saldo + valor);
	}

	public void sacar(Float valor) {
		this.saldo = this.saldo == null ? 0 - valor : (this.saldo - valor);
	}

}
