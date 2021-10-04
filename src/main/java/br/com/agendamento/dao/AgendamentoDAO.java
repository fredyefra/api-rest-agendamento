package br.com.agendamento.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.agendamento.entity.AgendamentoEmail;
import br.com.agendamento.entity.AgendamentoEmail_;


@Stateless
public class AgendamentoDAO {

	@PersistenceContext(name = "agendamentoDS")
	private EntityManager em;

	public List<AgendamentoEmail> findAll() {

		/*
		 * List<AgendamentoEmail> resultado =
		 * em.createQuery("SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class
		 * ).getResultList();
		 * 
		 * return resultado;
		 */

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AgendamentoEmail> cq = cb.createQuery(AgendamentoEmail.class);
		Root<AgendamentoEmail> agendamentoEmail = cq.from(AgendamentoEmail.class);

		cq.select(agendamentoEmail);
		TypedQuery<AgendamentoEmail> query = em.createQuery(cq);

		return query.getResultList();

	}

	public List<AgendamentoEmail> findAllNotSend() {

		/*
		 * List<AgendamentoEmail> resultado = em
		 * .createQuery("SELECT ae FROM AgendamentoEmail ae WHERE ae.agendado = false",
		 * AgendamentoEmail.class) .getResultList();
		 * 
		 * 
		 * 
		 * 
		 * return resultado;
		 */
       
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AgendamentoEmail> cq = cb.createQuery(AgendamentoEmail.class);
		Root<AgendamentoEmail> root = cq.from(AgendamentoEmail.class);

		cq.select(root).where(cb.equal(root.get(AgendamentoEmail_.agendado), false));
		
		TypedQuery<AgendamentoEmail> query = em.createQuery(cq);

		return query.getResultList();
	
	
	}

	public void save(AgendamentoEmail agendamentoEmail) {
		em.persist(agendamentoEmail);
	}

	public void update(AgendamentoEmail agendamentoEmail) {
		em.merge(agendamentoEmail);
	}
}