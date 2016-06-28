package com.wangban.dangdang.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wangban.dangdang.R;
import com.wangban.dangdang.fragment.CartFragment;
import com.wangban.dangdang.fragment.MineFragment;
import com.wangban.dangdang.fragment.StoreFragment;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {
	@ViewInject(value = R.id.vp_activity)
	private ViewPager vp;
	@ViewInject(value = R.id.radioGroup_dang)
	private RadioGroup rgGroup;
	@ViewInject(value = R.id.rbtn_store)
	private RadioButton rbtnStore;
	@ViewInject(value = R.id.rbtn_cart)
	private RadioButton rbtnCart;
	@ViewInject(value = R.id.rbtn_mine)
	private RadioButton rbtnMine;
	private List<Fragment> fragments;
	private FragmentPagerAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		x.view().inject(this);
		setData();
		setListeners();
	}

	private void setListeners() {
		vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rbtnStore.setChecked(true);
					break;
				case 1:
					rbtnCart.setChecked(true);
					break;
				case 2:
					rbtnMine.setChecked(true);
					break;

				default:
					break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rbtn_store:
					vp.setCurrentItem(0);
					break;
				case R.id.rbtn_cart:
					vp.setCurrentItem(1);
					break;
				case R.id.rbtn_mine:
					vp.setCurrentItem(2);
					break;

				default:
					break;
				}

			}
		});

	}

	private void setData() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new StoreFragment());
		fragments.add(new CartFragment());
		fragments.add(new MineFragment());

		adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return fragments.size();
			}
			@Override
			public Fragment getItem(int arg0) {
				return fragments.get(arg0);
			}
		};
		vp.setOffscreenPageLimit(3);
		vp.setAdapter(adapter);

	}

}
