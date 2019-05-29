package br.com.agendamento.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.agendamento.domain.Agendamento;
import br.com.agendamento.util.HibernateUtil;

public class AgendamentoDAO extends GenericDAO<Agendamento> {
	
	
	@SuppressWarnings("unchecked")
	public List<Agendamento> verificaAnimal(Long cod, Date data) {
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Agendamento.class);
			consulta.add(Restrictions.eq("codigo", cod));
			consulta.add(Restrictions.eq("dataAtendimento", data));
			// consulta.add(Restrictions.sqlRestriction("SELECT * FROM agendamentos WHERE codigo
			// <> "+ id +""));
			List<Agendamento> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
