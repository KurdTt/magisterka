package com.weatherclient.android.main.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weatherclient.R;
import com.weatherclient.android.activity.BaseActivity;
import com.weatherclient.android.chart.WeatherChartFactory;
import com.weatherclient.android.main.presenter.MainActivityPresenter;
import com.weatherclient.android.model.WeatherParameter;
import com.weatherclient.android.preferences.view.SettingsActivity;
import com.weatherclient.di.component.DaggerMainActivityComponent;
import com.weatherclient.di.module.MainActivityModule;
import com.weatherclient.utils.ArrayUtils;
import com.weatherclient.utils.BindUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @Inject
    MainActivityPresenter presenter;

    @BindView(R.id.testText)
    TextView testText;
    @BindView(R.id.chartPanel)
    LinearLayout chartPanel;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
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
        } else if (id == R.id.nav_exit) {
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setCharts(List<WeatherParameter> wpList) {
        chartPanel.removeAllViews();
        WeatherChartFactory.createChart(chartPanel,
                ArrayUtils.getTemperatureList(wpList), "Temperatura", Color.BLUE);
        WeatherChartFactory.createChart(chartPanel,
                ArrayUtils.getPressureList(wpList), "Ciśnienie", Color.GREEN);
        WeatherChartFactory.createChart(chartPanel,
                ArrayUtils.getPollinationList(wpList), "Zapylenie", Color.RED);
    }

    @Override
    public void setText(String text) {
        BindUtils.bindString(text, testText);
    }

    @Override
    protected void inject() {
        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }
}
