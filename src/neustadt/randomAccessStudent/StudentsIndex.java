package neustadt.randomAccessStudent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class StudentsIndex implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<StudentIndexRec> index;

	/**
	 * set up an index for the first time
	 * 
	 */
	public StudentsIndex() {
		index = new ArrayList<StudentIndexRec>();

	}

	/**
	 * 
	 * @param studentID
	 * @param fileLocation
	 * @throws Exception
	 *             if duplicate
	 */
	public void addStudentToIndex(Integer studentID, Long fileLocation)
			throws DuplicateDataException {
		if (hasStudent(studentID)) {
			throw new DuplicateDataException();
		}
		index.add(new StudentIndexRec(studentID, fileLocation));
		sortIndex();
	}

	/**
	 * 
	 * @param studentID
	 * @return Long - location of record in the data file
	 * @throws NotFoundException
	 */

	public Long findStudentLocation(Integer studentID) throws NotFoundException{
		int elemNum = findStudentBinSearch(studentID);
		StudentIndexRec indexRec = index.get(elemNum);
		return indexRec.getFileLocation();
	}

	/**
	 * 
	 * @param studentID
	 * @return element number of the studentindexrec in the array
	 * @throws NotFoundException
	 */
	public int findStudent(Integer studentID) throws NotFoundException {
		return findStudentBinSearch(studentID);
	}

	/**
	 * 
	 * @param studentID
	 * @return true if studentid appears in the index array
	 */

	public boolean hasStudent(Integer studentID) {
		return this.index.contains(studentID);
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		int elemNum = findStudentBinSearch(studentID);
		index.remove(elemNum);
	}

	private void sortIndex() {
	Collections.sort(index);
	}

	private int findStudentBinSearch(Integer studentID) throws NotFoundException {
		StudentIndexRec dummyRec = new StudentIndexRec(studentID, 0L);
		int elemNum = Collections.binarySearch(this.index, dummyRec);
		if (elemNum >= 0) {
			return elemNum;
		}
		throw new NotFoundException();
	}
}