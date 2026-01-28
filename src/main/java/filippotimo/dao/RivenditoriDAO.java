package filippotimo.dao;

import filippotimo.Application;
import filippotimo.entities.Rivenditore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class RivenditoriDAO {
	private final EntityManagerFactory emf = Application.getEntityManagerFactory();
	private final EntityManager em = emf.createEntityManager();
	public void save(Rivenditore r) {
		EntityTransaction t = em.getTransaction();

		/* inizio transazione db*/
		t.begin();
		/* salva rinvednti*/
		em.persist(r);

		t.commit();
		em.close();
	}

	public Rivenditore findById(long id) {
		Rivenditore r = em.find(Rivenditore.class, id);
		em.close();
		return r;
	}

	public void removeById(long id) {
		EntityTransaction t = em.getTransaction();

		t.begin();
		Rivenditore r = em.find(Rivenditore.class, id);
		if (r != null) {
			em.remove(r);
		}
		t.commit();

		em.close();
	}

	public void remove(Rivenditore r) {
		EntityTransaction t = em.getTransaction();

		t.begin();
		em.remove(r);
		t.commit();

		em.close();
	}

	public List<Rivenditore> findAll() {
		List<Rivenditore> list = 
			em.createQuery("SELECT r FROM Rivenditore r", Rivenditore.class)
			.getResultList();
		em.close();
		return list;
	}
}
