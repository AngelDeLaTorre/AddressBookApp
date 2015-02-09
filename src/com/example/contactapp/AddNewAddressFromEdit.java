package com.example.contactapp;



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
public class AddNewAddressFromEdit extends Activity {

	
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
		setContentView(R.layout.add_new_address_edit);
		
		nameA = (EditText) findViewById(R.id.addressNameE);
		streetA = (EditText) findViewById(R.id.streetE);
		numberA = (EditText) findViewById(R.id.addressNumberE);
		cityA = (EditText) findViewById(R.id.cityAddressE);
		stateA = (EditText) findViewById(R.id.stateAddressE);
		zipA = (EditText) findViewById(R.id.zipCodeAddressE);
		
		contactx = (Contact)this.getIntent().getSerializableExtra("contact1");
		
		
		
		
		
		
		save= (Button) findViewById(R.id.saveAddressbuttonE);
		save.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				Intent intent = new Intent(AddNewAddressFromEdit.this, EditContact.class);
				
				address = new Address(nameA.getText().toString(), streetA.getText().toString(),
						numberA.getText().toString(), cityA.getText().toString(),
						stateA.getText().toString(), zipA.getText().toString());
				ContactsManager.addAddresss(contactx, address);
				
				intent.putExtra("from1", "AddNewAddressFromEdit");
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
