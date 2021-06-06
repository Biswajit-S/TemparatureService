/**
 * 
 */
package com.example.temperature.database;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.example.temperature.constants.StatusCodes;
import com.example.temperature.exceptions.ServerErrException;

/**
 * Create a Database connection and returns a session instance for DB transactions.
 * 
 * USes a MySQL DB for this task. The Configuration is hard coded in hibernate.cfg.xml
 * 
 * @author Biswajit_Sahoo
 *
 */

// Future Considerations - Performance Improvement. Make it Singleton?? 
public class DbServerConnection {
	
	final static public Logger LOGGER = Logger.getLogger(DbServerConnection.class);
	
	/**
	 * @return Session
	 *
	 */
	public Session getSession() {
		Session session= null;
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		LOGGER.debug("StandardServiceRegistry is created" + registry);

		try {
			SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			LOGGER.debug("sessionFactory is created " + sessionFactory);
			session = sessionFactory.openSession();
		} catch (Exception ex) {
			LOGGER.debug("Error creating a session");
			StandardServiceRegistryBuilder.destroy(registry);
			throw new ServerErrException(StatusCodes.CODE_DB_CONN_ERROR, StatusCodes.MSSG_DB_CONN_ERROR);
		}
		
		return session;
		
	}

}
