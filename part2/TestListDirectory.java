package part2;

/**
 * This class tests linked list directory
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import part1.*;

public class TestListDirectory {
	public static void main(String[] args) throws FileNotFoundException {
		Directory<Entry> dir = new ListDirectory();
		
		FileReader from = new FileReader("C:\\Users\\slavi\\Desktop\\Programming\\inputData.txt"); // input file can be changed here
		Scanner sc = new Scanner(from);

		String surname, initials, extension;
		
		try{
			while (sc.hasNext()) {
				
				surname = sc.next();
				initials = sc.next();
				extension = sc.next();
				
				if(surname.matches(".*\\d.*") || initials.length() != 4 || !extension.matches("[0-9]+")){
					
					from.close();
					throw new IllegalArgumentException();
				}
				
				dir.insert(new Entry(surname, initials, extension));
			}
		}
		catch (Exception e){
			System.out.print("Invalid entry found");
			System.exit(1);
		}
		
		
		
	}
}
