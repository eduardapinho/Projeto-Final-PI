package controllers;

import java.util.List;


import models.Funcionario;
import models.Status;
import play.mvc.Controller;

public class Funcionarios extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void salvar(Funcionario funcionario) {
		String mensagem = "Cadastrado com sucesso!";
		if (funcionario.id != null) {
			mensagem = "Editado com sucesso!";
		}
		funcionario.nome = funcionario.nome.toUpperCase();
		funcionario.email = funcionario.email.toLowerCase();
		funcionario.save();
		flash.success(mensagem);
		listar(null);
	}
	
	public static void remover(Long id) {
		Funcionario f = Funcionario.findById(id);
		f.status = Status.INATIVO;
		f.save();
		flash.success("Removido com sucesso!");
		listar(null);
	}

	public static void listar(String termo) {
		List<Funcionario> funcionarios = null;
		if (termo == null) {
			funcionarios = Funcionario.find("status = ?1", Status.ATIVO).fetch();
		} else {
			funcionarios = Funcionario.find("(lower(nome) like ?1 or email like ?1) AND status = ?2",
					"%" + termo.toLowerCase() + "%", Status.ATIVO).fetch();
		}
		render(funcionarios, termo);
	}

	public static void editar(Long id) {
		Funcionario f = Funcionario.findById(id);
		renderTemplate("Funcionario/form.html", f);
	}

}
