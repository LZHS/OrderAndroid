package com.lzhs.order.commonlibrary.base.activity;

import android.app.Application;

import com.lzhs.order.commonlibrary.di.commone.AppComponent;
import com.lzhs.order.commonlibrary.di.commone.DaggerAppComponent;
import com.lzhs.order.commonlibrary.di.module.HttpModule;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/6 : 5:29 PM<br/>
 */
public class AppApplication extends Application {


    private static AppApplication instance;
    public static AppComponent appComponent;


    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .build();
        }
        return appComponent;
    }
}
