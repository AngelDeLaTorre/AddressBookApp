package com.example.contactapp;

import java.io.FileNotFoundException;

import clases.Contact;

import clases.ContactsManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ListView;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EditContact extends ListActivity {


	Button delete,save,addAddress;
	private EditText nameE;
	private EditText lastE;
	private EditText cellE;
	private EditText workE;
	private EditText emailE;
	private Contact contact;
	private int position;
	@Override


	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_contact);



		delete = (Button) findViewById(R.id.deleteButton);
		save = (Button) findViewById(R.id.saveButton);
		addAddress= (Button) findViewById(R.id.newAddress);


		nameE = (EditText) findViewById(R.id.firstNameE);
		lastE = (EditText) findViewById(R.id.lastNameE);
		cellE = (EditText) findViewById(R.id.phoneNumberE);
		workE = (EditText) findViewById(R.id.workNumberE);
		emailE = (EditText) findViewById(R.id.emailAddressE);

		if(this.getIntent().getStringExtra("from1").equals("ShowContact"))
		{
			contact = (Contact)this.getIntent().getSerializableExtra("editable");
			nameE.setText(contact.getFirstName());
			lastE.setText(contact.getLastName());
			cellE.setText(contact.getCellPhone());
			workE.setText(contact.getWorkPhone());
			emailE.setText(contact.getEmail());
			position =  Integer.parseInt(this.getIntent().getStringExtra("index")); 
		}
		else if(this.getIntent().getStringExtra("from1").equals("delete"))
		{
			contact = (Contact)this.getIntent().getSerializableExtra("contact");
			nameE.setText(contact.getFirstName());
			lastE.setText(contact.getLastName());
			cellE.setText(contact.getCellPhone());
			workE.setText(contact.getWorkPhone());
			emailE.setText(contact.getEmail());
			
		}
		else if(this.getIntent().getStringExtra("from1").equals("EditAddress")){
			contact = (Contact)this.getIntent().getSerializableExtra("contactBack");
			nameE.setText(contact.getFirstName());
			lastE.setText(contact.getLastName());
			cellE.setText(contact.getCellPhone());
			workE.setText(contact.getWorkPhone());
			emailE.setText(contact.getEmail());

		}
		else if(this.getIntent().getStringExtra("from1").equals("AddNewAddressFromEdit"))
		{
			contact = (Contact)this.getIntent().getSerializableExtra("contacto1");
			nameE.setText(contact.getFirstName());
			lastE.setText(contact.getLastName());
			cellE.setText(contact.getCellPhone());
			workE.setText(contact.getWorkPhone());
			emailE.setText(contact.getEmail());
		}
		

		if(contact.getAddress()!=null){
			EditAddressViewAdapter adapter = new EditAddressViewAdapter(this, contact.getAddress());
		setListAdapter(adapter);
		}
		




		delete.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				ContactsManager.removeContact(position);
				Intent intent = new Intent(EditContact.this, MainActivity.class);


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

		save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {


				Intent intent = new Intent(EditContact.this, MainActivity.class);


				Contact contacto = new Contact(nameE.getText().toString(), lastE.getText().toString(), cellE.getText().toString(), workE.getText().toString(), emailE.getText().toString(), contact.getAddress());

				ContactsManager.editContact(position,contacto);

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

		addAddress.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent addAdd = new Intent(EditContact.this, AddNewAddressFromEdit.class);
				addAdd.putExtra("contact1", contact);
				startActivity(addAdd);

			}
		});


	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		Intent ourIntent = new Intent(EditContact.this, EditAddress.class);

		ourIntent.putExtra("ContactEntero", contact);
		String index = Integer.toString(position);

		ourIntent.putExtra("index", index);		
		startActivity(ourIntent);



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
