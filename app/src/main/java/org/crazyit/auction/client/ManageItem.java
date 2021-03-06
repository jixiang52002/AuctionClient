package org.crazyit.auction.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import org.crazyit.BaseFragmentActivity;
import org.crazyit.constant.CONSTANT;

public class ManageItem extends BaseFragmentActivity
		implements Callbacks
{
	@Override
	public Fragment getFragment()
	{
		return new ManageItemFragment();
	}
	@Override
	public void onItemSelected(Integer id, Bundle bundle)
	{
		// 当用户单击添加按钮时，将会启动AddItem Activity
		Intent i = new Intent(this , AddItem.class);
		i.putExtra(CONSTANT.COMMON_TITLE,getString(R.string.add_goods));
		startActivity(i);
		finish();
	}
}