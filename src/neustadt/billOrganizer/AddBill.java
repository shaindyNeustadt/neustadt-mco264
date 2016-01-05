package neustadt.billOrganizer;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddBill extends JFrame{
private JLabel text1;
private JTextField info1;
private JLabel text2;
private JTextField info2;
private JLabel text3;
private JTextField info3;
private JLabel text4;
private JTextField info4;
private JLabel text5;
private JButton button;


	public AddBill(BillOrganizer organizer){
		 setTitle("Add Bill");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(500, 300);
		 
		 Container container = getContentPane();
			setLayout(new BoxLayout(container, getDefaultCloseOperation()));
			
			text1 = new JLabel("Vendor Name:");
			info1 = new JTextField();
			text2 = new JLabel("Amount Due:");
			info2 = new JTextField();
			text3 = new JLabel("Due Date: (MM/dd/YYYY)");
			info3 = new JTextField();
			text4 = new JLabel("Bill Type: \nCLOTHING, EDUCATION, FOOD, GROCERIES, PHONE, TRAVEL, UTILITIES");
			info4 = new JTextField();
			button = new JButton("Add Bill");
			text5 = new JLabel();
			
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try{
					organizer.insert(new Bill(info1.getText(), Double.parseDouble(info2.getText()), info3.getText(), info4.getText()));
					text5.setText("Bill Total: $" + organizer.totalBills());
					setVisible(false);
					}catch(InvalidDataException | DuplicateDataException ex){
						JOptionPane.showMessageDialog(null, "Invalid Data. Can not add bill record.");
					}
					}
				});
			
			container.add(text1);
			container.add(info1);
			container.add(text2);
			container.add(info2);
			container.add(text3);
			container.add(info3);
			container.add(text4);
			container.add(info4);
			container.add(button);
			container.add(text5);
	}
			
}
