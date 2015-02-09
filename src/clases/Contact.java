package clases;

import java.io.Serializable;


public class Contact  implements Comparable<Contact>, Serializable  {

	// all private fields for a contact
	private String firstName;
	private String lastName;
	private String cellPhone;
	private String workPhone;
	private String email;
	private ArrayList<Address> address;


	public Contact(String firstName, String lastName, String cellPhone,
			String workPhone, String email, ArrayList<Address> address) {
		super();

		this.firstName = firstName;
		this.lastName = lastName;
		this.cellPhone = cellPhone;
		this.workPhone = workPhone;
		this.email = email;
		this.address = new ArrayList<Address>(10);
		if(address != null)
		{
			for(Address a : address)
			{
				this.addAddress(a);
			}
		}
		if(firstName.equals(""))
		{
			this.firstName="Unknown";
		}

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.equals(""))
		{
			this.firstName="Unknown";
		}
		else{
		this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Address> getAddress() {
		return address;
	}
	public void addAddress(Address ad) {
		address.add(ad);
	}
	public void deleteAddress(int index) {
		address.remove(index);
	}

	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}

	@Override
	public int compareTo(Contact contact) {

		if(this.firstName.compareToIgnoreCase(contact.firstName)==0)
		{
			return this.lastName.compareToIgnoreCase(contact.lastName);
		}
		else
		{
			return this.firstName.compareToIgnoreCase(contact.firstName);
		}

	}




}
