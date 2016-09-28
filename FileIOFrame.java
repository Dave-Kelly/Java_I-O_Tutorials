package I_OTutorials;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class FileIOFrame extends JFrame implements ActionListener{

	private MenuBar menuBar; //Menu bar item
	private Menu file; //File menu item
	//File menu items
	private MenuItem openFile; //Open option
	private MenuItem saveFile; //Save option
	private MenuItem close; //Close option
	private File input, output;
	//List Box
	JComboBox  nameList;
	//TextBox
	JTextField nameOut, dateOut, addressOut;
	//Panels
	JPanel p1, p2, p3;
	JLabel name, date, address;
	JButton edit, amend;

	public FileIOFrame() {
		this.setSize(300, 200); //Initial size of the window frame
		this.setTitle("File I/O"); //Set title
		WindowCloser listener = new WindowCloser();
		addWindowListener(listener);

		this.getContentPane().setLayout(new BorderLayout()); 

		//MENU BAR********************************************
		// add menu bar
		menuBar = new MenuBar();
		file = new Menu();
		openFile = new MenuItem();
		saveFile = new MenuItem();
		close = new MenuItem();
		this.setMenuBar(menuBar);
		this.menuBar.add(file); 
		file.setLabel("File");
		// OPEN option
		this.openFile.setLabel("Open"); // set  label of the menu item
		this.openFile.addActionListener(this); // add  action listener 
		this.file.add(this.openFile); // add to the "File" menu
		// SAVE option
		this.saveFile.setLabel("Save");
		this.saveFile.addActionListener(this);
		this.file.add(this.saveFile);
		// CLOSE option
		this.close.setLabel("Close");
		this.close.addActionListener(this);
		this.file.add(this.close);
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(3,2));
		
		p3 = new JPanel();
		p3.setLayout(new GridLayout(1,1));
		
		nameList = new JComboBox();
		nameOut = new JTextField(10);
		dateOut = new JTextField(10);
		addressOut = new JTextField(10);
		name = new JLabel("Name : ");
		date = new JLabel("Date : ");
		address = new JLabel("Address : ");
		amend = new JButton("Add to File");
		edit = new JButton("Edit Profile");
		
		this.edit.addActionListener(this);
		this.amend.addActionListener(this);
		
		//Add components to first panel
		p1.add(nameList);
		p1.add(edit);
		
		//Add components to second panel
		p2.add(name);
		p2.add(nameOut);
		p2.add(date);
		p2.add(dateOut);
		p2.add(address);
		p2.add(addressOut);
		
		p3.add(amend);
		
		//Add panels to Frame
		this.add(p1, "North");
		this.add(p2, "Center");
		this.add(p3, "South");

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.openFile) {
			JFileChooser open = new JFileChooser(); // open file chooser
			int option = open.showOpenDialog(this); // get the option user selected
				if (option == JFileChooser.APPROVE_OPTION) {
					try {
						// create scanner instance to read chosen file
						Scanner input = new Scanner(new FileReader(open.getSelectedFile().getPath()));
						
						while (input.hasNextLine()){ //while there's something to read
							String line = input.nextLine();
							nameList.addItem(line);
						}
					} 
					catch (Exception ex) { // catch any exceptions
							// write to the debug console
							System.out.println(ex.getMessage());
						}
				}
			}
		else if(e.getSource() == edit){
			String n = (String)nameList.getSelectedItem();
			nameOut.setText(n);
		}
		else if(e.getSource() == amend){
			try {
				PrintWriter pw = new PrintWriter(new FileWriter("F:\\David\\java\\2015\\output.txt", true));
				pw.println(nameOut.getText() + " " + dateOut.getText() + " " + addressOut.getText());
				nameOut.setText("");
				dateOut.setText("");
				addressOut.setText("");
				pw.close();
			} catch (IOException e1) {
				// write to the debug console
				System.out.println(e1.getMessage());
			}
		}
	}

	private class WindowCloser extends WindowAdapter{
		public void windowClosing(WindowEvent event){ //window closer sub class
			System.exit(0); //close window
			
		}

	}

}
