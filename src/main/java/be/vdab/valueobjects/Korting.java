package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Korting implements Serializable {
	private static final long serialVersionUID = 1L;
	private int vanafAantal;
	private BigDecimal kortingspercentage;

	public Korting(int vanafAantal, BigDecimal kortingspercentage) {
		super();
		this.vanafAantal = vanafAantal;
		this.kortingspercentage = kortingspercentage;
	}

	public Korting() {
	}

	public int getVanafAantal() {
		return vanafAantal;
	}

	public BigDecimal getKortingspercentage() {
		return kortingspercentage;
	}

	@Override
	public String toString() {
		return "Vanaf aantal: " + vanafAantal + " Kortingspercentage: "
				+ kortingspercentage.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((kortingspercentage == null) ? 0 : kortingspercentage
						.hashCode());
		result = prime * result + vanafAantal;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korting other = (Korting) obj;
		if (kortingspercentage == null) {
			if (other.kortingspercentage != null)
				return false;
		} else if (!kortingspercentage.equals(other.kortingspercentage))
			return false;
		if (vanafAantal != other.vanafAantal)
			return false;
		return true;
	}

}
