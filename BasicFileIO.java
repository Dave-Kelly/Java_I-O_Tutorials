/**
 * 
 */
package I_OTutorials;

import java.io.*;
import java.util.Scanner;

/**
 * @author David
 *
 */
public class BasicFileIO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("test.txt");

		//Writes to file
		//Write name and age to file
		/*
		try{
			PrintWriter output = new PrintWriter(file);
			output.println("David Kelly");
			output.println(22);
			output.println("David Kelly");
			output.println(22);
			output.close();

		} catch(IOException ex){
			System.out.printf("Error: %s\n", ex);
		}
		*/
		//Reads from file
		try{
			Scanner input = new Scanner(file);
			String name = input.nextLine();
			int age = input.nextInt();

			System.out.printf("Name: %s Age %d\n", name,age);

		} catch (FileNotFoundException ex){
			System.out.printf("Error: %s\n", ex);
		}

	}
}
