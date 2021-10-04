package br.com.agendamento.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.agendamento.entity.AgendamentoEmail;
import br.com.agendamento.service.AgendamentoEmailService;

@Path("emails")
public class AgendamentoEmailResource {

	@Inject
	private AgendamentoEmailService service;

	@GET
	@Produces(value = javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response findAll() {
		return Response.ok(service.findAll()).build();
	}

	@POST
	@Consumes(value = javax.ws.rs.core.MediaType.APPLICATION_JSON) // email,assunto,mensagem
	public Response save(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(false);
		service.save(agendamentoEmail);
		return Response.status(201).build();
	}

	/*
	 * {
	 * 
	 * "email": "ALTERADO - Duvida WEB SERVICE", "assunto":
	 * "NOVO - Acesso ao banco de Dados Mysql", "mensagem": "testando"
	 * 
	 * }
	 */

}