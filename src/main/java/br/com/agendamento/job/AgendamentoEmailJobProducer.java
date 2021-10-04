package br.com.agendamento.job;

 import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.agendamento.entity.AgendamentoEmail;
import br.com.agendamento.service.AgendamentoEmailService;

@Singleton
public class AgendamentoEmailJobProducer {

	@Inject
	private AgendamentoEmailService service;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext jmsContetx; //JMSContext createProducer que cria um producer.
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
    @Schedule(hour = "*", minute = "*", second = "*/30")
	public void enviarEmailFake() {
	
	List<AgendamentoEmail> listEmailsNotSend  = service.findAllNotSend();
	
	listEmailsNotSend.forEach(emailNaoEnviado -> {
		//service.sendEmailFake(emailNaoEnivado); Email Fake manter
		jmsContetx.createProducer().send(queue, emailNaoEnviado); //envia o email para a fila para tratar
		service.update(emailNaoEnviado);
	});
	
	}
}