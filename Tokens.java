package I_OTutorials;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.*;

public class Tokens extends JFrame implements ActionListener {
	
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
	JTextField nameOut, studentNumOut, yearOut, fileOut;
	//Panels
	JPanel p1, p2, p3;
	JLabel name, studentNum, year, setOutput;
	//Buttons
	JButton edit, amend;


	public Tokens(){
		
		this.setSize(300, 250); //Initial size of the window frame
		this.setTitle("StudentList I/O"); //Set title
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
		//Panels
		//P1 holds comboBox and edit button
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		//P2 holds textFields and labels
		p2 = new JPanel();
		p2.setLayout(new GridLayout(3,2));
		//P3 holds output controls
		p3 = new JPanel();
		p3.setLayout(new GridLayout(2,1));
		
		nameList = new JComboBox();
		nameOut = new JTextField(10);
		studentNumOut = new JTextField(10);
		yearOut = new JTextField(10);
		fileOut = new JTextField(5);
		fileOut.setText("Output File Name");
		name = new JLabel("Name : ");
		studentNum = new JLabel("Student Number : ");
		year = new JLabel("Year : ");
		edit = new JButton("Edit Profile");
		amend = new JButton("Add to File");
		//Add comboBox/buttons to action listener
		this.nameList.addActionListener(this);
		this.edit.addActionListener(this);
		this.amend.addActionListener(this);
		//Add components to first panel
		p1.add(nameList);
		p1.add(edit);
		//Add components to second panel
		p2.add(name);
		p2.add(nameOut);
		p2.add(studentNum);
		p2.add(studentNumOut);
		p2.add(year);
		p2.add(yearOut);
		//Add to third panel
		p3.add(fileOut);
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
							String line = input.nextLine(); //Assign line to String variable "line"
							nameList.addItem(line); //Add each line to comboBox
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
			StringTokenizer tokenizer = new StringTokenizer(n); //to specify delimiter: (String n, String delim) in constructor
			nameOut.setText(n);
			while(tokenizer.hasMoreTokens()){
				nameOut.setText(tokenizer.nextToken());
				studentNumOut.setText(tokenizer.nextToken());
				yearOut.setText(tokenizer.nextToken());
			}
		}
		else if(e.getSource() == amend){
			try {
				output = new File(fileOut.getText());
				PrintWriter pw = new PrintWriter(new FileWriter(output, true));
				pw.println(nameOut.getText() + " " + studentNumOut.getText() + " " + yearOut.getText());
				nameOut.setText("");
				studentNumOut.setText("");
				yearOut.setText("");
				pw.close();
			}
			catch (IOException e1){
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
