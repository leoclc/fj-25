package br.com.caelum.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

@Entity
public class Tag {
	
	
	@Id @GeneratedValue
	private int id;
	private String nome;

	@Override
	public String toString() {
		return nome;
	}
	@Field(index=Index.TOKENIZED)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
}
