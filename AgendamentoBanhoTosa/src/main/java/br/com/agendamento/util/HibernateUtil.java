package br.com.agendamento.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

//Classe que vai criar a sessão cada vez que precisar acessar o bd
public class HibernateUtil {
	
	//objeto que cria a fabrica de sessoes
	private static SessionFactory FabricaSessoes = CriarFabricaSessoes();
	
	public static SessionFactory getFabricaSessoes() {
		return FabricaSessoes;
	}
	
	private static SessionFactory CriarFabricaSessoes() {
		try {
			Configuration configuracao =  new Configuration().configure();
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			return fabrica;
		}
		catch(Throwable ex) {
			System.err.println("A fábrica de sessões não pode ser criada. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
