package br.com.agendamento.util;

import org.hibernate.Session;
import org.junit.Test;

public class HibernateUtilTest {
	
	@Test
	public void conectar() {
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaSessoes().close();
	}
}
