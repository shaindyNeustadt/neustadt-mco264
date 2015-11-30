package neustadt.randomAccessStudent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

public class ManageStudentBalances {
	private StudentsIndex index;
	private String randomFileName;
	
	
	/**
	 * set up a new file with student balance data
	 * @param quantity
	 * @param randomFileName
	 */
	public ManageStudentBalances(String randomFileName){
		index = new StudentsIndex();   //delegate the job to set up a new  index
		this.randomFileName = randomFileName;  //record file where student data will eventually be stored
	}
	
	/**
	 * set up system to be able to process student balance data that has previously been entered and stored on disk
	 * @exception FileNotFoundException
	 * @exception IOException
	 * @param indexFileName
	 * @param randomFileName
	 * 
	 */
	
	public ManageStudentBalances(String indexFileName, String randomFileName)throws ClassNotFoundException,FileNotFoundException,IOException{
		
		ObjectInputStream indexFile = new ObjectInputStream(new FileInputStream(indexFileName));
		index = (StudentsIndex) indexFile.readObject();  //reads in entire index and restructures it in RAM
		indexFile.close();
		
		this.randomFileName = randomFileName;  //no need to read in data, just record the full path of the file
		}
	
	/**
	 * @exception DuplicateDataException - if try adding same Student record more than once
	 * @exception FileNotFoundException  - if cant find file
	 * @exception IOException - if cant seek to the location specified or can't write the data to the file
	 * @param studentID
	 * @param balance
	 * 
	 */
	
	public void addStudentRecord(Integer studentID, Double balance)throws DuplicateDataException,FileNotFoundException,IOException{
		//check to see if this student has already been entered into the system
		if (index.hasStudent(studentID)){
		      throw new DuplicateDataException();
		}
	   else{
			//if not found, then this is good
			//set up a new Student record
			Student studentInfo = new Student(studentID, balance);
			//ask Student record to write itself to the RandomAccessFile
			Long recordLocation = studentInfo.writeStudentRecord(randomFileName);
			//record the record's location to the index					
			index.addStudentToIndex(studentID, recordLocation);
		}
	}
	
	/**
	 * @exception NotFoundException - student data not recorded in system
	 * @exception IOException - file location not found, record couldnt be read from the data file
	 * @FileNotFoundException - data file could not be found
	 * @param studentID
	 * @param amount
	 */
	public void addToStudentBalance(Integer studentID, Double amount)throws
		FileNotFoundException,NotFoundException,IOException{
		Long location = index.findStudentLocation(studentID);
		Student stu = new Student(new RandomAccessFile(randomFileName, "rw"), location);
		stu.addToBalance(amount);
		stu.rewriteStudent(randomFileName, location);
		}
	

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	private Long getStudentFileLocation (Integer id)throws NotFoundException{
		return  index.findStudentLocation(id);
	}
	
	/**
	 * @exception NotFoundException - student data not recorded in the system
	 * @exception FileNotFoundException  - cant find the data file
	 * @exception IOException - can't find the designated location in the data file or cant read the data or data inconsistentcy found in file
	 * @param studentID
	 * @return
	 * @
	 */
	public Double getStudentBalance(Integer studentID)throws FileNotFoundException,IOException,NotFoundException{
		Long location = index.findStudentLocation(studentID);
		Student stu = new Student(new RandomAccessFile(randomFileName, "rw"), location);
		return stu.getStudentBalance();
	}
	
	
	/**
	 * @exception NotFoundException - student data not recorded in system
	 * @exception IOException - file location not found, record couldnt be read from the data file
	 * @FileNotFoundException - data file could not be found
	 * @param studentID
	 * @param amount
	 */
	
	public void payStudentBalance(Integer studentID, Double amount)throws FileNotFoundException,IOException,NotFoundException{
		Long location = index.findStudentLocation(studentID);
		Student stu = new Student(new RandomAccessFile(randomFileName, "rw"), location);
		stu.reduceBalance(amount);
		stu.rewriteStudent(randomFileName, location);
	}
	
	/**
	 * @exception FileNotFoundException
	 * @exception IOException
	 * @param indexFileName
	 * @
	 */
	public void shutdown(String indexFileName)throws FileNotFoundException,IOException{
		
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(indexFileName));
		output.writeObject(index);
		output.close();
	}
}
