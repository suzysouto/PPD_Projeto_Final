package application;

import java.io.Serializable;

public class LeilaoItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String nome;
	String valor;
	String descricao;
	
	public LeilaoItem(String nome, String valor, String descricao) {
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "LeilaoItem [nome=" + nome + ", valor=" + valor + ", descricao=" + descricao + "]";
	}
	
	

}
