package com.lzhs.order_kotlin.activitys

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.lzhs.commonlibrary.bases.activity.BaseActivity
import com.lzhs.commonlibrary.configs.ARoutConfig.MAINACTIVITY
import com.lzhs.order_kotlin.R

@Route(path = MAINACTIVITY)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(TAG)


    }

}
