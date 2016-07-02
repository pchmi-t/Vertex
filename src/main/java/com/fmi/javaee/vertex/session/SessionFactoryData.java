package com.fmi.javaee.vertex.session;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class SessionFactoryData {

	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration config = new Configuration();
			sessionFactory = config.configure().buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Error in while initialize SessionFactory object."
					+ e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void closeSession(final Session session) {
		if ( session != null) {
			final Transaction tx = session.getTransaction();
			if ( tx != null && tx.isActive() 
					&& TransactionStatus.COMMITTING.equals(tx.getStatus())) {
				tx.rollback();
			}
			session.close();
		}
	}
}
