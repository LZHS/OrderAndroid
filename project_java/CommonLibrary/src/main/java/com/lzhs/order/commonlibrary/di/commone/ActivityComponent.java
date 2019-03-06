package com.lzhs.order.commonlibrary.di.commone;


import android.app.Activity;

import com.lzhs.order.commonlibrary.base.activity.BaseActivity;
import com.lzhs.order.commonlibrary.di.module.ActivityModule;
import com.lzhs.order.commonlibrary.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();


}