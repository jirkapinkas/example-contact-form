package cz.jiripinkas.example.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import cz.jiripinkas.example.entity.Email;

public class DatabaseService {

	private static EntityManager entityManager;

	static {
		System.out.println("*** start create test database ***");
		entityManager = Persistence.createEntityManagerFactory("example-contact-form").createEntityManager();
		{
			Email email = new Email("from@email.com", "to1@email.com", "hello", "yo, wassup?");
			insert(email);
		}
		{
			Email email = new Email("from@email.com", "to2@email.com", "hello again", "yo, wassup?");
			insert(email);
		}
		{
			Email email = new Email("from@email.com", "to3@email.com", "hello yet again", "yo, wassup?");
			insert(email);
		}
		System.out.println("*** finish create test database ***");
	}

	public static void insert(Email email) {
		entityManager.getTransaction().begin();
		entityManager.persist(email);
		entityManager.getTransaction().commit();
	}

	public static List<Email> listEmails() {
		return entityManager.createQuery("select e from Email e", Email.class).getResultList();
	}

	public static Email getEmail(int emailId) {
		return entityManager.find(Email.class, emailId);
	}

	public static void delete(int emailId) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(Email.class, emailId));
		entityManager.getTransaction().commit();
	}
}
