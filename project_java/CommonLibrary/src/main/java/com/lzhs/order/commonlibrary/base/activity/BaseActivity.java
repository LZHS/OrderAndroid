package com.lzhs.order.commonlibrary.base.activity;

import android.os.Bundle;

import com.lzhs.order.commonlibrary.base.mvp.BasePresenter;
import com.lzhs.order.commonlibrary.base.mvp.BaseView;
import com.lzhs.order.commonlibrary.di.commone.ActivityComponent;
import com.lzhs.order.commonlibrary.di.commone.DaggerActivityComponent;
import com.lzhs.order.commonlibrary.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/6 : 3:45 PM<br/>
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject(DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                .appComponent(AppApplication.getAppComponent())
                .build());
        if (mPresenter != null) mPresenter.attachView(this);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            System.gc();
        }
    }

    public abstract void initInject(ActivityComponent activityComponent);
}
