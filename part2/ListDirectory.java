package part2;

/**
 * This class implement Directory interface using linked list
 */

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import part1.*;

public class ListDirectory implements Directory<Entry> {

	// private fields
	
	private List<Entry> entry;
	private ListIterator<Entry> iterator;
	private int i;

	public ListDirectory(){
		entry = new LinkedList<>();
	}
	
	@Override
	public boolean insert(Entry obj) {
		
		/**
		 * 	Iterate through the list
			and find the position to insert
			the new element
		 */
		
		iterator = entry.listIterator(); // make sure iterator is at the start
		
		while(iterator.hasNext()){
			
			Entry e = iterator.next();
			
			if (obj.getSurname().compareTo(e.getSurname()) < 0) {
				iterator.previous(); // we need to go one element back
				iterator.add(obj);
				return true;
			}
		}
		
		entry.add(obj);
		return true;
		
	}

	@Override
	public boolean delete(String str) {
		
		iterator = entry.listIterator();
		
		while(iterator.hasNext()){
			Entry e = iterator.next();
			if(str.equals(e.getSurname()) || str.equals(e.getNumber())){
				iterator.remove();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String findExNumber(String str) {
		
		iterator = entry.listIterator();
		
		while(iterator.hasNext()){
			Entry e = iterator.next();
			if(str.equals(e.getSurname()))
				return e.getNumber();
			}
		
		return null;
	}

	@Override
	public boolean changeNumber(String str, String newNubmer) {

		iterator = entry.listIterator();
		
		while(iterator.hasNext()){
			Entry e = iterator.next();
			if(str.equals(e.getSurname()))
				e.setNumber(newNubmer);
				iterator.set(e);
				return true;
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

}
