package com.example.contactapp;

import clases.Address;
import clases.ArrayList;
import android.R;
import android.app.Activity;
import android.content.Context;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class EditAddressViewAdapter extends BaseAdapter {

	private ArrayList<Address> addresses;
	private LayoutInflater inflater;

	public EditAddressViewAdapter(Activity context, ArrayList<Address> addressList) {
		super();
		this.addresses =  new ArrayList<Address>(10);
		for(Address ad: addressList)
		{
			this.addresses.add(ad);
		}
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {

		return addresses.size();
	}

	@Override
	public Object getItem(int index) {

		return addresses.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Address address1= addresses.get(position);
		View view = convertView;
		if(convertView==null)
			view= inflater.inflate(R.layout.simple_list_item_1, null);
		TextView textView = (TextView) view.findViewById(R.id.text1);
		textView.setText(address1.getName());
				
		
		return view;
	}

}
