package com.wangban.dangdang.adapter;

import java.util.List;

import org.w3c.dom.Text;

import com.wangban.dangdang.R;
import com.wangban.dangdang.entity.Address;
import com.wangban.dangdang.presenter.IAddressPresenter;
import com.wangban.dangdang.presenter.impl.AddressPresenterImpl;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by wangban 2016-6-28 ����5:00:21
 */
public class AddressAdapter extends BaseAdapter {
	private Context context;
	private List<Address> addresses;
	private LayoutInflater layoutInflater;
	private ViewHolder holder;
	private IAddressPresenter presenter;

	public AddressAdapter(Context context, List<Address> addresses) {
		super();
		this.context = context;
		this.addresses = addresses;
		this.layoutInflater = LayoutInflater.from(context);

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
		Address address = getItem(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.item_address, null);
			holder.tvAddressUserName = (TextView) convertView
					.findViewById(R.id.tv_address_user_name);
			holder.tvAddressUserPhone = (TextView) convertView
					.findViewById(R.id.tv_address_user_phone);
			holder.tvAddressUserAddress = (TextView) convertView
					.findViewById(R.id.tv_address_user_address);

			holder.rbtnAddressDefault = (RadioButton) convertView
					.findViewById(R.id.rbtn_address_default);
			holder.rbtnAddressEdit = (RadioButton) convertView
					.findViewById(R.id.rbtn_address_edit);

			holder.rbtnAddressDelete = (RadioButton) convertView
					.findViewById(R.id.rbtn_address_delete);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		holder.tvAddressUserName.setText(address.getReceiveName());
		holder.tvAddressUserPhone.setText(address.getPhone());
		holder.tvAddressUserAddress.setText(address.getFull_address());
		// ����Ĭ�ϵ�״̬
		holder.rbtnAddressDefault.setChecked(address.getIs_default() == 1);
		holder.rbtnAddressDefault
				.setOnCheckedChangeListener(new InnerOnCheckChangeListener(
						position));
		address.getId();
		return convertView;
	}

	class ViewHolder {
		TextView tvAddressUserName;
		TextView tvAddressUserPhone;
		TextView tvAddressUserAddress;
		RadioButton rbtnAddressDefault;
		RadioButton rbtnAddressEdit;
		RadioButton rbtnAddressDelete;

	}

	public void setPresenter(IAddressPresenter presenter) {
		this.presenter = presenter;

	}

	private class InnerOnCheckChangeListener implements OnCheckedChangeListener {
		private int position;

		public InnerOnCheckChangeListener(int position) {
			this.position = position;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			Address address = getItem(position);
			int id = address.getId();
			Log.i("supergirl", "id: " + id);
			presenter.setAddDefault(id);
		}
	}

}
