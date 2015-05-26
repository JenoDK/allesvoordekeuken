package be.vdab.dao;

import javax.persistence.EntityManager;

import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

public class ArtikelDAO {
	public Artikel read(long id) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return entityManager.find(Artikel.class, id);
		} finally {
			entityManager.close();
		}
	}
}