package com.example.contactapp;

import java.io.FileNotFoundException;

import clases.Contact;

import clases.ContactsManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ShowContact extends ListActivity{


	Button edit, delete;

	private TextView nameS;
	private TextView lastS;
	private TextView cellS;
	private TextView workS;
	private TextView emailS;
	
	private TextView nameAddress;
	
	private Contact contact;
	private int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_contact);
		
		

		nameS = (TextView) findViewById(R.id.firstNameShow);
		lastS = (TextView) findViewById(R.id.lastNameShow);
		cellS = (TextView) findViewById(R.id.phoneNumberShow);
		workS = (TextView) findViewById(R.id.workNumberShow);
		emailS = (TextView) findViewById(R.id.emailAddressShow);
		

		contact = (Contact) this.getIntent().getSerializableExtra("contact");
		
		AddressViewAdapter adapter = new AddressViewAdapter(this, contact.getAddress());
		setListAdapter(adapter);
		
		index =  Integer.parseInt(this.getIntent().getStringExtra("index")) ; 
		nameS.setText(contact.getFirstName());
		lastS.setText(contact.getLastName());
		cellS.setText(contact.getCellPhone());
		workS.setText(contact.getWorkPhone());
		emailS.setText(contact.getEmail());
		//nameAddress.setText(contact.getAddress().get(0).getName());

		

		edit = (Button) findViewById(R.id.editButton);
		delete = (Button) findViewById(R.id.deleteButton);

		edit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent editing = new Intent(ShowContact.this, EditContact.class);
				editing.putExtra("editable", contact);
				editing.putExtra("from1", "ShowContact");
				editing.putExtra("index", Integer.toString(index));
				startActivity(editing);

			}
		});

		delete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ContactsManager.removeContact(index);
				Intent intent = new Intent(ShowContact.this, MainActivity.class);
				
				
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				try {

					ContactsManager.write(openFileOutput(ContactsManager.direction, MODE_PRIVATE));

					} catch (FileNotFoundException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

					}
				startActivity(intent);

			}
		});

	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	@SuppressLint("NewApi")
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.rgb(100, 100, 45)));
		action.setTitle("Contact App");

		return true;
	}

}
