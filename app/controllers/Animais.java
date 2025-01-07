package controllers;

import java.util.List;

import models.Animal;
import models.Atendimento;
import models.Status;
import play.mvc.Controller;

public class Animais extends Controller {

	public static void form() {
		List<Atendimento> atendimentos = Atendimento.findAll();
		render(atendimentos);
	}

	public static void salvar(Animal a, List<Long> atendimentosIds) {
		String mensagem = null;

		Animal animalDuplicado = Animal.find(
				"nome = ?1 AND idade = ?2 AND raca = ?3 AND especie = ?4 AND peso = ?5 AND sexo = ?6 AND tutor = ?7 AND status = ?8",
				a.nome, a.idade, a.raca, a.especie, a.peso, a.sexo, a.tutor, Status.ATIVO).first();

		// Verificar duplicidade
		if (animalDuplicado != null && a.id == null) {
			flash.error("Já existe um animal com os mesmos dados cadastrados!"); // Exibir mensagem de erro no flash
			listar(null);
		}

		if (atendimentosIds != null) {
			List<Atendimento> atendimentos = Atendimento.find("id IN (?1)", atendimentosIds).fetch();
			a.atendimentos = atendimentos;

		}

		mensagem = "Cadastrado com sucesso!";
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
					"%" + termo.toLowerCase() + "%", Status.ATIVO).fetch();
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