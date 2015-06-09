package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artikelgroepen")
public class Artikelgroep implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String naam;
	@OneToMany(mappedBy = "artikelgroep")
	private Set<Artikel> artikels;

	public Artikelgroep(long id, String naam) {
		this.id = id;
		this.naam = naam;
		artikels = new LinkedHashSet<>();
	}

	public Artikelgroep() {
	}

	public void addArtikel(Artikel artikel) {
		artikels.add(artikel);
		if (artikel.getArtikelgroep() != this) {
			artikel.setArtikelgroep(this);
		}
	}

	public void removeArtikel(Artikel artikel) {
		artikels.remove(artikel);
		if (artikel.getArtikelgroep() == this) {
			artikel.setArtikelgroep(null);
		}
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Set<Artikel> getArtikels() {
		return Collections.unmodifiableSet(artikels);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Artikelgroep)) {
			return false;
		}
		return ((Artikelgroep) obj).naam.equalsIgnoreCase(this.naam);
	}

	@Override
	public int hashCode() {
		return naam.toUpperCase().hashCode();
	}
}
