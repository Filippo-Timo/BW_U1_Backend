package filippotimo.dao;

import filippotimo.Application;
import filippotimo.entities.Rivenditore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class RivenditoriDAO {
	private final EntityManagerFactory emf = Application.getEntityManagerFactory();

	public void save(Rivenditore r) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();

		t.begin();
		em.persist(r);
		t.commit();

		em.close();
	}

	public Rivenditore findById(long id) {
		final EntityManager em = emf.createEntityManager();
		final Rivenditore r = em.find(Rivenditore.class, id);
		em.close();
		return r;
	}

	public void removeById(long id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();

		t.begin();
		final Rivenditore r = em.find(Rivenditore.class, id);
		if (r != null) {
			em.remove(r);
		}
		t.commit();

		em.close();
	}

	public void remove(Rivenditore r) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();

		t.begin();
		if (em.contains(r)) {
			em.remove(r);
		}
		t.commit();

		em.close();
	}

	public List<Rivenditore> findAll() {
		final EntityManager em = emf.createEntityManager();
		final List<Rivenditore> list = em
			.createQuery("SELECT r FROM Rivenditore r", Rivenditore.class)
			.getResultList();
		em.close();
		return list;
	}
}
