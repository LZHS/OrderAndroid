package com.lzhs.order.commonlibrary.di.commone;

import com.lzhs.order.commonlibrary.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/6 : 4:36 PM<br/>
 */
@Singleton
@Component(modules = {HttpModule.class})
public interface AppComponent {
}
