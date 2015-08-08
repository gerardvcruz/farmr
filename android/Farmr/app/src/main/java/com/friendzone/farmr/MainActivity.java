package com.friendzone.farmr;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.friendzone.farmr.suppliers.OrdersFragment;
import com.friendzone.farmr.suppliers.StocksFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO check user type
        // Supplier = 1
        // Buyer = 2

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Orders", OrdersFragment.class)
                .add("Stocks", StocksFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

}