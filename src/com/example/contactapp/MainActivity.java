package com.example.contactapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import clases.Contact;
import clases.ContactsManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends ListActivity {
	
	MediaPlayer ourSong;
	Button add;
	EditText search;
	private ListViewAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_list);

		//leer del file
		try {

			ContactsManager.read(openFileInput(ContactsManager.direction));

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		//set list view
		adapter = new ListViewAdapter(this, ContactsManager.listContacts);
		setListAdapter(adapter);

		//animation
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		Animation  moveRighttoLeft = new TranslateAnimation(width, 0, 0, 0);
		moveRighttoLeft.setDuration(700);
		AnimationSet animation = new AnimationSet(false);
		animation.addAnimation(moveRighttoLeft);

		//Text setting Name
		search = (EditText) findViewById(R.id.search_bar);
		search.setAnimation(animation);

		search.setGravity(Gravity.CENTER);

		//Focus listener para saber cuando se va afiltrar la lista
		search.setOnFocusChangeListener(new OnFocusChangeListener() {           

			@Override

			public void onFocusChange(View v, boolean hasFocus) {

				search.setHint("");

			}

		});

		search.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				adapter.filterContact(arg0.toString());
				
			}

		});

		add = (Button) findViewById(R.id.add_button);

		add.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent adding = new Intent(MainActivity.this, AddNewContact.class);
				adding.putExtra("from", "MainActivity");
				startActivity(adding);

			}
		});

	}

	//for any item selected in the listView
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		ourSong = MediaPlayer.create(MainActivity.this,R.raw.coin_sound);
		ourSong.start();
		Intent ourIntent = new Intent(MainActivity.this, ShowContact.class);
		int p = ContactsManager.containAt((Contact) l.getAdapter().getItem(position));
		ourIntent.putExtra("contact", ContactsManager.listContacts.get(p));
		String index = Integer.toString(p);
		ourIntent.putExtra("index", index);
		startActivity(ourIntent);
		
		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}
		
	}

	@SuppressLint("NewApi")
	public boolean onCreateOptionsMenu(Menu menu) {
		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.rgb(100, 100, 45)));
		action.setTitle("Contact App");

		return true;
	}

}
