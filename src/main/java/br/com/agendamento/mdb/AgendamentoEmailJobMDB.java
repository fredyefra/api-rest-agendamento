package br.com.agendamento.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.agendamento.entity.AgendamentoEmail;
import br.com.agendamento.service.AgendamentoEmailService;

@MessageDriven(activationConfig = {
	 @ActivationConfigProperty(propertyName = "destinationLookup", 
			 propertyValue = "java:/jms/queue/EmailQueue"),
	 @ActivationConfigProperty(propertyName = "destinationType",
	 propertyValue = "javax.jms.Queue")
})
public class AgendamentoEmailJobMDB implements MessageListener {

	@Inject
	private AgendamentoEmailService service;

	@Override
	public void onMessage(Message message) {
	try {
	AgendamentoEmail agendamentoEmail =	message.getBody(AgendamentoEmail.class);
	    service.sendEmailFake(agendamentoEmail);
	
	} catch (JMSException e) {
		throw new RuntimeException(e);
	}
	
		
	}

	
	
	
}
