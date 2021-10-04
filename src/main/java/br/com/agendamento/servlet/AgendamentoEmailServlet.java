package br.com.agendamento.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agendamento.entity.AgendamentoEmail;
import br.com.agendamento.service.AgendamentoEmailService;

//@WebServlet("emails")
public class AgendamentoEmailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private AgendamentoEmailService service;
    
	@Inject
	private AgendamentoEmail agendamentoEmail; 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter writer = resp.getWriter();
		
		service.findAll().
		forEach(resultado-> writer.print("Emails(BUILD): " + resultado.getEmail() + "Assunto(BUILD): " + resultado.getAssunto()));
		
	}
	
	
	@Override
	//email,assunto,mensagem
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BufferedReader reader = req.getReader();
	    String [] emails = reader.readLine().split(",");
	    agendamentoEmail.setEmail(emails[0]);
	    agendamentoEmail.setAssunto(emails[1]);
	    agendamentoEmail.setMensagem(emails[2]);
	
	    service.save(agendamentoEmail);
	}
}