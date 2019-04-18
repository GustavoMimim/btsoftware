package br.com.agendamento.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.agendamento.domain.Cliente;
import br.com.agendamento.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente> {

	@SuppressWarnings("unchecked")
	public List<Cliente> verificaCpf(String cpf) {
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			consulta.add(Restrictions.eq("cpf", cpf));
			// consulta.add(Restrictions.sqlRestriction("SELECT * FROM usuarios WHERE codigo
			// <> "+ id +""));
			List<Cliente> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
