package com.example.contactapp;

import clases.Contact;

import clases.ContactsManager;
import clases.SortedArrayList;
import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


@SuppressLint("DefaultLocale")
public class ListViewAdapter extends BaseAdapter {

	private SortedArrayList<Contact> listaContacto;
	private LayoutInflater inflater;
	private SortedArrayList<Contact> listaContactoBackup;

	public ListViewAdapter(Activity context, SortedArrayList<Contact> listaContacto) {
		super();

		this.listaContacto =  new SortedArrayList<Contact>();
		this.listaContactoBackup =new SortedArrayList<Contact>();
		for(Contact c:listaContacto)
		{
			this.listaContacto.add(c);
			listaContactoBackup.add(c);
		}
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {

		return listaContacto.size();
	}

	@Override
	public Object getItem(int index) {

		return listaContacto.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Contact contact= ContactsManager.getContact(position);
		
		Contact contact = listaContacto.get(position);
		View view = convertView;
		if(convertView==null)
			view= inflater.inflate(R.layout.simple_list_item_1, null);
		TextView textView = (TextView) view.findViewById(R.id.text1);
		textView.setText(contact.getFirstName()+" "+contact.getLastName());
		return view;
	}


	@SuppressLint("DefaultLocale")
	public void filterContact(String substring)

	{

		if(substring==""){

			listaContacto.clear();

			for(Contact c: listaContactoBackup){

				listaContacto.add(c);


			}


		}

		else{

			listaContacto.clear();

			for(Contact c: listaContactoBackup){

				if(c.getFirstName().contains(substring) || c.getLastName().contains(substring))

					listaContacto.add(c);

			}

			//notify ListView to Rebuild

			notifyDataSetChanged();}

	}

}
