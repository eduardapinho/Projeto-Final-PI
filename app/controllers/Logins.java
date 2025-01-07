package controllers;

import models.Funcionario;
import play.mvc.Controller;

	public class Logins extends Controller {
	
		public static void form() {
			render();
		}
		
		public static void logar(String username, String senha) {
			String usuarioLogado = Funcionario.autenticar(username, senha);
			if (usuarioLogado == null) {
				//USUARIO NAO ENCONTRADO NO BANCO
				flash.error("Credenciais inválidas");
				form();
			} else {
				//SOMENTE USUARIO QUE FORAM ENCONTRADOS NO BANCO
				session.put("usuarioLogado", usuarioLogado);
				Animais.dashboard();
			}
		}
		
		public static void sair() {
			session.clear();
			flash.success("Você saiu do sistema");
			form();
		}

	}

	

