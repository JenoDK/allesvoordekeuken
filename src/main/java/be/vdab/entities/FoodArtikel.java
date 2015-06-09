package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {
	private static final long serialVersionUID = 1L;
	private int houdbaarheid;

	public FoodArtikel() {

	}

	public FoodArtikel(String naam, BigDecimal aankoopprijs,
			BigDecimal verkoopprijs, int houdbaarheid, Artikelgroep artikelgroep) {
		super(naam, aankoopprijs, verkoopprijs, artikelgroep);
		setHoudbaarheid(houdbaarheid);
	}

	public int getHoudbaarheid() {
		return houdbaarheid;
	}

	public void setHoudbaarheid(int houdbaarheid) {
		this.houdbaarheid = houdbaarheid;
	}

}
