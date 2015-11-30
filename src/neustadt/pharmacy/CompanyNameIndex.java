package neustadt.pharmacy;

import java.io.Serializable;

public class CompanyNameIndex implements Comparable<CompanyNameIndex>,
		Serializable {

	private static final long serialVersionUID = 1L;
	private String companyName;
	private Long recLocation;
	private boolean isActive;

	public CompanyNameIndex(String companyName, Long location) {
		this.companyName = companyName;
		this.recLocation = location;
		this.isActive = true;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public Long getLocation() {
		return this.recLocation;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive() {
		this.isActive = false;
	}

	public int compareTo(CompanyNameIndex cni) {
		return this.companyName.compareToIgnoreCase(cni.companyName);
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o instanceof CompanyNameIndex) {
			CompanyNameIndex cci = (CompanyNameIndex) o;
			return (this.companyName.compareTo(cci.companyName)) == 0;
		} else
			return false;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nCompany Name: ");
		builder.append(companyName);
		builder.append(" Location: ");
		builder.append(recLocation);
		return builder.toString();
	}
}
