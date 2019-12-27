package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.User;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	// use this instead of hibernate.cfg.xml file
	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				Properties properties = new Properties();
				// driver
				properties.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
				// url of database
				properties.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/project5?useSSL=false");
				// user
				properties.put(AvailableSettings.USER, "leanh");
				// password
				properties.put(AvailableSettings.PASS, "leanh");
				properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				properties.put(AvailableSettings.SHOW_SQL, true);
				properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				properties.put(AvailableSettings.HBM2DDL_AUTO, "update");
				properties.put(AvailableSettings.HBM2DDL_CREATE_SCHEMAS, true);
				properties.put(AvailableSettings.AUTOCOMMIT, true);
				

				configuration.setProperties(properties);
				
				configuration.addAnnotatedClass(User.class);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
				
			}

		}
		return sessionFactory;
	}
}
