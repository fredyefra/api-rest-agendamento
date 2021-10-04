package br.com.agendamento.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.agendamento.dao.AgendamentoDAO;
import br.com.agendamento.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailService {

	private final static Logger log = Logger.getLogger(AgendamentoEmail.class.getName());
	
	@Inject
	private AgendamentoDAO dao;

	public List<AgendamentoEmail> findAll() {

		List<AgendamentoEmail> agendamentos=  dao.findAll();

		return agendamentos;
	}

	public void save(AgendamentoEmail agendamentoEmail ) {
		agendamentoEmail.setAgendado(false);
		dao.save(agendamentoEmail);
	} 


	public List<AgendamentoEmail> findAllNotSend() {
		return dao.findAllNotSend();
	}

	public void update(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(true);
		dao.update(agendamentoEmail);
	}

	public void sendEmailFake(AgendamentoEmail agendamentoEmail) {
		try {
			Thread.sleep(5000); // 5 segundos
		     log.info("O email foi enviado para: " + agendamentoEmail.getEmail().toString() + " agendamento realizado com sucesso.");
		     
		} catch (InterruptedException e) {
			log.warning(e.getMessage());
		}
	}
}