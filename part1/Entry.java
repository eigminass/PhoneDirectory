package part1;

/**
 * This class represents Entry object
 * @author slavi
 *
 */

public class Entry{
	
	// private fields
	
	private String surname;
	private String initials;
	private String number;
	
	public Entry(String surname, String initials, String number){
		// check if surname start with capital letter
		if(surname.substring(0, 1).hashCode() >= 65 && surname.substring(0, 1).hashCode() <= 90){
			this.surname = surname;
			this.initials = initials;
			this.number = number;
		}
		// if surname starts with lower case letter, capitalise it!
		else{
			this.surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
			this.initials = initials;
			this.number = number;
		}
	}
	// getters and setter for number
	public String getSurname(){
		return surname;
	}
	public String getInitials(){
		return initials;
	}
	public String getNumber(){
		return number;
	}
	public void setNumber(String number){
		this.number = number;
	}
	/**
	 * The following methods are not used in this project.
	 * They are here for the best programming practice.
	 */
	public void setInitials(String initials){
		this.initials = initials;
	}
	public void setSurname(String surname){
		this.surname = surname;
	}
	// equals method if there is a need to compare entries
	@Override
	public boolean equals(Object obj) {
		// reflexitivity
		if(this == obj)
			return true;
		// non-nullity
		if(!(obj instanceof Entry))
			return false;
		// consistency
		Entry e = (Entry) obj;
		return surname == e.getSurname() && number == e.getNumber();
	}

	@Override
	public int hashCode() {
		return surname.charAt(0) - 65;
	}

	
}
 