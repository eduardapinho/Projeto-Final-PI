package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Atendimento extends Model{

	public String nome;
	public String especializacao;
	
	@Override
	public String toString() {
		return nome + " - " + especializacao;
	}
}
		