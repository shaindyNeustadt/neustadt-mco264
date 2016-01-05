package neustadt.billOrganizer;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PayBill extends JFrame{
private JLabel text1;
private JLabel text2;

	public PayBill(Bill bill, BillOrganizer organizer){
		 setTitle("BILL PAID");
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setSize(525, 150);
		 
		 Container container = getContentPane();
			setLayout(new GridLayout(2,0));
			
			text1 = new JLabel(bill.toString());
			text2 = new JLabel("Bill Total: $" + organizer.totalBills());
						
			container.add(text1);
			container.add(text2);
	}
}