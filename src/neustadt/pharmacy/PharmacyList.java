package neustadt.pharmacy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class PharmacyList implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<CompanyCodeIndex> companyCodes;
	private ArrayList<CompanyNameIndex> companyNames;
	transient private RandomAccessFile companyFile;

	public PharmacyList() {
		this.companyCodes = new ArrayList<CompanyCodeIndex>();
		this.companyNames = new ArrayList<CompanyNameIndex>();
	}

	public void connectToData(String fileName) throws FileNotFoundException {
		this.companyFile = new RandomAccessFile(fileName, "rw");
	}

	public void closeFile() throws IOException {
		companyFile.close();
	}

	public void addCompany(PharmaceuticalCo pc) throws DuplicateDataException,
			IOException {
		if (companyCodes
				.contains(new CompanyCodeIndex(pc.getCompanyCode(), 0L))
				|| companyNames.contains(new CompanyNameIndex(pc
						.getCompanyName(), 0L))) {
			throw new DuplicateDataException();
		}
		Long location = companyFile.length();
		pc.writeToFile(companyFile, location);
		companyCodes.add(new CompanyCodeIndex(pc.getCompanyCode(), location));
		companyNames.add(new CompanyNameIndex(pc.getCompanyName(), location));
		Collections.sort(companyCodes);
		Collections.sort(companyNames);
	}

	public void addCompany(String companyCode, String companyName,
			String phoneNumber) throws DuplicateDataException, IOException {
		if (companyCodes.contains(new CompanyCodeIndex(companyCode, 0L))
				|| companyNames.contains(new CompanyNameIndex(companyName, 0L))) {
			throw new DuplicateDataException();
		}
		PharmaceuticalCo pc = new PharmaceuticalCo(companyCode, companyName,
				phoneNumber);
		Long location = companyFile.length();
		pc.writeToFile(companyFile, location);
		companyCodes.add(new CompanyCodeIndex(companyCode, location));
		companyNames.add(new CompanyNameIndex(companyName, location));
		Collections.sort(companyCodes);
		Collections.sort(companyNames);
	}

	public PharmaceuticalCo findCompanyCode(String companyCode)
			throws NotFoundException, IOException {
		CompanyCodeIndex index = findCompanyByCode(companyCode);
		if (index.getIsActive()) {
			Long recLocation = index.getLocation();
			return new PharmaceuticalCo(companyFile, recLocation);
		} else {
			throw new NotFoundException();
		}
	}

	public PharmaceuticalCo findCompanyName(String companyName)
			throws NotFoundException, IOException {
		CompanyNameIndex index = findCompanyByName(companyName);
		if (index.getIsActive()) {
			Long recLocation = index.getLocation();
			return new PharmaceuticalCo(companyFile, recLocation);
		} else {
			throw new NotFoundException();
		}
	}

	public void modifyCompanyPhone(String companyCode, String newNumber)
			throws NotFoundException, IOException {
		CompanyCodeIndex index = findCompanyByCode(companyCode);
		if (index.getIsActive()) {
			Long recLocation = index.getLocation();
			PharmaceuticalCo pc = new PharmaceuticalCo(companyFile, recLocation);
			pc.setPhoneNumber(newNumber);
			pc.writeToFile(companyFile, recLocation);
		} else {
			throw new NotFoundException();
		}
	}

	public void removeRecord(String companyCode, String companyName)
			throws NotFoundException {
		CompanyCodeIndex codeIndex = findCompanyByCode(companyCode);
		CompanyNameIndex nameIndex = findCompanyByName(companyName);
		codeIndex.setIsActive();
		nameIndex.setIsActive();
	}

	public ArrayList<CompanyCodeIndex> getCompanyCodes() {
		return new ArrayList<CompanyCodeIndex>(companyCodes);
	}

	public RandomAccessFile getFile() {
		return companyFile;
	}

	private CompanyNameIndex findCompanyByName(String companyName)
			throws NotFoundException {
		CompanyNameIndex dummyRec = new CompanyNameIndex(companyName, 0L);
		int elemNum = Collections.binarySearch(companyNames, dummyRec);
		if (elemNum >= 0) {
			return companyNames.get(elemNum);
		}
		throw new NotFoundException();
	}

	private CompanyCodeIndex findCompanyByCode(String companyCode)
			throws NotFoundException {
		CompanyCodeIndex dummyRec = new CompanyCodeIndex(companyCode, 0L);
		int elemNum = Collections.binarySearch(companyCodes, dummyRec);
		if (elemNum >= 0) {
			return companyCodes.get(elemNum);
		}
		throw new NotFoundException();
	}
}