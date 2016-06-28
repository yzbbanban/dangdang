package com.wangban.dangdang.adapter;

import java.util.List;

import org.w3c.dom.Text;

import com.wangban.dangdang.entity.Address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 *Created by wangban 2016-6-28 ÏÂÎç5:00:21
 */
public class AddressAdapter extends BaseAdapter {
	private Context context;
	private List<Address> addresses;
	private LayoutInflater layoutInflater;
	
	public AddressAdapter(Context context, List<Address> addresses) {
		super();
		this.context = context;
		this.addresses = addresses;
		this.layoutInflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return addresses.size();
	}

	@Override
	public Address getItem(int position) {
		return addresses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	class ViewHolder{
		TextView 
	}
}
