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
				flash.success("Credenciais inválidas");
				form();
			} else {
				// Encontra o funcionário logado e salva o nível de acesso na sessão
		        Funcionario funcionario = Funcionario.find("login = ?1", username).first();
		        
				//SOMENTE USUARIO QUE FORAM ENCONTRADOS NO BANCO
				session.put("usuarioLogado", usuarioLogado);
		        session.put("nivelAcesso", funcionario.nivelAcesso.toString());  // Salva o ENUM na sessão
		    
	                Animais.dashboard(null);
	            }
			}
		
		
		public static void sair() {
			session.clear();
			flash.success("Você saiu do sistema");
			form();
		}

	}

	

