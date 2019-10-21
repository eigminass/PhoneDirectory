package part1;
/**
 * This class implements Directory interface using 
 * 
 * @author slavi
 *
 */
public class ArrayDirectory implements Directory<Entry> {
	// private fields
	private Entry[] entry;
	private int size = 0;

	private static final int DEFAULT_CAPACITY = 500;

	public ArrayDirectory() {
		entry = new Entry[DEFAULT_CAPACITY];
	}

	@Override
	public boolean insert(Entry obj) {

		// Case 1: Array is empty
		if (size == 0) {
			entry[size] = obj;
			size++;
			return true;
		}

		if (size == entry.length) {
			makeArrayBigger();
		}
		
		int insertHere = 0; // will tell us where to insert the object
		boolean check = false; // checks if object can be inserted within

		for (int i = 0; i < size; i++) {

			if (obj.getSurname().compareTo(entry[i].getSurname()) < 0) {
				insertHere = i;
				check = true;
				break;
			}
		}

		// Case 2: Insert new element at the end of the array
		if (insertHere == 0 && !check) {
			entry[size] = obj;
			size++;
			return true;
		}

		// Case 3: Insert new element within the array
		for (int i = size; i > insertHere; i--) {
			entry[i] = entry[i - 1];
		}

		entry[insertHere] = obj;
		size++; // increment the size of the array
		return true;
	}

	@Override
	public boolean delete(String str) {
		int deleteThis = 0;
		boolean completionStatus = false;

		for (int i = 0; i < size; i++) {
			if (entry[i].getSurname().equals(str) || entry[i].getNumber().equals(str)) {
				deleteThis = i;
				completionStatus = true;
			}
		}

		if (!completionStatus) {
			return completionStatus;
		}

		for (int i = deleteThis; i < size - 1; i++) {
			entry[i] = entry[i + 1];
		}
		size--;
		entry[size] = null;
		completionStatus = true;
		return completionStatus;
	}

	@Override
	public String findExNumber(String str) {

		for (int i = 0; i < size; i++) {
			if (str.equals(entry[i].getSurname())) {
				return entry[i].getNumber();
			}
		}
		return null;
	}

	@Override
	public boolean changeNumber(String str, String newNumber) {
		for (int i = 0; i < size; i++) {
			if (entry[i].getSurname().equals(str)) {
				entry[i].setNumber(newNumber);
				return true;
			}
		}
		return false;
	}

	@Override
	public void printDirectory() {
		
		for (Entry e : entry) {
			if (e == null)
				break;
			System.out.format("%-20s", e.getSurname());
			System.out.format("%-20s", e.getInitials());
			System.out.format("%-20s", e.getNumber());
			System.out.println();
		}
	}
	
	// this private method increases the size of the array if needed
	
	private void makeArrayBigger(){
		Entry[] copy = new Entry[entry.length*2];
		
		for(int i = 0; i < size; i++){
			copy[i] = entry[i];
		}
		
		this.entry = copy;
	}
}
