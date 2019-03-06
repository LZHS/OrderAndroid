package com.lzhs.order.commonlibrary.di.module;

import android.app.Activity;


import com.lzhs.order.commonlibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/4 : 4:15 PM<br/>
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}
