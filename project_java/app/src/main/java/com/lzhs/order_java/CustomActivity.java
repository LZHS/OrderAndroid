package com.lzhs.order_java;

import android.os.Bundle;
import android.widget.TextView;

import com.lzhs.order.commonlibrary.base.activity.BaseActivity;
import com.lzhs.order.commonlibrary.di.commone.ActivityComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/6 : 5:37 PM<br/>
 */
public class CustomActivity extends BaseActivity<TestPresenter> implements TestControl.View {

    @Inject
    TestPresenter testPresenter;
    @BindView(R.id.mTextShow)
    TextView mTextShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initInject(ActivityComponent activityComponent) {
        ((AppActivityCommponent)activityComponent).inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_custom;
    }

    @OnClick(R.id.mButtonClick)
    public void onViewClicked() {
        testPresenter.loadData("");
    }

    @Override
    public void changerData(String data) {
        mTextShow.setText(mTextShow.getText() + "\n" + data);
    }
}
