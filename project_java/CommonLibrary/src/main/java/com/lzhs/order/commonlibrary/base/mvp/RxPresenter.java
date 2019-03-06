package com.lzhs.order.commonlibrary.base.mvp;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/4 : 4:40 PM<br/>
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected WeakReference<T> mView;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(T view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
            unSubscribe();
        }
    }

    @Override
    public T getView() {
        return mView.get();
    }

    protected void unSubscribe() {
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(subscription);
    }
}
