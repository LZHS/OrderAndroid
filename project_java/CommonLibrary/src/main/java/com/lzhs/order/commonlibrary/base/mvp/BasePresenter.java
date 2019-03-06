package com.lzhs.order.commonlibrary.base.mvp;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/4 : 4:02 PM<br/>
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();

    T getView();


}
