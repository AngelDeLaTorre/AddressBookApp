package com.example.contactapp;

import java.io.FileNotFoundException;

import clases.Address;
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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EditAddress extends Activity{

	Button save,delete;
	private EditText name;
	private EditText street;
	private EditText number;
	private EditText city;
	private EditText state;
	private EditText zip;
	private Contact contact;
private int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_address);

		save= (Button) findViewById(R.id.savebuttonEditA);
		delete= (Button) findViewById(R.id.deletebuttonEditA);
		
		
		name = (EditText) findViewById(R.id.addressNameEdit);
		street = (EditText) findViewById(R.id.streetEdit);
		number = (EditText) findViewById(R.id.addressNumberEdit);
		city = (EditText) findViewById(R.id.cityAddressEdit);
		state = (EditText) findViewById(R.id.stateAddressEdit);
		zip = (EditText) findViewById(R.id.zipCodeAddressEdit);
		
		contact= (Contact)this.getIntent().getSerializableExtra("ContactEntero");
		 index = Integer.parseInt(this.getIntent().getStringExtra("index"));
		
		
		name.setText(contact.getAddress().get(index).getName());
		street.setText(contact.getAddress().get(index).getStreet());
		number.setText(contact.getAddress().get(index).getNumber());
		city.setText(contact.getAddress().get(index).getCity());
		state.setText(contact.getAddress().get(index).getState());
		zip.setText(contact.getAddress().get(index).getZipCode());


		save.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Address ad = new Address(name.getText().toString(), street.getText().toString(),
						number.getText().toString(), city.getText().toString(),
						state.getText().toString(), zip.getText().toString());
				
				Intent intent = new Intent(EditAddress.this, EditContact.class);
				ContactsManager.editAddresss(index, contact, ad);
				
				intent.putExtra("from1", "EditAddress");
				
				intent.putExtra("contactBack", contact);
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

		delete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(EditAddress.this, EditContact.class);
				intent.putExtra("from1", "delete");
				ContactsManager.removeAddress(index, contact);
				intent.putExtra("contact", contact);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
