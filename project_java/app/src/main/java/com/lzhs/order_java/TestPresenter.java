package com.lzhs.order_java;

import com.lzhs.library.utils.LogUtil;
import com.lzhs.order.commonlibrary.RxUtil;
import com.lzhs.order.commonlibrary.base.mvp.RxPresenter;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/4 : 5:00 PM<br/>
 */
public class TestPresenter extends RxPresenter<TestControl.View> implements TestControl.Presenter {

    @Inject
    public TestPresenter(){}

    @Override
    public void loadData(String name) {
        addSubscribe(Flowable.interval(0, 1, TimeUnit.SECONDS)
                .compose(RxUtil.schedulersFlowIoToMain())
                .subscribe(it -> {
                    if (it == 0)
                        getView().changerData("开始模拟请求数据");
                    else if (it <= 10)
                        getView().changerData("请求数据的进度" + it);
                    else if (it > 10) {
                        getView().changerData("数据请求完毕");
                        unSubscribe();
                    }
                    LogUtil.d("======= " + it + " =====");
                })
        );
    }
}
