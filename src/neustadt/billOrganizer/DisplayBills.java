package neustadt.billOrganizer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class DisplayBills extends JFrame {
	private JList<String> Jlist;
	private DefaultListModel<String> listModel;

	public DisplayBills(Iterator<Bill> iter) {

		setTitle("View Bills");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(525, 200);

		Container container = getContentPane();
		setLayout(new BorderLayout());

		listModel = new DefaultListModel<String>();
		while (iter.hasNext()) {
			listModel.addElement(iter.next().toString());
		}

		Jlist = new JList<String>(listModel);
		Jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Jlist.setLayoutOrientation(JList.VERTICAL);
		Jlist.setVisibleRowCount(-1);

		container.add(Jlist, BorderLayout.CENTER);
	}

}