package br.com.agendamento.main;

import org.hibernate.Session;
import br.com.agendamento.util.HibernateUtil;

public class HibernateUtilTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaSessoes().close();

	}

}
