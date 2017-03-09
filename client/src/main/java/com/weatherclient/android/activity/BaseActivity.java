package com.weatherclient.android.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */

public abstract class BaseActivity extends AppCompatActivity implements IContext {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        inject();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void openActivity(Class clazz){
        startActivity(new Intent(this, clazz));
    }

    protected abstract void inject();
}
