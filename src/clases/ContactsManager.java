package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.res.AssetManager;

public class ContactsManager
{


	public  static SortedArrayList<Contact> listContacts = new SortedArrayList<Contact>();

	public static String direction = "Contact.txt";

	public static File  file = new File(direction);




	public static void read(FileInputStream  file) throws FileNotFoundException {

		listContacts = ContactStore.Read(file);

		if (listContacts == null) {

			listContacts = new SortedArrayList<Contact>();

		}

	}




	public static void write(FileOutputStream file) {

		ContactStore.Write(listContacts,file);

	}


	public static int containAt(Contact s){

		for(int i =0;i< listContacts.size();i++){

			if(s.equals(listContacts.get(i))){

				return i;

			}


		}

		return 0;


	}

	public static void displayContactList(SortedArrayList<Contact> listContacts)
	{
		for(Contact contact: listContacts)
		{
			System.out.println("Name: "+contact.getFirstName()+" LastName: "+contact.getLastName()+
					" CellPhone: "+contact.getCellPhone()+" WorkPhone: "+contact.getWorkPhone()+
					" Email: "+contact.getEmail());
			for(Address a: contact.getAddress())
			{
				System.out.println("Address:  "+ ""+a.getName()+""+a.getStreet()+
						""+a.getNumber()+""+a.getCity()+""+a.getState()+""+a.getZipCode());
			}

		}
	}


	public static void addContact(Contact contact)
	{
		listContacts.add(contact);
	}

	public static void editContact(int index, Contact contact)
	{
		listContacts.set(index, contact);
	}

	public static void addAddresss(Contact contact,Address address)
	{
		contact.addAddress(address);
	}

	public static void editAddresss(int index,Contact contact, Address address)
	{
		contact.getAddress().set(index, address);
	}

	public static void removeContact(Contact contact)
	{
		listContacts.remove(contact);
	}

	public static void removeContact(int index)
	{
		listContacts.remove(index);
	}

	public static void removeAddress(int index, Contact contact)
	{
		contact.deleteAddress(index);
	}

	public static Contact getContact(int index) {

		return listContacts.get(index);
	}


}
