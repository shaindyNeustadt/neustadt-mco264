package neustadt.randomAccessStudent;

import java.io.Serializable;

public class StudentIndexRec implements Comparable<StudentIndexRec>, Serializable{
	private Integer studentID;
	private Long fileLocation;
	
	public StudentIndexRec(Integer studentID, Long fileLocation){
		this.studentID = studentID;
		this.fileLocation = fileLocation;
	}

	
	public Integer getStudentID(){
		return studentID;
	}
	
	public Long getFileLocation (){
		return fileLocation;
	}
	
	//base comparison on the value of the student ID
	public int compareTo(StudentIndexRec indexRec ){
		return this.getStudentID().compareTo(indexRec.getStudentID());
	}
}
