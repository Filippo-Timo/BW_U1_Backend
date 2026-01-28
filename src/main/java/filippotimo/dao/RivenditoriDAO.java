package filippotimo.dao;

import filippotimo.Application;
import filippotimo.entities.Rivenditore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class RivenditoriDAO {
	private final EntityManagerFactory emf = Application.getEntityManagerFactory();
	public void save(Rivenditore r) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		/* inizio transazione db*/
		t.begin();
		/* salva rinvednti*/
		em.persist(r);

		t.commit();
		em.close();
	}

	public Rivenditore findById(long id) {
		EntityManager em = emf.createEntityManager();
		Rivenditore r = em.find(Rivenditore.class, id);
		em.close();
		return r;
	}
}
