package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity
public class Animal extends Model{
	
	public String nome;
	public int idade;
	public String raca;
	public String especie;
	public double peso;
	public String sexo;
	public String tutor; 
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Animal() {
		this.status = Status.ATIVO;
	}
	
	@ManyToMany
	public List<Atendimento> atendimentos;

}
