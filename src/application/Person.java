package application;

import net.jini.core.entry.Entry;

public class Person implements Entry {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String nome;
	public String nickname;
	public Double lat;
	public Double lon;
	public Boolean ON;
	
	public Person() {}
	
	public Person(String nome, String nickname, Double lat, Double lon, Boolean ON) {
		super();
		this.nome = nome;
		this.nickname = nickname;
		this.lat = lat;
		this.lon = lon;
		this.ON = ON;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	public Boolean getON() {
		return ON;
	}

	public void setON(Boolean ON) {
		this.ON = ON;
	}

	@Override
	public String toString() {
		return "Person [nome=" + nome + ", nickname=" + nickname + ", lat=" + lat + ", lon=" + lon + ", ON=" + ON + "]";
	}
}
