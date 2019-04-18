package br.com.agendamento.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.agendamento.domain.Servico;
import br.com.agendamento.util.HibernateUtil;

public class ServicoDAO extends GenericDAO<Servico> {

	@SuppressWarnings("unchecked")
	public List<Servico> verificaNome(String tipoServico) {
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Servico.class);
			consulta.add(Restrictions.eq("tipoServico", tipoServico));
			List<Servico> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
