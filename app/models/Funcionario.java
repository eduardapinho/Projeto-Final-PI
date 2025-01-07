package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Funcionario extends Model{

	public String nome;
	public String email;

	public String login;
	public String senha;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Funcionario() {
		this.status = Status.ATIVO;
	}
	
	public static String autenticar(String login, String senha) {
		Funcionario f = Funcionario.find("login = ?1 and senha = ?2", login, senha).first();
		if (f == null) {
			return null;
		} else {
			return f.login;
		}
	}
}
