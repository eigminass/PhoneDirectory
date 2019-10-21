package part2;

/**
 * This class allows testing for each implementation.
 * 
 * It measures speed performance for every implementation
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

import part1.ArrayDirectory;
import part1.Directory;
import part1.Entry;
import part3.StopWatch;

public class TimeTesting {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		arrayDirectoryTime();
		listDirectoryTime();
		hashDirectoryTime();
	}

	private static void arrayDirectoryTime() throws FileNotFoundException {

		// reading from file

		StopWatch wa = new StopWatch();

		Directory<Entry> dir = new ArrayDirectory();

		FileReader from = new FileReader("C:\\Users\\slavi\\Desktop\\Programming\\inputData.txt");
		Scanner sc = new Scanner(from);

		String surname, initials, extension;

		try {
			while (sc.hasNext()) {

				surname = sc.next();
				initials = sc.next();
				extension = sc.next();

				if (surname.matches(".*\\d.*") || initials.length() < 4 || extension.matches("^[0-9]")) {

					from.close();
					System.out.println();
				}

				dir.insert(new Entry(surname, initials, extension));
			}
		} catch (Exception e) {
			System.out.println("Invalid entry found");
			System.exit(1);
		}

		// variables to store time

		long sumInsertion = 0L;
		long sumFind = 0L;
		long repeat = 10000;

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random r = new Random();

		for (int i = 0; i < repeat; i++) {
			String randomSurname = "";
			randomSurname += alphabet.charAt(r.nextInt(alphabet.length()));

			for (int k = 0; k < 5; k++) {
				randomSurname += alphabet.toLowerCase().charAt(r.nextInt(alphabet.length()));
			}

			Entry e = new Entry(randomSurname, "A.A", "12345");
			wa.start();

			dir.insert(e);
			sumInsertion += wa.getElapsedTime();
			wa.reset();

			wa.start();
			dir.findExNumber(randomSurname);
			sumFind += wa.getElapsedTime();
			wa.reset();

			dir.delete(randomSurname);
		}

		sumInsertion = sumInsertion / repeat;
		sumFind = sumFind / repeat;
		System.out.println("Array directory");
		System.out.println();
		System.out.println("Insertion time: " + sumInsertion + " ns");
		System.out.println("Find time: " + sumFind + " ns");
		System.out.println();
	}

	private static void listDirectoryTime() throws FileNotFoundException {

		// reading from file

		StopWatch wa = new StopWatch();

		Directory<Entry> dir = new ListDirectory();

		FileReader from = new FileReader("C:\\Users\\slavi\\Desktop\\Programming\\inputData.txt");
		Scanner sc = new Scanner(from);

		String surname, initials, extension;

		try {
			while (sc.hasNext()) {

				surname = sc.next();
				initials = sc.next();
				extension = sc.next();

				if (surname.matches(".*\\d.*") || initials.length() < 4 || extension.matches("^[0-9]")) {

					from.close();
					System.out.println();
				}

				dir.insert(new Entry(surname, initials, extension));
			}
		} catch (Exception e) {
			System.out.println("Invalid entry found");
			System.exit(1);
		}

		// variables to store time

		long sumInsertion = 0L;
		long sumFind = 0L;
		long repeat = 10000;

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random r = new Random();

		for (int i = 0; i < repeat; i++) {
			String randomSurname = "";
			randomSurname += alphabet.charAt(r.nextInt(alphabet.length()));

			for (int k = 0; k < 5; k++) {
				randomSurname += alphabet.toLowerCase().charAt(r.nextInt(alphabet.length()));
			}

			Entry e = new Entry(randomSurname, "A.A", "12345");
			wa.start();

			dir.insert(e);
			sumInsertion += wa.getElapsedTime();
			wa.reset();

			wa.start();
			dir.findExNumber(randomSurname);
			sumFind += wa.getElapsedTime();
			wa.reset();

			dir.delete(randomSurname);
		}

		sumInsertion = sumInsertion / repeat;
		sumFind = sumFind / repeat;
		System.out.println("List directory");
		System.out.println();
		System.out.println("Insertion time: " + sumInsertion + " ns");
		System.out.println("Find time: " + sumFind + " ns");
		System.out.println();
	}

	private static void hashDirectoryTime() throws FileNotFoundException {

		// reading from file

		StopWatch wa = new StopWatch();

		Directory<Entry> dir = new HashDirectory();

		FileReader from = new FileReader("C:\\Users\\slavi\\Desktop\\Programming\\inputData.txt");
		Scanner sc = new Scanner(from);

		String surname, initials, extension;

		try {
			while (sc.hasNext()) {

				surname = sc.next();
				initials = sc.next();
				extension = sc.next();

				if (surname.matches(".*\\d.*") || initials.length() < 4 || extension.matches("^[0-9]")) {

					from.close();
					System.out.println();
				}

				dir.insert(new Entry(surname, initials, extension));
			}
		} catch (Exception e) {
			System.out.println("Invalid entry found");
			System.exit(1);
		}

		// variables to store time

		long sumInsertion = 0L;
		long sumFind = 0L;
		long repeat = 10000;

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random r = new Random();

		for (int i = 0; i < repeat; i++) {
			String randomSurname = "";
			randomSurname += alphabet.charAt(r.nextInt(alphabet.length()));

			for (int k = 0; k < 5; k++) {
				randomSurname += alphabet.toLowerCase().charAt(r.nextInt(alphabet.length()));
			}

			Entry e = new Entry(randomSurname, "A.A", "12345");
			wa.start();

			dir.insert(e);
			sumInsertion += wa.getElapsedTime();
			wa.reset();

			wa.start();
			dir.findExNumber(randomSurname);
			sumFind += wa.getElapsedTime();
			wa.reset();

			dir.delete(randomSurname);
		}

		sumInsertion = sumInsertion / repeat;
		sumFind = sumFind / repeat;
		System.out.println("Hash directory");
		System.out.println();
		System.out.println("Insertion time: " + sumInsertion + " ns");
		System.out.println("Find time: " + sumFind + " ns");
		System.out.println();
	}

}
