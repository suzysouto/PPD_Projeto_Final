package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LeilaoItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String nome;
	String valor;
	String descricao;
	List<LeilaoLance> lances = new ArrayList<LeilaoLance>();
	
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
	
	public List<LeilaoLance> getLanceList() {
		return this.lances;
	}

	public void addLance(LeilaoLance lance) {
		this.lances.add(lance);
	}
	
	public void removeLanceList(Integer id) {
		for (int i = 0; i < this.lances.size(); i++) {
	          if (this.lances.get(i).getId() == id) {
	
	          
	        	  this.lances.remove(i);
	          }
		}
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	public String toStringDetails() {
		return nome+"\n"+valor+"\n"+descricao;
	}

}
