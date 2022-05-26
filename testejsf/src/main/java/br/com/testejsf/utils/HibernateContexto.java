package br.com.testejsf.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getFabricaDeSessoes().close();
		
		
	}

	public void contextInitialized(ServletContextEvent event ) {
		HibernateUtil.getFabricaDeSessoes();
		
	}

}
