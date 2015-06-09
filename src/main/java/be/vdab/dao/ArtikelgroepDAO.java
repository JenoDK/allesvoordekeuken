package be.vdab.dao;

import java.util.List;

import be.vdab.entities.Artikelgroep;

public class ArtikelgroepDAO extends AbstractDAO {

	public Artikelgroep read(long id) {
		return getEntityManager().find(Artikelgroep.class, id);
	}

	public List<Artikelgroep> findAll() {
		return getEntityManager().createNamedQuery("Artikelgroep.findAll",
				Artikelgroep.class).getResultList();
	}
}
