package com.wangban.dangdang.view;

import com.wangban.dangdang.R;
import com.wangban.dangdang.entity.Address;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by wangban 2016-6-28 ÏÂÎç4:04:44
 */
public class AddressDialog extends Dialog {
	private Context context;
	private EditText etAddressReceiveName;
	private EditText etAddressFullAddress;
	private EditText etAddressPostalCode;
	private EditText etAddressMobile;
	private EditText etAddressPhone;
	private Button btnAddressAdd;
	private Callback callback;
	public AddressDialog(Context context,Callback callback) {
		super(context);
		this.context = context;
		this.callback=callback;
	}

	public AddressDialog(Context context, int themeResId) {
		super(context, themeResId);
	}

	protected AddressDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	@Override
	public void show() {
		super.show();
		Window window = this.getWindow();
		window.setBackgroundDrawable(new ColorDrawable(0x00000000));
		View view = View.inflate(context, R.layout.address_mine, null);
		window.setContentView(view);
		setView(view);
		setListeners();
	}

	private void setView(View view) {
		etAddressReceiveName = (EditText) view
				.findViewById(R.id.et_address_receive_name);
		etAddressFullAddress = (EditText) view
				.findViewById(R.id.et_address_full_address);
		etAddressPostalCode = (EditText) view
				.findViewById(R.id.et_address_postal_code);
		etAddressMobile = (EditText) view.findViewById(R.id.et_address_mobile);
		etAddressPhone = (EditText) view.findViewById(R.id.et_address_phone);
		btnAddressAdd = (Button) view.findViewById(R.id.btn_address_add);
	}

	private void setListeners() {
		btnAddressAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Address address = new Address();
				address.setFull_address(etAddressFullAddress.getText()
						.toString());
				address.setPhone(etAddressPhone.getText().toString());
				address.setPostalCode(etAddressPostalCode.getText().toString());
				address.setReceiveName(etAddressReceiveName.getText()
						.toString());
				address.setMobile(etAddressMobile.getText().toString());
				//Log.i("supergirl","AddressDialog: "+address.toString());
				callback.onSubmit(address);
				
			}
		});
	}
	public interface Callback{
		void onSubmit(Address address);
	}
}
