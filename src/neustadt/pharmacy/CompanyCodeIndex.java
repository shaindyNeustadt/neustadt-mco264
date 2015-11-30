package neustadt.pharmacy;

import java.io.Serializable;

public class CompanyCodeIndex implements Comparable<CompanyCodeIndex>,
		Serializable {

	private static final long serialVersionUID = 1L;
	private String companyCode;
	private Long recLocation;
	private boolean isActive;

	public CompanyCodeIndex(String companyCode, Long location) {
		this.companyCode = companyCode;
		this.recLocation = location;
		this.isActive = true;
	}

	public String getCompanyCode() {
		return this.companyCode;
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

	public int compareTo(CompanyCodeIndex cci) {
		return this.companyCode.compareToIgnoreCase(cci.companyCode);
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o instanceof CompanyCodeIndex) {
			CompanyCodeIndex cci = (CompanyCodeIndex) o;
			return (this.companyCode.compareTo(cci.companyCode)) == 0;
		} else
			return false;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nCompany Code: ");
		builder.append(companyCode);
		builder.append(" Location: ");
		builder.append(recLocation);
		return builder.toString();
	}
}