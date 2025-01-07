package jobs;

import models.Atendimento;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		if (Atendimento.count() == 0) {
			Atendimento a1 = new Atendimento();
			a1.nome = "Luísa Souza";
			a1.especializacao = "Banho & Tosa";
			a1.save();
			
			Atendimento a2 = new Atendimento();
			a2.nome = "Patricia Silva";
			a2.especializacao = "Cauterização";
			a2.save();
			
			Atendimento a3 = new Atendimento();
			a3.nome = "Mauro Fernando";
			a3.especializacao = "Penteados e Tonalizadores";
			a3.save();
			
		}
	}

}
