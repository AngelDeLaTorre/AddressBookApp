package com.example.contactapp;

import java.io.FileNotFoundException;

import clases.ArrayList;
import clases.Contact;

import clases.ContactsManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


@SuppressLint("NewApi")
public class AddNewContact extends Activity {

	private EditText name;
	private EditText last;
	private EditText cell;
	private EditText work;
	private EditText email;
	private Contact contacto2;

	Button addAddress,save;
	private Contact contacto;
	private Contact contacto1;
	private clases.Address address;
	private ArrayList<clases.Address> addressList;
	//private Address address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_contact);
		
		name = (EditText)findViewById(R.id.firstName);
		last = (EditText)findViewById(R.id.lastName);
		cell = (EditText)findViewById(R.id.phoneNumber);
		work = (EditText)findViewById(R.id.workNumber);
		email = (EditText)findViewById(R.id.emailAddress);
		
		addAddress= (Button) findViewById(R.id.newAddress);
		save= (Button) findViewById(R.id.saveContact);
		
		//contacto2= new Contact("","","","","",null);
		if(this.getIntent().getStringExtra("from").equals("AddNewAddress"))
		{
		
			 contacto2 = (Contact) this.getIntent().getSerializableExtra("contacto1");
			
			name.setText(contacto2.getFirstName());
			last.setText(contacto2.getLastName());
			cell.setText(contacto2.getCellPhone());
			work.setText(contacto2.getWorkPhone());
			email.setText(contacto2.getEmail());
			
			
			
		}
		
		
		contacto1 = (Contact)this.getIntent().getSerializableExtra("contact1");
		

		//clicking add address
		addAddress.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent addAdd = new Intent(AddNewContact.this, AddNewAddress.class);
				if(!(contacto2==null))
				{
				contacto =  new Contact(name.getText().toString(), last.getText().toString(),
						cell.getText().toString(), work.getText().toString(), 
						email.getText().toString(),contacto2.getAddress() );
				}
				else
				{
					contacto =  new Contact(name.getText().toString(), last.getText().toString(),
							cell.getText().toString(), work.getText().toString(), 
							email.getText().toString(),null);
				}

				addAdd.putExtra("contact", contacto);
				
				startActivity(addAdd);

			}
		});
		
		//clicking save
		save.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				
				Intent intent = new Intent(AddNewContact.this, MainActivity.class);
				
			
				if(contacto2!=null)
				{
				contacto1 = new Contact(name.getText().toString(), last.getText().toString(),
						cell.getText().toString(), work.getText().toString(), 
						email.getText().toString(), contacto2.getAddress());
				}
				else{
					contacto1 = new Contact(name.getText().toString(), last.getText().toString(),
							cell.getText().toString(), work.getText().toString(), 
							email.getText().toString(), null);
				}
				
				
				
				//ContactsManager.addAddresss(contacto, address);
				ContactsManager.addContact(contacto1);
				
				//ContactsManager.writeManager(ContactsManager.listContacts);
				//intent.putExtra("contact", name.getText().toString());
				//intent.putExtra("contact", ContactsManager.)
				
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				
				//ContactsManager.writeManager(ContactsManager.listContacts);
				
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
