package com.weatherclient.android.main.view;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.weatherclient.R;
import com.weatherclient.android.activity.BaseActivity;
import com.weatherclient.android.chart.view.ChartAdapter;
import com.weatherclient.android.chart.view.ChartFragment;
import com.weatherclient.android.chart.view.EmptyViewAdapter;
import com.weatherclient.android.main.presenter.MainActivityPresenter;
import com.weatherclient.android.model.WeatherParameter;
import com.weatherclient.android.preferences.view.SettingsActivity;
import com.weatherclient.di.component.DaggerMainActivityComponent;
import com.weatherclient.di.module.MainActivityModule;
import com.weatherclient.utils.widgets.IClearableAdapter;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @Inject
    MainActivityPresenter presenter;

    @BindView(R.id.content)
    ViewPager content;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private ChartAdapter chartAdapter = new ChartAdapter(getSupportFragmentManager());
    private int actionBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        noDataFound();
        setListeners();
        actionBarHeight = getActionBarHeight();
    }

    private void setListeners() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                content.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.setupWithViewPager(content);
    }

    private void setTabs(int visibility) {
        if(tabLayout.getVisibility() != visibility) {
            tabLayout.setVisibility(visibility);
            if (visibility == View.VISIBLE) {
                content.setPadding(content.getPaddingLeft(), content.getPaddingTop() + actionBarHeight, content.getPaddingRight(), content.getPaddingBottom());
            } else {
                content.setPadding(content.getPaddingLeft(), content.getPaddingTop() - actionBarHeight, content.getPaddingRight(), content.getPaddingBottom());
            }
        }
    }

    private int getActionBarHeight() {
        int actionBarHeight = 0;
        if(getSupportActionBar() != null) {
            actionBarHeight = getSupportActionBar().getHeight();
            if (actionBarHeight != 0)
                return actionBarHeight;
        }
        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        } else if (getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_refresh:
                presenter.getWeatherParameters();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            openActivity(SettingsActivity.class);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDataLoaded(List<List<WeatherParameter>> result) {
        clearCharts();

        setTabs(View.VISIBLE);

        for (List<WeatherParameter> wpList : result) {
            ChartFragment fragment = ChartFragment.newInstance(wpList);
            chartAdapter.addFragment(fragment);
            tabLayout.addTab(tabLayout.newTab().setText(getTabTitle(wpList)));
        }
        content.setAdapter(chartAdapter);
    }

    private String getTabTitle(List<WeatherParameter> wpList) {
        if(wpList.size() > 0) {
            return wpList.get(0).getDeviceName();
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    public void clearCharts() {
        content.removeAllViews();

        PagerAdapter adapter = content.getAdapter();

        if (adapter != null) {
            if (adapter instanceof IClearableAdapter) {
                ((IClearableAdapter) adapter).clear();
            }
        }

        tabLayout.removeAllTabs();
        content.setAdapter(null);
    }

    @Override
    public void noDataFound() {
        clearCharts();
        setTabs(View.GONE);
        content.setAdapter(new EmptyViewAdapter(getSupportFragmentManager()));
    }


    @Override
    protected void inject() {
        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }
}
