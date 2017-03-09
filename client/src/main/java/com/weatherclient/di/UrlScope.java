package com.weatherclient.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Przemysław Książek
 * on 2017-02-22.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlScope {
}