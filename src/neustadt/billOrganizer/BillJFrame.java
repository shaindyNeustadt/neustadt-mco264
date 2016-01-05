package neustadt.billOrganizer;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BillJFrame extends JFrame {

	public static void main(String[] args) {
		new BillJFrame().setVisible(true);
	}

	private JButton button1;
	private JButton button2;
	private BillOrganizer organizer = null;

	public BillJFrame() {
		setTitle("Bill Organizer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150);

		Container container = getContentPane();
		setLayout(new GridLayout(0, 2, 10, 10));

		button1 = new JButton("New User");
		button2 = new JButton("Returning User");

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					Scanner readFile = new Scanner(new File("bills.txt"));
					organizer = new BillOrganizer(readFile);
					new Menu(organizer).setVisible(true);
					setVisible(false);
				} catch (FileNotFoundException ex) {
					System.out
							.println("Could not set up records. File not found.");
					System.exit(1);
				}
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					organizer = new BillOrganizer("bills.ser");
					new Menu(organizer).setVisible(true);
					setVisible(false);
				} catch (FileNotFoundException ex) {
					System.out
							.println("Could not set up records. File not found.");
					System.exit(1);
				} catch (InvalidDataException ex) {
					System.out
							.println("Could not set up records. Invalid Data.");
					System.exit(1);
				} catch (ClassNotFoundException ex) {
					System.out
							.println("Could not set up records. Inconsistant Class format.");
					System.exit(1);
				} catch (IOException ex) {
					System.out
							.println("Could not set up records. I/O Exception.");
					System.exit(1);
				}
			}
		});

		container.add(button1);
		container.add(button2);
	}

}
