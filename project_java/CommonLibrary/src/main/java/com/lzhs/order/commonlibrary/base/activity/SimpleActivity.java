package com.lzhs.order.commonlibrary.base.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.lzhs.library.bases.BaseSwipeBackActivity;
import com.lzhs.order.commonlibrary.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/3/6 : 3:02 PM<br/>
 */
public abstract class SimpleActivity extends BaseSwipeBackActivity {
    protected String TAG = this.getClass().getSimpleName();


    private Unbinder mUnBinder;

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    public void setTitle(@StringRes int title) {
        setTitle(getString(title));
    }

    public void setTitle(@NonNull String title) {
        mToolbar = findViewById(R.id.mToolbar);
        if (mToolbar != null) {
            mToolbar.setTitleTextColor(Color.WHITE);
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (!TextUtils.isEmpty(title)) {
                if (title.length() > 11) {
                    mToolbar.setTitle(title.substring(0, 10) + "...");
                } else {
                    mToolbar.setTitle(title);
                }
            }
        }
    }

    public Toolbar getToolbar() {
        if (mToolbar == null) mToolbar = findViewById(R.id.mToolbar);
        return mToolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
}
