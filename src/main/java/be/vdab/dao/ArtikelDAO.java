package be.vdab.dao;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Artikel;

public class ArtikelDAO extends AbstractDAO {
	public Artikel read(long id) {
		return getEntityManager().find(Artikel.class, id);
	}

	public void create(Artikel artikel) {
		getEntityManager().persist(artikel);
	}

	public List<Artikel> findByNameLike(String naam, int vanafRij,
			int aantalRijen) {
		return getEntityManager()
				.createNamedQuery("Artikel.findByNameLike", Artikel.class)
				.setParameter("naam", "%" + naam + "%")
				.setFirstResult(vanafRij).setMaxResults(aantalRijen)
				.getResultList();
	}

	public void algemeneOpslag(BigDecimal factor) {
		getEntityManager().createNamedQuery("Artikel.algemenePrijsverhoging")
				.setParameter("factor", factor).executeUpdate();
	}

	public List<Artikel> findAll(int vanafRij, int aantalRijen) {
		return getEntityManager()
				.createNamedQuery("Artikel.findAll", Artikel.class)
				.setFirstResult(vanafRij).setMaxResults(aantalRijen)
				.getResultList();
	}

	public List<Artikel> findAll() {
		return getEntityManager()
				.createNamedQuery("Artikel.findAll", Artikel.class)
				.setHint(
						"javax.persistence.loadgraph",
						getEntityManager().createEntityGraph(
								"Artikel.metArtikelgroep")).getResultList();
	}

}