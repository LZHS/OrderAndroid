package com.lzhs.order_java;

import com.lzhs.order.commonlibrary.di.commone.ActivityComponent;

import dagger.Component;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/6 : 5:40 PM<br/>
 */
@Component(dependencies = ActivityComponent.class)
public interface AppActivityCommponent  extends ActivityComponent{

    void inject(CustomActivity customActivity);
}
