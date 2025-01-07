package jobs;

import models.Atendimento;
import models.Funcionario;
import models.NivelAcesso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.Animal;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		if (Atendimento.count() == 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			Atendimento a1 = new Atendimento();
			a1.nome = "Luísa Souza";
			a1.especializacao = "Banho & Tosa";
			a1.data = sdf.parse("20/01/25"); // Converte a string para Date
			a1.save();
			
			Atendimento a2 = new Atendimento();
			a2.nome = "Patricia Silva";
			a2.especializacao = "Cauterização";
			a2.data = sdf.parse("27/01/25"); // Converte a string para Date
			a2.save();
			
			Atendimento a3 = new Atendimento();
			a3.nome = "Mauro Fernando";
			a3.especializacao = "Penteados e Tonalizadores";
			a3.data = sdf.parse("30/01/25"); // Converte a string para Date
			a3.save();
			

			Funcionario f1 = new Funcionario();
			f1.nome = "Cristina";
			f1.email = "cristina11@gmail.com";
			f1.login = "cristina";
			f1.senha = "1234";
			f1.nivelAcesso = NivelAcesso.RECEPCIONISTA;
			f1.save();
			
			Funcionario f2 = new Funcionario();
			f2.nome = "Welt";
			f2.email = "weltluocha234@gmail.com";
			f2.login = "welt";
			f2.senha = "1234";
			f2.nivelAcesso = NivelAcesso.ADMIN;
			f2.save();
			
			Animal a01 = new Animal();
			a01.nome = "Titi";
			a01.idade = 4;
			a01.peso = 22;
			a01.raca = "vira lata";
			a01.especie = "cachorro";
			a01.sexo = "M";
			a01.tutor = "Kafka";
			// Atribuindo a lista de atendimentos ao animal
			a01.atendimentos = new ArrayList<Atendimento>();   // Inicializa a lista
			a01.atendimentos.add(a1); // Adiciona o atendimento à lista
			a01.save();
			
			Animal a02 = new Animal();
			a02.nome = "Rappa";
			a02.idade = 9;
			a02.peso = 28;
			a02.raca = "vira lata";
			a02.especie = "cachorro";
			a02.sexo = "F";
			a02.tutor = "Asta";
			// Atribuindo a lista de atendimentos ao animal
			a02.atendimentos = new ArrayList<Atendimento>();   // Inicializa a lista
			a02.atendimentos.add(a2); // Adiciona o atendimento à lista
			a02.save();
			



			
			
		}
	}

}
