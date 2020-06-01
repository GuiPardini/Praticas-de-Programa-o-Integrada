package model;

import java.io.Serializable;

public class Paises implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private long populacao;
	private double area;

	public Paises(int id, String nome, long populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}

	public Paises() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	@Override
	public String toString() {
		return "Paises [id= " + id + ", nome= " + nome + ", populacao= " + populacao + ", area= " + area + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paises other = (Paises) obj;

		if (area == 0.0) {
			if (other.area != 0.0)
				return false;
		}

		if (populacao == 0) {
			if (other.populacao != 0)
				return false;
		}

		if (id != other.id)
			return false;

		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;

		return true;
	}
}
