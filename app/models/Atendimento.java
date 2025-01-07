package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity
public class Atendimento extends Model{

	public String nome;
	public String especializacao;
	
	@Override
	public String toString() {
		return nome + " - " + especializacao;
	}
	
	@ManyToMany
	public List<Animal> animais;
}
		