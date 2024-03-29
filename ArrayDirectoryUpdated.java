package part1;
/**
 * This class implements Directory interface using 
 * 
 * FOLLOWING METHODS HAVE BEEN IMPROVED:
 * 
 * 1) insert
 * 2) delete
 * 3) findExNumber
 * 
 * @author slavi
 *
 */
public class ArrayDirectory implements Directory<Entry> {
	// private fields
	private Entry[] entry;
	private int size = 0;

	private static final int DEFAULT_CAPACITY = 700;
	
	private BinarySearch bin;

	public ArrayDirectory() {
		entry = new Entry[DEFAULT_CAPACITY];
		bin = new BinarySearch();
	}

	@Override
	public boolean insert(Entry obj) {
				
		String key = obj.getSurname();
		
		int l = 0, r = size;
		
		
		int m = (l+r)/2;
		
		if(size == 0){
			entry[size] = obj;
			size++;
			return true;
		}
		
		
		if(key.compareTo(entry[m].getSurname()) > 0){
			
			while(key.compareTo(entry[m].getSurname()) > 0){
				if(m+1 >= size)
					break;
				l = m+1;
				m = (l+r)/2;
			}
			
		}
		
		
		
		else{
			while(key.compareTo(entry[m].getSurname()) < 0){
				if(m-1 <= 0)
					break;
				r = m-1;
				m = (l+r)/2;
			}
			
		}
		
		int here = -20;
		
		for(int i = l; i < r; i++){
			if(key.compareTo(entry[i].getSurname()) <= 0){
				here = i;
				break;
			}
		}
		
		
		if(here == -20){
			// first element
			if(key.compareTo(entry[0].getSurname()) < 0){
				here = 0;
			}
			
			if(r < size){
				if(key.compareTo(entry[r].getSurname()) < 0 && key.compareTo(entry[r-1].getSurname()) > 0){
					here = r;
					
				}
				
			}
			
			
			// greater that r but smaller than r+1
			if(r+1 < size){
				if(key.compareTo(entry[r].getSurname()) > 0 && key.compareTo(entry[r+1].getSurname()) < 0){
					here = r+1;
				}
			}
			
			
			else{
				here = size;
			}
		}
		
		
		for(int i = size; i > here; i--){
			entry[i] = entry[i-1];
		}
		
		
		
		entry[here] = obj;
		
		size++;
		
		return true;
	}
	
	

	@Override
	public boolean delete(String str) {
		
		boolean completionStatus = false;
		
			int deleteThis = bin.binSearchSur(entry, str, 0, size - 1);
			
			if(deleteThis == -1)
				return completionStatus;
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
		
		int index = bin.binSearchSur(entry, str, 0, size-1);
		if(index < 0)
			return null;
		return entry[index].getNumber();
		
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
