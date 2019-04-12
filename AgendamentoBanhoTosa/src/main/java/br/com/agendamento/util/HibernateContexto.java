package br.com.agendamento.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//Classe para carregar o Hibernate ao subir o Servidor tomcat
public class HibernateContexto implements ServletContextListener{

	//ao ligar tomcat
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
		HibernateUtil.getFabricaSessoes();
	}

	//ao desligar tomcat
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
		HibernateUtil.getFabricaSessoes().close();;
		
	}

}
