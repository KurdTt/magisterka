package com.weatherclient.android.chart.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.weatherclient.utils.widgets.IClearableAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemysław Książek
 * on 2017-03-13.
 */

public class ChartAdapter extends FragmentPagerAdapter implements IClearableAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final FragmentManager fragmentManager;

    public ChartAdapter(FragmentManager manager) {
        super(manager);
        this.fragmentManager = manager;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < mFragmentList.size(); i++)
            fragmentManager.beginTransaction().remove(mFragmentList.get(i)).commit();
        mFragmentList.clear();
    }
}
