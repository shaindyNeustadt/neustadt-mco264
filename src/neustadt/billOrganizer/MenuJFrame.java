package neustadt.billOrganizer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import neustadt.linkedList.ListEmptyException;

public class MenuJFrame extends JFrame {

	private JList<String> list;
	private DefaultListModel<String> listModel;

	public MenuJFrame(BillOrganizer organizer) {
		setTitle("BILL MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);

		Container container = getContentPane();
		setLayout(new BorderLayout());

		listModel = new DefaultListModel<String>();
		listModel.addElement("1. Add a bill");
		listModel.addElement("2. View bills by date");
		listModel.addElement("3. View bills by type");
		listModel.addElement("4. View bills by amount due");
		listModel.addElement("5. Pay next bill by date");
		listModel.addElement("6. Pay next bill by type");
		listModel.addElement("7. Pay next bill by amount due");
		listModel.addElement("8. Pay Bill by bill ID");
		listModel.addElement("EXIT APPLICATION");

		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<String> list = (JList<String>) evt.getSource();

				int index = list.locationToIndex(evt.getPoint());
				switch (index) {
				case 0:
					new AddBillJFrame(organizer).setVisible(true);
					break;
				case 1:
					new DisplayBillsJFrame(organizer.iteratorByDate())
							.setVisible(true);
					break;
				case 2:
					new DisplayBillsJFrame(organizer.iteratorByType())
							.setVisible(true);
					break;
				case 3:
					new DisplayBillsJFrame(organizer.iteratorByAmount())
							.setVisible(true);
					break;
				case 4:
					try{
					new PayBillJFrame(
							organizer.payNextBill(BillCriteria.BILLDUEDATE),
							organizer).setVisible(true);
					}catch(ListEmptyException ex){
						JOptionPane.showMessageDialog(null, "There are no unpaid bills in the system.");
					}
					break;
				case 5:
					new PayBillJFrame(organizer.payNextBill(BillCriteria.BILLTYPE),
							organizer).setVisible(true);
					break;
				case 6:
					new PayBillJFrame(organizer.payNextBill(BillCriteria.BILLAMOUNT),
							organizer).setVisible(true);
					break;
				case 7:
					String ID = JOptionPane.showInputDialog(null, "Enter ID:");
					try {
						new PayBillJFrame(organizer.removeBillByID(Integer
								.parseInt(ID)), organizer).setVisible(true);
					} catch (NotFoundException ex) {
						JOptionPane.showMessageDialog(null, "Bill not found.");
					}
					break;
				case 8:
					try {
						organizer.closeOrganizer();
						System.exit(0);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null,
								"Can not shut down properly. Contact IT.");
					}
					break;
				}
			}
		});

		container.add(list, BorderLayout.CENTER);
	}
}