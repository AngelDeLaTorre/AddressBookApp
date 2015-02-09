package clases;




import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




public class ContactStore {
	
	public static SortedArrayList<Contact> Read(FileInputStream file)  {
	

	
		SortedArrayList<Contact> list = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;

		try {

			fis = file;
			in = new ObjectInputStream(fis);
			list = (SortedArrayList<Contact>) in.readObject();
			in.close();
		} catch (IOException ex) {
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 

		return list;
	}

	

	public static void Write(SortedArrayList<Contact> list, FileOutputStream file) {
		

		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = file;
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			fos.close();
			System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

