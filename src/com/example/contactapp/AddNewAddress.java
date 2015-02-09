package com.example.contactapp;

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
import clases.Address;
import clases.Contact;
import clases.ContactsManager;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AddNewAddress extends Activity {

	
	private EditText nameA;
	private EditText streetA;
	private EditText numberA;
	private EditText cityA;
	private EditText stateA;
	private EditText zipA;
	
	private Address address;
	private Contact contactx;
	Button save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_address);
		
		nameA = (EditText) findViewById(R.id.addressName);
		streetA = (EditText) findViewById(R.id.street);
		numberA = (EditText) findViewById(R.id.addressNumber);
		cityA = (EditText) findViewById(R.id.cityAddress);
		stateA = (EditText) findViewById(R.id.stateAddress);
		zipA = (EditText) findViewById(R.id.zipCodeAddress);
		
		contactx = (Contact)this.getIntent().getSerializableExtra("contact");
		
		
		
		
		
		
		save= (Button) findViewById(R.id.saveAddressbutton);
		save.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent intent = new Intent(AddNewAddress.this, AddNewContact.class);
				
				address = new Address(nameA.getText().toString(), streetA.getText().toString(),
						numberA.getText().toString(), cityA.getText().toString(),
						stateA.getText().toString(), zipA.getText().toString());
				ContactsManager.addAddresss(contactx, address);
				
				intent.putExtra("from", "AddNewAddress");
				intent.putExtra("contacto1", contactx);
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
