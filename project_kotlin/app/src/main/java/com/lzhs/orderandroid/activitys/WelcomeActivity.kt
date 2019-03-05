package com.lzhs.orderandroid.activitys

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.lzhs.commonlibrary.bases.activity.BaseActivity
import com.lzhs.commonlibrary.configs.ARoutConfig.WELCOMEACTIVITY
import com.lzhs.library.utils.StatusBarUtil
import com.lzhs.orderandroid.R
import com.lzhs.orderandroid.fragments.Welcome1Fragment

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/2/25 : 2:54 PM<br/>
 */
@Route(path = WELCOMEACTIVITY)
class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        StatusBarUtil.setTransparentForImageViewInFragment(this,null)
        setSwipeBackEnable(false)
        initData()


    }

    private fun initData() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_content, Welcome1Fragment.newInstance())
                .commit()

    }

}