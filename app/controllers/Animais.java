package controllers;

import java.util.List;

import models.Animal;
import models.Atendimento;
import models.Funcionario;
import models.Status;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Animais extends Controller {
	
	public static void form() {
		List<Atendimento> atendimentos = Atendimento.findAll();
		render(atendimentos);
	}
	
	public static void dashboard(String termo) {
		 String login = session.get("usuarioLogado");
		    if (login != null) {
		    	// Recupera o funcionário logado 
		    	Funcionario a = Funcionario.find("login = ?1", login).first();
		    	
		    	List<Animal> animais = null;
				if (termo == null) {
					animais = Animal.find("status = ?1", Status.ATIVO).fetch();
				} else {
					animais = Animal.find("(lower(nome) like ?1 or tutor like ?1) AND status = ?2",
							"%" + termo.toLowerCase() + "%",Status.ATIVO).fetch();
				}
		    	
		    	
		    	render(a, animais, termo); // Passa o objeto 'a' para a view
		    	
		    	 renderTemplate("Animais/dashboard.html");
		    } else {
		        flash.error("Você precisa estar logado para acessar o dashboard.");
		       
		    }
		}
	
	
	public static void salvar(Animal a, List<Long> atendimentosIds) {
		if (atendimentosIds != null) {
	        List<Atendimento> atendimentos = Atendimento.find("id IN (?1)", atendimentosIds).fetch();
	        a.atendimentos = atendimentos;
	    }

	    String mensagem = "Cadastrado com sucesso!";
	    if (a.id != null) {
	        mensagem = "Editado com sucesso!";
	    }

	    a.save();

	    flash.success(mensagem);
	    listar(null);
	}

	public static void listar(String termo) {
		List<Animal> animais = null;
		if (termo == null) {
			animais = Animal.find("status = ?1", Status.ATIVO).fetch();
		} else {
			animais = Animal.find("(lower(nome) like ?1 or tutor like ?1) AND status = ?2",
					"%" + termo.toLowerCase() + "%",Status.ATIVO).fetch();
		}
		render(animais, termo);
	}
	
	
	public static void remover(Long id) {
		Animal a = Animal.findById(id);
		a.status = Status.INATIVO;
		a.save();
		flash.success("Removido com sucesso!");
		listar(null);
		
	}
	
	public static void editar(Long id) {
		Animal a = Animal.findById(id);
		List<Atendimento> atendimentos = Atendimento.findAll();
		renderTemplate("Animais/form.html", a, atendimentos);
	}
	
	
}