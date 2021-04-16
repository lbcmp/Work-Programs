import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * My supervisor (at my career center job) gave me a list of students that attended an information session.
 * The list had over a hundred names in lastName,firstName format. I need the names in firstName lastName format in order
 * to input the names into the database where we log student attendance. So I wrote this simple program to convert names in 
 * lastName, firstName format to firstName lastName format.
 * 
 * Use Sample LastName,FirstName to see it at work.
 * 
 * @author Leah
 *
 */

public class LastNameCommaFirstName {

	public static void main(String[] args) {
		Scanner scnr = null;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		FileInputStream file = null;
		String fileName = "";
		boolean validFile = false;
		
		String[] nameArr = new String[200];
		
		while (!validFile) {
			try {
				System.out.println("What is the name of your file?");
				fileName = input.nextLine();
				file = new FileInputStream(fileName);
				scnr = new Scanner(file);
				
				int index = 0;
				while (scnr.hasNextLine()) {
					String[] strArr = new String[2];
					String s = scnr.nextLine();
					
					strArr = s.split(",");
					s = strArr[1] + " " + strArr[0];
					
					
					nameArr[index] = s;
					++index;
				}
				
			} catch (IOException e) {
				System.out.println("File Not Found: " + fileName);
			}
			
			try {
				if (file != null && scnr != null) {
					file.close();
					scnr.close();
					validFile = true;
				}
			} catch (IOException e) {
				System.out.println("Trouble closing file.");
			}
		}
		
		/*int size = 0;
		for (int i = 0; i < nameArr.length; ++i) {
			if (nameArr[i] != null) {
				System.out.println(nameArr[i]);
				++size;
			}
		}
		
		System.out.println("\n\nSize of Array: " + size);*/
	}
}
