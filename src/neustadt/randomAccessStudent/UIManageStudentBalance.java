package neustadt.randomAccessStudent;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UIManageStudentBalance {

	public static void main(String[] args) {
		ManageStudentBalances studBalances=null;
		JFileChooser fileChooser = new JFileChooser();
		
		JOptionPane.showMessageDialog (null,"choose data file");
		 fileChooser.showOpenDialog(null);
		String randomFile = fileChooser.getSelectedFile().getPath();
		JOptionPane.showMessageDialog(null,"choose index file");
		 fileChooser.showOpenDialog(null);
		String indexFile = fileChooser.getSelectedFile().getPath();
		try{
		   studBalances = new ManageStudentBalances(indexFile,randomFile);
		}
		catch(Exception e){
			if (e instanceof FileNotFoundException){
				System.out.println("cant locate data files");
				System.exit(1);
			}
			else{
				if (e instanceof IOException ){
					System.out.println("Cant read index file");
					System.exit(1);
				}
			}
			
		}
		
		try{
		//process student 1
		System.out.println("Student 1 balance: " + studBalances.getStudentBalance(1));
		studBalances.addToStudentBalance(1, 100.00);
		System.out.println("Student 1 balance after added 100 " + studBalances.getStudentBalance(1));
		//process student 3
		System.out.println("Student 3 balance: " + studBalances.getStudentBalance(3));
		studBalances.addToStudentBalance(3, 300.00);
		System.out.println("Student 3 balance after added 300 " + studBalances.getStudentBalance(3));
		
		//process student 2
		System.out.println("Student 2 balance: " + studBalances.getStudentBalance(2));
		studBalances.payStudentBalance(2, 200.00);
		System.out.println("Student 2 balance after paid 200 " + studBalances.getStudentBalance(2));
		//close the files
		studBalances.shutdown(indexFile);
		
		}
		catch(Exception e){
			if (e instanceof NotFoundException){
				System.out.println("couldnt find student data");
			}
			else{
				if (e instanceof IOException ){
					System.out.println("couldnt read data properly");
				}
				else{
					if (e instanceof FileNotFoundException){
						System.out.println("couldnt find data or index file");
					}
				}
			}
		}
	}

}
