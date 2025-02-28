package _00_Intro_To_ArrayLists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class _02_GuestBook implements ActionListener {
    /*
     * Create a GUI with two buttons. One button reads "Add Name" and the other
     * button reads "View Names". When the add name button is clicked, display
     * an input dialog that asks the user to enter a name. Add that name to an
     * ArrayList. When the "View Names" button is clicked, display a message
     * dialog that displays all the names added to the list. Format the list as
     * follows:
     * Guest #1: Bob Banders
     * Guest #2: Sandy Summers
     * Guest #3: Greg Ganders
     * Guest #4: Donny Doners
     */
	JPanel panel = new JPanel();
	JFrame frame = new JFrame("GuestBook");
	JButton addName = new JButton("Add Name");
	JButton viewNames = new JButton("View Names");
	
	public static void main(String[] args) {
		_02_GuestBook guestBook = new _02_GuestBook();
		guestBook.buildGUI();
	}

	private void buildGUI() {
		// TODO Auto-generated method stub
		panel.add(addName);
		panel.add(viewNames);
		
		frame.add(panel);
		
		addName.addActionListener(this);
		
		viewNames.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		
		frame.setVisible(true);
		
	
	}
	
	
	@Override
	public  void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addName) {
			
		}
	}
	

}
