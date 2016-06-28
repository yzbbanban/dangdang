package com.wangban.dangdang.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.R.layout;
import com.wangban.dangdang.R.menu;
import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.view.AddressDialog;
import com.wangban.dangdang.view.IAddressView;
import com.wangban.dangdang.view.AddressDialog.Callback;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class AddressActivity extends Activity implements IAddressView,OnClickListener {
	@ViewInject(R.id.btn_address_add)
	private Button btnAddressAdd;
	@ViewInject(R.id.lv_address_manager)
	private ListView lvAddressManager;
	
	private AddressDialog dialog;
	private List<Address> addresses;
	private Address address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		x.view().inject(this);
		
		setListeners();
	}

	private void setListeners() {
		btnAddressAdd.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_address_add:
			dialog=new AddressDialog(this,new AddressDialog.Callback() {
				
				@Override
				public void onSubmit(Address address) {
					AddressActivity.this.address=address;
					
				}
			});
			dialog.show();
			break;

		default:
			break;
		}

	}

	@Override
	public void setData(List<Address> addresses) {
		this.addresses=addresses;
		
	}

	@Override
	public void showData() {
		
		lvAddressManager.set
		
	}

}
