package com.wangban.dangdang.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.R.layout;
import com.wangban.dangdang.R.menu;
import com.wangban.dangdang.adapter.AddressAdapter;
import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.presenter.IAddressPresenter;
import com.wangban.dangdang.presenter.impl.AddressPresenterImpl;
import com.wangban.dangdang.view.AddressDialog;
import com.wangban.dangdang.view.IAddressView;
import com.wangban.dangdang.view.AddressDialog.Callback;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AddressActivity extends Activity implements IAddressView,
		OnClickListener {
	@ViewInject(R.id.btn_address_add)
	private Button btnAddressAdd;
	@ViewInject(R.id.lv_address_manager)
	private ListView lvAddressManager;

	private AddressDialog dialog;
	private List<Address> addresses;
	private Address address;

	private AddressAdapter adapter;

	private IAddressPresenter presenter;

	public AddressActivity() {
		presenter = new AddressPresenterImpl(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		x.view().inject(this);
		presenter.listAddress();
		setListeners();
	}

	private void setListeners() {
		btnAddressAdd.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_address_add:
			dialog = new AddressDialog(this, new AddressDialog.Callback() {

				@Override
				public void onSubmit(Address address) {
					AddressActivity.this.address = address;
					// Log.i("supergirl","addressActivity: "+
					// address.toString());
					presenter.loadAddress(address);
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
		if (addresses != null) {
			this.addresses = addresses;
		} else {
			Toast.makeText(this, "还没有保存地址", Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public void showData() {
		adapter = new AddressAdapter(this, addresses);
		lvAddressManager.setAdapter(adapter);
		adapter.setPresenter(presenter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void addAddressSuccess() {
		Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
		dialog.dismiss();
		presenter.listAddress();
	}

	@Override
	public void addAddressFail(String message) {
		Toast.makeText(this, "添加失败" + message, Toast.LENGTH_SHORT).show();
	}

}
