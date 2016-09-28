package I_OTutorials;

import java.io.*;

import javax.swing.*;

import java.util.StringTokenizer;

import javax.swing.JFileChooser;



public class CarSales
{

	public static void main(String args[])
	{
		
		//this.setSize(500, 500); //Initial size of the window frame
		//this.setTitle("Java Notepad"); //Set title
		//JFileChooser open = new JFileChooser(); // open file chooser
		//int option = open.showOpenDialog(this); // get the option user selected
		//if (option == JFileChooser.APPROVE_OPTION) {	
			try
			{
				BufferedReader file_in = new BufferedReader(new FileReader("E:\\David\\java\\2015\\sales.dat"));
				StringTokenizer tokenizer;
				String inputLine;
				int totalSales = 0;  

				inputLine = file_in.readLine(); // read line from input file

				while(inputLine != null) // while not end of file
				{ 
					tokenizer = new StringTokenizer(inputLine);
					tokenizer.nextToken();   // skip country name
					try
					{
						totalSales += Integer.parseInt(tokenizer.nextToken()) * Integer.parseInt(tokenizer.nextToken());
					}
					catch(NumberFormatException e)
					{ System.out.println("Error in input file format");   }

					inputLine = file_in.readLine(); // get the next line
				} // end while not end of file
				System.out.println("Total car sales in euro: " + totalSales);
				file_in.close();
			}
			catch(FileNotFoundException e){ 
				System.out.println("Input file: " + args[0] + "not found"); 
			}
			catch(IOException e){ 
				System.out.println("Unexpected I/O error, quitting..."); 
			}
		}
	}
