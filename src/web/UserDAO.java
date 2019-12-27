package web;

import java.util.List;
import java.util.Optional;


import org.hibernate.Session;
import org.hibernate.Transaction;

import model.User;
import util.HibernateUtil;

public class UserDAO {

	@SuppressWarnings("unchecked")
	public static User findUser(String userName, String password) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		List<User> listUsers = null;
		
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start transaction
				transaction = session.beginTransaction();
				listUsers = session.createQuery("from User").list();
				// commit transaction
				transaction.commit();
			} catch (Exception e) {

				e.printStackTrace();
			}
		
		
		Optional<User> op = listUsers.stream()
				.filter(u -> u.getEmail().equalsIgnoreCase(userName) 
						&& u.getPassword().equals(password)).findFirst();

		if (op.isPresent()) {
			return op.get();
		}

		return null;
	}

	

}
