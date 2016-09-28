package I_OTutorials;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.*;

public class CarTax extends JFrame implements ActionListener {

	private MenuBar menuBar; //Menu bar item
	private Menu file; //File menu item
	//File menu items
	private MenuItem openFile; //Open option
	private MenuItem saveFile; //Save option
	private MenuItem close; //Close option
	private File input, output;
	//List Box
	JComboBox  carList;
	//TextBox
	JTextField carOut, engineOut, taxOut, fileOut;
	//Panels
	JPanel p1, p2, p3;
	JLabel make, engine, tax, setOutput;
	//Buttons
	JButton edit, amend;

	public CarTax(){
		this.setSize(300, 250); //Initial size of the window frame
		this.setTitle("StudentList I/O"); //Set title
		WindowCloser listener = new WindowCloser();
		addWindowListener(listener);

		//CONTENT PANE
		this.getContentPane().setLayout(new BorderLayout()); 

		//BUILD MENU
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

		//BUILD PANELS
		//P1 holds comboBox and edit button
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		//P2 holds textFields and labels
		p2 = new JPanel();
		p2.setLayout(new GridLayout(3,2));
		//P3 holds output controls
		p3 = new JPanel();
		p3.setLayout(new GridLayout(2,1));

		//BUILD COMPONENTS
		carList = new JComboBox();
		carOut = new JTextField(10);
		engineOut = new JTextField(10);
		taxOut = new JTextField(10);
		fileOut = new JTextField(5);
		fileOut.setText("Output File Name");
		make = new JLabel("Make/Model : ");
		engine = new JLabel("Engine Size : ");
		tax = new JLabel("Annual Road Tax : ");
		edit = new JButton("Amend Entry");
		amend = new JButton("Add to File");
		//Add comboBox/buttons to action listener
		this.carList.addActionListener(this);
		this.edit.addActionListener(this);
		this.amend.addActionListener(this);

		//ADD COMPONENTS TO PANELS
		//Add components to first panel
		p1.add(carList);
		p1.add(edit);
		//Add components to second panel
		p2.add(make);
		p2.add(carOut);
		p2.add(engine);
		p2.add(engineOut);
		p2.add(tax);
		p2.add(taxOut);
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
						carList.addItem(line); //Add each line to comboBox
					}
				} 
				catch (Exception ex) { // catch any exceptions
					// write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}
		else if(e.getSource() == edit){
			String n = (String)carList.getSelectedItem();
			StringTokenizer tokenizer = new StringTokenizer(n); //to specify delimiter: (String n, String delim) in constructor
			carOut.setText(n);
			while(tokenizer.hasMoreTokens()){
				carOut.setText(tokenizer.nextToken());
				engineOut.setText(tokenizer.nextToken());
			}
			double rt = Double.parseDouble(engineOut.getText());
			rt = rt * 150;
			String s = String.valueOf(rt);
			taxOut.setText(s);
		}
		else if(e.getSource() == amend){
			try {
				output = new File(fileOut.getText());
				PrintWriter pw = new PrintWriter(new FileWriter(output, true));
				//pw.println(nameOut.getText() + " " + studentNumOut.getText() + " " + yearOut.getText());
				//carOut.setText("");
				//studentNumOut.setText("");
				//yearOut.setText("");
				//pw.close();
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
