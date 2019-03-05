package com.lzhs.commonlibrary.configs

import com.alibaba.android.arouter.launcher.ARouter

/**
 * Description: 常量 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/2/18 : 3:46 PM<br/>
 */

object ARoutConfig {
        const val ORDER = "/order"
        const val MAINACTIVITY = "/order/MainActivity"
        const val WELCOMEACTIVITY = "/order/WelcomeActivity"

    fun startMainActivity() {
        ARouter.getInstance().build(ARoutConfig.MAINACTIVITY).navigation()
    }

    fun startWelcomeActivity() {
        ARouter.getInstance().build(ARoutConfig.WELCOMEACTIVITY).navigation()
    }
}


