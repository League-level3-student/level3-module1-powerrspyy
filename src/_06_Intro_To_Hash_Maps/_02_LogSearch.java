package _06_Intro_To_Hash_Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_LogSearch implements ActionListener {
    /*
     * Crate a HashMap of Integers for the keys and Strings for the values.
     * Create a GUI with three buttons.
     * Button 1: Add Entry
     *      When this button is clicked, use an input dialog to ask the user
     *      to enter an ID number.
     *      After an ID is entered, use another input dialog to ask the user
     *      to enter a name. Add this information as a new entry to your
     *      HashMap.
     * 
     * Button 2: Search by ID
     *      When this button is clicked, use an input dialog to ask the user
     *      to enter an ID number.
     *      If that ID exists, display that name to the user.
     *      Otherwise, tell the user that that entry does not exist.
     * 
     * Button 3: View List
     *      When this button is clicked, display the entire list in a message
     *      dialog in the following format:
     *      ID: 123  Name: Harry Howard
     *      ID: 245  Name: Polly Powers
     *      ID: 433  Name: Oliver Ortega
     *      etc...
     * 
     * When this is complete, add a fourth button to your window.
     * Button 4: Remove Entry
     *      When this button is clicked, prompt the user to enter an ID using
     *      an input dialog.
     *      If this ID exists in the HashMap, remove it. Otherwise, notify the
     *      user that the ID is not in the list.
     */
	
	public static void main(String[] args) {
		_02_LogSearch item = new _02_LogSearch();
		item.buildGUI();
		
	}
	HashMap<Integer, String> hash;
	JPanel panel;
	JFrame frame;
	JButton addEntry;
	JButton searchByID;
	JButton viewList;
	JButton removeEntry;
	
	private void buildGUI() {
		hash = new HashMap<Integer, String>();
		panel = new JPanel();
		frame = new JFrame("Log Search");
		addEntry = new JButton("Add Entry");
		searchByID = new JButton("Search by ID");
		viewList = new JButton("View List");
		removeEntry = new JButton("Remove Entry");
		
		panel.add(addEntry);
		panel.add(removeEntry);
		panel.add(searchByID);
		panel.add(viewList);
		
		
		frame.add(panel);
		
		addEntry.addActionListener(this);
		
		searchByID.addActionListener(this);
		
		viewList.addActionListener(this);
		
		removeEntry.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addEntry) {
			Integer id = Integer.parseInt(JOptionPane.showInputDialog("Enter an ID number")); 
			String name = JOptionPane.showInputDialog("Enter a name");
			hash.put(id, name);
		}
		else if(e.getSource() == removeEntry) {
			Integer id = Integer.parseInt(JOptionPane.showInputDialog("Enter an ID number to remove"));
			if(hash.get(id) != null) {
				hash.remove(id);
			}
			
		}
		else if(e.getSource() == viewList) {
			String formattedString = "";
			for(Entry<Integer, String> entry: hash.entrySet()) {
				formattedString += "ID: " + entry.getKey() + "  Name: " + entry.getValue() + "\n";
			}
			JOptionPane.showMessageDialog(frame, formattedString);
		}
		else if(e.getSource() == searchByID) {
			Integer id = Integer.parseInt(JOptionPane.showInputDialog("Enter an ID number")); 
			JOptionPane.showMessageDialog(frame, hash.get(id));
		}
		
	}

}
