package br.mello.arthur.correcuritiba;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


public class DetailPagerAdapter extends FragmentStatePagerAdapter {
	static final int COUNT = MainActivity.events.length;
	private DetailFragment[] fragments = new DetailFragment[COUNT]; 
	
	public DetailPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		return COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = DetailFragment.newInstance(position);
		return fragment;
	}

	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}
	
	public DetailFragment getFragment(int position) {
		return fragments[position];
	}
}