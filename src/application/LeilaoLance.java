package application;

import java.io.Serializable;

public class LeilaoLance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id;
	String nome;
	String valor;
	
	public LeilaoLance(Integer id, String nome, String valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return this.nome +" - "+ this.valor;
	}
}
