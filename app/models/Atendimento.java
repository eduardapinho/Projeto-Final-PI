package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class Atendimento extends Model{

	public String nome;
	public String especializacao;
	
	@Temporal(TemporalType.DATE)
	public Date data;
	
	@Override
	public String toString() {
		return nome + " - " + especializacao;
	}
	
	@ManyToMany
	public List<Animal> animais;
}
		