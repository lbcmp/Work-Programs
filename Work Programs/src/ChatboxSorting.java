import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * In the beginning of the pandemic, my job (a career center) logged workshop attendance by making students write
 * "present" in the Zoom chat. Then they would send the Zoom chat to me, and I would have to read through the whole thing
 * to make sure I got everyone. It was tedious so I made this program. It takes the Zoom chat and condenses all of the
 * entries into an alphabetized list of usernames.
 * 
 * Use Sample Zoom Chat to see it at work.
 * 
 * @author Leah
 *
 */

public class ChatboxSorting {
	public static void main(String[] args) {
		Scanner scnr = null;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		FileInputStream file = null;
		String fileName = "";
		boolean validFile = false;
		
		String[] chatArr = new String[200];
		
		while (!validFile) {	
		try {
			System.out.println("What is the name of your file?");
			fileName = input.nextLine();
			file = new FileInputStream(fileName);
			scnr = new Scanner(file);
			
			int index = 0;
			while (scnr.hasNextLine()) {
				String[] strArr = new String[3];
				String s = scnr.nextLine();
				
				strArr = s.split("From  ");
				s = strArr[1];
				strArr = s.split(" : ");
				s = strArr[0];
				strArr = s.split("   to   ");
				s = strArr[0];
				
				chatArr[index] = s;
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
		
		//Capitalize first letter in first/last name & make all other letters lowercase.
		for (int i = 0; i < chatArr.length; ++i) {
			if (chatArr[i] != null) {
				String s = chatArr[i];
				Character c = Character.toUpperCase(s.charAt(0));
				s = c + s.substring(1, s.length());
				
				for (int j = 1; j < s.length(); ++j) {
					c = s.charAt(j);
					
					if (s.charAt(j - 1) == ' ') {
						c = Character.toUpperCase(c);
					} else {
						c = Character.toLowerCase(c);
					}
					
					s = s.substring(0, j) + c + s.substring(j + 1, s.length());
				}
				
				chatArr[i] = s;
			}
		}
		
		//Alphabetize List
		for (int i = 0; i < chatArr.length - 1; ++i) {
			int smallestIndex = i;
			for (int j = i + 1; j < chatArr.length; ++j) {
				if ((chatArr[j] != null) && chatArr[j].compareTo(chatArr[smallestIndex]) < 0) {
					smallestIndex = j;
				}
			}
			String temp = chatArr[i];
			chatArr[i] = chatArr[smallestIndex];
			chatArr[smallestIndex] = temp;
		}
		
		//Remove Duplicates
		LinkedHashSet<String> lhChatArr = new LinkedHashSet<String>(Arrays.asList(chatArr));
        String[] newArray = lhChatArr.toArray(new String[ lhChatArr.size() ]);
        
        //Print List
      		for (int i = 0; i < newArray.length; ++i) {
      			if (newArray[i] != null) {
      				System.out.println(newArray[i]);
      			}
      		}
        
		} //end of while loop
	}

	/*
	In a class named ReadAndPrintFile.java, use the following prompt to get the fileName of the input file from the User:  “What is the name of your file?”

	If the File doesn’t exist, do not crash, but instead display “File Not Found “ + fileName
	and repeatedly prompt until the user has entered a valid file name.

	If the File exists, read from the File and display its content on standard output (System.out)
	*/
}
