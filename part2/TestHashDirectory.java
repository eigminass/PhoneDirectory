package part2;

/**
 * This class tests HashDirectory
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import part1.Directory;
import part1.Entry;

public class TestHashDirectory {

	public static void main(String[] args) throws FileNotFoundException {

		Directory<Entry> dir = new HashDirectory();

		FileReader from = new FileReader("H:\\Desktop\\inputData.txt"); // <-- input file can be changed here
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
		dir.printDirectory();
		
	}

}
