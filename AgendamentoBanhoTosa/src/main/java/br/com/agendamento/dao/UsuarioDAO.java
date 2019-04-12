package br.com.agendamento.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.agendamento.domain.Usuario;
import br.com.agendamento.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);

			consulta.add(Restrictions.eq("usuario", login));
			consulta.add(Restrictions.eq("senha", senha));

			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	// lista
	@SuppressWarnings("unchecked")
	public List<Usuario> verificaCpf(String cpf) {
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("cpf", cpf));
			//consulta.add(Restrictions.sqlRestriction("SELECT * FROM usuarios WHERE codigo <> "+ id +""));
			List<Usuario> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	// lista
		@SuppressWarnings("unchecked")
		public List<Usuario> verificaUsuario(String usuario) {
			Session sessao = HibernateUtil.getFabricaSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(Usuario.class);
				consulta.add(Restrictions.eq("usuario", usuario));
				//consulta.add(Restrictions.sqlRestriction("SELECT * FROM usuarios WHERE codigo <> "+ id +""));
				List<Usuario> resultado = consulta.list();
				return resultado;

			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}

}
