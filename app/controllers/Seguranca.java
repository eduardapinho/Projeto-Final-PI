package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	@Before
	static void verificarAutenticacao() {
		if (!session.contains("usuarioLogado")) {
			flash.error("Por favor realize login");
			Logins.form();
		
		
	} else {
        String nivelAcesso = session.get("nivelAcesso");
        // Verifica se a URL solicitada é permitida de acordo com o nível de acesso
        if (request.controller.equals("Funcionarios") && !nivelAcesso.equals("ADMIN")) {
            flash.error("Você não tem permissão para acessar esta página.");
            Animais.dashboard(null); // ou outra página de sua escolha
        } else if (request.controller.equals("Animais") && nivelAcesso.equals("ADMIN")) {
            // Administrador não deve acessar o dashboard
        
           Funcionarios.form(); 
        }
	}

}
	
}