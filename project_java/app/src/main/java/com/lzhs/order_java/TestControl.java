package com.lzhs.order_java;


import com.lzhs.order.commonlibrary.base.mvp.BasePresenter;
import com.lzhs.order.commonlibrary.base.mvp.BaseView;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/4 : 4:30 PM<br/>
 */
public interface TestControl {
    interface View extends BaseView {
        void changerData(String data);
    }

    interface Presenter extends BasePresenter<View> {
        void loadData(String name);
    }


}
